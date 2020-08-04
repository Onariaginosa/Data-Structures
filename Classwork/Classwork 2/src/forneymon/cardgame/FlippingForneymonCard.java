package forneymon.cardgame;

/**
 * Basic card class for the Forneymon Card Matching game
 */

/**
 * @author Ona Igbinedion
 * @author Elise Sawan
 * @author Ameya Melacheruvu
 */

public class FlippingForneymonCard extends ForneymonCard {
	public static void main (String args[]) {
		
	}
	private boolean orientation;
	
	private static final boolean DEFAULT_ORIENTATION = true;

	public FlippingForneymonCard(String name, String type) {
		super (name, type);
		this.orientation = DEFAULT_ORIENTATION;
	}
	public FlippingForneymonCard(String arg) {
		super (arg);
		this.orientation = DEFAULT_ORIENTATION;
	}
	public FlippingForneymonCard(String name, String type, boolean orientation) {
		super (name, type);
		this.orientation = orientation;
	}
	public FlippingForneymonCard() {
		super ();
		this.orientation = DEFAULT_ORIENTATION;
	}
	
	/**
     * Method that flips the forneymon cards, 
     which makes it faceup if it was previously facedown, and vice versa.
     * @return true when card faces down 
     */
	
	public boolean flip() {
		this.orientation = !(this.orientation);
		return this.orientation;
	}
	
	/**
     * Returns integers 2,1,0 where an outcome of 
     2 means that either the card or the other flippingForneymonCard is face down
     1 means that both cards are facing up and share the same forneymon type and name 
     0 if both cards face up and both the name and type are different   
     * @param the "second" forneymon card or the card which player compares to
     * @return integers that return 2,1,0 which represent the three possible outcomes of game
     */
	
	public int match (FlippingForneymonCard other) {
		if (other.orientation == false && this.orientation == false) {
			if (other.getType().equals(this.getType()) && other.getName().equals(this.getName())) {
				return 1;
			} else {
				return 0;
			}
			
		} else {
			return 2;
			}
	}
	
	/**
     * Returns the String representation of name and type
     * @return The name and type of Forneymon card 
     */
	
	public String toString() {
		if (this.orientation == true) {
			return "?: ?";
			
		}else {
			return this.getType() + ": " + this.getName();
		}
	}

}