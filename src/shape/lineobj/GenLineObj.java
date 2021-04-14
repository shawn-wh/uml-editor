package shape.lineobj;

import shape.LineObj;

import java.awt.Point;

public class GenLineObj extends LineObj {
    public GenLineObj(Point startPort, Point endPort, int endPortPosition) {
        super(startPort, endPort, endPortPosition);
    }

    @Override
    protected void setLineIcon() {
        icon.moveTo(endPort.x, endPort.y);
        icon.lineTo(arrowPoint1.x, arrowPoint1.y);
        icon.lineTo(arrowPoint2.x, arrowPoint2.y);
        icon.closePath();
    }

    @Override
    protected void setArrow() {
        super.setArrow();
        switch(endPortPosition) { // 0-->North, 1-->East, 2-->South, 3-->West
            case 0:
                arrowPoint3.x = endPort.x;
                arrowPoint3.y = endPort.y - ICON_SIZE;
                break;
            case 1:
                arrowPoint3.x = endPort.x + ICON_SIZE;
                arrowPoint3.y = endPort.y;
                break;
            case 2:
                arrowPoint3.x = endPort.x ;
                arrowPoint3.y = endPort.y + ICON_SIZE;
                break;
            case 3:
                arrowPoint3.x = endPort.x - ICON_SIZE;
                arrowPoint3.y = endPort.y;
                break;
            default:
        }
    }

}