/**
 * 
 */
package filters;

import java.awt.Color;
import java.awt.Paint;

import figures.Figure;

/**
 * @author nicolasmakaroff
 *
 */
public class EdgeColorFilter extends FigureFilter<Paint> {
	
	public EdgeColorFilter() {
		this.element=Color.black;
	}
	
	public boolean test(Figure figure) {
		return figure.getEdgePaint().equals(element);
	}

}
