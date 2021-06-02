package shape;

import gui.Canvas;

import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.Color;
import java.awt.BasicStroke;

public abstract class LineObj extends ShapeObj {
    protected static final int ICON_SIZE = 5;
    protected Port startPort;
    protected Port endPort;
    protected Point arrowPoint1 = new Point();
    protected Point arrowPoint2 = new Point();
    protected Point arrowPoint3 = new Point();
    protected Path2D icon = new Path2D.Double();

    protected LineObj(Port startPort, Port endPort) {
        this.startPort = startPort;
        this.endPort = endPort;
        depth = gui.Canvas.depthCounter;
        Canvas.depthCounter--;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(THICKNESS));
        drawIcon(g2d);
        g2d.drawLine(startPort.location.x, startPort.location.y, arrowPoint3.x, arrowPoint3.y);
    }

    protected void drawIcon(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(THICKNESS));
        setArrow();
        setLineIcon();
        g2d.draw(icon);
    }

    protected void setArrow() {
        switch(endPort.portPosition) {
            case "North":
                arrowPoint1.x = endPort.location.x - ICON_SIZE;
                arrowPoint1.y = endPort.location.y - ICON_SIZE;
                arrowPoint2.x = endPort.location.x + ICON_SIZE;
                arrowPoint2.y = endPort.location.y - ICON_SIZE;
                break;
            case "East":
                arrowPoint1.x = endPort.location.x + ICON_SIZE;
                arrowPoint1.y = endPort.location.y + ICON_SIZE;
                arrowPoint2.x = endPort.location.x + ICON_SIZE;
                arrowPoint2.y = endPort.location.y - ICON_SIZE;
                break;
            case "South":
                arrowPoint1.x = endPort.location.x - ICON_SIZE;
                arrowPoint1.y = endPort.location.y + ICON_SIZE;
                arrowPoint2.x = endPort.location.x + ICON_SIZE;
                arrowPoint2.y = endPort.location.y + ICON_SIZE;
                break;
            case "West":
                arrowPoint1.x = endPort.location.x - ICON_SIZE;
                arrowPoint1.y = endPort.location.y - ICON_SIZE;
                arrowPoint2.x = endPort.location.x - ICON_SIZE;
                arrowPoint2.y = endPort.location.y + ICON_SIZE;
                break;
            default:
        }
    }

    @Override
    public void moveReset(Point selectStartPoint, Point selectEndPoint) {}

    protected abstract void setLineIcon();

}