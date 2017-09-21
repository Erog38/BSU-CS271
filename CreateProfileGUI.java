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
	private JButton createProfileButton;
	private JButton exitButton;
	private JButton editProfileButton;
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
		createSignUpPage(mainPanel);
		add(mainPanel);
		buttonListener = new ButtonListener();// create button listener
	}

	private void createSignUpPage(JPanel tmpMainPanel) {
		tmpMainPanel.setBackground(Color.LIGHT_GRAY);
		JTextField firstName = new JTextField(10);
		JTextField lastName = new JTextField(10);
		JTextField email = new JTextField(15);
		JTextField userName = new JTextField(10);
		JTextField password = new JTextField(10);
		JLabel firstNameLabel = new JLabel("First Name: ");
		firstNameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		JLabel lastNameLabel = new JLabel("Last Name: ");
		lastNameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		JLabel userNameLabel = new JLabel("Username: ");
		userNameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		editProfileButton = new JButton("Edit Profile");
		editProfileButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		editProfileButton.addActionListener(buttonListener);
		// editProfileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		tmpMainPanel.add(firstNameLabel);
		tmpMainPanel.add(firstName);
		tmpMainPanel.add(lastNameLabel);
		tmpMainPanel.add(lastName);
		tmpMainPanel.add(emailLabel);
		tmpMainPanel.add(email);
		tmpMainPanel.add(userNameLabel);
		tmpMainPanel.add(userName);
		tmpMainPanel.add(passwordLabel);
		tmpMainPanel.add(password);
		tmpMainPanel.add(editProfileButton);
		createNewProfile(firstName.getText(), lastName.getText(), email.getText(), userName.getText(),
				password.getText());
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

	private void initCenterPanel() {

		JPanel centerPanel = new JPanel();

		centerPanel.add(Box.createVerticalGlue());
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(centerPanel);
		mainPanel.add(Box.createVerticalGlue());
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(editProfileButton)) {
				System.out.println("Here");
				String str = JOptionPane.showInputDialog("First Name:", profiles.userAccounts.get(currentlyLoggedIn).getFirstName());
				System.out.println(str);
			}
			if (e.getSource().equals(createProfileButton)) {

			}
			if (e.getSource().equals(exitButton)) {

			}
		}
	}
}
