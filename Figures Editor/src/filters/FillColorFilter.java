/**
 * 
 */
package filters;

import figures.Figure;

import java.awt.Paint;

/**
 * @author nicolas.makaroff
 *
 */
public class FillColorFilter<Paint> extends FigureFilter<Paint> {

	/**
	 * constructeur 
	 * @param paint la couleur
	 */
	public FillColorFilter(Paint paint) {
		super(paint);
	}
	
	public boolean test(Figure figure) {
		return figure.getFillPaint().equals(element);
	}
	
}
