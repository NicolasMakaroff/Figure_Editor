/**
 * 
 */
package figures.listeners.transform;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JLabel;

import figures.Drawing;
import figures.Figure;
import history.HistoryManager;
import utils.Vector2D;


/**
 * @author nicolasmakaroff
 *
 */
public class RotateShapeTransformListener extends AbstractTransformShapeListener {
	
	/**
	 * vecteur permettant de définir l'angle de la rotation entre le centre et le point de début
	 */
	private Vector2D initVector;
	
	/**
	 * vecteur entre le centre et la position du curseur de la souris
	 */
	private Vector2D currVector;

	/**
	 * @param model
	 * @param history
	 * @param tipLabel
	 */
	public RotateShapeTransformListener(Drawing model, HistoryManager<Figure> history, JLabel tipLabel) {
		super(model, history, tipLabel);
		keyMask = InputEvent.CTRL_MASK;
	}

	/* (non-Javadoc)
	 * @see figures.listeners.transform.AbstractTransformShapeListener#init()
	 */
	@Override
	public void init() {
		if((currentFigure != null) && (center != null)) {
			initVector = new Vector2D(center,startPoint);
			currVector = new Vector2D(initVector);
			initialTransform = currentFigure.getRotation();
		}
		else {
			System.err.println(getClassName() + "::" + getMethodName() + " failed" );
		}

	}

	/* (non-Javadoc)
	 * @see figures.listeners.transform.AbstractTransformShapeListener#updateDrag(java.awt.event.MouseEvent)
	 */
	@Override
	public void updateDrag(MouseEvent e) {
		Point2D currPoint = e.getPoint();
		currVector.setEnd(currPoint);
		if(currentFigure != null) {
			double angle = initVector.angle(currVector);
			AffineTransform rotate = AffineTransform.getRotateInstance(angle);
			rotate.concatenate(initialTransform);
			currentFigure.setRotation(rotate);
		}
		else {
			System.err.println(getClassName() + "::" + getMethodName() + " failed");
		}

	}

}
