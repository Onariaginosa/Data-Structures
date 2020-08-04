
/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Forneymonagerie.java
 *  Purpose       :  Provides a class defining methods for the class Forneymonagerie as well as an inner 
 *                   class ForneymonType.
 *  @author       :  Andrew Francis Forney (skeleton)              
 *  @author       :  Ona Igbinedion
 *  Date written  :  2019-09-27
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:     Reason for change/modification
 *           -----  ----------  ---------------  -----------------------------------------------------------
 *  @version 1.0.0  2019-09-27  Ona Igbinedion   Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package forneymonagerie;

public class Forneymonagerie implements ForneymonagerieInterface {
	

    // Private Classes
    // ----------------------------------------------------------
    private class ForneymonType {
    	//Fields
        String type;
        int count;
        
        //Constructor
		ForneymonType (String t, int c) {
            type = t;
            count = c;
        }
    }
    
    
    // Fields
    // ----------------------------------------------------------
    private ForneymonType[] collection;
    private int typeSize;
    private int size;
    private int space;
    private static final int START_SIZE = 16;
    
    
    // Constructor
    // ----------------------------------------------------------
    Forneymonagerie () {
    	this.space = START_SIZE;
    	this.collection = new ForneymonType[START_SIZE];
        this.size = 0; 
        this.typeSize = 0;
        
    }
    
    
    // Methods
    // ----------------------------------------------------------
    
