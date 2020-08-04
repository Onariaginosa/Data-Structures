/**
 * 
 */
package intlist;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Ona Igbinedion
 * @author Elise Sawan
 * @author Ameya Melacheruvu
 */
public class IntListTests {

	/**
	 * Test method for {@link intlist.IntList#prepend(int)}.
	 */
	@Test
	public void testPrepend() {
		IntList Ona = new IntList();
		
		Ona.prepend(1);
		assertEquals("1 ", Ona.toString());
		
		Ona.prepend(2);
		Ona.prepend(3);
		assertEquals("3 2 1 ", Ona.toString());
		
		Ona.prepend(4);
		assertEquals("4 3 2 1 ", Ona.toString());
	}

	/**
	 * Test method for {@link intlist.IntList#insertAt(int, int)}.
	 */
	@Test
	public void testInsertAt() {
		IntList Elise = new IntList();
		
		Elise.insertAt(3,0);
		assertEquals("3 ", Elise.toString());
		
		Elise.insertAt(2, 1);
		Elise.insertAt(1, 2);
		assertEquals("3 2 1 ", Elise.toString());
		
		Elise.insertAt(4,3);
		assertEquals("3 2 1 4 ", Elise.toString());
		
		Elise.insertAt(88,2);
		assertEquals("3 2 88 1 4 ", Elise.toString());

	}

	/**
	 * Test method for {@link intlist.IntList#removeAll(int)}.
	 */
	@Test
	public void testRemoveAll() {
		IntList Ameya = new IntList();
		
		Ameya.append(1);
		Ameya.append(2);
		Ameya.append(2);
		Ameya.append(3);
		Ameya.append(4);
		Ameya.append(5);
		
		Ameya.removeAll(5);
		assertEquals("1 2 2 3 4 ", Ameya.toString());
		
		Ameya.removeAll(2);
		assertEquals("1 3 4 ", Ameya.toString());
		
		Ameya.removeAll(3);
		assertEquals("1 4 ", Ameya.toString());
		
		
		
	}

}
