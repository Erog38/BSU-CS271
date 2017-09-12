import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginGUI extends JPanel{

	private JPanel mainPanel;
	private JButton loginButton;
	private ButtonListener buttonListener;
	
	public LoginGUI() {
		
		setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);
		buttonListener = new ButtonListener();//create button listener
		initCenterPanel();
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
		JTextField userNameField = new JTextField(20);
		userNamePanel.add(userNameField);
		return userNamePanel;
	}
	
	private JPanel initPasswordPanel(){
		
		JPanel passwordPanel = new JPanel();
		JLabel passwordLabel = new JLabel("password: ");
		passwordLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		passwordPanel.add(passwordLabel);
		JTextField passwordField = new JTextField(20);
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

	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(loginButton)){
				System.out.println("Login button pushed!");
			}
		}		
	}
}
