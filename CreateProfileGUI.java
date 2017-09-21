import java.awt.BorderLayout;
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
@SuppressWarnings("serial")
public class CreateProfileGUI extends LoginGUI {

	private JPanel mainPanel;
	private JButton returnButton;
	private JButton signupButton;
	private ButtonListener buttonListener;
	private JTextField username;
	private JTextField password;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField email;
	private JTextField passwordConfirm;
	private AccountManager acct;

	public CreateProfileGUI() {
		initInstanceVars();
	}

	public CreateProfileGUI(AccountManager acct) {
		this();
		this.acct = acct;
	}

	private void initInstanceVars() {
		acct = new AccountManager();
		setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		buttonListener = new ButtonListener();// create button listener
		createSignUpPage();
		add(mainPanel);
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
		username = new JTextField(20);
		userNamePanel.add(username);
		
		return userNamePanel;
	}
	
	private JPanel initFirstNamePanel(){
		JPanel firstNamePanel = new JPanel();
		
		JLabel firstNameLabel = new JLabel("First name: ");
		firstNameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		firstNamePanel.add(firstNameLabel);
		firstName = new JTextField(20);
		firstNamePanel.add(firstName);
		
		return firstNamePanel;
	}
	
	private JPanel initLastNamePanel(){
		JPanel lastNamePanel = new JPanel();
		
		JLabel lastNameLabel = new JLabel("Last name: ");
		lastNameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		lastNamePanel.add(lastNameLabel);
		lastName = new JTextField(20);
		lastNamePanel.add(lastName);
		
		return lastNamePanel;
	}
	private JPanel initEmailPanel(){
		JPanel emailPanel = new JPanel();
		
		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		emailPanel.add(emailLabel);
		email= new JTextField(20);
		emailPanel.add(email);
		
		return emailPanel;
	}
	
	private JPanel initPasswordPanel(){
		
		JPanel passwordPanel = new JPanel();
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		passwordPanel.add(passwordLabel);
		password = new JTextField(20);
		passwordPanel.add(password);
		JLabel confirmLabel = new JLabel("Confirm password: ");
		confirmLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		passwordPanel.add(confirmLabel);
		passwordConfirm = new JTextField(20);
		passwordPanel.add(passwordConfirm);
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

	private boolean createNewProfile(String first, String last, String email, String usrName, String pass) {
		UserProfile newUser = new UserProfile();
		newUser.setFirstName(first);
		newUser.setLastName(last);
		newUser.setEmail(email);
		newUser.setUserName(usrName);
		newUser.setPassword(pass);
		if (verifyProfileInformation(newUser)) {
			acct.put(newUser);
			return true;
		} else {
			return false;
		}
	}

	private boolean verifyProfileInformation(UserProfile newUser) {
		if (acct.verifyCreateProfile(newUser)) {
			return true;
		}
		return false;
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(signupButton)) {
				
				if (password.getText().equals(passwordConfirm.getText())) {
				
					if(createNewProfile(firstName.getText(), lastName.getText(), email.getText(), username.getText(),
							password.getText())) {
						JOptionPane.showMessageDialog(null, "Account Created! Welcome!", null, 0);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Password Mismatch", null, 0);

				}
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
