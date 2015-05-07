package data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class TestData {
	
	@Test
	public void readData1() {
		SensorReading next;
		SensorReading prev;
		MovementData tMovementData = new MovementData("sample.txt");
		assertTrue(tMovementData.validate());
		
		prev = tMovementData.getPrevious();
		assertNull(prev);
		
		List<Integer> tempArr1 = createArray(0, 0, 0, 0, 1);		
		SensorReading nextMock1 = new SensorReading(tempArr1);
		next = tMovementData.getNext();
		compareEquals(nextMock1, next);
		prev = tMovementData.getPrevious();
		compareEquals(nextMock1, prev);
				
		List<Integer> tempArr2 = createArray(123, 568, 890, 678, 555);
		SensorReading nextMock2 = new SensorReading(tempArr2);
		next = tMovementData.getNext();
		compareEquals(nextMock2, next);
		prev = tMovementData.getPrevious();
		compareEquals(nextMock2, prev);
		
		List<Integer> tempArr3 = createArray(888, 900, 1020, 866, 545);
		SensorReading nextMock3 = new SensorReading(tempArr3);
		next = tMovementData.getNext();
		compareEquals(nextMock3, next);
		prev = tMovementData.getPrevious();
		compareEquals(nextMock3, prev);
		
		List<Integer> tempArr4 = createArray(555, 132, 557, 899, 776);
		SensorReading nextMock4 = new SensorReading(tempArr4);
		next = tMovementData.getNext();
		compareEquals(nextMock4, next);
		prev = tMovementData.getPrevious();
		compareEquals(nextMock4, prev);
		
		List<Integer> tempArr5 = createArray(554, 898, 766, 343, 123);
		SensorReading nextMock5 = new SensorReading(tempArr5);
		next = tMovementData.getNext();
		compareEquals(nextMock5, next);
		prev = tMovementData.getPrevious();
		compareEquals(nextMock5, prev);
		
		List<Integer> tempArr6 = createArray(543, 887, 989, 22, 666);
		SensorReading nextMock6 = new SensorReading(tempArr6);
		next = tMovementData.getNext();
		compareEquals(nextMock6, next);
		prev = tMovementData.getPrevious();
		compareEquals(nextMock6, prev);
		
		List<Integer> tempArr7 = createArray(666, 845, 274, 567, 787);
		SensorReading nextMock7 = new SensorReading(tempArr7);
		next = tMovementData.getNext();
		compareEquals(nextMock7, next);
		prev = tMovementData.getPrevious();
		compareEquals(nextMock7, prev);
		
		// reaches the end of the list
		next = tMovementData.getNext();
		assertNull(next);
		prev = tMovementData.getPrevious();
		compareEquals(nextMock7, prev);
		
	}
	
	@Test
	public void readData2() {
		MovementData tMovementData = new MovementData("sample2.txt");
		assertFalse(tMovementData.validate());
	}
	
	@Test
	public void readData3() {
		MovementData tMovementData = new MovementData("sample3.txt");
		assertFalse(tMovementData.validate());
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
	
	private void compareEquals(SensorReading next, SensorReading nextMock) {
		assertEquals(next.getFlex1(), nextMock.getFlex1());
		assertEquals(next.getFlex2(), nextMock.getFlex2());
		assertEquals(next.getAccel().getX(), nextMock.getAccel().getX());
		assertEquals(next.getAccel().getY(), nextMock.getAccel().getY());
		assertEquals(next.getAccel().getZ(), nextMock.getAccel().getZ());
	}
}
