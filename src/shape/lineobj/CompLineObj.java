package shape.lineobj;

import shape.LineObj;

import java.awt.Point;

public class CompLineObj extends LineObj {
    public CompLineObj(Point startPort, Point endPort, int endPortPosition) {
        super(startPort, endPort, endPortPosition);
    }

    @Override
    protected void setLineIcon() {
        icon.moveTo(endPort.x, endPort.y);
        icon.lineTo(arrowPoint1.x, arrowPoint1.y);
        icon.lineTo(arrowPoint3.x, arrowPoint3.y);
        icon.lineTo(arrowPoint2.x, arrowPoint2.y);
        icon.closePath();
    }

    @Override
    protected void setArrow() {
        super.setArrow();
        switch(endPortPosition) { // 0-->North, 1-->East, 2-->South, 3-->West
            case 0:
                arrowPoint3.x = endPort.x;
                arrowPoint3.y = endPort.y - (2 * ICON_SIZE);
                break;
            case 1:
                arrowPoint3.x = endPort.x + (2 * ICON_SIZE);
                arrowPoint3.y = endPort.y;
                break;
            case 2:
                arrowPoint3.x = endPort.x ;
                arrowPoint3.y = endPort.y + (2 * ICON_SIZE);
                break;
            case 3:
                arrowPoint3.x = endPort.x - (2 * ICON_SIZE);
                arrowPoint3.y = endPort.y;
                break;
            default:
        }
    }

}