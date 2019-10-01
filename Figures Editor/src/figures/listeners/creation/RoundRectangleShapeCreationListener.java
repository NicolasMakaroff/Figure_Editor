/**
 * 
 */
package figures.listeners.creation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JLabel;

import figures.Drawing;
import figures.Figure;
import history.HistoryManager;
import figures.RoundRectangle;

/**
 * @author nicolasmakaroff
 *
 */
public class RoundRectangleShapeCreationListener extends AbstractCreationListener {

	/**
	 * @param model
	 * @param history
	 * @param infoLabel
	 * @param nbSteps
	 */
	public RoundRectangleShapeCreationListener(Drawing model, HistoryManager<Figure> history, JLabel infoLabel,
			int nbSteps) {
		super(model, history, infoLabel, nbSteps);
		tips[0] = new String("Faites un clique gauche et bougez la souris pour créer le rectangle");
		tips[1] = new String("Relâcher pour finaliser la dimension de la figure");
		tips[2] = new String("Puis faites de nouveau un clic gauche pour finaliser les dimensions des angles");
		
		updateTip();
		
		System.out.println(getClassName() + "::" + getMethodName() + " created");
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if ((e.getButton() == MouseEvent.BUTTON1) && (currentStep == 2))
		{
			endAction(e);
		}

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if ((e.getButton() == MouseEvent.BUTTON1) && (currentStep == 0))
		{
			startAction(e);
		}

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if((e.getButton() == MouseEvent.BUTTON1) && (currentStep == 1)){
			nextStep();
		}

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
		
		if (currentStep == 1)
		{
			// AbstractFigure figure = drawingModel.getLastFigure();
			if (currentFigure != null)
			{
				currentFigure.setLastPoint(e.getPoint());
			}
			else
			{
				System.err.println(getClassName() + "::" + getMethodName()
				    + " : null figure");
			}

			drawingModel.update();
		}

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		
		if ((e.getButton() == MouseEvent.NOBUTTON) && (currentStep == 2))
		{
			
			((RoundRectangle) currentFigure).setAngle(e.getPoint());
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
