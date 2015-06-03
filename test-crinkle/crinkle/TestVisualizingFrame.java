package crinkle;

import static org.junit.Assert.*;

import org.junit.Test;

import visualiser.Visualiser;

public class TestVisualizingFrame {

	@Test
	public void testVisualiser1() {
		VisualizingFrame sampleFrame = new VisualizingFrame();
		assertNull(sampleFrame.getVisualiser());
	}
	
	@Test
	public void testVisualiser2() {
		Visualiser sampleVisualizer = new Visualiser();
		VisualizingFrame sampleFrame = new VisualizingFrame();
		sampleFrame.setVisualiser(sampleVisualizer);
		assertEquals(sampleVisualizer, sampleFrame.getVisualiser());
	}
}
