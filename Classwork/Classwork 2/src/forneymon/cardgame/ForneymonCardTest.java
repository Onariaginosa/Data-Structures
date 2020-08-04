  package forneymon.cardgame;
  
  
  /**
   *Written for debugging purposes
   *Testing to see if missingNu and other parts work for forneymon
   */
  
  /**
  * @author Ona Igbinedion
  * @author Ameya Mellacheruvu
  * @author Elise Sawan
  */
  
  
  public class ForneymonCardTest {
  
      public static void main (String[] args) {
          ForneymonCard burny = new ForneymonCard("burny", "Burnymon");
          // "Burnymon: burny"
          System.out.println(burny.toString());
          ForneymonCard missingNu = new ForneymonCard();
          System.out.println(missingNu.toString());
          // "Burnymon: MissingNu"
          ForneymonCard leafy = new ForneymonCard("leafy", "Leafymon");
          // "Leafymon: leafy"
          System.out.println( leafy.toString());
          ForneymonCard dampy = new ForneymonCard("dampy", "Dampymon");
          // "Dampymon: dampy"
          System.out.println(dampy.toString());
          ForneymonCard lulu = new ForneymonCard("Leafymon");
          System.out.println(lulu.toString());
          // "Leafymon: MissingNu"
          ForneymonCard lala = new ForneymonCard("Burnymon");
          System.out.println(lala.toString());
          // "Burnymon: MissingNu"
          ForneymonCard lolo = new ForneymonCard("Dampymon");
          System.out.println(lolo.toString());
          // "Dampymon: MissingNu"
          ForneymonCard lele = new ForneymonCard("lil c");
          System.out.println(lele.toString());
          // "Burnymon: lil c"
          ForneymonCard EltonBB = new ForneymonCard("", "Burnymon");
          // This is an error BB 
      }
      
  }