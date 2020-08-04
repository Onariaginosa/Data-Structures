/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  LinkedForneymonagerie.java
 *  Purpose       :  Provides a class defining methods for the class LinkedForneymonagerie as well as an inner 
 *                   class ForneymonType, and an inner class Iterator.
 *  @author       :  Andrew Francis Forney (skeleton)              
 *  @author       :  Ona Igbinedion
 *  Date written  :  2019-10-20
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:     Reason for change/modification
 *           -----  ----------  ---------------  -----------------------------------------------------------
 *  @version 1.0.0  2019-10-21  Ona Igbinedion   Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package forneymonagerie;

import java.util.NoSuchElementException;

public class LinkedForneymonegerie implements ForneymonagerieInterface {

	
    // Fields
    // -----------------------------------------------------------
    private ForneymonType head, tail;
    public int size, typeSize, modCount;
    
    
    // Constructor
    // -----------------------------------------------------------
    LinkedForneymonegerie () {
        this.head = null; 
        this.tail = null;
        this.size = 0;
        this.typeSize = 0;
        this.modCount = 0;
    }
    
    
    // Methods
    // -----------------------------------------------------------
    
    /**
     * Method to determine if LinkedForneymonagerie is empty
     * @return boolean type true if empty and false if not empty
     */
    
    public boolean empty () {
        return (this.size == 0);
    }
    
    /**
     * Method to determine the size of a Forneymonagerie
     * @return int representation of the size of the Forneymonagerie
     */
    
    public int size () {
        return this.size;
    }
    
    /**
     * Method to determine the number of different types of Forneymon in the Forneymonagerie
     * @return int representation of the size of types in the Forneymonagerie
     */
    
    public int typeSize () {
        return this.typeSize;
    }
    
    /**
     * Method to collect an new Forneymon 
     * @param String to Add    the type of the Forneymon to be added
     * @return boolean representaion of whether there was at least one Forneymon of the same type in the Forneymonagerie
     */
	
	// [DM] Make sure to replace all tabs with spaces. (-3)
    public boolean collect (String toAdd) {
    	for (ForneymonType n = this.head; n != null; n = n.next) {
    		if (n.type.equals(toAdd)) {
    			n.count++;
    			positionCheck(n);
    			this.size++;
    			this.modCount++;
    			return false;
    		}
		}
    	if(this.head == null) {
    		this.head = new ForneymonType(toAdd,1);
    		this.tail = this.head;
    	} 
    	else {
    		ForneymonType newMon = new ForneymonType(toAdd,1);
    		this.tail.next = newMon;
    		newMon.prev = this.tail;
    		this.tail = newMon;
    	}
    	this.size++;
    	this.typeSize++;
    	this.modCount++;
    	return true;
    }
     
    /**
     * Method to release a new Forneymon
     * @param String toRemove     the type of the Forneymon to be removed
     * @return boolean representation of whether a Forneymon was released or not
     */
    
