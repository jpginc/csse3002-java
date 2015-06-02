package crinkle;

import static org.junit.Assert.fail;

import org.junit.Test;

public class TestRealTimeMode {

	@Test
	public void testDestory() {
		try {
			RealTimeMode sampleMode = new RealTimeMode();
			sampleMode.destroy();
		} catch (Exception e) {
			fail("Should not have thrown any exception.");
		}
	}
}
