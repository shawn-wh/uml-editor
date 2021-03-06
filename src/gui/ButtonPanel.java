package gui;

import mode.*;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.String;
import java.lang.System;
import java.util.ArrayList;

public class ButtonPanel {
	private Canvas canvas;
	private ArrayList<JButton> buttonList = new ArrayList<>();
	private int previousBtnIndex;
	protected JPanel buttonPanel = new JPanel(new GridLayout(6,1));

	public ButtonPanel() {
		canvas = Canvas.getInstance();
		new ButtonInit("icon/icon1.png", new SelectObjMode("select"));
		new ButtonInit("icon/icon2.png", new CreateLineMode("assocLine"));
		new ButtonInit("icon/icon3.png", new CreateLineMode("genLine"));
		new ButtonInit("icon/icon4.png", new CreateLineMode("compLine"));
		new ButtonInit("icon/icon5.png", new CreateBasicObjMode("class"));
		new ButtonInit("icon/icon6.png", new CreateBasicObjMode("useCase"));
	}

	private class ButtonInit extends JButton {
		private Mode mode; // different mode means different mouseListener

		private ButtonInit (String path, Mode mode) {
			this.mode = mode;
			JButton button = new JButton(createImageIcon(path));
			button.setContentAreaFilled(false);
			button.setOpaque(true);
			button.setBackground(Color.white);
			button.addActionListener(new ButtonListener());
			buttonPanel.add(button);
			buttonList.add(button);
		}

		class ButtonListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				JButton source  = (JButton)e.getSource();
				buttonList.get(previousBtnIndex).setBackground(Color.white);
				source.setBackground(new Color(40, 140, 150));
				canvas.currentMode = mode;
				canvas.setCurrentMode();
				previousBtnIndex = buttonList.indexOf(source);
			}
		}
	}

	/* It is a method to check whether the program finds the icon.png */
	private static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = Window.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Could not find the icon: "+ path);
			return null;
		}
	}

}