/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SentUtils.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  Ona Igbinedion
 *  @author       :  Ameya Mellacheruvu
 *  @author       :  Elise Sawan
 *  Date written  :  2019-09-01
 *  Description   :  This small library returns the number of unique words in the given sentence that are repeated
 *                   anywhere else in the sentence. This is for Classwork 1.
 *
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

package sentutil;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Simple library which might (outside of this assignment) contain
 * various functions related to some sentence utilities.
 */

public class SentUtils {

    public static void main (String[] args) {
    	repeats("two f f two toby  f f f r q w toby");

    }

    /**
     * Returns the number of unique, repeated words that are found
     * in the given sentence sent
     * @param sent String value of the sentence in which to count repeated words
     * @return Integer value of the number of unique, repeated words
     */

    public static int repeats(String sent) {
      int count = 0;
      int innerCount;
      String[] wordArray = sent.split(" ");

      Arrays.sort(wordArray);
      for (int i = 0; i < wordArray.length;i++) {
        innerCount = 0;
        for (int j = i+1; j < wordArray.length;j++) {
          if(wordArray[j].equals(wordArray[i])){
            innerCount ++;
          }
        }
        if(innerCount > 0) {
          count++;
          i+=innerCount;
        }
      }
	    System.out.println("Number of Repeated Words: " + count);
      return count;
		}
    }
