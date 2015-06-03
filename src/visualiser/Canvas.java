package visualiser;

import data.SensorReading;
import de.jreality.scene.Viewer;

public interface Canvas {

	/**
	 * 
	 * This function accepts a sensor reading and breaks it up into small steps
	 * these steps are cached. you should cache all sensor readings before starting
	 * the visualization.
	 * 
	 * @param reading
	 * the sensor reading to add to the cached points, must be a valid SensorReading
	 * <pre> reading != null
	 */
	public abstract void appendCache(SensorReading reading);
	public abstract void appendCacheOffline(SensorReading reading);

	/**
	 * Applies the next mutation, if any, to the canvas. 
	 * 
	 * @param steps
	 * the number of steps to mutate
	 * @return
	 * 	True if a mutation has occurred
	 *  False if all mutations have occurred i.e. the end of the visualization is reached
	 */
	public abstract boolean next(int steps);

	/**
	 * reverses a mutation
	 * 
	 * @param steps
	 * the number of steps to mutate
	 * @return
	 * 	True if a mutation has occurred
	 *  False if all mutations have occurred i.e. the start of the visualization is reached
	 */
	public abstract boolean previous(int steps);

	public abstract Viewer getViewer();

	public abstract void setViewer(Viewer viewer);

	public abstract int getHistroyIndex();

	public abstract int getPointIndex();

	public abstract void reset();

}