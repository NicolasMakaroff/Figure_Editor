/**
 * 
 */
package figures.listeners.transform;

import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JLabel;

import figures.Drawing;
import figures.Figure;
import history.HistoryManager;

/**
 * @author nicolasmakaroff
 *
 */
public class MoveShapeTransformListener extends AbstractTransformShapeListener {

	
	/**
	 * point auquel est déplacé la figure
	 */
	private Point2D finalDestination;
	
	
	/**
	 * @param model
	 * @param history
	 * @param tipLabel
	 */
	public MoveShapeTransformListener(Drawing model, HistoryManager<Figure> history, JLabel tipLabel) {
		super(model, history, tipLabel);
		//Rien
	}

	/* (non-Javadoc)
	 * @see figures.listeners.transform.AbstractTransformShapeListener#init()
	 */
	@Override
	public void init() {
		finalDestination = startPoint;
		if(currentFigure != null) {
			initialTransform = currentFigure.getTranslation();
			System.out.println(getClassName() + "::" + getMethodName() + " initianilised");
		}
		else {
			System.err.println(getClassName() + "::" + getMethodName() + " failed initialising");
		}

	}

	/* (non-Javadoc)
	 * @see figures.listeners.transform.AbstractTransformShapeListener#updateDrag(java.awt.event.MouseEvent)
	 */
	@Override
	public void updateDrag(MouseEvent e) {
		Point2D currPoint = e.getPoint();
		double dx = currPoint.getX() - finalDestination.getX();
		double dy = currPoint.getY() - finalDestination.getY();
		AffineTransform move = AffineTransform.getTranslateInstance(dx, dy);
		move.concatenate(initialTransform);
		currentFigure.setTranslation(move);
	}

}
