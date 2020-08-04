/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  WebNavigator.java
 *  Purpose       :  Provides a class defining methods for the WebNavigator class
 *  @author       :  Ona Igbinedion
 *  @author       :  Ameya Mellacheruvu
 *  @author       :  Elise Sawan
 *  Date written  :  2019-09-27
 *  Notes         :  None right now.  We'll add some as they occur.
 *  Warnings      :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2019-09-01  all authors   Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package webnav;

import java.util.LinkedList;

/**
 * Web Navigator used to track which URLs a user is currently
 * or was previously browsing, as well as tools for updating the
 * currently viewed based on their session history.
 */
public class WebNavigator {
	
	public static void main(String[] args) {
		// Example Interaction
		  WebNavigator navi = new WebNavigator();
		  
		  navi.visit("www.google.com");
		  System.out.println(navi.getCurrent());
		  // www.google.com
		  
		  navi.visit("www.reddit.com");
		  System.out.println(navi.getCurrent());
		  // www.reddit.com
		  
		  navi.back();
		  System.out.println(navi.getCurrent());
		  // www.google.com
		  
		  navi.back();
		  System.out.println(navi.getCurrent());
		  // www.google.com
		  
		  navi.forw();
		  System.out.println(navi.getCurrent());
		  // www.reddit.com
		  
		  navi.forw();
		  System.out.println(navi.getCurrent());
		  // www.reddit.com
		  
		  navi.visit("www.facebook.com");
		  System.out.println(navi.getCurrent());
		  // www.facebook.com
		  
		  navi.back();
		  System.out.println(navi.getCurrent());
		  // www.reddit.com
		  
		  // Visiting another site after moving back wipes
		  // the "forward" collection
		  navi.visit("www.amazon.com");
		  System.out.println(navi.getCurrent());
		  // www.amazon.com
		  
		  // See? doesn't go back to reddit
		  navi.forw();
		  System.out.println(navi.getCurrent());
		  // www.amazon.com
	}

    private String current;
    @SuppressWarnings("rawtypes")
	private LinkedList history = new LinkedList();
    private Integer currentIndex;
    
    WebNavigator () {
        this.current = "null"; 
        currentIndex = 0;
    }
    
    /**
     *  Visits the current site, clears the forward history,
     *  and records the visited site in the back history
     *  @param site The new site being visited
     */

	@SuppressWarnings("unchecked")
	public void visit (String site) {
		this.current = site;
        this.history.addFirst(this.current);
        currentIndex = 0;
    }
    
    /**
     *  Changes the current site to the one that was last
     *  visited in the order on which visit was called on it
     */
    public void back () {
    	if(currentIndex < this.history.size()-1) {
    		currentIndex++;
    	}
    	this.current = (String) this.history.get(currentIndex);
    
    }
    /**
     * Changes the current site to the one that was last
     * returned to via back()
     */
    public void forw () {
    	if (currentIndex>0) {
    		currentIndex--;
    	}
    	this.current = (String) this.history.get(currentIndex);
        
    }
    
    /**
     * Returns the String representing the site that the navigator
     * is currently at
     * @return The current site's URL
     */
    public String getCurrent () {
        return this.current;
    }
    
}
