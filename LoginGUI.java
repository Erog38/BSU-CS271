import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoginGUI extends JPanel{

	private JPanel mainPanel;
	
	public LoginGUI(JFrame frame) {
		setLayout(new BorderLayout());
		mainPanel = new JPanel();
		add(mainPanel);
	}

}
