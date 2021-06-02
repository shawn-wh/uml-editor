package gui;

import mode.Mode;
import shape.ShapeObj;
import shape.LineObj;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Canvas extends JPanel {
	private static Canvas instance = new Canvas(); // for singleton
	public static int depthCounter = 99;
	public static List<ShapeObj> shapeList = new ArrayList<>();
	public static List<LineObj> lineList = new ArrayList<>();
	public static List<ShapeObj> selectedList = new ArrayList<>();
	private MouseAdapter listener = null;
	protected Mode currentMode;

	/* Singleton design pattern */
	private Canvas() {} // private constructor

	public static Canvas getInstance() {
		return instance;
	}

	/* Remove the past listener and get into the current listener */
    public void setCurrentMode() {
		removeMouseListener(listener);
		listener = currentMode;
		addMouseListener(listener);
	}

	@Override
	public void paintComponent(Graphics g) {
    	super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		Collections.sort(shapeList); // sort the List by depth before draw shape objects
		for (ShapeObj shape : shapeList) {
			shape.draw(g2d);
		}
		Collections.sort(lineList);
		for (LineObj line : lineList) {
			line.draw(g2d);
		}
	}

}

