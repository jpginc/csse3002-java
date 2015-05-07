package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovementData {
	private List<SensorReading> sensorData;
	private int prevIndex;
	private int nextIndex;
	
	/** Constructor with file name generated by hardware **/
	public MovementData(String fileName) {
		this.sensorData = buildMovementData(fileName);
		this.nextIndex = 0;
	}
	
	/** Read data from sample file generated by hardware **/
	private List<SensorReading> buildMovementData(String fileName) {
		List<SensorReading> store = new ArrayList<SensorReading>();		
			
		// read from a file
		Scanner sc;
		try {
			sc = new Scanner(new File(fileName));		
			while (sc.hasNextLine()) {
				String sCurrentLine = sc.nextLine();
				List<Integer> tempArray = new ArrayList<Integer>();
				String[] sr = sCurrentLine.split(",");
				for(int i=0; i<sr.length; i++) {
					if(isNumeric(sr[i])) {
						int num = Integer.parseInt(sr[i]);
						tempArray.add(num);
						//System.out.println(num);
					}
				}					
				SensorReading tempReading = new SensorReading(tempArray);
				store.add(tempReading);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		return store;	
	}
	
	public boolean isNumeric(String str) {  
		try {  
			double d = Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe) {  
			return false;  
		}  
		return true;  
	}
	
	/** Check if every element in sensorData is a valid SensorReading **/
	public boolean validate() {
		for(int i=0; i<sensorData.size(); i++) {
			if(!sensorData.get(i).isValid()) {
				return false;
			}
		}
		return true;
	}
	
	public SensorReading getNext() {
		if(nextIndex != numOfSensorReading()) {
			SensorReading next = sensorData.get(nextIndex);
			setPrev();
			setNext();
			return next;
		} else {
			return null;
		}
	}
	
	public SensorReading getPrevious() {
		if(nextIndex == 0) {
			return null;
		} else {
			return sensorData.get(prevIndex);
		}
	}
	
	public void setNext() {
		nextIndex++;
	}
	
	public void setPrev() {
		prevIndex = nextIndex;
	}

	public List<SensorReading> getSensorData() {
		return sensorData;
	}

	public void setSensorData(List<SensorReading> sensorData) {
		this.sensorData = sensorData;
	}
	
	private int numOfSensorReading() {
		return sensorData.size();		
	}
}
