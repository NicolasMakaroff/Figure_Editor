package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Point;
import java.awt.geom.Point2D;

import figures.enums.FigureType;

/**
 * Une classe représentant les ligne polygonales composées de 2 ou + de points
 * @author nicolasmakaroff
 */
public class Polygon extends Figure
{
	
	private static int counter = 0;

	/**
	 
	 * @param stroke le style de la ligne
	 * @param edgeColor la couleur de la ligne
	 * @param fillColor la couleur de remplissage
	 * @param p le premier point de la ligne
	 * @param p2 le second point de la ligne
	 */
	public Polygon(BasicStroke stroke, Paint edgeColor, Paint fillColor,
		Point2D p, Point2D p2)
	{
		super(stroke, edgeColor, fillColor);
		instanceNumber = ++counter;

		java.awt.Polygon polygon = new java.awt.Polygon();
		polygon.addPoint((int)p.getX(),(int) p.getY());
		polygon.addPoint((int)p2.getX(),(int)p2.getY());
		shape = polygon;
	}

	/**
	 * Constructeur de copie assurant une copie distincte du polygone
	 * @param poly le polygone à copier
	 */
	public Polygon(Polygon poly)
	{
		super(poly);
		java.awt.Polygon ancienPolygon = (java.awt.Polygon) poly.shape;
		int npoints = ancienPolygon.npoints;
		int[] xPoints = new int[npoints];
		int[] yPoints = new int[npoints];
		for (int i = 0; i < npoints; i++){
			xPoints[i] = ancienPolygon.xpoints[i];
			yPoints[i] = ancienPolygon.ypoints[i];
		}
		shape = new java.awt.Polygon(xPoints, yPoints, npoints);
	}

	/**
	 * Création d'une copie distincte de la figure
	 * @see figures.Figure#clone()
	 */
	@Override
	public Figure clone()
	{
		return new Polygon(this);
	}

	/**
	 * Ajout d'un point au polygone
	 * @param x l'abcisse du point à ajouter
	 * @param y l'ordonnée du point à ajouter
	 */
	public void addPoint(int x, int y)
	{
		java.awt.Polygon polygon = (java.awt.Polygon) shape;
		polygon.addPoint(x, y);
	}

	/**
	 * Suppression du dernier point du polygone.
	 * Uniquement s'il y en a plus d'un
	 */
	public void removeLastPoint()
	{
		java.awt.Polygon poly = (java.awt.Polygon) shape;

		if (poly.npoints > 1)
		{
			
			int[] xs = new int[poly.npoints-1];
			int[] ys = new int[poly.npoints-1];
			for (int i = 0; i < xs.length; i++){
				xs[i] = poly.xpoints[i];
				ys[i] = poly.ypoints[i];
			}
			poly.reset();
			for (int i = 0; i < xs.length; i++){
				poly.addPoint(xs[i], ys[i]);
			}
		}
	}

	/**
	 * Déplacement du dernier point du polygone
	 * @param p la position du dernier point
	 * @see lines.AbstractLine#setLastPoint(Point2D)
	 */
	@Override
	public void setLastPoint(Point2D p)
	{
		java.awt.Polygon polygon = (java.awt.Polygon) shape;
		int lastIndex = polygon.npoints-1;
		if (lastIndex >= 0){
			polygon.xpoints[lastIndex] = Double.valueOf(p.getX()).intValue();
			polygon.ypoints[lastIndex] = Double.valueOf(p.getY()).intValue();
		}
	}

	/**
	 * Calcule le barycentre des points du polygone (sans transformations)
	 * @return le barycentre des points du polygone
	 */
	protected Point2D computeCenter()
	{
		java.awt.Polygon polygon = (java.awt.Polygon) shape;
		double[] center = {0.0, 0.0};
		if (polygon.npoints > 0){
			for (int i = 0; i < polygon.npoints; i++){
				center[0] += polygon.xpoints[i];
				center[1] += polygon.ypoints[i];
			}
			center[0] /= polygon.npoints;
			center[1] /= polygon.npoints;
		}
		return new Point2D.Double(center[0], center[1]);
	}

	/**
	 * Obtention du barycentre de la figure.
	 * @return le point correspondant au barycentre de la figure
	 */
	@Override
	public Point2D getCenter()
	{
		Point2D center = computeCenter();
		Point2D Center2 = new Point2D.Double();
		getTransform().transform(center,Center2);
		return Center2;
	}

	/**
	 * Normalise une figure de manière à exprimer tous ses points par rapport
	 * à son centre, puis transfère la position réelle du centre dans l'attribut
	 * {@link #translation}
	 */
	@Override
	public void normalize()
	{
		System.out.println("Polygon Normalize");
		Point2D center = computeCenter();
		double cx = center.getX();
		double cy = center.getY();
		translation.setToTranslation(cx, cy);
		java.awt.Polygon polygon = (java.awt.Polygon) shape;
		if (polygon.npoints > 0){
			int[] nX = new int[polygon.npoints];
			int[] nY = new int[polygon.npoints];
			for (int i = 0; i < polygon.npoints; i++){
				nX[i] = polygon.xpoints[i] - Double.valueOf(cx).intValue();
				nY[i] = polygon.ypoints[i] - Double.valueOf(cy).intValue();
			}
			polygon.reset();

			for (int i = 0; i < nX.length; i++){
				polygon.addPoint(nX[i], nY[i]);
			}
		}
	}

 	/**
 	 * Accesseur du type de figure selon {@link FigureType}
 	 * @return le type de figure
 	 */
 	@Override
	public FigureType getType()
 	{
 		return FigureType.POLYGON;
 	}

 	public void printPoints()
 	{
 		System.out.print(this + " ");
		java.awt.Polygon polygon = (java.awt.Polygon) shape;
		if (polygon.npoints > 0){
			for (int i = 0; i < polygon.npoints; i++){
				System.out.print("(" + polygon.xpoints[i] + ", " + polygon.ypoints[i] + ")");
			}
		}
		System.out.print("[" + computeCenter() + "]");
 		System.out.println();
 	}
}
