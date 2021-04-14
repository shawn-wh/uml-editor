package shape;

import gui.Canvas;

import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.Color;
import java.awt.BasicStroke;

public abstract class LineObj extends ShapeObj {
    protected static final int ICON_SIZE = 5;
    protected int endPortPosition;
    protected Point startPort;
    protected Point endPort;
    protected Point arrowPoint1 = new Point();
    protected Point arrowPoint2 = new Point();
    protected Point arrowPoint3 = new Point();
    protected Path2D icon = new Path2D.Double();

    protected LineObj(Point startPort, Point endPort, int endPortPosition) {
        this.startPort = startPort;
        this.endPort = endPort;
        this.endPortPosition = endPortPosition;
        depth = gui.Canvas.depthCounter;
        Canvas.depthCounter--;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(THICKNESS));
        drawIcon(g2d);
        g2d.drawLine(startPort.x, startPort.y, arrowPoint3.x, arrowPoint3.y);
    }

    protected void drawIcon(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(THICKNESS));
        setArrow();
        setLineIcon();
        g2d.draw(icon);
    }

    protected void setArrow() {
        switch(endPortPosition) { // endPortPosition: 0-->North, 1-->East, 2-->South, 3-->West
            case 0:
                arrowPoint1.x = endPort.x - ICON_SIZE;
                arrowPoint1.y = endPort.y - ICON_SIZE;
                arrowPoint2.x = endPort.x + ICON_SIZE;
                arrowPoint2.y = endPort.y - ICON_SIZE;
                break;
            case 1:
                arrowPoint1.x = endPort.x + ICON_SIZE;
                arrowPoint1.y = endPort.y + ICON_SIZE;
                arrowPoint2.x = endPort.x + ICON_SIZE;
                arrowPoint2.y = endPort.y - ICON_SIZE;
                break;
            case 2:
                arrowPoint1.x = endPort.x - ICON_SIZE;
                arrowPoint1.y = endPort.y + ICON_SIZE;
                arrowPoint2.x = endPort.x + ICON_SIZE;
                arrowPoint2.y = endPort.y + ICON_SIZE;
                break;
            case 3:
                arrowPoint1.x = endPort.x - ICON_SIZE;
                arrowPoint1.y = endPort.y - ICON_SIZE;
                arrowPoint2.x = endPort.x - ICON_SIZE;
                arrowPoint2.y = endPort.y + ICON_SIZE;
                break;
            default:
        }
    }

    @Override
    public void moveReset(Point selectStartPoint, Point selectEndPoint) {}

    protected abstract void setLineIcon();

}