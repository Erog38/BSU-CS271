import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class displays a create profile GUI to the user.
 * 
 * @author Jorah Hinman
 */
public class CreateProfileGUI extends JPanel{

	private JPanel mainPanel;
	private JButton createProfileButton;
	private JButton exitButton;
	private ButtonListener buttonListener;
	private JTextField userNameField;
	private JTextField passwordField;
	private AccountManager profile;
	
	public CreateProfileGUI() {
		initInstanceVars();
	}
	
	private void initInstanceVars(){
		profile = new AccountManager();
		setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);
		buttonListener = new ButtonListener();//create button listener
	}
	
	private void initCenterPanel(){
		
		JPanel centerPanel = new JPanel();
		
		centerPanel.add(Box.createVerticalGlue());		
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(centerPanel);		
		mainPanel.add(Box.createVerticalGlue());
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(createProfileButton)){

			}		
			if(e.getSource().equals(exitButton)){

			}
		}
	}
}
