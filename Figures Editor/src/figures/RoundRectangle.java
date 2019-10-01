/**
 * 
 */
package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

import figures.enums.FigureType;
import utils.Signature;

/**
 * @author nicolas.makaroff
 *
 */
public class RoundRectangle extends Figure implements Signature {

	private static int counter = 0;
	
	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 */
	public RoundRectangle(BasicStroke stroke, Paint edge, Paint fill,Point2D topLeft, Point2D bottomRight,double arcwidth) {
		super(stroke, edge, fill);
		instanceNumber=++counter;
		double x = topLeft.getX();
		double y = topLeft.getY();
		double w = bottomRight.getX() - x;
		double h = bottomRight.getY() - y;
		shape = new RoundRectangle2D.Double(x, y, w, h,arcwidth,arcwidth);
	}

	/**
	 * @param f
	 */
	public RoundRectangle(RoundRectangle roundrectangle) {
		super(roundrectangle);
		RoundRectangle2D oldroundrectangle = (RoundRectangle2D) roundrectangle.shape;
		shape = new RoundRectangle2D.Double(oldroundrectangle.getMinX(),
		                             oldroundrectangle.getMinY(),
		                             oldroundrectangle.getWidth(),
		                             oldroundrectangle.getHeight(),
		                             oldroundrectangle.getArcHeight(),
		                             oldroundrectangle.getArcWidth());
	}

	/* (non-Javadoc)
	 * @see figures.Figure#clone()
	 */
	@Override
	public Figure clone() {
		return new RoundRectangle(this);
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
			RoundRectangle e = (RoundRectangle) o;
			return ((RoundRectangle2D.Double) shape).equals(e.shape);
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
			RoundRectangle2D.Double roundrectangle = (RoundRectangle2D.Double) shape;
			double newWidth = p.getX() - roundrectangle.x;
			double newHeight = p.getY() - roundrectangle.y;
			roundrectangle.width = newWidth;
			roundrectangle.height = newHeight;
		}
		else
		{
			System.err.println(getClassName() + "::" + getMethodName()
			    + " null shape");
		}

	}

	public void setAngle(Point2D point) {
		if (shape != null) {
			
			RoundRectangle2D.Double roundrectangle = (RoundRectangle2D.Double) shape;
			double bottomRightX = roundrectangle.getMaxX();
			double bottomRightY = roundrectangle.getMaxY();
			double x = point.getX();
			double y = point.getY();
			
			if (x <= bottomRightX) {
				if (y > bottomRightY) {
					roundrectangle.arcwidth = bottomRightX - x;
					roundrectangle.archeight = roundrectangle.arcwidth; 
				}
				else {
					roundrectangle.arcwidth = 0;
					roundrectangle.archeight = 0;
				}
			}
			else { // x > bottomrightX
				if (y < bottomRightY) {
					roundrectangle.arcwidth = bottomRightY - y;
					roundrectangle.archeight = roundrectangle.arcwidth;
				}
				else {
					roundrectangle.arcwidth = 0;
					roundrectangle.archeight = 0;
				}
			}
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
		RoundRectangle2D.Double roundrectangle = (RoundRectangle2D.Double) shape;
		translation.translate(cx, cy);
		roundrectangle.setFrame(roundrectangle.x - cx,
		                 roundrectangle.y - cy,
		                 roundrectangle.width,
		                 roundrectangle.height);
	}

	/* (non-Javadoc)
	 * @see figures.Figure#getCenter()
	 */
	@Override
	public Point2D getCenter() {
		RoundRectangle2D.Double roundrectangle = (RoundRectangle2D.Double) shape;
		Point2D center = new Point2D.Double(roundrectangle.getCenterX(),
		                                    roundrectangle.getCenterY());
		Point2D tCenter = new Point2D.Double();
		getTransform().transform(center, tCenter);

		return tCenter;
	}

	/* (non-Javadoc)
	 * @see figures.Figure#getType()
	 */
	@Override
	public FigureType getType() {
		return FigureType.ROUNDED_RECTANGLE;
	}

}
