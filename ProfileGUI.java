import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProfileGUI extends JPanel{
	
	private JPanel mainPanel;
	private JButton editProfileButton;
	private JButton exitButton;
	private ButtonListener buttonListener;
	private AccountManager profile;
	
	public ProfileGUI() {
		initInstanceVars();
	}
	
	public ProfileGUI(AccountManager acct){
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
	
	private void setProfile(AccountManager acct) {
		profile = acct;		
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(editProfileButton)){

			}		
			if(e.getSource().equals(exitButton)){

			}
		}
	}

}
