/**
 * 
 */
package figures.listeners.creation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;

import javax.swing.JLabel;

import figures.NGon;
import figures.Drawing;
import figures.Figure;
import history.HistoryManager;

/**
 * @author nicolasmakaroff
 *
 */
public class NGonShapeCreationListener extends AbstractCreationListener {
	
	/**
	 * Nombre initial de pics avant le choix de l'utilisateur
	 */
	private int NbPics;
	
	/**
	 * Point de fin d'action pour le nb de pics en fonction de cette position lors du mouvement de la roulette
	 */
	private Point2D releasedPoint;
	
	/**
	 * distance à parcourir pour ajouter un bras au NGon
	 */
	private static final double ajoutdifference = 25;

	/**
	 * @param model
	 * @param history
	 * @param infoLabel
	 * @param nbSteps
	 */
	public NGonShapeCreationListener(Drawing model, HistoryManager<Figure> history, JLabel infoLabel, int nbSteps) {
		super(model, history, infoLabel, nbSteps);
		tips[0] = new String("Cliquez et bougez la souris pour commencer la figure");
		tips[1] = new String("Relâcher pour finaliser la dimension du NGon");
		tips[2] = new String("Bougez la souris pour choisir le nombre de pics et cliquez pour finaliser la figure");
		
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
			NbPics = ((NGon) currentFigure).getNbPics();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if((e.getButton() == MouseEvent.BUTTON1) && (currentStep == 1)) {
			releasedPoint = e.getPoint();
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
		if(currentStep == 1) {
			currentFigure.setLastPoint(e.getPoint());
			drawingModel.update();
		}

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if (currentStep == 2) {
			NGon ngon = (NGon) currentFigure;
			Point2D point = e.getPoint();
			double difference = point.getY() - Double.valueOf(releasedPoint.getY()).intValue(); 
			double NbPicsDifference = difference / ajoutdifference ;
			double newNbPics = NbPics + NbPicsDifference;
			System.out.println("Difference = " + difference);
			System.out.println(" Nb Pics difference = " + NbPicsDifference);
			System.out.println("new nb of pics = " + newNbPics);
			if((nbPicsDifference != 0) && (newNbPics <= NGon.maxNbPics) && (newNbPics >= NGon.minNbPics)) {
				ngon.setNbPics(newNbPics);
				drawingModel.update();
			}
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
