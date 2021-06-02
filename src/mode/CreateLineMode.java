package mode;

import gui.Canvas;
import shape.Port;
import shape.BasicObj;
import shape.ShapeObj;
import shape.lineobj.*;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Collections;

public class CreateLineMode extends Mode {
	public Point startPoint;
	public Point endPoint;
	public Port startPort;
	public Port endPort;
	public BasicObj startObj;
	public BasicObj endObj;
	private Canvas canvas;

	public CreateLineMode (String modeName) {
		this.modeName = modeName;
		canvas = Canvas.getInstance();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Initial();
		startPoint = e.getPoint();
		setStartPort();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		endPoint = e.getPoint();
		setEndPort();
		if (startPort == null || endPort == null) return;
		if (startObj == endObj) return;

		switch(modeName) {
			case "assocLine":
				Canvas.lineList.add(new AssoLineObj(startPort, endPort));
				break;
			case "genLine":
				Canvas.lineList.add(new GenLineObj(startPort, endPort));
				break;
			case "compLine":
				Canvas.lineList.add(new CompLineObj(startPort, endPort));
				break;
			default:
		}
		canvas.repaint();
	}

	private void Initial() {
		startPoint = null;
		endPoint = null;
		startPort = null;
		endPort = null;
		startObj = null;
		endObj = null;
	}

	private void setStartPort() {
		boolean notFound = true;
		// when there is more than one object in the same place,
		// drawing a line on the object with a minimum depth value
		Collections.sort(Canvas.shapeList);
		for (ShapeObj shapeObj : Canvas.shapeList) {
			if (shapeObj instanceof BasicObj && notFound) { // CreateLineMode can not use on composite objects
				BasicObj basicobj = (BasicObj) shapeObj;
				for(Port port : basicobj.portList){
					if (port.portArea.contains(startPoint)){
						startPort  = port;
						startObj = basicobj;
						notFound = false;
						break;
					}
				}
			}
		}
	}

	private void setEndPort(){
		boolean notFound = true;

		Collections.sort(Canvas.shapeList);
		for (ShapeObj shapeobj : Canvas.shapeList){
			if (shapeobj instanceof BasicObj  && notFound) {
				BasicObj basicobj = (BasicObj) shapeobj;
				for (Port port : basicobj.portList) {
					if (port.portArea.contains(endPoint)) {
						endPort = port;
						endObj = basicobj;
						notFound = false;
						break;
					}
				}
			}
		}
	}

}