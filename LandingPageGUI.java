import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * This class is the first place a user starts when using the Profile Login program.
 * 
 * @author Jorah Hinman
 *
 */
public class LandingPageGUI extends JPanel{
		
		private JPanel mainPanel;
		private JButton createProfileButton;
		private JButton loginButton;
		private ButtonListener buttonListener;
		private AccountManager profile;
		
	public LandingPageGUI() {
		initInstanceVars();
		initCenterPanel();
	}
	
	public LandingPageGUI(AccountManager acct){
		this();
		setProfile(acct);
	}
	
	private void initInstanceVars(){
		setLayout(new BorderLayout());
		mainPanel = new JPanel();//panel which all of the other panel are added to
		mainPanel.setLayout(new BorderLayout());
		add(mainPanel);
		buttonListener = new ButtonListener();//create button listener
		profile = new AccountManager();
	}

	private void initCenterPanel() {
		JPanel centerPanel = new JPanel();//create panel & set the layout
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		createProfileButton = new JButton("Create Profile!");
		loginButton = new JButton("Login");
		createProfileButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		loginButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		createProfileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		createProfileButton.addActionListener(buttonListener);
		loginButton.addActionListener(buttonListener);
		centerPanel.add(Box.createVerticalGlue());
		centerPanel.add(createProfileButton);
		centerPanel.add(loginButton);
		centerPanel.add(Box.createVerticalGlue());
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
	}
	
	private void setProfile(AccountManager acct) {
		profile = acct;		
	}

	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource().equals(createProfileButton)){
				removeAll();
				add(new CreateProfileGUI());
				repaint();
				revalidate();
				setPreferredSize(new Dimension(800, 800));
			    setVisible(true);
			}
			
			if(e.getSource().equals(loginButton)){
				removeAll();
				add(new LoginGUI(profile));
				repaint();
				revalidate();
				setPreferredSize(new Dimension(800, 800));
			    setVisible(true);
			}
		}		
	}
}
