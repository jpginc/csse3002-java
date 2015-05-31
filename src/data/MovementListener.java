package data;

public interface MovementListener {
	/**
	 * the callback for when new movement data has been added to the movement file
	 * @param s 
	 */
	public abstract void movementNotify(SensorReading s);
}
