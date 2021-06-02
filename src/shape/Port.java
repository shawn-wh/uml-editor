package shape;

import java.awt.Point;
import java.awt.geom.Path2D;

public class Port {
    public int portSize = 6;
    public Point location;
    public String portPosition; // North, East, South, West
    public Path2D portArea;

    public Port(Point location, String portPosition, Path2D portArea) {
        this.location = location;
        this.portPosition = portPosition;
        this.portArea = portArea;
    }

}