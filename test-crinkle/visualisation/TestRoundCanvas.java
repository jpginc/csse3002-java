package visualisation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import data.SensorReading;
import visualiser.RoundCanvas;

public class TestRoundCanvas {

	@Test
	public void testCanvas1() {
		try {
			RoundCanvas sampleCanvas = new RoundCanvas(8*24);
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
		RoundCanvas sampleCanvas = new RoundCanvas(8*24);
		sampleCanvas.appendCache(null);
	}
	
	@Test
	public void testAppendCacheOffline1() {
		try {
			RoundCanvas sampleCanvas = new RoundCanvas(8*24);
			List<Integer> listInt = createArray(3,100,2,25,6);
			List<Integer> zero = createArray(0,0,0,0,0);
			SensorReading sampleReading = new SensorReading(listInt, zero);
			sampleCanvas.appendCacheOffline(sampleReading);
		} catch(Exception e) {
			fail("Should not have thrown any exception.");
		}	
	}
	
	@Test(expected=Exception.class)
	public void testAppendCacheOffline2() {
		RoundCanvas sampleCanvas = new RoundCanvas(8*24);
		sampleCanvas.appendCacheOffline(null);
	}
	
	@Test
	public void testPrevious() {
		RoundCanvas sampleCanvas = new RoundCanvas(8*24);
		List<Integer> listInt1 = createArray(3,100,2,25,6);
		List<Integer> listInt2 = createArray(5,45,12,623,4);
		List<Integer> zero = createArray(0,0,0,0,0);
		SensorReading sampleReading1 = new SensorReading(listInt1, zero);
		SensorReading sampleReading2 = new SensorReading(listInt2, zero);
		sampleCanvas.appendCache(sampleReading1);
		assertEquals(8*24, sampleCanvas.getHistroyIndex());
		sampleCanvas.appendCache(sampleReading2);
		assertEquals(8*24*2, sampleCanvas.getHistroyIndex());
		assertTrue(sampleCanvas.previous(8*24));
		assertFalse(sampleCanvas.previous(8*24+1));
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
