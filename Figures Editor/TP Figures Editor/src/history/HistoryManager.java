package history;

import java.util.Iterator;
import java.util.Stack;

import utils.Signature;

/**
 * Classe permettant de gérer les piles de Undo et de Redo de E
 * @param E l'état à sauvegarder dans les piles
 * @author davidroussel
 */
public class HistoryManager<E extends Prototype<E>> implements Signature
{
	/**
	 * Le nombre maximum d'undo / redo
	 */
	private int size;

	/**
	 * L'originator dont on doit sauvegarder l'état.
	 * Permet de demander à l'originator de générer un memento ou de
	 * mettre en place un memento qu'on lui fournit
	 */
	private Originator<E> originator;

	/*
	 * TODO Compléter les attributs par les piles de undo et de redo
	 */
	private Stack<Memento<E>> undo;
	
	private Stack<Memento<E>> redo;

	/**
	 * Constructeur du manager de Undo/Redo
	 * @param origin l'originator dont on doit savegarder l'état
	 * @param size ne nombre maximum d'undos/redos à mémorsier
	 */
	public HistoryManager(Originator<E> origin, int size)
	{
		this.size = size;
		originator = origin;
		// TODO Compléter ...
		undo = new Stack<Memento<E>>();
		redo = new Stack<Memento<E>>();
		
	}

	@Override
	protected void finalize() throws Throwable
	{
		// TODO Compléter ...
		undo.clear();
		redo.clear();
		super.finalize();
	}

	/**
	 * Nombre d'éléments accumulés dans la pile de undo
	 * @return Le nombre d'éléments accumulés dans la pile de undo
	 */
	public int undoSize()
	{
		return undo.size();
		
	}

	/**
	 * Nombre d'éléments accumulés dans la pile de redo
	 * @return Le nombre d'éléments accumulés dans la pile de redo
	 */
	public int redoSize()
	{
		
		return redo.size();
		
	}

	/**
	 * Enregistre un {@link Memento} de l'{@link #originator} pour pouvoir
	 * le restituer par la suite.
	 */
	public void record()
	{
		// TODO Compléter ...
		undo.push(originator.createMemento());
	}

	/**
	 * Restitue le dernier Memento sauvegardé dans la pile des undo
	 * @return le dernier memento sauvegardé dans la pile des undo
	 * ({@link #undoStack}), ou bien null si celle-ci est vide.
	 * @post un {@link Memento} de l'{@link #originator} a été créé au préalable
	 * dans la pile des redo.
	 */
	public void undo()
	{
		if (!undo.isEmpty()) {
			redo.push(originator.createMemento());
			originator.setMemento(undo.pop());
		}
		else {
			originator.setMemento(null);
		}
	}

	/**
	 * Annule le dernier {@link Memento} enregistré dans la pile des undo.
	 * Lorsque l'action n'a pas modifié l'état (par exemple si elle a échoué)
	 */
	public void cancel()
	{
		// TODO Compléter ...
		undo.pop();
	}

	/**
	 * Restitue de dernier Memento sauvegardé dans la pile des redo
	 * @return Le dernier Memento sauvegardé dans la pile des redo
	 * ({@link #redoStack}) ou bien null si celle-ci est vide.
	 * @post un {@link Memento} de l'{@link #originator} a été créé au préalable
	 * dans la pile des undo.
	 */
	public void redo()
	{
		if(!redo.isEmpty()) {
			undo.push(originator.createMemento());
			originator.setMemento(redo.pop());
		}
		else {
			originator.setMemento(null);
		}
	}

	/**
	 * Affichage du contenu des piles {@link #undoStack} et {@link #redoStack}
	 * @return Une chaîne permettant d'afficher le contenu des piles (utile
	 * pour débuguer)
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append(super.toString());
		sb.append("[" + String.valueOf(size) + "] :\nUndo = {");
		// TODO Compléter ...
		for (Iterator<Memento<E>> it = undo.iterator(); it.hasNext();)
		{
			sb.append(it.next());
			if (it.hasNext())
			{
				sb.append(", ");
			}
}
		sb.append("},\nRedo = {");
		// TODO Compléter ...
		for (Iterator<Memento<E>> it = redo.iterator(); it.hasNext();)
		{
			sb.append(it.next());
			if (it.hasNext())
			{
				sb.append(", ");
			}
}
		sb.append("}");
		return sb.toString();
	}
}
