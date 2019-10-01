/**
 * 
 */
package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import figures.enums.FigureType;
import utils.Signature;

/**
 * Classe de Rectangle pour les {@link Figure}
 * @author nicolas.makaroff
 * @uml.dependency supplier="java.awt.geom.Rectangle2D.Double"
 */
public class Rectangle extends Figure implements Signature{

	private static int counter = 0;
	
	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 */
	public Rectangle(BasicStroke stroke, Paint edge, Paint fill,Point2D topLeft,Point2D bottomRight) {
		super(stroke, edge, fill);
		instanceNumber=++counter;
		double x = topLeft.getX();
		double y = topLeft.getY();
		double w = bottomRight.getX() - x;
		double h = bottomRight.getY() - y;
		shape = new Rectangle2D.Double(x, y, w, h);
	}

	/**
	 * @param f
	 */
	public Rectangle(Rectangle rectangle) {
		super(rectangle);
		Rectangle2D oldRectangle = (Rectangle2D) rectangle.shape;
		shape = new Rectangle2D.Double(oldRectangle.getMinX(),
		                             oldRectangle.getMinY(),
		                             oldRectangle.getWidth(),
		                             oldRectangle.getHeight());
	}

	/* (non-Javadoc)
	 * @see figures.Figure#clone()
	 */
	@Override
	public Figure clone() {
		return new Rectangle(this);
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
			Rectangle e = (Rectangle) o;
			return ((Rectangle2D.Double) shape).equals(e.shape);
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
			Rectangle2D.Double rectangle = (Rectangle2D.Double) shape;
			double newWidth = p.getX() - rectangle.x;
			double newHeight = p.getY() - rectangle.y;
			rectangle.width = newWidth;
			rectangle.height = newHeight;
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
		Rectangle2D.Double rectangle = (Rectangle2D.Double) shape;
		translation.translate(cx, cy);
		rectangle.setFrame(rectangle.x - cx,
		                 rectangle.y - cy,
		                 rectangle.width,
		                 rectangle.height);
	}

	/* (non-Javadoc)
	 * @see figures.Figure#getCenter()
	 */
	@Override
	public Point2D getCenter() {
		Rectangle2D.Double rectangle = (Rectangle2D.Double) shape;
		Point2D center = new Point2D.Double(rectangle.getCenterX(),
		                                    rectangle.getCenterY());
		Point2D tCenter = new Point2D.Double();
		getTransform().transform(center, tCenter);

		return tCenter;
	}

	/* (non-Javadoc)
	 * @see figures.Figure#getType()
	 */
	@Override
	public FigureType getType() {
		return FigureType.RECTANGLE;
	}

}
