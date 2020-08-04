package forneymon.cardgame;

/**
 * Basic card class for the Forneymon Trading Card game
 * 
 */

/**
 * @author Ona Igbinedion
 * @author Elise Sawan
 * @author Ameya Melacheruvu
 */


public class ForneymonCard {
	private static String DEFAULT_NAME = "MissingNu";
	private static String DEFAULT_TYPE = "Burnymon";
	
	private String name;
	private String type;
	
	public static void main (String args[]) {
		
	}
	

	public ForneymonCard (String name, String type) {
		validateName(name);
		validateType(type);
	}
	
	public ForneymonCard (String arg) {
		if (arg.equals( "Burnymon") || arg.equals( "Dampymon") || arg.equals( "Leafymon")) {
			this.type = arg;
			this.name = DEFAULT_NAME;
		}
		else {
			validateName(arg);
			this.type = DEFAULT_TYPE;
		}
	}
	
	/**
	 * Constructor goes here 
	 */
	
	public ForneymonCard () {
		this.name = DEFAULT_NAME;
		this.type = DEFAULT_TYPE;
	}
	
	/**
	 * Method that validates the Forneymon name
	 * @param String, takes in a Forneymon name
	 */
	 
	public void validateName(String name) {
		if (name.equals("")) {
			throw new IllegalArgumentException();
		}
		else {
			this.name = name;
		}
	}
	
	/**
	 * Method that validates the Forneymon type
	 * @param String, takes in a type
	 */
	
	public void validateType(String type) {
		if (type.equals("Burnymon") || type.equals("Dampymon") || type.equals("Leafymon")) {
			this.type = type;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 *  The String representation of type and name
	 * @return the Forneymon type and name 
	 */
	
	public String toString() {
		return this.type + ": " + this.name;
	}
	  /**
	   *  Method to access the type 
	   *  @return the Forneymon type
	   */
	
	public String getType() {
		return this.type;
	}
	
	 /**
	   *  Method to access the name 
	   *  @return the Forneymon name
	   */
	
	public String getName() {
		return this.name;
	}
}
