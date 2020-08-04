/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  AutoCompleter.java
 *  Purpose       :  Provides a class defining methods for the class AutoCompleter as well as an inner 
 *                   class TTNode, and an inner class StorageContainer.
 *  @author       :  Andrew Francis Forney (skeleton)              
 *  @author       :  Ona Igbinedion
 *  Description   :  A ternary-search-tree implementation of a text-autocompletion trie, a simplified 
 *  				 version of some autocomplete software.
 *  Date written  :  2019-10-20
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:     Reason for change/modification
 *           -----  ----------  ---------------  -----------------------------------------------------------
 *  @version 1.0.0  2019-11-19  Ona Igbinedion   Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package autocompleter;

import java.util.ArrayList;

public class AutoCompleter implements AutoCompleterInterface {
	

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    TTNode root;
    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    AutoCompleter () {
        root = null;
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    
    /**
     * Method that determines whether anything has been collected yet.
     * @return boolean representation of the emptiness of the AutoCompleter
     */
    public boolean isEmpty () {
        return this.root == null;
    }
    
    /**
     * Method that adds a given String to the AutoCompleter
     * @param String toAdd     a String representation of the word to be added;
     */
    public void addTerm (String toAdd) {
        toAdd = normalizeTerm(toAdd);
        if (isEmpty()) {
        	this.root = new TTNode(toAdd.charAt(0), toAdd.length() == 1);
        	buildDown(toAdd.substring(1), root);
        } else {
        append (root, 0, toAdd);}  
    }
    
    /**
     * Method that determines if a given String is already collected in the AutoCompleter
     * @param String query    a String representation of the word to be searched for
     * @return a Boolean representation of whether the term has been previously collected         
     */
    public boolean hasTerm (String query) {
        if (isEmpty() && query.length() != 0) {return false;}
        StorageContainer answer = looper(query);
        if (answer.booleanAnswer) {return answer.nodeAnswer.wordEnd;}
        return answer.booleanAnswer;
    }
    
    /**
     * Method that returns a suggested term for the given prefix, or null if the prefix has yet to appear
     * @param String query   A String representation of the prefix to be searched for
     * @return a String representation of the suggested term, or null
     */
    public String getSuggestedTerm (String query) {
    	String word = query;
    	StorageContainer answer = looper(query);
    	if (answer.booleanAnswer) {
    		if(answer.nodeAnswer.wordEnd) {
    			return word;
    		} else {
    			while (!answer.nodeAnswer.wordEnd && answer.nodeAnswer.mid != null) {
    				answer.nodeAnswer = answer.nodeAnswer.mid;
    	    		word += answer.nodeAnswer.letter;
    			}
    	    return word;
    		}
    	} else { return null;}
    }
    
    /**
     * Method that returns an ArrayList of the sorted words stored in the AutoCompleter
     * @return  An ArrayList of the String representation of each word collected in 
     * 		    alphabetical order
     */
    public ArrayList<String> getSortedTerms () {
        ArrayList<String> wordArray =  new ArrayList<String>();
        getTerms(root, "", wordArray);
        return wordArray;
    }
    
    
	// -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------
    
    /**
     * Normalizes a term to either add or search for in the tree,
     * since we do not want to allow the addition of either null or
     * empty strings within, including empty spaces at the beginning
     * or end of the string (spaces in the middle are fine, as they
     * allow our tree to also store multi-word phrases).
     * @param s The string to sanitize
     * @return The sanitized version of s
     */
    private String normalizeTerm (String s) {
        // Edge case handling: empty Strings illegal
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.trim().toLowerCase();
    }
    
    /**
     * Given two characters, c1 and c2, determines whether c1 is
     * alphabetically less than, greater than, or equal to c2
     * @param c1 The first character
     * @param c2 The second character
     * @return
     *   - some int less than 0 if c1 is alphabetically less than c2
     *   - 0 if c1 is equal to c2
     *   - some int greater than 0 if c1 is alphabetically greater than c2
     */
    private int compareChars (char c1, char c2) {
        return Character.toLowerCase(c1) - Character.toLowerCase(c2);
    }
    
    /**
     * Method to loop through an AutoCompleter and find a particular word, or prefix within it.
     * @param String word       A String representation of the word or phrase or query that is 
     * 							being searching for within the AutoCompleter
     * @return StorageContainer type of possible answers to be used.
     */
    private StorageContainer looper(String word) {
    	StorageContainer answer = new StorageContainer();
    	TTNode currentNode = root;
        char c;
        int comparator;
        for (int i = 0; i < word.length(); i++) {
    		c = word.charAt(i);
    		currentNode = charExists(c, currentNode);
    		comparator = compareChars(c, currentNode.letter);
    		while(comparator != 0) {
    			comparator = compareChars(c, currentNode.letter);
    			if (comparator > 0) {
    				if (currentNode.right == null) {
    					answer.nodeAnswer = null;
    					answer.booleanAnswer = false;
    					return answer;
    				} else {
    					currentNode = currentNode.right;
    				}
    			}
    			else if (comparator < 0 ) {
    				if (currentNode.left == null) {
    					answer.nodeAnswer = null;
    					answer.booleanAnswer = false;
    					return answer;
    				} else {
    					currentNode = currentNode.left;
    				}
    			}
    		}
    		if (i < word.length() - 1) {
        		currentNode = currentNode.mid;
    		}
        }
        answer.booleanAnswer = true;
		answer.nodeAnswer = currentNode;
		return answer;
    }
    
    /**
     * Method to add a word to the AutoCompleter (see addTerm(String toAdd))
     * @param TTNode currentNode   A TTNode representation of the current node being altered
     * @param int index			   An int representation of the character that is currently 
     * 							   being added to the AutoCompleter	
     * @param String toAdd		   A String representation of the word to be added into the AutoCompleter
     * @return A TTNode representation of the most currently altered node
     */
    private TTNode append (TTNode currentNode, int index, String toAdd) {
    	if (currentNode == null) {currentNode = new TTNode(toAdd.charAt(index), false);}
        int comparator = compareChars(toAdd.charAt(index), currentNode.letter);
        if (comparator < 0) {
            currentNode.left = append(currentNode.left, index, toAdd);
        } else if (comparator > 0) {
            currentNode.right = append(currentNode.right, index, toAdd);
        } else {
            if (index < toAdd.length() -1) {
                currentNode.mid = append(currentNode.mid, index + 1, toAdd);
            } else {
                currentNode.wordEnd = true;
            }
        }
        return currentNode;
    }
    
    /**
     * Method to determine whether a specified character lives at a particular node
     * @param char c 		A char representation of the specified character
     * @param TTNode node   A TTNode representtion of the current node being checked
     * @return A TTNode representation of the node that the character lives in or null
     */
    private TTNode charExists(char c, TTNode node) {
    	if (node == null) { return null;}
    	if (compareChars (c, node.letter) == 0) {
    		return node;
    	} else if (compareChars (c, node.letter) > 0 ) {
    		charExists( c, node.right);
    	} else if (compareChars (c, node.letter) < 0 ) {
    		charExists( c, node.left);
    	}
    	return node;
    }

    /**
     * A method to add terms along the middle path once the spot is located (used only in root creation)
     * @param String phrase			A String representation of the word to be added
     * @param TTNode node			A TTNode representation of the node that collects the word
     */
    private void buildDown(String phrase, TTNode node) {
    	for (char c : phrase.toCharArray()) {
    		node.mid = new TTNode(c, false);
    		node = node.mid;
    	}
    	node.wordEnd = true;
    }
    
    /**
     * Method to collect the terms stored in the AutoCompleter in order
     * @param TTNode currentNode 			A TTNode representation of the current node being collected 
     * @param String prefix					A String representation of the current prefix being collected
     * @param ArrayList<String> array		An ArrayList<String> representation of the words that were collected
     */
    private void getTerms(TTNode currentNode, String prefix, ArrayList<String> array) {
    	if(currentNode == null) {return;}
    	getTerms(currentNode.left, prefix, array);
    	if (currentNode.wordEnd) {
    		array.add(prefix + currentNode.letter);
    	}
    	getTerms(currentNode.mid, prefix + currentNode.letter,array);
    	getTerms(currentNode.right, prefix,array);
    }


    // -----------------------------------------------------------
    // TTNode Internal Storage
    // -----------------------------------------------------------
    
    /**
     * Internal storage of autocompleter search terms
     * as represented using a Ternary Tree with TTNodes
     */

	private class TTNode {
		
		//FIELDS
	
	    boolean wordEnd;
	    char letter;
	    TTNode left, mid, right;
	
	    /**
	     * Constructs a new TTNode containing the given character
	     * and whether or not it represents a word-end, which can
	     * then be added to the existing tree.
	     * @param c Letter to store at this node
	     * @param w Whether or not this is a word-end
	     */
	    TTNode (char c, boolean w) {
	        letter  = c;
	        wordEnd = w;
	    }
	}
	
	 // -----------------------------------------------------------
     // Answer Storage Container
     // -----------------------------------------------------------
    
     /**
      * Storage object for answers because Hash maps are not allowed.
      * Represented by an object with boolean and node feilds.
      */
	private class StorageContainer {
		
		//FEILDS 
		
		Boolean booleanAnswer;
		TTNode nodeAnswer;
		/**
	     * Constructs a new Storage Container containing the given
	     * boolean and TTNode for the given reason.
	     */
		StorageContainer() {
			nodeAnswer = null;
			booleanAnswer = false;
		}
	}

    
}