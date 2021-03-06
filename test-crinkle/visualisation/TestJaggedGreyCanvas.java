package visualisation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import visualiser.JaggedGreyCanvas;
import data.SensorReading;

public class TestJaggedGreyCanvas {
	
	@Test
	public void testCanvas1() {
		try {
			JaggedGreyCanvas sampleCanvas = new JaggedGreyCanvas(8*24);
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
		JaggedGreyCanvas sampleCanvas = new JaggedGreyCanvas(8*24);
		sampleCanvas.appendCache(null);

	}
	
	@Test
	public void testAppendCacheOffline1() {
		try {
			JaggedGreyCanvas sampleCanvas = new JaggedGreyCanvas(8*24);
			List<Integer> tempArr1 = createArray(123, 568, 890, 678, 555);
			List<Integer> zero = createArray(0,0,0,0,0);
			SensorReading sampleReading = new SensorReading(tempArr1, zero);
			sampleCanvas.appendCacheOffline(sampleReading);
		} catch (Exception e) {
			fail("Should not have thrown any exception.");
		}
		
	}
	
	@Test(expected=Exception.class)
	public void testAppendCacheOffline2() {
		JaggedGreyCanvas sampleCanvas = new JaggedGreyCanvas(8*24);
		sampleCanvas.appendCacheOffline(null);
	}
	
	@Test
	public void testNext() {
		JaggedGreyCanvas sampleCanvas = new JaggedGreyCanvas(8*24);
		List<Integer> tempArr1 = createArray(123, 568, 890, 678, 555);
		List<Integer> zero = createArray(0,0,0,0,0);
		SensorReading sampleReading = new SensorReading(tempArr1, zero);
		sampleCanvas.appendCache(sampleReading);
		assertTrue(sampleCanvas.next(8*24));
		assertTrue(sampleCanvas.next(8*24));
		assertFalse(sampleCanvas.next(8*24));
	}
	
	@Test
	public void testPrevious() {
		JaggedGreyCanvas sampleCanvas = new JaggedGreyCanvas(8*24);
		List<Integer> tempArr1 = createArray(123, 568, 890, 678, 555);
		List<Integer> tempArr2 = createArray(564, 562, 214, 13, 569);
		List<Integer> zero = createArray(0,0,0,0,0);
		SensorReading sampleReading1 = new SensorReading(tempArr1, zero);
		SensorReading sampleReading2 = new SensorReading(tempArr2, zero);
		sampleCanvas.appendCache(sampleReading1);
		sampleCanvas.appendCache(sampleReading2);
		assertTrue(sampleCanvas.next(8*24));
		assertTrue(sampleCanvas.next(8*24));
		assertTrue(sampleCanvas.next(8*24));
		assertFalse(sampleCanvas.next(8*24));
		assertTrue(sampleCanvas.previous(8*24));
		assertTrue(sampleCanvas.previous(8*24));
		assertFalse(sampleCanvas.previous(8*24));
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
