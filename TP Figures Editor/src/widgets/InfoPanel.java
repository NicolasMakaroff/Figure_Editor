package widgets;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Paint;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import figures.Figure;
import figures.enums.FigureType;
import figures.enums.LineType;
import utils.IconFactory;
import utils.PaintFactory;

public class InfoPanel extends JPanel
{
	/**
	 * Serializable class must have a serial version UID
	 */
	private static final long serialVersionUID = -5637980381468626303L;

	/**
	 * Une chaine vide pour remplir les champs lorsque la souris n'est au dessus
	 * d'aucune figure
	 */
	private static final String emptyString = new String();

	/**
	 * Une icône vide pour remplir les chanmps avec icône lorsque la souris
	 * n'est au dessus d'aucune figure
	 */
	private static final ImageIcon emptyIcon = IconFactory.getIcon("None");

	/**
	 * Le formatteur à utiliser pour formater les coordonnés
	 */
	private final static DecimalFormat coordFormat = new DecimalFormat("000");

	/**
	 * Le label contenant le nom de la figure
	 */
	private JLabel lblFigureName;

	/**
	 * Le label contenant l'icône correspondant à la figure
	 */
	private JLabel lblTypeicon;

	/**
	 * La map contenant les différentes icônes des types de figures
	 */
	private Map<FigureType, ImageIcon> figureIcons;

	/**
	 * Le label contenant l'icône de la couleur de remplissage
	 */
	private JLabel lblFillcolor;

	/**
	 * Le label contenant l'icône de la couleur du contour
	 */
	private JLabel lblEdgecolor;

	/**
	 * Map contenant les icônes relatives aux différentes couleurs (de contour
	 * ou de remplissage)
	 */
	private Map<Paint, ImageIcon> paintIcons;

	/**
	 * Le label contenant le type de contour
	 */
	private JLabel lblStroketype;

	/**
	 * Map contenant les icônes relatives au différents types de traits de
	 * contour
	 */
	private Map<LineType, ImageIcon> lineTypeIcons;

	/**
	 * Le label contenant l'abcisse du point en haut à gauche de la figure
	 */
	private JLabel lblTlx;

	/**
	 * Le label contenant l'ordonnée du point en haut à gauche de la figure
	 */
	private JLabel lblTly;

	/**
	 * Le label contenant l'abcisse du point en bas à droite de la figure
	 */
	private JLabel lblBrx;

	/**
	 * Le label contenant l'ordonnée du point en bas à droite de la figure
	 */
	private JLabel lblBry;

	/**
	 * Le label contenant la largeur de la figure
	 */
	private JLabel lblDx;

	/**
	 * Le label contenant la hauteur de la figure
	 */
	private JLabel lblDy;

	/**
	 * Le label contenant l'abcisse du barycentre de la figure
	 */
	private JLabel lblCx;

	/**
	 * Le label contenant l'ordonnée du barycentre de la figure
	 */
	private JLabel lblCy;

	/**
	 * Create the panel.
	 */
	public InfoPanel()
	{
		// --------------------------------------------------------------------
		// Initialisation des maps
		// --------------------------------------------------------------------
		figureIcons = new HashMap<FigureType, ImageIcon>();
		for (int i = 0; i < FigureType.NbFigureTypes; i++)
		{
			FigureType type = FigureType.fromInteger(i);
			figureIcons.put(type, IconFactory.getIcon(type.toString()));
		}

		paintIcons = new HashMap<Paint, ImageIcon>();
		String[] colorStrings = { "Black", "Blue", "Cyan", "Green", "Magenta",
		    "None", "Orange", "Others", "Red", "White", "Yellow" };

		for (int i = 0; i < colorStrings.length; i++)
		{
			Paint paint = PaintFactory.getPaint(colorStrings[i]);
			if (paint != null)
			{
				paintIcons.put(paint, IconFactory.getIcon(colorStrings[i]));
			}
		}

		lineTypeIcons = new HashMap<LineType, ImageIcon>();
		for (int i = 0; i < LineType.NbLineTypes; i++)
		{
			LineType type = LineType.fromInteger(i);
			lineTypeIcons.put(type, IconFactory.getIcon(type.toString()));
		}

		// --------------------------------------------------------------------
		// Création de l'UI
		// --------------------------------------------------------------------
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);

	}

	/**
	 * Mise à jour de tous les labels avec les informations de figure
	 * @param figure la figure dont il faut extraire les informations
	 */
	public void updateLabels(Figure figure)
	{
		/*
		 * TODO Mise à jour des widgets en fonction de "figure"
		 * On pourra utiliser les maps :
		 * 	- figureIcons
		 * 	- paintIcons
		 * 	- lineTypeIcons
		 * pour obtnir les icônes désirées
		 */
	}

	/**
	 * Effacement de tous les labels
	 */
	public void resetLabels()
	{
		/*
		 * TODO Mise à vide des widgets lorqu'aucune figure ne se trouve sous
		 * le curseur de la souris
		 * On pourra utiliser "emptyString" et "emptyIcon" si besoin
		 */
	}
}
