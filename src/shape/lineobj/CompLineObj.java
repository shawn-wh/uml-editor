package shape.lineobj;

import shape.LineObj;
import shape.Port;

import java.awt.Point;

public class CompLineObj extends LineObj {
    public CompLineObj(Port startPort, Port endPort) {
        super(startPort, endPort);
    }

    @Override
    protected void setLineIcon() {
        icon.moveTo(endPort.location.x, endPort.location.y);
        icon.lineTo(arrowPoint1.x, arrowPoint1.y);
        icon.lineTo(arrowPoint3.x, arrowPoint3.y);
        icon.lineTo(arrowPoint2.x, arrowPoint2.y);
        icon.closePath();
    }

    @Override
    protected void setArrow() {
        super.setArrow();
        switch(endPort.portPosition) { // 0-->North, 1-->East, 2-->South, 3-->West
            case "North":
                arrowPoint3.x = endPort.location.x;
                arrowPoint3.y = endPort.location.y - (2 * ICON_SIZE);
                break;
            case "East":
                arrowPoint3.x = endPort.location.x + (2 * ICON_SIZE);
                arrowPoint3.y = endPort.location.y;
                break;
            case "South":
                arrowPoint3.x = endPort.location.x ;
                arrowPoint3.y = endPort.location.y + (2 * ICON_SIZE);
                break;
            case "West":
                arrowPoint3.x = endPort.location.x - (2 * ICON_SIZE);
                arrowPoint3.y = endPort.location.y;
                break;
            default:
        }
    }

}