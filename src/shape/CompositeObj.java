package shape;

import gui.Canvas;
import gui.Window;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class CompositeObj extends ShapeObj {
    public List<ShapeObj> compositeList = new ArrayList<>();
    private int maxX = 0;
    private int minX = Window.WINDOW_WIDTH;
    private int maxY = 0;
    private int minY = Window.WINDOW_HEIGHT;
    private int maxWidth;
    private int maxHeight;
    private int compObjSideLength;

    public CompositeObj() {
        groupObjects();
    }

    @Override
    public void draw (Graphics2D g2d){
        for (ShapeObj shapeObj : compositeList) {
            if (shapeObj instanceof CompositeObj){
                shapeObj.draw(g2d);
            } else {
                BasicObj basicObj = (BasicObj) shapeObj;
                basicObj.draw(g2d);
            }
        }
    }

    @Override
    public void pressedCheck(Point selectStartPoint, ShapeObj shapeObj) {
        super.pressedCheck(selectStartPoint, shapeObj);
        deapPressedCheck(shapeObj);
    }

    @Override
    public void releasedCheck(Path2D selectRegion, ShapeObj shapeObj, Point selectStartPoint, Point selectEndPoint) {
        super.releasedCheck(selectRegion, shapeObj, selectStartPoint, selectEndPoint);
        CompositeObj compObj = (CompositeObj) shapeObj;
        for (ShapeObj shape : compObj.compositeList) {
            shape.selected = shapeObj.selected;
        }
        if (shapeObj.moved) {
            shapeObj.moveReset(selectStartPoint, selectEndPoint);
            shapeObj.moved = false;
        }
    }

    @Override
    public void moveReset(Point selectStartPoint, Point selectEndPoint) {
        minX = minX + (selectEndPoint.x - selectStartPoint.x);
        minY = minY + (selectEndPoint.y - selectStartPoint.y);
        for (ShapeObj shape : compositeList) {
            shape.moveReset(selectStartPoint, selectEndPoint);
            objectRegion = new Rectangle2D.Double(minX, minY, compObjSideLength, compObjSideLength);
        }
    }

    public void deapPressedCheck(ShapeObj shapeObj) {
        CompositeObj compObj = (CompositeObj) shapeObj;
        for (ShapeObj shape : compObj.compositeList) {
            shape.selected = compObj.selected;
            shape.moved = compObj.moved;
            if (shape instanceof CompositeObj) {
                deapPressedCheck(shape);
            }
        }
    }

    public void groupObjects() {
       setCompositeBound();
       for (ShapeObj shapeObj : Canvas.selectedList) {
           Canvas.shapeList.remove(shapeObj);
           shapeObj.selected = false;
           compositeList.add(shapeObj);
       }
    }

    public void setCompositeBound() {
        for (ShapeObj shapeObj : Canvas.selectedList) {
            if (shapeObj instanceof CompositeObj)
                compositeObjSetBound(shapeObj);
            else
                basicObjSetBound(shapeObj);
        }
        compObjSideLength = setSquareSideLength();
        objectRegion = new Rectangle2D.Double(minX, minY, compObjSideLength, compObjSideLength);
    }

    private void compositeObjSetBound(ShapeObj shapeObj) {
        CompositeObj compObj = (CompositeObj) shapeObj;
        for (ShapeObj shape : compObj.compositeList) {
            if (shape instanceof CompositeObj) {
                compositeObjSetBound(shape);
            } else {
                basicObjSetBound(shape);
            }
        }
    }

    private void basicObjSetBound(ShapeObj shapeObj) {
        BasicObj basicObj = (BasicObj) shapeObj;
        maxWidth = findMaxWidth(basicObj);
        maxHeight = findMaxHeight(basicObj);
    }

    private int findMaxWidth(BasicObj basicObj) {
        if (maxX < basicObj.sePoint.x)
            maxX = basicObj.sePoint.x;
        if (minX > basicObj.nwPoint.x)
            minX = basicObj.nwPoint.x;
        return maxX - minX;
    }

    private int findMaxHeight(BasicObj basicobj) {
        if (maxY < basicobj.sePoint.y)
            maxY = basicobj.sePoint.y;
        if (minY > basicobj.nwPoint.y)
            minY = basicobj.nwPoint.y;
        return maxY - minY;
    }

    private int setSquareSideLength() {
        if (maxWidth >= maxHeight)
            return maxWidth;
        else
            return maxHeight;
    }

    public void unGroupObjects() {
        for (ShapeObj shapeObj : compositeList) {
            Canvas.shapeList.add(shapeObj);
        }
    }

}

