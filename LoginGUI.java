import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Chandra Adhikari, Jorah Hinman, Phil Gore
 *
 */
@SuppressWarnings("serial")
public class LoginGUI extends JPanel{

	private JPanel mainPanel;
	private JButton loginButton;
	private JButton returnButton;
	private ButtonListener buttonListener;
	private JTextField userNameField;
	private JTextField passwordField;
	protected String currentlyLoggedIn;
	private AccountManager profile;
	
	private int numFail;
	
	public LoginGUI() {
		initInstanceVars();
		initCenterPanel();
	}
	
	public LoginGUI(AccountManager acct){
		this();
		setProfile(acct);
	}

	private void initInstanceVars(){
		setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);
		buttonListener = new ButtonListener();//create button listener
		numFail = 0;
	}

	private void initCenterPanel() {
		
		JPanel centerPanel = new JPanel();
		
		centerPanel.add(Box.createVerticalGlue());
		centerPanel.add(initNamePanel());
		centerPanel.add(initPasswordPanel());
		centerPanel.add(initButtonPanel());
		centerPanel.add(Box.createVerticalGlue());
		
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(centerPanel);		
		mainPanel.add(Box.createVerticalGlue());
	}
	
	private JPanel initNamePanel(){
		
		JPanel userNamePanel = new JPanel();
		JLabel userNameLabel = new JLabel("username: ");
		userNameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		userNamePanel.add(userNameLabel);
		userNameField = new JTextField(20);
		userNamePanel.add(userNameField);
		return userNamePanel;
	}
	
	private JPanel initPasswordPanel(){
		
		JPanel passwordPanel = new JPanel();
		JLabel passwordLabel = new JLabel("password: ");
		passwordLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		passwordPanel.add(passwordLabel);
		passwordField = new JTextField(20);
		passwordPanel.add(passwordField);
		return passwordPanel;
	}
	
	private JPanel initButtonPanel(){
		
		JPanel buttonPanel = new JPanel();
		
		loginButton = new JButton("Login");
		loginButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		returnButton = new JButton("Return");
		returnButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginButton.addActionListener(buttonListener);
		returnButton.addActionListener(buttonListener);
		buttonPanel.add(loginButton);
		buttonPanel.add(returnButton);
		
		return buttonPanel;
	}
	
	private void setProfile(AccountManager acct) {
		profile = acct;		
	}

	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean showPopup = false;
			String errorName = "";
			String errorPassword = "";
			if(e.getSource().equals(loginButton)){
				if(profile.verifyUserName(userNameField.getText()) && profile.get(userNameField.getText()).getIsLocked()){
					JOptionPane.showMessageDialog(null, "You have 3 failed login attempts. \nYour account access will be temporarially"
							+ " suspended for 2 minutes.", null, JOptionPane.PLAIN_MESSAGE);
					return;
				}
				if(!profile.verifyUserName(userNameField.getText())){
					showPopup = true;
					errorName = "Please enter a valid user name";
				}
				if(!profile.verifyPassword(userNameField.getText(), passwordField.getText())){
					showPopup = true;
					errorPassword = "Please enter a valid password";
					if(profile.verifyUserName(userNameField.getText())){
						numFail++;
					}
				} else {
					currentlyLoggedIn = userNameField.getText();
				}
				if(showPopup){
					invalidLogin(errorName, errorPassword);
				} else {
					JOptionPane.showMessageDialog(null, "Welcome "+ currentlyLoggedIn + ", you are now logged in!", null, JOptionPane.PLAIN_MESSAGE);
					removeAll();
					add(new ProfileGUI(profile, currentlyLoggedIn));
					repaint();
					revalidate();
					setPreferredSize(new Dimension(800, 800));
				    setVisible(true);
				}
				if(numFail == 3){
					JOptionPane.showMessageDialog(null, "You have 3 failed login attempts. \nYour account has been temporarially"
							+ " suspended for 2 minutes.", null, JOptionPane.PLAIN_MESSAGE);
					numFail = 0;
					loginButton.setEnabled(false);
					profile.get(userNameField.getText()).setIsLocked(true);
					Timer timer = new Timer();
					timer.schedule(new TimerTask(){
						String user = userNameField.getText();
						public void run(){
							loginButton.setEnabled(true);
							profile.get(user).setIsLocked(false);
						}
					}, 120000);
				}
			}
			
			if (e.getSource().equals(returnButton)) {
				removeAll();
				add(new LandingPageGUI(profile));
				repaint();
				revalidate();
				setPreferredSize(new Dimension(800, 800));
			    setVisible(true);
			}
		}

		private void invalidLogin(String errorName, String errorPassword) {
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			JLabel name = new JLabel(errorName);
			name.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			name.setAlignmentX(Component.CENTER_ALIGNMENT);
			JLabel password = new JLabel(errorPassword);
			password.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			password.setAlignmentX(Component.CENTER_ALIGNMENT);
			panel.add(Box.createVerticalGlue());
			panel.add(name);
			panel.add(password);
			panel.add(Box.createVerticalGlue());
			JOptionPane.showConfirmDialog(null, panel, "Invalid login",
					JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
		}		
	}
}
