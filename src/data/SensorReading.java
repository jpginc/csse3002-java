package data;

import java.util.List;

/*
 * This class will have an array of reading from a single sensor
 */
public class SensorReading {
	
	private List<Integer> arr;
	
	public SensorReading(List<Integer> arr) {
		this.arr = arr;
	}
	
	public boolean isValid() {
		if(arr.size() == 5) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getFlex1() {
		return arr.get(0);
	}
	
	public int getFlex2() {
		return arr.get(1);
	}
	
	public AccelReading getAccel() {
		return new AccelReading(arr.get(2), arr.get(3), arr.get(4));
	}
	
	 
}
