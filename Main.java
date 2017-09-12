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
		JFrame frame = new JFrame("Welcome to Profile Login!");//make new frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new LandingPageGUI());
		frame.setPreferredSize(new Dimension(800, 800));
		frame.pack();
		frame.setVisible(true);
	}
}