    /**
     * Method to determine if Forneymonagerie is empty
     * @return boolean type true if empty and false if not empty
     */
    public boolean empty () {
        if (this.size == 0) {
        	return true;
        } else {
        	return false;
        }
    }
    /**
     * Method to determine if Forneymonagerie is empty
     * @return int representation of the size of the Forneymonagerie
     */
    public int size () {
        return this.size;
    }
    /**
     * Method to determine if Forneymonagerie is empty
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
    public boolean collect (String toAdd) {
    	checkAndGrow();
    	for (int i = 0; i < this.typeSize; i++) {
    		if (this.collection[i].type == toAdd) {
    			this.collection[i].count++;
    			rarityCheckerCollect(i);
    			this.size++;
    			return true;
    			}
    		}
    		this.collection[this.typeSize] = new ForneymonType(toAdd, 1);
    		this.size++;
    		this.typeSize++;
    		return false;
    }
    
    /**
     * Method to release a new Forneymon
     * @param String toRemove     the type of the Forneymon to be removed
     * @return boolean representation of whether a Forneymon was released or not
     */
    public boolean release (String toRemove) {
    	for (int i= 0; i< this.typeSize; i++) {
    		if (this.collection[i].type == toRemove) {
    			this.collection[i].count--;
				rarityCheckerRelease(i);
				this.size--;
    			if(this.collection[i].count < 1) {
    				this.typeSize--;
    				return true;
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
    	for (int i= 0; i< this.typeSize; i++) {
    		if (this.collection[i].type == toNuke) {
    			this.size -= this.collection[i].count;
    			this.collection[i].count = 0;
				rarityCheckerRelease(i);
    			this.typeSize--;
    		}
    	}
    }
    
    /**
     * Method that determines how many Forneymon of a specific type are in the Forneymonagerie
     * @param String toCount the type of the Forneymon to be counted
     * @return int representation of how manny Forneymon of the type are in the Forneymonagerie
     */
    
    public int countType (String toCount) {
    	for (int i= 0; i< this.typeSize; i++) {
    		if (this.collection[i].type == toCount) {
    			return this.collection[i].count;
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
    	for (int i= 0; i< this.typeSize; i++) {
    		if (this.collection[i].type == toCheck) {
    			return true;
    		}
    	}
    	return false;
    }
    /**
     * Method that returns the type of Forneymon in the Forneymonagerie at the specified index
     * @param Int n the index of the forneymon that is to be returned
     * @return String representation of the type of Forneymon in the Forneymonagerie
     */
    public String nth (int n) {
    	if(n>=this.size) {
    		throw new IllegalArgumentException("argument beyond size");
    	}
    	int counter = 0;
    	if (n == 0) {
    		return this.collection[0].type;
    	}
    	int i = -1;
    	while (counter <= n) {
    		i++;
    		counter += this.collection[i].count;
    	}
    	
    	return this.collection[i].type;	
    }
    
    /**
     * Method that determines the most recently altered ForneymonType in it's rarity group
     * @param int count    the rarity grouping that we are checking
     * @return a String representation of the type of the most recently altered Forneymon in the rarity group
     */
    public String mostRecentAtRarity (int count) {
    	for (int i= 0; i< this.typeSize; i++) {
    		if (this.collection[i].count == count) {
    			if(isLowest(i)) {
    				return this.collection[i].type;
    			}
    		}
    	}
        return null;
    }
    
    /**
     * Method that trades Forneymonageries with a specified Forneymonagerie
     * @param Forneymonagerie other   the other Forneymonagerie that is involved in the trade
     */
    public void trade (Forneymonagerie other) {
        Forneymonagerie placeholder = this.clone();
        this.collection = other.collection;
        this.size = other.size;
        this.typeSize = other.typeSize;
        other.collection = placeholder.collection;
        other.size = placeholder.size;
        other.typeSize = placeholder.typeSize;
        
    }
    
    /**
     * Method that deep copies the Forneymonagerie into a clone
     * @return a Forneymonagerie representation of the Forneymonagerie that is separate from the initial Forneymonagerie
     */
    public Forneymonagerie clone () {
    	Forneymonagerie clone = new Forneymonagerie();
        clone.collection = this.collection;
        clone.size = this.size;
        clone.typeSize = this.typeSize;
        
        return clone;
    }
 
    /**
     * Method that String-ifies the Forneymonagerie
     * @return a String representation the Forneymonagerie collection
     */
    public String toString () {
        String[] result = new String[typeSize];
        for (int i = 0; i < typeSize; i++) {
            result[i] = "\"" + collection[i].type + "\": " + collection[i].count;
        }
        return "[ " + String.join(", ", result) + " ]";
    }

    
    
    // Static methods
    // ----------------------------------------------------------
    
    /*
     * Static method that provides a Forneymonagerie comprising of the Forneymon in y1 but not in y2
     * @param Forneymonagerie y1 Forneymonagerie to be compared
     * @param Forneymonagerie y2 Forneymonagerie to be compared
     * @return Forneymonagerie representation of the difference in Forneymon between y1 and y2
     */
    public static Forneymonagerie diffMon (Forneymonagerie y1, Forneymonagerie y2) {
    	Forneymonagerie different = new Forneymonagerie();
    	int diff;
    	int count;
    	for (int i= 0; i < y1.typeSize; i++) {
    		count = 0;
    		for(int j = 0; j < y2.typeSize; j++) {
    			 if(y1.collection[i].type == y2.collection[j].type) {
    				 diff = y1.collection[i].count - y2.collection[j].count;
    				 count++;
    				 if(diff > 0) {
    					 different.collection[different.typeSize] = different.new ForneymonType(y1.collection[i].type, diff);
    					 different.size+=diff;
    					 different.typeSize++;
    				 }
    			 } 
    		}
    		if(count == 0) {
    			different.collection[different.typeSize] = different.new ForneymonType(y1.collection[i].type, y1.collection[i].count);
    			different.size+=y1.collection[i].count;
				different.typeSize++;
    		}
    	}
    	return different;
    }
    
    /**
     * Method that provides a Forneymonagerie of the Forneymons that are the same in y1 and y2
     * @param Forneymonagerie y1 the Forneymonagerie to be compared
     * @param Forneymonagerie y2 the Forneymonagerie to be compared
     * @return a Forneymonagerie representation of the Forneymon shared in y1 and y2
     */
    public static boolean sameCollection (Forneymonagerie y1, Forneymonagerie y2) {
    	int count;
    	if (y1.typeSize != y2.typeSize || y1.size != y2.size) {
    		return false;
    	}
    	for (int i= 0; i < y1.typeSize; i++) {
    		count = 0;
    		for(int j = 0; j < y2.typeSize; j++) {
    			if(y1.collection[i].type == y2.collection[j].type && y1.collection[i].count == y2.collection[j].count) {
    				count ++;
    			}
    		}
    		if(count == 0) {
    			return false;
    		}
    	}
    	return true;
    }
    
   
    // Private helper methods
    // ----------------------------------------------------------
    
    /**
     * Method that orders the Forneymonagerie when a Forneymon is added to the Forneymonagerie
     * in the order of least rare to most rare, and within the same rarity, last changed to most recently changed
     * @param int index the position of the current ForneymonType to be checked
     */
    private void rarityCheckerCollect (int index) {
    	int prev;
    	ForneymonType placeholder;
    	boolean checking = true;
    	while (checking && index>0 ) {
    		prev = index -1;
    		if (this.collection[index].count > this.collection[prev].count) {
    			placeholder = this.collection[prev];
    			this.collection[prev] = this.collection[index];
    			this.collection[index] = placeholder;
    			index--;
    		} else {
    			checking = false;
    		}
    	}
    }
    
    /**
     * Method that orders the Forneymonagerie when a Forneymon is removed to the Forneymonagerie
     * in the order of least rare to most rare, and within the same rarity, last changed to most recently changed
     * @param int index the position of the current ForneymonType to be checked
     */
    private void rarityCheckerRelease (int index) {
    	int next;
    	ForneymonType placeholder;
    	boolean checking = true;
    	while (checking && index< this.typeSize-1 ) {
    		next = index +1;
    		if (this.collection[index].count <= this.collection[next].count) {
    			placeholder = this.collection[index];
    			this.collection[index] = this.collection[next];
    			this.collection[next] = placeholder;
    			index++;
    		} else {
    			checking = false;
    		}
    	}
    }
    
    /**
     * Method that checks if the ForneymonType is the last in its rarity Group
     * @param index the position of the ForneymonType to be checked
     * @return boolean representation of whether it is the last in it's rarity group;
     */
    private boolean isLowest(int index) {
    		if (index == this.typeSize-1) {
    			return true;
    		}
    		int next = index +1;
    		if (this.collection[index].count <= this.collection[next].count) {
    			return false;
    		} 
    		return true;
    }
    
    /**
     * Expands the size of the Forneymonagerie whenever it is at
     * capacity
     */
    private void checkAndGrow () {
        if (this.typeSize< this.collection.length) {
            return;
        }
        ForneymonType[] newItems = new ForneymonType[(this.collection.length) * 2];
        for (int i = 0; i < this.typeSize; i++) {
            newItems[i] = this.collection[i];
        }
        this.collection = newItems;
    }
    
    
}
