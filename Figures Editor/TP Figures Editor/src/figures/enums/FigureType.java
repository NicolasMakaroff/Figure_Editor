package figures.enums;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;

import javax.swing.JLabel;

import figures.Circle;
import figures.Drawing;
import figures.Ellipse;
import figures.Figure;
import figures.Polygon;
import figures.Rectangle;
import figures.RoundRectangle;
import figures.listeners.creation.AbstractCreationListener;
import figures.listeners.creation.PolygonalShapeCreationListener;
import figures.listeners.creation.RectangularShapeCreationListener;
import figures.listeners.creation.RoundRectangleShapeCreationListener;
import history.HistoryManager;
import utils.Signature;

/**
 * Enumeration des différentes figures possibles
 * @author davidroussel
 */
public enum FigureType implements Signature
{
	/**
	 * Les différents types de figures
	 */
	CIRCLE, ELLIPSE, RECTANGLE, ROUNDED_RECTANGLE, POLYGON, NGON, STAR;

	/**
	 * Nombre de figures référencées ici (à changer si on ajoute des types de
	 * figures)
	 */
	public final static int NbFigureTypes = 7;

	/**
	 * Obtention d'une instance de figure correspondant au type
	 * @param stroke la césure du trait (ou pas de trait si null)
	 * @param edge la couleur du trait (ou pas de trait si null)
	 * @param fill la couleur de remplissage (ou pas de remplissage si null)
	 * @param x l'abcisse du premier point de la figure
	 * @param y l'ordonnée du premier point de la figure
	 * @return une nouvelle instance correspondant à la valeur de cet enum
	 * @throws AssertionError si la valeur de cet enum n'est pas prévue
	 */
	public Figure getFigure(BasicStroke stroke,
	                        Paint edge,
	                        Paint fill,
	                        Point2D p)
	    throws AssertionError
	{
		switch (this)
		{
			case CIRCLE:
				return new Circle(stroke,edge,fill,p,p);
			case ELLIPSE:
				return new Ellipse(stroke, edge, fill, p, p);
			case RECTANGLE:
				return new Rectangle(stroke, edge, fill,p,p);
			case ROUNDED_RECTANGLE:
				return new RoundRectangle(stroke, edge, fill, p, p, 30);
			case POLYGON:
				return new Polygon(stroke,edge,fill,p,p);
			case NGON:
				// TODO Enlever le message et renvoyer une nouvelle figure de type Ngon
				System.err.println(getClassName() + "::" + getMethodName()
				    + " for " + NGON + ": No such figure yet");
				return null;
			case STAR:
				// TODO Enlever le message et renvoyer une nouvelle figure de type Star
				System.err.println(getClassName() + "::" + getMethodName()
				    + " for " + STAR + ": No such figure yet");
				return null;
		}

		throw new AssertionError(getClassName() + "::" + getMethodName()
		    + " unknown assertion: " + this);
	}

	/**
	 * Obtention d'un CreationListener adequat pour la valeur de cet enum
	 * @param model le modèle de dessin à modifier
	 * @param history le gestionnaire d'historique pour les Undo/Redo
	 * @param tipLabel le label dans lequel afficher les conseils utilisateur
	 * @return une nouvelle instance de CreationListener adéquate pour le type
	 * de figure de cet enum.
	 * @throws AssertionError si la valeur de cet enum n'est pas prévue
	 */
	public AbstractCreationListener getCreationListener(Drawing model,
	                                                    HistoryManager<Figure> history,
	                                                    JLabel tipLabel)
	    throws AssertionError
	{
		switch (this)
		{
			case CIRCLE:
			case ELLIPSE:
			case RECTANGLE:
				return new RectangularShapeCreationListener(model, history, tipLabel);
			case ROUNDED_RECTANGLE:
				return new RoundRectangleShapeCreationListener(model, history, tipLabel);
			case POLYGON:
				return new PolygonalShapeCreationListener(model, history, tipLabel);
			case NGON:
				// TODO Enlever le message et renvoyer une nouveau CreationListener adapté aux Ngon
				System.err.println(getClassName() + "::" + getMethodName()
				    + " for " + NGON + ": No such listener yet");
				return null;
			case STAR:
				// TODO Enlever le message et renvoyer une nouveau CreationListener adapté aux Star
				System.err.println(getClassName() + "::" + getMethodName()
				    + " for " + STAR + ": No such listener yet");
				return null;
		}

		throw new AssertionError(getClassName() + "::" + getMethodName()
		    + " unknown assertion: " + this);
	}

	/**
	 * Représentation sous forme de chaine de caractères
	 * @return une chaine de caractère représentant la valeur de cet enum
	 * @throws AssertionError si la valeur de cet enum n'est pas prévue
	 */
	@Override
	public String toString() throws AssertionError
	{
		switch (this)
		{
			case CIRCLE:
				return new String("Circle");
			case ELLIPSE:
				return new String("Ellipse");
			case RECTANGLE:
				return new String("Rectangle");
			case ROUNDED_RECTANGLE:
				return new String("Rounded Rectangle");
			case POLYGON:
				return new String("Polygon");
			case NGON:
				return new String("Ngon");
			case STAR:
				return new String("Star");
		}

		throw new AssertionError(getClassName() + "::" + getMethodName()
		    + " unknown assertion: " + this);
	}

	/**
	 * Otention d'un tableau de chaine de caractères contenant l'ensemble des
	 * nom des figures
	 * @return un tableau de chaine de caractères contenant l'ensemble des nom
	 * des figures
	 */
	public static String[] stringValues()
	{
		FigureType[] values = FigureType.values();
		String[] stringValues = new String[values.length];

		for (int i = 0; i < stringValues.length; i++)
		{
			stringValues[i] = values[i].toString();
		}

		return stringValues;
	}

	/**
	 * Conversion d'un entier en FigureType
	 * @param i l'entier à convertir en FigureType
	 * @return le FigureType correspondant à l'entier
	 */
	public static FigureType fromInteger(int i)
	{
		switch (i)
		{
			case 0:
				return CIRCLE;
			case 1:
				return ELLIPSE;
			case 2:
				return RECTANGLE;
			case 3:
				return ROUNDED_RECTANGLE;
			case 4:
				return POLYGON;
			case 5:
				return NGON;
			case 6:
				return STAR;
			default:
				return POLYGON;
		}
	}
	/**
	 * Conversion en entier d'un type de figure
	 * @return un entier correspondant à l'index du type de figure
	 */
	public int intValue() throws AssertionError
	{
		switch (this)
		{
			case CIRCLE:
				return 0;
			case ELLIPSE:
				return 1;
			case RECTANGLE:
				return 2;
			case ROUNDED_RECTANGLE:
				return 3;
			case POLYGON:
				return 4;
			case NGON:
				return 5;
			case STAR:
				return 6;
		}

		throw new AssertionError(getClassName() + "::" + getMethodName()
		    + " unknown assertion: " + this);
	}
}
