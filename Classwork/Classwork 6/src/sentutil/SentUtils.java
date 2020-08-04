package sentutil;
import java.util.*;

/**
 * Simple library which might (outside of this assignment) contain
 * various functions related to some sentence utilities.
 */

public class SentUtils {

    /**
     * Returns the number of unique, repeated words that are found
     * in the given sentence sent
     * @param sent The sentence in which to count repeated words
     * @return The number of unique, repeated words
     */
    
    public static int repeats (String sent) {
        String[] words = sent.split(" ");
        int count = 0;
        HashMap<String, Integer> wordMap = new HashMap<String, Integer>();

        // Compare each pair of words
          for (int i = 0; i < words.length; i++) {
              if (!wordMap.containsKey(words[i])){
                  wordMap.put(words[i], i);
                  count++;
              }
          }
        return count;
    }
}
