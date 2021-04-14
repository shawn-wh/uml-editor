package shape;

import gui.Canvas;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public abstract class ShapeObj extends gui.Canvas implements Comparable<ShapeObj> {
    protected static final int THICKNESS = 2;
    public int depth;
    // public boolean isComposite = false;
    public boolean selected = false;
    public boolean moved = false;
    public Rectangle2D objectRegion;

    public int compareTo(ShapeObj shape) {
        return shape.depth -  this.depth; // sort from big to small
    }

    public void pressedCheck(Point selectStartPoint, ShapeObj shapeobj) {
        selectedInit(shapeobj);

        if (Canvas.selectedList.contains(shapeobj))
            Canvas.selectedList.remove(shapeobj);
        if (shapeobj.objectRegion.contains(selectStartPoint)) {
            shapeobj.selected = true;
            shapeobj.moved = true;
            Canvas.selectedList.add(shapeobj);
        }
    }

    private void selectedInit(ShapeObj shapeObj){
        shapeObj.selected = false;
        shapeObj.moved = false;
        if (shapeObj instanceof CompositeObj){
            for (ShapeObj shape : ((CompositeObj) shapeObj).compositeList) {
                selectedInit(shape);
            }
        }
    }

    public void releasedCheck(Path2D selectRegion, ShapeObj shapeobj, Point selectStartPoint, Point selectEndPoint) {
        if (selectRegion.contains(shapeobj.objectRegion)) {
            shapeobj.selected = true;
            if (! Canvas.selectedList.contains(shapeobj))
                Canvas.selectedList.add(shapeobj);
        }
        if (selectEndPoint.equals(selectStartPoint))
            shapeobj.moved = false; // do not move the object when we only click it
    }

    public abstract void draw(Graphics2D g2d);

    public abstract void moveReset(Point selectStartPoint, Point selectEndPoint);
}