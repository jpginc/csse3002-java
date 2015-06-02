package visualisation;

import static org.junit.Assert.*;

import org.junit.Test;

import de.jreality.scene.Viewer;
import visualiser.GenericCanvas;

public class TestGenericCanvas {

	private Viewer sampleViewer;
	
	@Test
	public void testCanvas1() {
		try{
			GenericCanvas sampleCanvas = new GenericCanvas(8*24);
			sampleCanvas.setViewer(sampleViewer);
			assertEquals(sampleViewer, sampleCanvas.getViewer());
		} catch(Exception e) {
			fail("Should not have thrown any exception.");
		}
	}
	
	@Test 
	public void testCanvas2() {
		try{
			GenericCanvas sampleCanvas = new GenericCanvas(8*24);			
			sampleCanvas.setViewer(null);
			assertNull(sampleCanvas.getViewer());
		} catch(Exception e) {
			fail("Should not have thrown any exception.");
		}
	}
	
	@Test
	public void testCanvas3() {
		GenericCanvas sampleCanvas = new GenericCanvas(8*24);			
		assertNotNull(sampleCanvas.getViewer());
	}
}
