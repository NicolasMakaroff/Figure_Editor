package filters;

import figures.Figure;
import figures.enums.LineType;

public class LineFilter extends FigureFilter<LineType> {

	public LineFilter(LineType type) {
		super(type);
	}

	@Override
	public boolean test(Figure figure) {
		
		return element == LineType.fromStroke(figure.getStroke());
	}
}
