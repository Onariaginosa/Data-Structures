/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SentUtils.java
 *  Purpose       :  To do the classwork in order to not fail
 *  @author       :  Ona Igbinedion
 *  @author       :  Ameya Mellacheruvu
 *  @author       :  Elise Sawan
 *  Date written  :  2019-09-23
 *  Description   :  Created methods for the IntList class
 *  Notes         :  None right now.  We'll add some as they occur.
 *  Warnings      :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2019-09-23  all authors   Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package intlist;

import static org.junit.Assert.assertEquals;

public class IntList {

    // Fields
    private int[] items;
    private int   size;
    private static final int START_SIZE = 8;
    
    /**
     * Initializes a new IntList with the given class constant
     * START_SIZE, which can be expanded through dynamic
     * allocation as need arises
     */
    
    public static void main (String[] args) {
		}
    
    
    IntList () {
        items = new int[START_SIZE];
        size  = 0;
    }

    /**
     * Returns the int at the specified index
     * @param The index of the IntList expected
     * @return The value at that index
     */
    public int getAt(int index) {
        indexValidityCheck(index);
        return items[index];
    }

    /**
     * Adds the given item toAdd to the end of the
     * IntList, expanding its capacity as needed
     * @param toAdd The int to add
     */
    public void append(int toAdd) {
        checkAndGrow();
        items[size] = toAdd;
        size++;
    }
    
    /**
     * Adds the given item toAdd to the start of the
     * IntList, expanding its capacity as needed
     * @param toAdd The int to add
     */
    public void prepend (int toAdd) {
        checkAndGrow();
        int[] newItems = new int[items.length];
        newItems[0] = toAdd;
        for (int i = 0; i < items.length - 1; i++) {
            newItems[i+1] = items[i];
        }
        items = newItems; 
        size++;
        
    }

    /**
     * Adds the given item toAdd to the index specified
     * in the IntList, expanding its capacity as needed
     * @param toAdd The int to add
     * @param index The index at which to add it
     */
    public void insertAt (int toAdd, int index) {
    	checkAndGrow();
        int[] newItems = new int[items.length];
        for (int i = 0; i < index; i++) {
            newItems[i] = items[i];
        }
        newItems[index] = toAdd;
        for (int i = index; i < items.length -1; i++) {
            newItems[i+1] = items[i];
        }
        size ++;
        items = newItems; 
    }
    
    /**
     * Removes all instances of the given int toRemove,
     * retaining the relative ordering of remaining
     * ints in the IntList.
     * @param toRemove The int value to purge
     */
    public void removeAll (int toRemove) {   
        for (int j = 0; j < size; j++) {
                if(items[j] == toRemove) {
                    removeAt(j);
                    // to our graders: when "j--;" was not in the code and there were multiple of
                    // the toRemove instance in a row, it would only delete one in a pair of two instances
                    // we could only do this for it to work in all circumstances
                    j--;
                   
                }
                
        }
   }

    /**
     * Removes the value at the given index in the
     * IntList, retaining relative orderings of remaining
     * ints.
     * @param index The index to remove
     */
    public void removeAt(int index) {
        indexValidityCheck(index);
        shiftLeft(index);
        size--;
    }
    
    /**
     * Checks to make sure the requested index (for either
     * manipulation or access) is within the legal bounds
     * @param index The index being requested
     */
    private void indexValidityCheck (int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
    
    /**
     * Expands the size of the list whenever it is at
     * capacity
     */
    private void checkAndGrow () {
        // Case: big enough to fit another item, so no
        // need to grow
        if (size < items.length) {
            return;
        }
        
        // Case: we're at capacity and need to grow
        // Step 1: create new, bigger array; we'll
        // double the size of the old one
        int[] newItems = new int[items.length * 2];
        
        // Step 2: copy the items from the old array
        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }
        
        // Step 3: update IntList reference
        items = newItems;
    }
    
    /**
     * Shifts all elements to the right of the given
     * index one left
     * @param index The index at which the values are shifted
     */
    private void shiftLeft (int index) {
        for (int i = index; i < size-1; i++) {
            items[i] = items[i+1];
        }
    }
	
    /**
     * Returns the String representation of name and type
     * @return String representation of the IntList
     */
    public String toString () {
    	String print = "";
    	for (int i = 0; i < size; i++) {
            print += items[i]+ " ";
        }
        return print;
    }
    
}
