package mode;

import gui.Canvas;
import shape.basicobj.ClassObj;
import shape.basicobj.UseCaseObj;

import java.awt.event.MouseEvent;

public class CreateBasicObjMode extends Mode {
	private Canvas canvas;

	public CreateBasicObjMode(String modeName) {
		this.modeName = modeName;
		canvas = Canvas.getInstance();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (modeName == "class")
			Canvas.shapeList.add(new ClassObj(e.getPoint()));
		else if (modeName == "useCase")
			Canvas.shapeList.add(new UseCaseObj(e.getPoint()));
		canvas.repaint();
	}

}