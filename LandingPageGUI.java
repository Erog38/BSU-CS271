import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * This class is the first place a user starts when using the Profile Login program.
 * 
 * @author Jorah Hinman
 *
 */
public class LandingPageGUI extends JPanel{

		private JPanel mainPanel;
		private JButton createButton;
		private JButton loginButton;
		private ButtonListener buttonListener;
		
	public LandingPageGUI() {
		setLayout(new BorderLayout());
		mainPanel = new JPanel();//panel which all of the other panel are added to
		mainPanel.setLayout(new BorderLayout());
		add(mainPanel);
		buttonListener = new ButtonListener();//create button listener
		initCenterPanel();
	}
		
		
	private void initCenterPanel() {
		JPanel centerPanel = new JPanel();//create panel & set the layout
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBackground(new Color(65, 245, 225));
		createButton = new JButton("Create Profile!");
		loginButton = new JButton("Login");
		createButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		loginButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		createButton.addActionListener(buttonListener);
		loginButton.addActionListener(buttonListener);
		centerPanel.add(Box.createVerticalGlue());
		centerPanel.add(createButton);
		centerPanel.add(loginButton);
		centerPanel.add(Box.createVerticalGlue());
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
	}

	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource().equals(createButton)){
				System.out.println("Create button pushed!");
			}
			
			if(e.getSource().equals(loginButton)){
				System.out.println("Login button pushed!");
			}
		}		
	}
}
