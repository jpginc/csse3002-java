package data;

import java.util.List;

/*
 * This class will have an array of reading from a single sensor
 */
public class SensorReading {
	
	private List<Integer> sensorValue;
	
	public SensorReading(List<Integer> sensorValue) {
		sensorValue.set(0, sensorValue.get(0) -480);
		sensorValue.set(1, sensorValue.get(1) -480);
		sensorValue.set(2, sensorValue.get(2) -480);
		sensorValue.set(3, sensorValue.get(3) -480);
		this.sensorValue = sensorValue;
	}
	
	public boolean isValid() {
		if(sensorValue.size() == 5) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getFlex1() {
		return sensorValue.get(0);
	}
	
	public int getFlex2() {
		return sensorValue.get(1);
	}
	
	public AccelReading getAccel() {
		return new AccelReading(sensorValue.get(2), sensorValue.get(3), sensorValue.get(4));
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < sensorValue.size(); i++) {
			str += sensorValue.get(i) + " ";
		}
		return str;
	} 
}
