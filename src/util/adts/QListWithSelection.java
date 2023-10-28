package util.adts;


/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 *
 * Interface QListWithSelection that extends {@link QList} used to represent lists
 * that can have at most one element selected.
 *
 * @param <E> A generic representing type of the elements of this list.
 */
public interface QListWithSelection<E> extends QList<E> {

	/**
	 * Selects the element at the specified index.
	 * 
	 * @param i the index of the element to be selected
	 * @requires {@code 0 <= i <= size() - 1}
	 * @ensures {@code someSelected() && getIndexSelected() == i}
	 */
	void select(int i);

	/**
	 * Adds an element at the end of the list, and selects the added element.
	 * 
	 * @param e 	the element to be added
	 * @requires 	{@code e != null}
	 * @ensures 	{someSelected() && getIndexSelected() == size() - 1}
	 */
	@Override
	void add(E e);

	/**
	 * Checks if there is any element currently selected.
	 * 
	 * @return true if there is an element selected, false otherwise.
	 */
	boolean someSelected();

	/**
	 * Returns the index of the currently selected element.
	 * 
	 * @requires 	{@code someSelected()}.
	 * @ensures 	{@code 0 <= \result < size()}.
	 * @return 		the index of the currently selected element.
	 */
	int getIndexSelected();

	/**
	 * Selects the element after the currently selected element, if any. Otherwise
	 * unselects the currently selected element.
	 * 
	 * @requires {@code someSelected()}
	 */
	void next();

	/**
	 * Selects the element before the currently selected element, if any. Otherwise
	 * unselects the currently selected element.
	 * 
	 * @requires {@code someSelected()}
	 */
	void previous();

	/**
	 * Removes the currently selected element from the list, if any. Otherwise does nothing.
	 * 
	 * @ensures {@code !someSelected() && size() <= \old size()}
	 */
	void remove();

	/**
	 * Returns the currently selected element in the list.
	 * 
	 * @requires {@code someSelected()}.
	 * @ensures  {@code \result != null}
	 * @return   the currently selected element.
	 */
	E getSelected();
}
