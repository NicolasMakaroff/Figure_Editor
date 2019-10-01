/**
 * 
 */
package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.ArrayList;
import java.awt.geom.GeneralPath;
import figures.enums.FigureType;
import utils.Signature;

/**
 * @author nicolas.makaroff
 *
 */
public class Polygon extends Figure implements Signature {

	/**
	 * Le compteur d'instance des ellipses. Utilisé pour donner un numéro
	 * d'instance après l'avoir incrémenté
	 */
	private static int counter=0;
	
	/**
	 * Collection des points d'un polygon 
	 */
	protected Collection<Point2D> points;
	
	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 */
	public Polygon(BasicStroke stroke, Paint edge, Paint fill,Point2D ... pts ) {
		super(stroke, edge, fill);
		instanceNumber = ++counter;
		//points = new ArrayList<Point2D>();
		//Point2D[] ptsArray  = points.toArray(new Point2D[0]);
		//for (Point2D p :pts) {
		//	points.add(p);
		//}
		GeneralPath Polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
		        pts.length);
		Polygon.moveTo(pts[0].getX(),pts[0].getY());
		for (int index = 1; index < pts.length; index++) {
		      Polygon.lineTo(pts[index].getX(), pts[index].getY());
		    }
		
		Polygon.closePath();
		shape = new GeneralPath.Double();
		//shape = polygon;
		// shape = Path2D.Double(Polygon);
	}

	/**
	 * @param f
	 */
	public Polygon(Polygon polygon) {
		super(polygon);
		GeneralPath oldPolygon = (GeneralPath) polygon.shape;
		shape = new GeneralPath(oldPolygon);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see figures.Figure#clone()
	 */
	@Override
	public Figure clone() {
		return new Polygon(this);
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
			Polygon e = (Polygon) o;
			return ((GeneralPath) shape).equals(e.shape);
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
			GeneralPath polygon = (GeneralPath) shape;
			double newWidth = p.getX() - polygon.getBounds2D().getX();
			double newHeight = p.getY() - polygon.getBounds2D().getY();
			polygon.getBounds2D().setFrame(polygon.getBounds2D().getX(),polygon.getBounds2D().getY(), newWidth, newHeight);
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
		GeneralPath polygon = (GeneralPath) shape;
		translation.translate(cx, cy);
		polygon.getBounds2D().setFrame(polygon.getBounds().getX() - cx,
		                 polygon.getBounds().getY() - cy,
		                 polygon.width,
		                 polygon.height);

	}

	/* (non-Javadoc)
	 * @see figures.Figure#getCenter()
	 */
	@Override
	public Point2D getCenter() {
		GeneralPath polygon = (GeneralPath) shape;
		Point2D center = new Point2D.Double(polygon.getBounds2D().getCenterX(),
		                                    polygon.getBounds2D().getCenterY());
		Point2D tCenter = new Point2D.Double();
		getTransform().transform(center, tCenter);

		return tCenter;
	}

	/* (non-Javadoc)
	 * @see figures.Figure#getType()
	 */
	@Override
	public FigureType getType() {
		return  FigureType.POLYGON;
	}

}
