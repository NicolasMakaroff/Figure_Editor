/**
 * 
 */
package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import figures.enums.FigureType;
import utils.Signature;

/**
 * Classe de Cercle pour les {@link Figure}
 * @author nicolas.makaroff
 * @uml.dependency supplier="java.awt.geom.Ellipse2D.Double"
 */
public class Circle extends Figure implements Signature{

	private static int counter = 0;
	
	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 */
	public Circle(BasicStroke stroke, Paint edge, Paint fill, Point2D topLeft, Point2D bottomRight) {
		super(stroke, edge, fill);
		instanceNumber = ++counter;
		double x = topLeft.getX();
		double y = topLeft.getY();
		double w = bottomRight.getX() - x;
		shape = new Ellipse2D.Double(x, y, w, w);
	}

	/**
	 * @param f
	 */
	public Circle(Circle circle) {
		super(circle);
		Ellipse2D oldCircle = (Ellipse2D) circle.shape;
		shape = new Ellipse2D.Double(oldCircle.getMinX(),
		                             oldCircle.getMinY(),
		                             oldCircle.getWidth(),
		                             oldCircle.getHeight());
	}

	/* (non-Javadoc)
	 * @see figures.Figure#clone()
	 */
	@Override
	public Figure clone() {
		return new Circle(this);
	}

	/**
	 * Comparaison de deux figures
	 * @param Object o l'objet à comparer
	 * @return true si obj est une figure de même type et que son contenu est
	 * identique
	 */
	@Override
	public boolean equals(Object o)
	{
		if (super.equals(o))
		{
		Circle e = (Circle) o;
			return ((Ellipse2D.Double) shape).equals(e.shape);
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see figures.Figure#setLastPoint(java.awt.geom.Point2D)
	 */
	@Override
	public void setLastPoint(Point2D p) {
		if (shape != null)
		{
			Ellipse2D.Double circle = (Ellipse2D.Double) shape;
			double newWidth = p.getX() - circle.x;
			double newHeight = p.getY() - circle.y;
			circle.width = newWidth;
			circle.height = newHeight;
		}
		else
		{
			System.err.println(getClassName() + "::" + getMethodName()
			    + " null shape");
		}

	}

	/* (non-Javadoc)
	 * @see figures.Figure#normalize()
	 */
	@Override
	public void normalize() {
		Point2D center = getCenter();
		double cx = center.getX();
		double cy = center.getY();
		Ellipse2D.Double circle = (Ellipse2D.Double) shape;
		translation.translate(cx, cy);
		circle.setFrame(circle.x - cx,
		                 circle.y - cy,
		                 circle.width,
		                 circle.height);

	}

	/* (non-Javadoc)
	 * @see figures.Figure#getCenter()
	 */
	@Override
	public Point2D getCenter() {
		Ellipse2D.Double circle = (Ellipse2D.Double) shape;
		Point2D center = new Point2D.Double(circle.getCenterX(),
		                                    circle.getCenterY());
		Point2D tCenter = new Point2D.Double();
		getTransform().transform(center, tCenter);

		return tCenter;
	}

	/* (non-Javadoc)
	 * @see figures.Figure#getType()
	 */
	@Override
	public FigureType getType() {
		return FigureType.CIRCLE;
	}

}
