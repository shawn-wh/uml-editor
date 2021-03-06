package shape.lineobj;

import shape.LineObj;
import shape.Port;

import java.awt.geom.Path2D;

public class GenLineObj extends LineObj {
    public GenLineObj(Port startPort, Port endPort) {
        super(startPort, endPort);
    }

    @Override
    protected void setLineIcon(Path2D icon) {
        icon.moveTo(endPort.location.x, endPort.location.y);
        icon.lineTo(arrowPoint1.x, arrowPoint1.y);
        icon.lineTo(arrowPoint2.x, arrowPoint2.y);
        icon.closePath();
    }

    @Override
    protected void setArrow() {
        super.setArrow();
        switch(endPort.portPosition) {
            case "North":
                arrowPoint3.x = endPort.location.x;
                arrowPoint3.y = endPort.location.y - ICON_SIZE;
                break;
            case "East":
                arrowPoint3.x = endPort.location.x + ICON_SIZE;
                arrowPoint3.y = endPort.location.y;
                break;
            case "South":
                arrowPoint3.x = endPort.location.x ;
                arrowPoint3.y = endPort.location.y + ICON_SIZE;
                break;
            case "West":
                arrowPoint3.x = endPort.location.x - ICON_SIZE;
                arrowPoint3.y = endPort.location.y;
                break;
            default:
        }
    }

}