package util.adts;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArrayQListWithSelectionTests {
	
	private List<Integer> initList;
	private ArrayQListWithSelection<Integer> qList;
	
	@BeforeEach
	void setup() {
		this.initList = new ArrayList<>(Arrays.asList(2, 3, 7, 10, 2, 45));
		this.qList = new ArrayQListWithSelection<>(initList);
	}
	
	@Test
	@DisplayName("Checks size: empty and filled list")
	public void testSize() {
		assertEquals(this.initList.size(), this.qList.size());
		ArrayQListWithSelection<Integer> qList2 = new ArrayQListWithSelection<>();
		assertEquals(0, qList2.size());
	}
	
	@Test
	@DisplayName("Checks add: adds an element and checks if it gets selected")
	public void testAdd() {
		int initSize = this.qList.size();
		this.qList.add(2);
		int expectedSize = initSize + 1;
		assertEquals(expectedSize, this.qList.size());
		assertTrue(this.qList.someSelected());
		assertEquals(2, this.qList.getSelected().intValue());
	}
	
	@Test
	@DisplayName("Checks get: calls get for every element")
	public void testGet() {
		for (int i = 0; i < this.initList.size(); i++) {
			assertEquals(this.initList.get(i), this.qList.get(i));
		}
	}
	
	@Test
	@DisplayName("Checks iterator: compares the iterator of ArrayQListWithSelection with the list")
	public void testIterator() {
		assertIterableEquals(this.initList, this.qList);
	}
	
	@Test
	@DisplayName("Checks select: checks if an element gets selected an his index")
	public void testSelect() {
		this.qList.select(0);
		assertTrue(this.qList.someSelected());
		assertEquals(0, this.qList.getIndexSelected());
	}
	
	@Test
	@DisplayName("Checks someSelected: checks if list starts with no element selected")
	public void testSomeSelected() {
		assertFalse(this.qList.someSelected());
	}
	
	@Test
	@DisplayName("Checks indexSelected: checks if it returns the correct index after selection")
	public void testIndexSelected() {
		this.qList.select(0);
		assertEquals(0, this.qList.getIndexSelected());
		this.qList.select(2);
		assertEquals(2, this.qList.getIndexSelected());
	}
	
	@Test
	@DisplayName("Checks next: checks if it goes to the next element or the elements gets unselected")
	public void testNext() {
		this.qList.select(this.qList.size() - 2);
		this.qList.next();
		assertTrue(this.qList.someSelected());
		int expectedIndex = this.qList.size() - 1;
		assertEquals(expectedIndex, this.qList.getIndexSelected());
		this.qList.next();
		assertFalse(this.qList.someSelected());
	}
	
	@Test
	@DisplayName("Checks next: checks if it goes to the previous element or the elements gets unselected")
	public void testPrevious() {
		this.qList.select(1);
		this.qList.previous();
		assertTrue(this.qList.someSelected());
		assertEquals(0, this.qList.getIndexSelected());
		this.qList.previous();
		assertFalse(this.qList.someSelected());
	}
	
	@Test
	@DisplayName("Checks remove: checks if element is removed and unselected")
	public void testRemove() {
		this.qList.remove();
		assertEquals(this.initList.size(), this.qList.size());
		this.qList.select(0);
		int expectedSize = this.initList.size() - 1;
		this.qList.remove();
		assertEquals(expectedSize, this.qList.size());
		assertFalse(this.qList.someSelected());
	}
	
	@Test
	@DisplayName("Checks getSelected: checks if getSelected is the element at selected index")
	public void testGetSelected() {
		qList.select(1);
		assertEquals(3, qList.getSelected().intValue());
		qList.select(0);
		assertEquals(2, qList.getSelected().intValue());
	}	
}
