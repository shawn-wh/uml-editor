package shape.lineobj;

import shape.LineObj;
import shape.Port;

import java.awt.Point;

public class AssoLineObj extends LineObj {
    public AssoLineObj(Port startPort, Port endPort) {
        super(startPort, endPort);
    }

    @Override
    protected void setLineIcon() {
        icon.moveTo(endPort.location.x, endPort.location.y);
        icon.lineTo(arrowPoint1.x, arrowPoint1.y);
        icon.moveTo(endPort.location.x, endPort.location.y);
        icon.lineTo(arrowPoint2.x, arrowPoint2.y);
    }

    @Override
    protected void setArrow() {
        super.setArrow();
        arrowPoint3 = endPort.location;
    }

}