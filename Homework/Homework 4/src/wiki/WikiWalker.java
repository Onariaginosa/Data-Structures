/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  WikiWalker.java
 *  Purpose       :  Provides a class defining methods for the class WikiWalker 
 *  @author       :  Andrew Francis Forney (skeleton)              
 *  @author       :  Ona Igbinedion
 *  Description   :  A HashMap implementation of a graph depicting websites, and how often their
 *  				 links are clicked.
 *  Date written  :  2019-12-13
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:     Reason for change/modification
 *           -----  ----------  ---------------  -----------------------------------------------------------
 *  @version 1.0.0  2019-12-13  Ona Igbinedion   Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package wiki;

import java.util.*;

public class WikiWalker {

	// -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    public HashMap<String, List<String>> siteMap;
    public HashMap<String, List<Integer>> edges;
    
    // -----------------------------------------------------------
    // Constructor
    // 
    WikiWalker() {
        siteMap = new HashMap<>();
        edges = new HashMap<>();
    }

    /**
     * Adds an article with the given name to the site map and associates the
     * given linked articles found on the page. Duplicate links in that list are
     * ignored, as should an article's links to itself.
     * 
     * @param articleName
     *            The name of the page's article
     * @param articleLinks
     *            List of names for those articles linked on the page
     */
    public void addArticle(String articleName, List<String> articleLinks) {
        List<Integer> visitedList = new ArrayList<Integer>();
    	HashSet<String> links = new HashSet<String>();
        links.addAll(articleLinks);
        links.remove(articleName);
        String[] linksAsStrings = Arrays.stream(links.toArray()).toArray(String[]::new);
        articleLinks = Arrays.asList(linksAsStrings);
    	if(siteMap.containsKey(articleName)) {
    		List<String> originLink = siteMap.get(articleName);
    		List<Integer> originEdge = edges.get(articleName);
    		articleLinks = concat(articleLinks, siteMap.get(articleName));
    		for (int i = 0; i < articleLinks.size(); i++) {
    			visitedList.add(0);
    		}
    		siteMap.replace(articleName, articleLinks);
        	edges.replace(articleName, visitedList);
        	int newIndex = 0;
    		for (int i = 0; i < originLink.size(); i++) {
    			newIndex = siteMap.get(articleName).indexOf(originLink.get(i));
    			edges.get(articleName).set(newIndex, originEdge.get(i));
    		}
        } else {
        	for (int i = 0; i <articleLinks.size(); i++) {
    			visitedList.add(0);
    		}
        	siteMap.put(articleName, articleLinks);
        	edges.put(articleName, visitedList);
        }
    }

    /**
     * Determines whether or not, based on the added articles with their links,
     * there is *some* sequence of links that could be followed to take the user
     * from the source article to the destination.
     * 
     * @param src
     *            The beginning article of the possible path
     * @param dest
     *            The end article along a possible path
     * @return boolean representing whether or not that path exists
     */
    public boolean hasPath(String src, String dest) {
        return pathFinder(src, dest, new HashSet<String>());
    }

    /**
     * Increments the click counts of each link along some trajectory. For
     * instance, a trajectory of ["A", "B", "C"] will increment the click count
     * of the "B" link on the "A" page, and the count of the "C" link on the "B"
     * page. Assume that all given trajectories are valid, meaning that a link
     * exists from page i to i+1 for each index i
     * 
     * @param traj
     *            A sequence of a user's page clicks; must be at least 2 article
     *            names in length
     */
    public void logTrajectory(List<String> traj) {
        if (traj.size() > 1) {
        	int edgeIndex = 0;
        	for (int i = 0; i < traj.size()-1; i++) {
        		edgeIndex = siteMap.get(traj.get(i)).indexOf(traj.get(i+1));
        		edges.get(traj.get(i)).set(edgeIndex, edges.get(traj.get(i)).get(edgeIndex)+1);	
        	}
        }
    }

    /**
     * Returns the number of clickthroughs recorded from the src article to the
     * destination article. If the destination article is not a link directly
     * reachable from the src, returns -1.
     * 
     * @param src
     *            The article on which the clickthrough occurs.
     * @param dest
     *            The article requested by the clickthrough.
     * @throws IllegalArgumentException
     *             if src isn't in site map
     * @return The number of times the destination has been requested from the
     *         source.
     */
    public int clickthroughs(String src, String dest) {
    	if (!siteMap.containsKey(src)) {
    		throw new IllegalArgumentException();
    	} else if (!siteMap.get(src).contains(dest)) {
    		return -1;
    	} else {
    		int edgeIndex = siteMap.get(src).indexOf(dest);
    		return edges.get(src).get(edgeIndex);
    	}
    }

