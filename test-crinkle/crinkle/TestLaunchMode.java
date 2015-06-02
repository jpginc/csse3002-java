package crinkle;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLaunchMode {
	private javax.swing.JLabel lblStatus;
	
	@Test
	public void testFrame1() {
		LaunchMode sampleMode = new LaunchMode();
		assertNull(sampleMode.getCurrentFrame());
	}
	
	@Test
	public void testFrame2() {
		LaunchMode sampleMode = new LaunchMode();
		VisualizingFrame sampleFrame = new VisualizingFrame();
		sampleMode.setCurrentFrame(sampleFrame);
		assertEquals(sampleFrame, sampleMode.getCurrentFrame());
	}
	
	@Test
	public void testDestoryFrame() {
		LaunchMode sampleMode = new LaunchMode();
		VisualizingFrame sampleFrame = new VisualizingFrame();
		sampleMode.setCurrentFrame(sampleFrame);
		assertEquals(sampleFrame, sampleMode.getCurrentFrame());
		sampleMode.destroyFrame();
		assertNull(sampleMode.getCurrentFrame());
	}
	
	@Test
	public void testFeedback() {
		try {
			LaunchMode sampleMode = new LaunchMode();
			sampleMode.setFeedback("Good");
			sampleMode.setTxfRecentFile("Good");
		} catch(Exception e) {
			fail("Should not have thrown any exception.");
		}
	}
}
