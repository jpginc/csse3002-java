package visualisation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import data.SensorReading;
import de.jreality.geometry.Primitives;
import de.jreality.scene.IndexedFaceSet;
import visualiser.Canvas;
import visualiser.JaggedCanvas;



public class TestJaggeredCanvas {
	private int maxPlaySpeed = 8;
	
	@Test
	public void testCanvas1() {
		try {
			Canvas sampleCanvas = new JaggedCanvas(8*24);
			List<Integer> listInt = createArray(3,100,2,25,6);
			List<Integer> zero = createArray(0,0,0,0,0);
			SensorReading sampleReading = new SensorReading(listInt, zero);
			sampleCanvas.appendCache(sampleReading);
		} catch(Exception e) {
			fail("Should not have thrown any exception.");
		}
	}
	
	@Test(expected=Exception.class)
	public void testCanvas2() {
		Canvas sampleCanvas = new JaggedCanvas(8*24);
		sampleCanvas.appendCache(null);

	}
	
	@Test
	public void testPreviousPosition() {
		JaggedCanvas sampleCanvas = new JaggedCanvas(8*24);
		List<Integer> zero = createArray(0,0,0,0,0);
		List<Integer> listInt1 = createArray(3,100,2,25,6);
		SensorReading sampleReading1 = new SensorReading(listInt1, zero);
		SensorReading sampleReading2 = new SensorReading(listInt1, zero);
		sampleCanvas.appendCache(sampleReading1);
		sampleCanvas.appendCache(sampleReading2);

		assertTrue(sampleCanvas.next(maxPlaySpeed));
		assertTrue(sampleCanvas.next(maxPlaySpeed));
		assertTrue(sampleCanvas.previous(maxPlaySpeed));
		assertTrue(sampleCanvas.previous(maxPlaySpeed));
		assertFalse(sampleCanvas.previous(maxPlaySpeed));

	}
	
	@Test
	public void testNextPosition() {
		
	}
	
	
	private List<Integer> createArray(int one, int two, int three, int four, int five) {
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(one);
		arr.add(two);
		arr.add(three);
		arr.add(four);
		arr.add(five);
		return arr;
	}
}
