/**
 * 
 */
package figures.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JLabel;

import figures.Drawing;
import figures.Figure;
import history.HistoryManager;

/**
 * @author nicolasmakaroff
 *
 */
public class SelectionFigureListener extends AbstractFigureListener {

	/**
	 * @param model
	 * @param history
	 * @param infoLabel
	 * @param nbSteps
	 */
	
	
	public SelectionFigureListener(Drawing model, HistoryManager<Figure> history, JLabel infoLabel, int nbSteps) {
		super(model, history, infoLabel, 1);
		tips[0] = new String("Cliquez pour sélectionner-déselectionner une figure");
		updateTip();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		nextStep(); // inutile
		currentFigure = drawingModel.getFigureAt(e.getPoint());
		if (currentFigure != null){
			currentFigure.setSelected(!currentFigure.isSelected());
			drawingModel.updateSelection();
		}

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		nextStep(); 
		currentFigure = drawingModel.getFigureAt(e.getPoint());
		if (currentFigure != null){
			currentFigure.setSelected(currentFigure.isSelected());
			drawingModel.updateSelection();
		}

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
		// Rien

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.MouseWheelEvent)
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// Rien

	}

	/* (non-Javadoc)
	 * @see figures.listeners.AbstractFigureListener#startAction(java.awt.event.MouseEvent)
	 */
	@Override
	public void startAction(MouseEvent e) {
		// Rien

	}

	/* (non-Javadoc)
	 * @see figures.listeners.AbstractFigureListener#endAction(java.awt.event.MouseEvent)
	 */
	@Override
	public void endAction(MouseEvent e) {
		// Rien

	}

}
