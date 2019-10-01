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

/**
 * @author nicolasmakaroff
 *
 */
public class ScaleShapeTransformListener extends AbstractTransformShapeListener {
	
	/**
	 * scale initial de la figure à partir de la distance centre et le startPoint
	 */
	private double initScale;
	
	/**
	 * scale courant
	 */
	private double currScale;

	/**
	 * @param model
	 * @param history
	 * @param tipLabel
	 */
	public ScaleShapeTransformListener(Drawing model, HistoryManager<Figure> history, JLabel tipLabel) {
		super(model, history, tipLabel);
		
		keyMask = InputEvent.SHIFT_MASK;
	}

	/* (non-Javadoc)
	 * @see figures.listeners.transform.AbstractTransformShapeListener#init()
	 */
	@Override
	public void init() {
		if((currentFigure != null) && (center != null)) {
			initScale = center.distance(startPoint);
			currScale = initScale;
			initialTransform = currentFigure.getScale();
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
		if((currentFigure != null) && (center != null)) {
			currScale = center.distance(currPoint);
			double newScale =currScale / initScale;
			AffineTransform scale = AffineTransform.getScaleInstance(newScale, newScale);
			scale.concatenate(initialTransform);
			currentFigure.setScale(scale);
		}
		else {
			System.err.println(getClassName() + "::" + getMethodName() + " failed");
		}

	}

}
