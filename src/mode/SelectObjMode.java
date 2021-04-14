package mode;

import gui.Canvas;
import shape.ShapeObj;

import java.awt.geom.Path2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Collections;

public class SelectObjMode extends Mode {
    private Canvas canvas;
    private Point selectStartPoint;
    private Point selectEndPoint;

    public SelectObjMode(String modeName, Canvas canvas) {
        this.modeName = modeName;
        this.canvas = canvas;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        selectStartPoint = e.getPoint();
        Point usedPoint = null;

        // we can only select the object with a small depth value, when more than one object in the same space
        Collections.sort(Canvas.shapeList);
        for (ShapeObj shapeObj : Canvas.shapeList) {
            if (usedPoint != null && shapeObj.objectRegion.contains(usedPoint))
                continue;
            shapeObj.pressedCheck(selectStartPoint, shapeObj);
            if (shapeObj.selected)
               usedPoint = selectStartPoint;
        }
        canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        selectEndPoint = e.getPoint();
        Path2D selectRegion = setSelectRegion();

        Collections.sort(Canvas.shapeList);
        for (ShapeObj shapeObj : Canvas.shapeList) {
            shapeObj.releasedCheck(selectRegion, shapeObj, selectStartPoint, selectEndPoint);
        }
        canvas.repaint();
    }

    public Path2D setSelectRegion(){
        Path2D selectRegion = new Path2D.Double();
        selectRegion.moveTo(selectStartPoint.x, selectStartPoint.y);
        selectRegion.lineTo(selectEndPoint.x, selectStartPoint.y);
        selectRegion.lineTo(selectEndPoint.x, selectEndPoint.y);
        selectRegion.lineTo(selectStartPoint.x, selectEndPoint.y);
        selectRegion.closePath();
        return selectRegion;
    }

}