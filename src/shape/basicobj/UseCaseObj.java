package shape.basicobj;

import shape.BasicObj;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

public class UseCaseObj extends BasicObj {
	private static int width = 80;
	private static int height = 60;

	public UseCaseObj(Point p) {
		super(p, width, height);
		nwPoint = p;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(THICKNESS));
        g2d.drawOval(nwPoint.x, nwPoint.y, width, height);
        if (selected)
        	showPort(g2d);
		showObjectName(g2d);
	}

	@Override
	public void showObjectName(Graphics2D g2d) {
		if (objectName != null){
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font( "SansSerif", Font.BOLD, FONTSIZE));
			g2d.drawString(objectName, nwPoint.x + width/5, nwPoint.y + height/4);
		}
	}

}