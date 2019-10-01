/**
 * 
 */
package figures.listeners.creation;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JLabel;

import figures.Drawing;
import figures.Figure;
import figures.Polygon;
import history.HistoryManager;

/**
 * @author nicolasmakaroff
 *
 */
public class PolygonalShapeCreationListener extends AbstractCreationListener {

	/**
	 * @param model
	 * @param history
	 * @param infoLabel
	 * @param nbSteps
	 */
	public PolygonalShapeCreationListener(Drawing model, HistoryManager<Figure> history, JLabel infoLabel,
			int nbSteps) {
		super(model, history, infoLabel, nbSteps);
		tips[0] = new String("Cliquez pour initialiser la création du polygone");
		tips[1] = new String("Cliquez pour ajouter un sommet et cliquez le bouton droit pour finaliser la création");
		
		updateTip();
		
		System.out.println(getClassName() + "::" + getMethodName() + " created");
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		
		if (currentStep == 0 ) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				startAction(e);
				drawingModel.setstatus(Status.ADDED);
				System.out.println("initiating Polygon");
			}
		}
		else {
			Polygon polygon = (Polygon) currentFigure;
			switch(e.getButton()) {
			case MouseEvent.BUTTON1:
				polygon.addPoint(p.x,p.y);
				polygon.printPoint();
				break;
			case MouseEvent.BUTTON2 :
				polygon.removeLastPoint();
				break;
			case MouseEvent.BUTTON3:
				endAction(e);
				polygon.printPoint();
				break;
			}
		}
		drawingModel.update();
		updateTip();

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// Rien

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// Rien

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// Rien

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// Rien

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// Rien

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if(currentStep > 0) {
			if(currentFigure != null) {
				currentFigure.setLastPoint(e.getPoint());
			}
			else {
				System.err.println(getClass().getSimpleName() + getMethodName() + " : null figure");
			}
			drawingModel.update();
		}

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.MouseWheelEvent)
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// Rien

	}

}
