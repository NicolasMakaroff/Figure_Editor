/**
 * 
 */
package filters;

import figures.Figure;
import figures.enums.FigureType;

/**
 * @author nicolasmakaroff
 *
 */
public class ShapeFilter extends FigureFilter<FigureType> {

	public ShapeFilter(FigureType type) {
		super(type);
	}

	@Override
	public boolean test(Figure figure) {
		
		return element == figure.getType();
	}
	
}
