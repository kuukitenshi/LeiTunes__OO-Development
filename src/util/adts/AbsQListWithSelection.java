package util.adts;

import java.util.Iterator;
import java.util.List;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 *
 * Abstract class AbsQListWithSelection that implements {@link QListWithSelection}, used 
 * to provide a skeleton implementation of the interface.
 *
 * @param <E> A generic representing type of the elements of this list.
 */
public abstract class AbsQListWithSelection<E> implements QListWithSelection<E> {

	private final List<E> list;
	private int selectedIndex = -1;

	/**
	 * Constructor that creates an AbsQListWithSelection, and calls the
	 * method {@link AbsQListWithSelection#createList()} to initiate the
	 * creation of the list by the sub class.
	 */
	protected AbsQListWithSelection() {
		this.list = createList();
	}
	
	/**
	 * Constructor that creates an AbsQListWithSelection with
	 * the specified list. This constructor is marked package-protected
	 * and only used in JUnit tests.
	 * 
	 * @param list the list to initialize with class with.
	 */
	AbsQListWithSelection(List<E> list) {
		this.list = list;
	}

	/**
	 * Protected abstract method to be implemented by classes that extends
	 * {@link AbsQListWithSelection} that returns the list used to initialize
	 * the abstract class.
	 * 
	 * @return the list used to initialize the abstract class.
	 */
	protected abstract List<E> createList();

	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public void add(E e) {
		this.list.add(e);
		this.selectedIndex = size() - 1;
	}

	@Override
	public E get(int i) {
		return list.get(i);
	}

	@Override
	public Iterator<E> iterator() {
		return this.list.iterator();
	}

	@Override
	public void select(int i) {
		this.selectedIndex = i;
	}

	@Override
	public boolean someSelected() {
		return this.selectedIndex != -1;
	}

	@Override
	public int getIndexSelected() {
		return this.selectedIndex;
	}

	@Override
	public void next() {
		this.selectedIndex = this.selectedIndex + 1 >= size() ? -1 : this.selectedIndex + 1;
	}

	@Override
	public void previous() {
		this.selectedIndex = this.selectedIndex - 1 < 0 ? -1 : this.selectedIndex - 1; 
	}

	@Override
	public void remove() {
		if(someSelected()) {
			this.list.remove(this.selectedIndex);
			this.selectedIndex = -1;
		}
	}

	@Override
	public E getSelected() {
		return get(this.selectedIndex);
	}
}