    public boolean release (String toRemove) {
    	for (ForneymonType n = this.head; n != null; n = n.next) {
    		if (n.type == toRemove) {
    			if (n.count == 1) {
					// [DM] Great use of using a helper method to cut down on code!
    				releaseType(n.type);
    			}
    			else {
    				n.count--;
    				positionCheck(n);
    				this.modCount++;
        			this.size--;
    			}
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Method to release all Forneymon of a specific type
     * @param String toRemove     the type of the Forneymon to be removed
     */
    
    public void releaseType (String toNuke) {
    	for (ForneymonType n = this.head; n != null; n = n.next) {
    		if(n.type == toNuke) {
    			this.modCount++;
    			this.size-= n.count;
    			this.typeSize--;
    			if(n != this.head && n != this.tail) {
    				n.prev.next = n.next;
    				n.next.prev = n.prev;
    			}
    			else if(n == this.head && n == this.tail) {
    				this.head = null;
    				this.tail = null;
				}
    			else if(n == this.tail) {
    				this.tail = n.prev;
    				n.prev.next = null;
    			}
    			else if(n == this.head) {
    				this.head = n.next;
    				n.next.prev = null;
    			}
    		}
    	}
    }
    
    /**
     * Method that determines how many Forneymon of a specific type are in the Forneymonagerie
     * @param String toCount the type of the Forneymon to be counted
     * @return int representation of how manny Forneymon of the type are in the Forneymonagerie
     */

    public int countType (String toCount) {
    	for (ForneymonType n = this.head; n != null; n = n.next) {
    		if (n.type == toCount) {
    			return n.count;
    		}
    	}
    	return 0;
    }
    
    /**
     * Method that checks if a Forneymon of a specified type is in the Forneymonagerie
     * @param String toCheck the Forneymon type to be checked
     * @return boolean representation of 
     */

    public boolean contains (String toCheck) {
    	for (ForneymonType n = this.head; n != null; n = n.next) {
    		if (n.type == toCheck) {
    			return true;
    		}
    	}
        return false;
    }
    
    /**
     * Method that determines the most recently altered ForneymonType in it's rarity group
     * @param int count    the rarity grouping that we are checking
     * @return a String representation of the type of the most recently altered Forneymon in the rarity group
     */
    
    public String mostRecentAtRarity (int count) {
    	for (ForneymonType n = this.head; n != null; n = n.next) {
    		if (n.count == count) {
    			if(isLowest(n)) {
    				return n.type;
    			}
    		}
    	}
        return null;
    }    
    
    /**
     * Method that deep copies the LinkedForneymonagerie into a clone
     * @return a LinkedForneymonagerie representation of the LinkedForneymonagerie that is separate from the initial Forneymonagerie
     */
    
    public LinkedForneymonegerie clone () {
    	LinkedForneymonegerie clone = new LinkedForneymonegerie();
    	if(this.empty()) {
    		return clone;
    	}
    	clone.head = new ForneymonType(this.head.type, this.head.count);
    	clone.tail = new ForneymonType(this.tail.type, this.tail.count);
        for (ForneymonType n = clone.head, x = this.head.next; n != null; n = n.next) {
        	if (x != null) {
        		n.next = new ForneymonType(x.type, x.count);
        		n.next.prev = n;
        		x = x.next;
        	}
        	else {
        		clone.tail = n;
        	}
        }
        clone.size = this.size;
        clone.typeSize = this.typeSize;
        clone.modCount = this.modCount;
    	return clone;
    }
    
    /**
     * Method that trades Forneymonageries with a specified Forneymonagerie
     * @param LinkedForneymonagerie other   the other LinkedForneymonagerie that is involved in the trade
     */
    
    public void trade (LinkedForneymonegerie other) {
    	LinkedForneymonegerie placeholder = other.clone();
    	
    	other.head = this.head;
    	other.tail = this.tail;
    	other.size = this.size;
    	other.typeSize = this.typeSize;
    	other.modCount = this.modCount+1;
    	
    	this.head = placeholder.head;
    	this.tail = placeholder.tail;
    	this.size = placeholder.size;
    	this.typeSize = placeholder.typeSize;
    	this.modCount = placeholder.modCount+1;
    }
    
    /**
     * Method that returns the type of Forneymon in the LinkedForneymonagerie at the specified index
     * @param Int n the index of the forneymon that is to be returned
     * @return String representation of the type of Forneymon in the Forneymonagerie
     */
    
    public String nth (int num) {
    	if(num >= this.size || num <0) {
    		throw new IllegalArgumentException("argument beyond size");
    	}
    	if(num == 0) {
    		return this.head.type;
    	}
    	int counter = -1;
    	for (ForneymonType n = this.head; n != null; n = n.next) {
    		counter += n.count;
    		if( num <= counter) {
    			return n.type;
    		}
    	}
    	return "";
    }
    
    /**
     * A Method that returns an iterator to loop through the given forneymonagerie
     * @return An Iterator that is capable of looping through the given LinkedForneymonagerie
     */
    
    public LinkedForneymonegerie.Iterator getIterator () {
        if (this.head == null) {
        	throw new IllegalStateException();
        }
        return new LinkedForneymonegerie.Iterator(this);
    }
    
    @Override
    
    /**
     * A method that returns a String representation of the given LinkedForneymonagerie
     * @return String representation of given LinkedForneymonagerie
     */
    
    public String toString () {
        String[] result = new String[this.typeSize];
        int i = 0;
        for (ForneymonType n = this.head; n != null && i < this.typeSize; n = n.next) {
            result[i] = "\"" + n.type + "\": " + n.count;
            i++;
        }
        return "[ " + String.join(", ", result) + " ]";
    }
    
    
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------
    
    /*
     * Static method that provides a LinkedForneymonagerie comprising of the Forneymon in y1 but not in y2
     * @param LinkedForneymonagerie y1 LinkedForneymonagerie to be compared
     * @param LinkedForneymonagerie y2 LinkedForneymonagerie to be compared
     * @return LinkedForneymonagerie representation of the difference in Forneymon between y1 and y2
     */
    
    public static LinkedForneymonegerie diffMon (LinkedForneymonegerie y1, LinkedForneymonegerie y2) {
    	LinkedForneymonegerie different = new LinkedForneymonegerie(); 
    	boolean breakCheck;
    	boolean check;
        for (ForneymonType a = y1.head; a != null; a = a.next) {
        	check = false;
        	breakCheck = false;
        	for (ForneymonType b = y2.head; b != null; b = b.next) {
        		if (a.type == b.type) {
        			if (a.count != b.count && a.count > b.count) {
        			different.collect(a.type);
        			different.tail.count = a.count - b.count;
        			}
        			check = true;
        			breakCheck = true;
        			break;
        		}
        		if (breakCheck) {
        			breakCheck = false;
        			break;
        		}
        	}
        	if(!check) {
        		different.collect(a.type);
        		different.tail.count = a.count;
        	}
        }
        return different;
    }
    
    /**
     * Method that provides a boolean representation of whether two LinkedFOrneymonagerie are equal
     * @param LinkedForneymonagerie y1 the LinkedForneymonagerie to be compared
     * @param LinkedForneymonagerie y2 the LinkedForneymonagerie to be compared
     * @return a boolean representation of if the y1 equals y2
     */
    
    public static boolean sameCollection (LinkedForneymonegerie y1, LinkedForneymonegerie y2) {
    	if(y1.typeSize != y2.typeSize || y1.size != y2.size ) {
    		return false;
    	}
    	for (ForneymonType a = y1.head, b = y2.head; a != null && b != null; a = a.next, b = b.next) {
    		if(a.type != b.type || a.count != b.count) {
    			return false;
    		}
    	}
    	return true;
    	
    }
    
    
    // Private helper methods
    // -----------------------------------------------------------

    /**
     * Method that checks whether the given ForneymonType is in the correct position, and swaps it
     * @param ForneymonType n   the ForneymonType to check and fix if needed
     */
    
    private void positionCheck (ForneymonType n) {
    	boolean correctState = false;
    	while (!correctState) {
    		if (n != this.head && n.count > n.prev.count) {
    			swap(n.prev, n);
    		}
    		else if (n != this.tail && n.count <= n.next.count) {
    			swap(n, n.next);
    		}
    		else {
    			correctState = true;
    		}
    	}
    }
    
    /**
     * Method that swaps wo given ForneymonTypes
     * @param ForneymonType a the first of the two ForneymonTypes to be swapped
     * @param ForneymonType b the second of the two ForneymonTypes to be swapped
     */
    
    private void swap (ForneymonType a, ForneymonType b) {
		// [DM] What do c and d mean? Instead of naming them c and d, next time give more descriptive
		// names like aPrev and dNext. (-1)
    	ForneymonType c = a.prev;
    	ForneymonType d = b.next;
    	b.prev = c;
    	b.next = a;
    	a.next = d;
    	a.prev = b;
    	if (c != null) {
    		c.next = b;
    	}
    	else {
    		this.head = b;
    	}
    	if (d != null) {
    		d.prev = a;
    	}
    	else {
    		this.tail = a;
    	}
    }
    
    /**
     * Method that determines if a given ForneymonType is lower than the next ForneymonType
     * @param ForneymonType n ForneymonType representation of the ForneymonType to be compared to the next ForneymonType
     * @return boolean representation of of whether the given FOrneymonType is lower than the next ForenymonType 
     */
    
    private boolean isLowest(ForneymonType n) {
		if (n == this.tail) {
			return true;
        }
		else {
			if (n.count <= n.next.count) {
				return false;
			}
		} 
		return true;
}
    
    // Inner Classes
    // -----------------------------------------------------------
    
    public class Iterator implements LinkedForneymonegerieIteratorInterface {
    	
    	
    	//Fields
        private LinkedForneymonegerie owner;
        private ForneymonType current;
        public int itModCount;
        private int forneymon;
        
        //Constructor
        Iterator (LinkedForneymonegerie y) {
            owner = y;
            current = y.head;
            itModCount = y.modCount;
            forneymon = 1;
            
        }
        
        /**
         * Method that determines if the Forneymon has a next Forneymon
         * @return boolean representation of whether there is a next Forneymon
         */
        
        public boolean hasNext () {
        	return isValid() && (current.next != null || (current.next == null && forneymon < current.count));
        }
        
        /**
         * Method that determines if the Forneymon has a previous Forneymon
         * @return boolean representation of whether there is a prior Forneymon
         */
        
        public boolean hasPrev () {
        	return isValid() && (current.prev != null || (current.prev == null && forneymon > 1));
        }
        
        /**
         * Method that determines if the iterator is valid
         * @return boolean representaion of whether the iterator is valid
         */
        
        public boolean isValid () {
            return itModCount == owner.modCount;
        }
        
        /**
         * Method that returns the type of the current Forneymon
         * @return String representation of the current Forneymon Type
         */
        
        public String getType () {
        	if(!isValid()) {
        		throw new IllegalStateException();
        	}
        	return current.type;
        }
        
        /**
         * Method that changes the current Forneymon to the next Forneymon
         */

        public void next () {
        	if(!isValid()) {
        		throw new IllegalStateException();
        	}
        	if(hasNext()) {
        		if (forneymon >= current.count) {
        			forneymon = 1;
        			current = current.next;
        		}
        		else {
        			forneymon++;
        		}
        	}
        	else {
        		throw new NoSuchElementException();
        	}
        }
        
	/**
         * Method that changes the current Forneymon to the prior Forneymon
         */
	    
        public void prev () {
        	if(!isValid()) {
        		throw new IllegalStateException();
        	}
        	if(hasPrev()) {
        		if (forneymon <= 1) {
        			forneymon = current.prev.count;
        			current = current.prev;
        		}
        		else {
        			forneymon--;
        		}
        	}
        	else {
        		throw new NoSuchElementException();
        	}
        }
	    
         /**
         * Method that replaces the current ForneymonType with that of a given value
         * @param String toReplaceWith the type of the new ForneymonType to replace the current ForneymonType
         */
	    
        public void replaceAll (String toReplaceWith) {
		if(!isValid()) {
        		throw new IllegalStateException();
        	}
		this.itModCount++;
		owner.modCount++;
		if(!owner.contains(toReplaceWith)) {
			current.type = toReplaceWith;
		}
		else {
			for(ForneymonType n = owner.head; n != null; n = n.next) {
				if(n.type == current.type) {
					n.count += current.count;
					current.type = null;
					owner.releaseType(null);
					owner.positionCheck(n);
					break;
				}
			}
		}
	}
    }
    
    private class ForneymonType {
	    
	    
        // Fields
	ForneymonType next, prev;
        String type;
        int count;
	    
	    
        //Constructor
        ForneymonType (String t, int c) {
            type = t;
            count = c;
        }
    }  
}

// ===================================================
// >> [DM] Summary
// ---------------------------------------------------
// Correctness:         78 / 85
// Style:               11 / 15
// Total:               89 / 100
// ---------------------------------------------------
// This is a very good submission that has a great
// eye for style and utilizies helper methods in a
// superb manner. For next time, look up how to
// replace tabs with spaces so you do not lose easy
// points and even when creating temporary varaibles
// make sure they have descriptive names. Overall,
// splendid job!
// ===================================================
