import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class displays a create profile GUI to the user.
 * 
 * @author Jorah Hinman, Chandra Adhikari
 */
public class CreateProfileGUI extends LoginGUI {

	private JPanel mainPanel;
	private JButton returnButton;
	private JButton signupButton;
	private ButtonListener buttonListener;
	private JTextField userNameField;
	private JTextField passwordField;
	private AccountManager profiles;

	public CreateProfileGUI() {
		initInstanceVars();
	}

	public CreateProfileGUI(AccountManager acct) {
		this();
		addNewProfile(acct);
	}

	private void initInstanceVars() {
		profiles = new AccountManager();
		setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		createSignUpPage();
		add(mainPanel);
		buttonListener = new ButtonListener();// create button listener
	}

	private void createSignUpPage() {
		
		JPanel centerPanel = new JPanel();
		
		centerPanel.add(Box.createVerticalGlue());	
		mainPanel.add(Box.createVerticalGlue());
		centerPanel.add(initUserNamePanel());
		centerPanel.add(initFirstNamePanel());
		centerPanel.add(initLastNamePanel());
		centerPanel.add(initEmailPanel());
		centerPanel.add(initPasswordPanel());
		centerPanel.add(initButtonPanel());
		mainPanel.add(centerPanel);		
		mainPanel.add(Box.createVerticalGlue());
	}
	
	private JPanel initUserNamePanel(){
		
		JPanel userNamePanel = new JPanel();
		
		JLabel userNameLabel = new JLabel("Username: ");
		userNameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		userNamePanel.add(userNameLabel);
		JTextField userNameField = new JTextField(20);
		userNamePanel.add(userNameField);
		
		return userNamePanel;
	}
	
	private JPanel initFirstNamePanel(){
		JPanel firstNamePanel = new JPanel();
		
		JLabel firstNameLabel = new JLabel("First name: ");
		firstNameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		firstNamePanel.add(firstNameLabel);
		JTextField firstNameField = new JTextField(20);
		firstNamePanel.add(firstNameField);
		
		return firstNamePanel;
	}
	
	private JPanel initLastNamePanel(){
		JPanel lastNamePanel = new JPanel();
		
		JLabel lastNameLabel = new JLabel("Last name: ");
		lastNameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		lastNamePanel.add(lastNameLabel);
		JTextField lastNameField = new JTextField(20);
		lastNamePanel.add(lastNameField);
		
		return lastNamePanel;
	}
	private JPanel initEmailPanel(){
		JPanel emailPanel = new JPanel();
		
		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		emailPanel.add(emailLabel);
		JTextField emailField = new JTextField(20);
		emailPanel.add(emailField);
		
		return emailPanel;
	}
	
	private JPanel initPasswordPanel(){
		
		JPanel passwordPanel = new JPanel();
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		passwordPanel.add(passwordLabel);
		passwordField = new JTextField(20);
		passwordPanel.add(passwordField);
		JLabel confirmLabel = new JLabel("Confirm password: ");
		confirmLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		passwordPanel.add(confirmLabel);
		JTextField confirmField = new JTextField(20);
		passwordPanel.add(confirmField);
		return passwordPanel;
	}
	
	private JPanel initButtonPanel(){
		
		JPanel buttonPanel = new JPanel();
		
		signupButton = new JButton("Create Profile!");
		signupButton.addActionListener(buttonListener);
		signupButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		buttonPanel.add(signupButton);
		returnButton = new JButton("Return");
		returnButton.addActionListener(buttonListener);
		returnButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		buttonPanel.add(returnButton);
		
		return buttonPanel;
	}

	private void createNewProfile(String first, String last, String email, String usrName, String pass) {
		UserProfile newUser = new UserProfile();
		newUser.setFirstName(first);
		newUser.setLastName(last);
		newUser.setEmail(email);
		newUser.setUserName(usrName);
		newUser.setPassword(pass);
		if (verifyProfileInformation(newUser)) {
			profiles.userAccounts.put(usrName, newUser);
		} else {
			// print what piece of information is invalid.
		}
	}

	private boolean verifyProfileInformation(UserProfile newUser) {
		if (profiles.verifyCreateProfile(newUser)
				&& profiles.verifyLogin(newUser.getUserName(), newUser.getPassword())) {
			return true;
		}
		return false;
	}

	private void addNewProfile(AccountManager acct) {
		profiles = acct;
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(signupButton)) {
				System.out.println("Here");
				String str = JOptionPane.showInputDialog("First Name:", profiles.userAccounts.get(currentlyLoggedIn).getFirstName());
				System.out.println(str);
			}
			if (e.getSource().equals(returnButton)) {
				removeAll();
				add(new LandingPageGUI());
				repaint();
				revalidate();
				setPreferredSize(new Dimension(800, 800));
			    setVisible(true);
			}
		}
	}
}
