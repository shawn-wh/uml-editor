package gui;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Window {
	protected JFrame frame;
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 1000;
	protected Canvas canvas = new Canvas();

	public void go() {
		frame = new JFrame("UML Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(BorderLayout.CENTER, canvas);

		ButtonPanel buttonPanel = new ButtonPanel(canvas);
		frame.getContentPane().add(BorderLayout.WEST, buttonPanel.buttonPanel);

		Menu menu = new Menu(canvas);
		frame.setJMenuBar(menu.menubar);

		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setVisible(true);
	}

}

