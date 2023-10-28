package util.adts;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 *
 * Class ArrayQListWithSelection that extends {@link AbsQListWithSelection} and
 * provides an implemention based in arrays.
 *
 * @param <E> A generic representing type of the elements of this list.
 */
public final class ArrayQListWithSelection<E> extends AbsQListWithSelection<E> {
	
	/**
	 * Empty constructor of this class that just calls the super() constructor of
	 * the super class.
	 */
	public ArrayQListWithSelection() {}
	
	/**
	 * Constructor that creates an {@link ArrayQListWithSelection} with the provided list as
	 * the current list of elements. This method is marked package-protected and is
	 * only used for JUnit tests.
	 * 
	 * @param initList the list to initialize this class with.
	 */
	ArrayQListWithSelection(List<E> initList) {
		super(initList);
	}

	@Override
	protected List<E> createList() {
		return new ArrayList<>();
	}
}
