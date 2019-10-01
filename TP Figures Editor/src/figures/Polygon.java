/**
 * 
 */
package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.awt.geom.GeneralPath;

import figures.enums.FigureType;
import utils.Signature;

/**
 * @author nicolas.makaroff
 *
 */
public class Polygon extends Figure implements Signature {

	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 */
	public Polygon(BasicStroke stroke, Paint edge, Paint fill,Point2D p ) {
		super(stroke, edge, fill);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param f
	 */
	public Polygon(Figure f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see figures.Figure#clone()
	 */
	@Override
	public Figure clone() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see figures.Figure#setLastPoint(java.awt.geom.Point2D)
	 */
	@Override
	public void setLastPoint(Point2D p) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see figures.Figure#normalize()
	 */
	@Override
	public void normalize() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see figures.Figure#getCenter()
	 */
	@Override
	public Point2D getCenter() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see figures.Figure#getType()
	 */
	@Override
	public FigureType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
