import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Driver class
 * 
 * @author Jorah Hinman
 *
 */
public class Main {

	public static void main(String[] args) {
		
		AccountManager acctManager = new AccountManager();
		
		JFrame frame = new JFrame("Welcome to Profile Login!");//make new frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new LandingPageGUI(acctManager));
		frame.setPreferredSize(new Dimension(400, 600));
		frame.pack();
		frame.setVisible(true);
	}
}

