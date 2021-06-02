package mode;

import gui.Canvas;
import shape.*;
import shape.lineobj.*;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.Collections;

public class CreateLineMode extends Mode {
	public Point startPoint;
	public Point endPoint;
	public Point startPort;
	public Point endPort;
	public BasicObj startObj;
	public BasicObj endObj;
	public int endPortPosition;
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
				Canvas.lineList.add(new AssoLineObj(startPort, endPort, endPortPosition));
				break;
			case "genLine":
				Canvas.lineList.add(new GenLineObj(startPort, endPort, endPortPosition));
				break;
			case "compLine":
				Canvas.lineList.add(new CompLineObj(startPort, endPort, endPortPosition));
				break;
			default:
		}
		// relatedLineList store line objects which would be deleted after moving the object
		startObj.relatedLineList.add(Canvas.lineList.get(Canvas.lineList.size() - 1));
		endObj.relatedLineList.add(Canvas.lineList.get(Canvas.lineList.size() - 1));
		canvas.repaint();
	}

	private void Initial() {
		startPoint = null;
		endPoint = null;
		startPort = null;
		endPort = null;
		startObj = null;
		endObj = null;
		endPortPosition = -1;
	}

	private void setStartPort() {
		boolean notFound = true;
		// when there is more than one object in the same place,
		// drawing a line on the object with a minimum depth value
		Collections.sort(Canvas.shapeList);
		for (ShapeObj shapeObj : Canvas.shapeList) {
			if (shapeObj instanceof BasicObj && notFound) { // CreateLineMode can not use on composite objects
				BasicObj basicobj = (BasicObj) shapeObj;
				for(Path2D portArea : basicobj.portAreaList){
					if (portArea.contains(startPoint)){
						int portIndex = basicobj.portAreaList.indexOf(portArea);
						startPort  = basicobj.portList.get(portIndex);
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
				for (Path2D portArea : basicobj.portAreaList) {
					if (portArea.contains(endPoint)) {
						int portIndex = basicobj.portAreaList.indexOf(portArea);
						endPort = basicobj.portList.get(portIndex);
						endObj = basicobj;
						endPortPosition = portIndex;
						notFound = false;
						break;
					}
				}
			}
		}
	}

}