/**
 * JUnit Tests for FlippingForneymonCard methods.
 * We are not testing each individual constructor because we simply don't have the time for that.
 */
package forneymon.cardgame;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Ona Igbinedion
 * @author Elise Sawan
 * @author Ameya Melacheruvu
 */
public class FlippingForneymonCardTests {
	
	

	/**
	 * Test method for {@link forneymon.cardgame.FlippingForneymonCard#toString()}.
	 */
	@Test
	public void testToString() {
		FlippingForneymonCard Ona = new FlippingForneymonCard("Ona", "Dampymon", false);
		assertEquals("Dampymon: Ona", Ona.toString());
		Ona.flip();
		assertEquals("?: ?", Ona.toString());
		
		FlippingForneymonCard Elise = new FlippingForneymonCard("Cinamonroll", "Leafymon");
		assertEquals("?: ?", Elise.toString());
		Elise.flip();
		assertEquals("Leafymon: Cinamonroll", Elise.toString());
		
		FlippingForneymonCard Ameya = new FlippingForneymonCard("VscoGurl");
		assertEquals("?: ?", Ameya.toString());
		Ameya.flip();
		assertEquals("Burnymon: VscoGurl", Ameya.toString());
		
		FlippingForneymonCard EltonBB = new FlippingForneymonCard();
		assertEquals("?: ?", EltonBB.toString());
		EltonBB.flip();
		assertEquals("Burnymon: MissingNu", EltonBB.toString());
	}

	/**
	 * Test method for {@link forneymon.cardgame.FlippingForneymonCard#flip()}.
	 */
	@Test
	public void testFlip() {
		FlippingForneymonCard lilC = new FlippingForneymonCard("lilC", "Burnymon", false);
		assertEquals(true, lilC.flip());
		assertEquals(false, lilC.flip());
	}

	/**
	 * Test method for {@link forneymon.cardgame.FlippingForneymonCard#match(forneymon.cardgame.FlippingForneymonCard)}.
	 */
	@Test
	public void testMatch() {
		FlippingForneymonCard Ona = new FlippingForneymonCard("Ona", "Dampymon", false);
		// Ona is face-up
		FlippingForneymonCard Onariaginosa = new FlippingForneymonCard("Ona", "Dampymon", false);
		// Onariaginosa is face-up
		FlippingForneymonCard WHYYYYY = new FlippingForneymonCard("WHYYYYY", "Leafymon");
		// WHYYYYY is face-down by default
		
		assertEquals(1, Ona.match(Onariaginosa));
		// Ona and Onariaginosa are face-up and the same
		Ona.flip();
		// Ona is face-down
		assertEquals(2,Onariaginosa.match(Ona));
		// Ona is face down, Onariaginosa is face-up
		assertEquals(2, Onariaginosa.match(WHYYYYY));
		//Onariaginosa is face up,WHYYYYY is face-down
		WHYYYYY.flip();
		// WHYYYYY is face-up
		assertEquals(0, Onariaginosa.match(WHYYYYY));
		// Onariaginosa and WHYYYYY are face up with different names and types
		
	}

}
