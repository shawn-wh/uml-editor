package shape.basicobj;

import shape.BasicObj;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

public class ClassObj extends BasicObj {
	private static int width = 80;
	private static int height = 110;

	public ClassObj(Point p) {
		super(p, width, height);
		nwPoint = p;
	}

	@Override
	public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(THICKNESS));

		int x1 = nwPoint.x;
		int x2 = x1 + width;
		int y1 = nwPoint.y;
		int partOneHeight = width / 4;
		int partOneTwoHeight = (height - partOneHeight) / 2 + partOneHeight;

		g2d.drawLine(nwPoint.x, y1 + partOneHeight, x2, y1 + partOneHeight);
        g2d.drawRect(x1, y1, width, height);
		g2d.drawLine(nwPoint.x, y1 + partOneTwoHeight, x2, y1 + partOneTwoHeight);

		if (selected)
			showPort(g2d);
		showObjectName(g2d);
	}

	@Override
	public void showObjectName(Graphics2D g2d) {
		if (objectName != null){
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font( "SansSerif", Font.BOLD, FONTSIZE));
			g2d.drawString(objectName, nwPoint.x + width/5, nwPoint.y + height/7);
		}
	}

}