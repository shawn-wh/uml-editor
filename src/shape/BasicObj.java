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
	public ArrayList<Port> portList = new ArrayList<>(4);
	public ArrayList<LineObj> relatedLineList = new ArrayList<>();

	public BasicObj(Point p, int width, int height) {
		this.width = width;
		this.height = height;
		depth = Canvas.depthCounter;
		Canvas.depthCounter--;
		objectRegion = new Rectangle2D.Double(p.x, p.y, width, height);
		setVertex(p, width, height);
		setPort(p, width, height);
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

	public void setPort (Point p, int width, int height) {
		nPoint.x = p.x + width / 2;
		nPoint.y = p.y;
		portList.add(new Port(nPoint, "North", setPortArea(nwPoint, center, nePoint)));

		ePoint.x = p.x + width;
		ePoint.y = p.y + height / 2;
		portList.add(new Port(ePoint, "East", setPortArea(nePoint, center, sePoint)));

		sPoint.x = p.x + width / 2;
		sPoint.y = p.y + height;
		portList.add(new Port(sPoint, "South", setPortArea(sePoint, center, swPoint)));

		wPoint.x = p.x;
		wPoint.y = p.y + height / 2;
		portList.add(new Port(wPoint, "West", setPortArea(swPoint, center, nwPoint)));
	}

	public Path2D setPortArea(Point p1, Point p2, Point p3) {
		Path2D sectorArea = new Path2D.Double();
		sectorArea.moveTo(p1.x, p1.y);
		sectorArea.lineTo(p2.x, p2.y);
		sectorArea.lineTo(p3.x, p3.y);
		sectorArea.closePath();
		return sectorArea;
	}

	public void showPort(Graphics2D g2d){
		for (Port port : portList){
			g2d.setColor(Color.BLACK);
			g2d.fillRect(port.location.x - 3, port.location.y - 3, port.portSize, port.portSize);
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
		setPort(nwPoint, width, height);
	}

	@Override
	public void draw(Graphics2D g2d) {}

	public abstract void showObjectName(Graphics2D g2d);

}