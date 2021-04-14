package shape;

import gui.Canvas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class BasicObj extends ShapeObj {
	protected static final int FONTSIZE = 12;
	private int width;
	private int height;
	public String objectName;

	private Point nPoint = new Point();
	private Point wPoint = new Point();
	private Point sPoint = new Point();
	private Point ePoint = new Point();
	private Point center = new Point();
	public Point nwPoint = new Point();
	private Point nePoint = new Point();
	public Point sePoint = new Point();
	private Point swPoint = new Point();
	public ArrayList<Point> portList = new ArrayList<>(4);
	public ArrayList<Path2D> portAreaList = new ArrayList<>(4);
	public ArrayList<LineObj> relatedLineList = new ArrayList<>();

	public BasicObj(Point p, int width, int height) {
		this.width = width;
		this.height = height;
		depth = Canvas.depthCounter;
		Canvas.depthCounter--;
		objectRegion = new Rectangle2D.Double(p.x, p.y, width, height);
		setVertex(p, width, height);
		setPortPoint(p, width, height);
		setAllPortArea();
	}

	public void setVertex (Point p, int width, int height) {
		center.x = p.x + width / 2;
		center.y = p.y + height / 2;
		nwPoint.x = p.x ;
		nwPoint.y = p.y ;
		nePoint.x = p.x + width;
		nePoint.y = p.y ;
		sePoint.x = p.x + width;
		sePoint.y = p.y + height ;
		swPoint.x = p.x ;
		swPoint.y = p.y + height;
	}

	public void setPortPoint (Point p, int width, int height) {
		nPoint.x = p.x + width / 2;
		nPoint.y = p.y ;
		portList.add(nPoint);
		ePoint.x = p.x + width;
		ePoint.y = p.y + height / 2;
		portList.add(ePoint);
		sPoint.x = p.x + width / 2;
		sPoint.y = p.y + height;
		portList.add(sPoint);
		wPoint.x = p.x;
		wPoint.y = p.y + height / 2;
		portList.add(wPoint);
	}

	public void setAllPortArea() {
		setPortArea(nwPoint, center, nePoint); // North
		setPortArea(nePoint, center, sePoint); // East
		setPortArea(sePoint, center, swPoint); // South
		setPortArea(swPoint, center, nwPoint); // West
	}

	public void setPortArea(Point p1, Point p2, Point p3 ) {
		Path2D sectorArea = new Path2D.Double();
		sectorArea.moveTo(p1.x, p1.y);
		sectorArea.lineTo(p2.x, p2.y);
		sectorArea.lineTo(p3.x, p3.y);
		sectorArea.closePath();
		portAreaList.add(sectorArea);
	}

	public void showPort(Graphics2D g2d){
		int portSize = 6;
		for (Point port : portList){
			g2d.setColor(Color.BLACK);
			g2d.fillRect(port.x - 3, port.y - 3, portSize, portSize);
		}
	}

	@Override
	public void releasedCheck(Path2D selectRegion, ShapeObj shapeobj, Point selectStartPoint, Point selectEndPoint) {
		super.releasedCheck(selectRegion, shapeobj, selectStartPoint, selectEndPoint);
		if (shapeobj.moved) {
			shapeobj.moveReset(selectStartPoint, selectEndPoint);
			BasicObj basicObj = (BasicObj) shapeobj;
			for (LineObj lineObj : basicObj.relatedLineList) {
				Canvas.lineList.remove(lineObj);
			}
			shapeobj.moved = false;
		}
	}

	@Override
	public void moveReset(Point selectStartPoint, Point selectEndPoint) {
		nwPoint.x = nwPoint.x + (selectEndPoint.x - selectStartPoint.x);
		nwPoint.y = nwPoint.y + (selectEndPoint.y - selectStartPoint.y);
		objectRegion = new Rectangle2D.Double(nwPoint.x, nwPoint.y, width, height);
		setVertex(nwPoint, width, height);
		setPortPoint(nwPoint, width, height);
		setAllPortArea();
	}

	@Override
	public void draw(Graphics2D g2d) {}

	public abstract void showObjectName(Graphics2D g2d);

}