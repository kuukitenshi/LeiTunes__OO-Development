package domain.core;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateTests {

	
	@Test
	@DisplayName("Checks state change by increment: change for a supported rate")
	public void testInc() {
		Rate rate1 = Rate.OK;
		assertEquals(Rate.GOOD, rate1.inc());
		
		Rate rate2 = Rate.VERY_GOOD;
		assertEquals(Rate.VERY_GOOD, rate2.inc());
	}
	
	@Test
	@DisplayName("Checks state change by decrement: change for a supported rate")
	public void testDec() {
		Rate rate1 = Rate.UNRATED;
		assertEquals(Rate.UNRATED, rate1.dec());
		
		Rate rate2 = Rate.OK;
		assertEquals(Rate.BAD, rate2.dec());
	}
	
}
