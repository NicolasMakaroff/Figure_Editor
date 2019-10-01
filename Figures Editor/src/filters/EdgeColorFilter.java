/**
 * 
 */
package filters;

import java.awt.Paint;

import figures.Figure;

/**
 * @author nicolasmakaroff
 *
 */
public class EdgeColorFilter extends FigureFilter<Paint> {
	
	public EdgeColorFilter(Paint paint) {
		super(paint);
	}
	
	public boolean test(Figure figure) {
		return figure.getEdgePaint().equals(element);
	}

}
