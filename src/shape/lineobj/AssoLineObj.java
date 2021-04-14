package shape.lineobj;

import shape.LineObj;

import java.awt.Point;

public class AssoLineObj extends LineObj {
    public AssoLineObj(Point startPort, Point endPort, int endPortPosition) {
        super(startPort, endPort, endPortPosition);
    }

    @Override
    protected void setLineIcon() {
        icon.moveTo(endPort.x, endPort.y);
        icon.lineTo(arrowPoint1.x, arrowPoint1.y);
        icon.moveTo(endPort.x, endPort.y);
        icon.lineTo(arrowPoint2.x, arrowPoint2.y);
    }

    @Override
    protected void setArrow() {
        super.setArrow();
        arrowPoint3 = endPort;
    }

}