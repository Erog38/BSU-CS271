import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProfileGUI extends JPanel{

	private JPanel mainPanel;
	private JButton editProfileButton;
	private JButton logoutButton;
	private ButtonListener buttonListener;
	private AccountManager acct;
	private String username;
	private boolean editable;

	JTextField email;
	JTextField usernameField;
	JTextField firstName;
	JTextField lastName;
	JTextField password;

	public ProfileGUI(AccountManager acct, String username){
		this.acct = acct;
		this.username = username;
		initInstanceVars();
	}

	private void initInstanceVars(){
		editable = false;
		setLayout(new BorderLayout());
		mainPanel = new JPanel();
		buttonListener = new ButtonListener();//create button listener
		mainPanel.add(initUserInfo());
		setLayout(new BorderLayout());
		add(mainPanel);
	}

	private JPanel initButtons() {
		JPanel buttonPanel = new JPanel();

		editProfileButton = new JButton("Edit Profile");
		editProfileButton.addActionListener(buttonListener);
		editProfileButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		buttonPanel.add(editProfileButton);

		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(buttonListener);
		logoutButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		buttonPanel.add(logoutButton);

		return buttonPanel;
	}

	private JPanel initUserInfo() {
		JPanel userInfo = new JPanel();
		userInfo.setLayout(new BoxLayout(userInfo, BoxLayout.Y_AXIS));
		UserProfile user = acct.get(username);
		userInfo.add(initFirstNamePanel());
		userInfo.add(initLastNamePanel());
		userInfo.add(initUserNamePanel());
		userInfo.add(initEmailPanel());
		userInfo.add(initPasswordPanel());
		userInfo.add(initButtons());
		return userInfo;
	}
	private JPanel initUserNamePanel(){

		JPanel userNamePanel = new JPanel();
		userNamePanel.setLayout(new GridLayout(2,1));
		JLabel userNameLabel = new JLabel("Username: ");
		userNameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		userNamePanel.add(userNameLabel);
		usernameField = new JTextField(20);
		usernameField.setText(acct.get(username).getUserName());
		usernameField.setEnabled(false);
		userNamePanel.add(usernameField);

		return userNamePanel;
	}

	private JPanel initFirstNamePanel(){
		JPanel firstNamePanel = new JPanel();
		firstNamePanel.setLayout(new GridLayout(2,1));
		JLabel firstNameLabel = new JLabel("First name: ");
		firstNameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		firstNamePanel.add(firstNameLabel);
		firstName = new JTextField(20);
		firstName.setText(acct.get(username).getFirstName());
		firstName.setEnabled(false);
		firstNamePanel.add(firstName);

		return firstNamePanel;
	}

	private JPanel initLastNamePanel(){
		JPanel lastNamePanel = new JPanel();
		lastNamePanel.setLayout(new GridLayout(2,1));
		JLabel lastNameLabel = new JLabel("Last name: ");
		lastNameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		lastNamePanel.add(lastNameLabel);
		lastName = new JTextField(20);
		lastName.setText(acct.get(username).getLastName());
		lastName.setEnabled(false);
		lastNamePanel.add(lastName);

		return lastNamePanel;
	}
	private JPanel initEmailPanel(){
		JPanel emailPanel = new JPanel();
		emailPanel.setLayout(new GridLayout(2,1));
		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		emailPanel.add(emailLabel);
		email = new JTextField(20);
		email.setText(acct.get(username).getEmail());
		email.setEnabled(false);
		emailPanel.add(email);

		return emailPanel;
	}

	private JPanel initPasswordPanel(){

		JPanel passwordPanel = new JPanel();
		passwordPanel.setLayout(new GridLayout(6, 1));
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		passwordPanel.add(passwordLabel);
		password = new JTextField(20);
		password.setText(acct.get(username).getPassword());
		password.setEnabled(false);
		passwordPanel.add(password);
		return passwordPanel;
	}
	public boolean checkForBlanks() {
		boolean hasBlank = false;
		if (firstName.getText().equals("")) {
			hasBlank = true;
			JOptionPane.showMessageDialog(null, "First name is required.", null, JOptionPane.PLAIN_MESSAGE);
		}
		if (lastName.getText().equals("")) {
			hasBlank = true;
			JOptionPane.showMessageDialog(null, "Last name is required.", null, JOptionPane.PLAIN_MESSAGE);
		}
		if (usernameField.getText().equals("")) {
			hasBlank = true;
			JOptionPane.showMessageDialog(null, "Username is required.", null, JOptionPane.PLAIN_MESSAGE);
		}
		if (email.getText().equals("")) {
			hasBlank = true;
			JOptionPane.showMessageDialog(null, "Email is required.", null, JOptionPane.PLAIN_MESSAGE);
		}
		if (password.getText().equals("")) {
			hasBlank = true;
			JOptionPane.showMessageDialog(null, "Password is required.", null, JOptionPane.PLAIN_MESSAGE);
		}
		return hasBlank;
	}
	
	private boolean verifyProfileInformation(UserProfile newUser) {
		if (acct.verifyCreateProfile(newUser, username)) {
			return true;
		}
		return false;
	}

	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource().equals(editProfileButton)){
				if (editable) {

					if (!checkForBlanks()) {
						UserProfile newUser = new UserProfile(); 
						newUser.setEmail(email.getText());
						newUser.setFirstName(firstName.getText());
						newUser.setLastName(lastName.getText());
						newUser.setUserName(usernameField.getText());
						newUser.setPassword(password.getText());
						
						if (verifyProfileInformation(newUser)) {
							
							acct.delete(username);
							acct.put(newUser);
							
							editProfileButton.setText("Edit Profile");
							editable = false;
							email.setEnabled(false);
							usernameField.setEnabled(false);
							firstName.setEnabled(false);
							lastName.setEnabled(false);
							password.setEnabled(false);
						} 
					} 
					
				} else {
					editProfileButton.setText("Save");
					editable = true;
					email.setEnabled(true);
					usernameField.setEnabled(true);
					firstName.setEnabled(true);
					lastName.setEnabled(true);
					password.setEnabled(true);
				}

			}		
			if(e.getSource().equals(logoutButton)){
				removeAll();
				add(new LandingPageGUI(acct));
				repaint();
				revalidate();
				setPreferredSize(new Dimension(800, 800));
				setVisible(true);
			}
		}
	}

}
