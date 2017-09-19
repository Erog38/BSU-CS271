import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginGUI extends JPanel{

	private JPanel mainPanel;
	private JButton loginButton;
	private ButtonListener buttonListener;
	private JTextField userNameField;
	private JTextField passwordField;
	private AccountManager profile;
	
	public LoginGUI() {
		initInstanceVars();
		initCenterPanel();
	}
	
	public LoginGUI(AccountManager acct){
		this();
		setProfile(acct);
	}

	private void initInstanceVars(){
		profile = new AccountManager();
		setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);
		buttonListener = new ButtonListener();//create button listener
	}

	private void initCenterPanel() {
		
		JPanel centerPanel = new JPanel();
		
		centerPanel.add(Box.createVerticalGlue());
		centerPanel.add(initNamePanel());
		centerPanel.add(initPasswordPanel());
		centerPanel.add(initLoginButton());
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
	
	private JButton initLoginButton(){
		
		loginButton = new JButton("Login");
		loginButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginButton.addActionListener(buttonListener);
		return loginButton;
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
				if(!profile.verifyUserName(userNameField.getText()) || userNameField.getText().equals("")){
					showPopup = true;
					errorName = "Please enter a valid user name";
				}
				if(!profile.verifyPassword(userNameField.getText(), passwordField.getText()) || 
						passwordField.getText().equals("")){
					showPopup = true;
					errorPassword = "Please enter a valid password";
				}
				if(showPopup){
					invalidLogin(errorName, errorPassword);
				}
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
			int result = JOptionPane.showConfirmDialog(null, panel, "Invalid login",
					JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
		}		
	}
}
