package crinkle;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import data.SensorReading;
import de.jreality.geometry.Primitives;
import de.jreality.scene.IndexedFaceSet;
import visualiser.Canvas;
import visualiser.JaggeredCanvas;



public class TestCanvas {
	private int maxPlaySpeed = 8;
	
//	IndexedFaceSet sphere = Primitives.sphere(10);
	
	@Test
	public void testNextPosition() {
		Canvas sampleCanvas = new JaggeredCanvas(8*24);
		List<Integer> listInt = createArray(3,100,2,25,6);
		//SensorReading sampleReading = new SensorReading(listInt);
		
		assertEquals(0, sampleCanvas.getPointIndex());
		assertEquals(1, sampleCanvas.getPointIndex());
	}
	
	@Test
	public void testPreviousPosition() {
		Canvas sampleCanvas = new JaggeredCanvas(8*24);
		List<Integer> listInt = createArray(3,100,2,25,6);
		
		assertEquals(0, sampleCanvas.getHistroyIndex());
		sampleCanvas.next(maxPlaySpeed);
		assertEquals(1, sampleCanvas.getHistroyIndex());
		sampleCanvas.next(maxPlaySpeed);
		assertEquals(2, sampleCanvas.getHistroyIndex());
		sampleCanvas.next(maxPlaySpeed);
		assertEquals(3, sampleCanvas.getHistroyIndex());
		
		sampleCanvas.previous(maxPlaySpeed);
		assertEquals(2, sampleCanvas.getHistroyIndex());
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