    /**
     * Based on the pattern of clickthrough trajectories recorded by this
     * WikiWalker, returns the most likely trajectory of k clickthroughs
     * starting at (but not including in the output) the given src article.
     * Duplicates and cycles are valid output along a most likely trajectory. In
     * the event of a tie in max clickthrough "weight," this method will choose
     * the link earliest in the ascending alphabetic order of those tied.
     * 
     * @param src
     *            The starting article of the trajectory (which will not be
     *            included in the output)
     * @param k
     *            The maximum length of the desired trajectory (though may be
     *            shorter in the case that the trajectory ends with a terminal
     *            article).
     * @return A List containing the ordered article names of the most likely
     *         trajectory starting at src.
     */
    public List<String> mostLikelyTrajectory(String src, int k) {
        List<String> path = new ArrayList<String>();
        String key = "";
        int largestEdge = 0;
        while (k > 0) {      
        	if (siteMap.containsKey(src) && edges.get(src).size() > 0){
        		for(int i = 1; i < edges.get(src).size(); i++) {
        			if (edges.get(src).get(i) > edges.get(src).get(largestEdge)) {
        				largestEdge = i;
        			} else if (edges.get(src).get(i) == edges.get(src).get(largestEdge)) {
        				key = compareStrings(siteMap.get(src).get(i), siteMap.get(src).get(largestEdge));
        				largestEdge = siteMap.get(src).indexOf(key);
        			}
        		}
        		path.add(siteMap.get(src).get(largestEdge));
        		src = siteMap.get(src).get(largestEdge);
        		largestEdge = 0;
        		k--;
        	} else {
        		k = 0;
        	}
        }
        return path;
    }
    
    // -----------------------------------------------------------
    // HELPER METHODS
    // -----------------------------------------------------------
    
    /**
     * Method that adds two strings together and removes any duplicates
     * @param one 		the first String
     * @param two		the second String
     * @return			the new unique string comprised of the elements of the parameters
     */
    private List<String> concat(List<String> one, List<String> two){
    	HashSet<String> congregates = new HashSet<String>();
        congregates.addAll(one);
        congregates.addAll(two);
        String[] congregatesAsStrings = Arrays.stream(congregates.toArray()).toArray(String[]::new);
        return Arrays.asList(congregatesAsStrings);
    }
    
    /**
     * Reccursive helper method for the hasPath method. Determines whether there is a path from
     * the current site to the destination site.
     * @param current    the current site you are on
     * @param dest       the site you are trying to go to
     * @param visited    a HashSet representation of the nodes you have visited prior
     * @return			 a boolean representation of whether you can get to the destination
     * 					 from the current link.
     */
    private boolean pathFinder(String current, String dest, HashSet<String> visited) {
    	if (current.equals(dest)) {
    		return true;
    	}
    	if(visited.contains(current)) {
    		return false;
    	}
    	visited.add(current);
    	if (siteMap.get(current) != null) {
	    	for(int i = 0; i < siteMap.get(current).size(); i++) {
	    		if(siteMap.get(current).get(i).equals(dest)) {
	    			return true;
	    		} else if (siteMap.containsKey(siteMap.get(current).get(i))) {
	    			if (pathFinder(siteMap.get(current).get(i), dest, visited)) {
	    				return true;
	    			}
	    		}
	    	}
    	}
    	return false;
    }
    
    /**
     * Given two unique Strings, s1 and s2, determines which of the strings is alphabetically
     * less than the other
     * @param s1 The first String
     * @param s2 The second String
     * @return the alphabetically smaller string
     */
    private String compareStrings (String s1, String s2) {
    	int length = s1.length() < s2.length()? s1.length(): s2.length();
    	int index = 0;
    	while (length > 0) {
    		if (compareChars(s1.charAt(index), s2.charAt(index)) == 0) {
    			length--;
    		} else {
    			return (compareChars(s1.charAt(index), s2.charAt(index)) > 0)? s2: s1;
    		} 
    	}
    	return s1.length() < s2.length()? s1: s2;
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
}