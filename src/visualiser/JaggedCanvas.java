package visualiser;

import java.awt.Color;
import java.util.ArrayList;

import data.AccelReading;
import data.SensorReading;
import de.jreality.geometry.Primitives;
import de.jreality.plugin.JRViewer;
import de.jreality.scene.Appearance;
import de.jreality.scene.IndexedFaceSet;
import de.jreality.scene.data.Attribute;
import de.jreality.scene.data.StorageModel;
import de.jreality.tools.ClickWheelCameraZoomTool;
import static de.jreality.shader.CommonAttributes.*;
import de.jreality.util.CameraUtility;

/*
 * 
 * This class contains the details of the visualization itself i.e. the sphere
 * 
 * This class is initialized with a 100 point sphere that is a dark grey color. 
 * This sphere is then mutated depending on the SensorReading that are passed to it.
 * 
 * After you have created this class you should call appendCache with all of the 
 * sensor readings. This constructs a cache of mutations that will be applied to
 * the canvas whenever the next/previous functions are called.
 * 
 * 
 */
public class JaggedCanvas extends GenericCanvas implements Canvas {

	//the shape to form the visualization
	IndexedFaceSet sphere = Primitives.sphere(10);

	//when moving a point this value indicates which point will be moved
	protected int pointIndex = 0;
	//this is the number of points in the sphere
	protected int pointMax = sphere.getNumPoints();

	//a history of color values for the sphere
	protected ArrayList<double[]> colorHistory = new ArrayList<double[]>();
	//a history of point values for the sphere
	protected ArrayList<double[]> pointHistory = new ArrayList<double[]>();

	//the current sphere points
	double[][] cachedPoints = new double[sphere.getNumPoints()][];
	double[][] originalSphere = new double[sphere.getNumPoints()][];
	double[] prevCachedPoint;

	/**
	 * 
	 * Create the canvas with a sphere
	 * 
	 * @param maxStepsPerMutation
	 * 	the maximum number of mutations per second. ie fps * max play speed
	 */
	public JaggedCanvas(int maxStepsPerMutation) {
		super(maxStepsPerMutation);
		world.setGeometry(sphere);

		//Initialise the color of the sphere to dark grey. (must be after initialising the enviroment?)
		double[] base = {70, 70, 70};
		colorHistory.add(base);
		this.setColor(historyIndex);

		//populate the cached points
		sphere.getVertexAttributes(Attribute.COORDINATES).toDoubleArrayArray(cachedPoints);
		sphere.getVertexAttributes(Attribute.COORDINATES).toDoubleArrayArray(originalSphere);

		//make the point history array the same size as the color history
		double [] temp = cachedPoints[historyIndex];
		prevCachedPoint = new double[] {temp[0], temp[1], temp[2], pointIndex};
		pointHistory.add(prevCachedPoint);
	}


	/**
	 * 
	 */
	@Override
	public void appendCache(SensorReading reading) {
		for(int i = 0; i < maxStepsPerMutation; i++) {
			prevCachedPoint = generatePoint(prevCachedPoint, reading.getFlex1(), 
					reading.getAccel().getX(), reading.getAccel().getY());
		}
		generateColor(colorHistory.get(colorHistory.size() - 1), reading.getFlex2());
		pointIndex++;
		if(pointIndex == pointMax) {
			pointIndex = 0;
		}
		prevCachedPoint = cachedPoints[pointIndex];
	}

	/**
	 * 
	 * @param reading
	 */
	public void appendCacheOffline(SensorReading reading) {
		for(int i = 0; i < maxStepsPerMutation; i++) {
			prevCachedPoint = generatePoint(prevCachedPoint, reading.getFlex1(), 
					reading.getAccel().getX(), reading.getAccel().getY());
			setPoint(historyIndex++);
		}
		generateColor(colorHistory.get(colorHistory.size() - 1), reading.getFlex2());
		pointIndex++;
		if(pointIndex == pointMax) {
			pointIndex = 0;
		}
		prevCachedPoint = cachedPoints[pointIndex];
	}

	/**
	 * call after appending all the sensor readings using appendCache (or to restart the canvas)
	 */
	public void reset() {
		historyIndex = 0;
		cachedPoints = originalSphere.clone();
	}

	/**
	 * 
	 */
	@Override
	public boolean next(int steps) {
		if(historyIndex >= pointHistory.size()) {
			return false;
		}
		for(int i = 0; i < steps; i++) {
			historyIndex++;
			if(historyIndex >= pointHistory.size()) {
				break;
			}
			setPoint(historyIndex);
		}
		setColor(historyIndex);
		reDraw();
		return true;
	}

	/* (non-Javadoc)
	 * @see visualiser.Canvas#previous(int)
	 */
	@Override
	public boolean previous(int steps) {
		for(int i = 0; i < steps; i++) {
			historyIndex--;
			if(historyIndex < 0) {
				cachedPoints = originalSphere.clone();
				reDraw();
				setColor(0);
				return false;
			}
			setPoint(historyIndex);
		}
		setColor(historyIndex);
		reDraw();
		return true;
	}

	/**
	 * generates the next position a point should be in based on the previous point 
	 * 
	 * @param prev
	 * 	previous point
	 * @param x
	 * 	the x distance to move (will be divided by maxstepsPerMutation)
	 * @param y
	 *  the y distance to move (will be divided by maxstepsPerMutation)
	 * @param z
	 *  the z distance to move (will be divided by maxstepsPerMutation)
	 * @return
	 *  the next position of the point
	 */
	protected double[] generatePoint(double[] prev, double x, double y, double z) {
		double[] point = {prev[0] + (x / maxStepsPerMutation),
				prev[1] + (y / maxStepsPerMutation),
				prev[2] + (z / maxStepsPerMutation),
				pointIndex};
		pointHistory.add(point);
		return point;
	}

	/**
	 * sets the spheres point based on the cache
	 * @param historyIndex
	 *  the part of the history we are up to 
	 */
	protected void setPoint(int historyIndex) {
		double [] toSet = pointHistory.get(historyIndex);
		int index = (int) toSet[3];
		double [] pointPos = {toSet[0], toSet[1], toSet[2]};
		cachedPoints[index] = pointPos;
	}

	/**
	 * re-draws the sphere using cachedPoints
	 * adjusts the camera to fit the visualisation.
	 */
	protected void reDraw() {
		sphere.setVertexAttributes(Attribute.COORDINATES,StorageModel.DOUBLE_ARRAY.array(3).createReadOnly(cachedPoints));
		try {
			CameraUtility.encompass(viewer);
		} catch(Exception e) {
			//no need to worry about the exception
		}
	}

	/**
	 * sets the color of the sphere using the color kept in colorHistory
	 * @param index
	 * 	the point in history to color to
	 */
	protected void setColor(int index) {
		index = index / maxStepsPerMutation;
		Appearance ap = world.getAppearance();
		double[] rgb = colorHistory.get(index);
		Color newColor = new Color((int) rgb[0], (int) rgb[1], (int) rgb[2]);
		ap.setAttribute(POLYGON_SHADER+"."+DIFFUSE_COLOR, newColor);
	}

	/**
	 * Gets the next color change and inserts it into the colorHistory array
	 * 
	 * @param base
	 *  the previous color rgb values
	 * @param reading
	 *  the distance to move the color between 0 and 1024 is used
	 * @param direction
	 *  the direction to move the color. true goes towards white, false towards black
	 */
	public void generateColor(double[] base, int reading) {
		double modifier = 1.0;

		double oldRed = base[0];
		double oldGreen = base[1];
		double oldBlue = base[2];

		//if we only add to the RGB values then every visualization will turn white
		//the direction variable tells the values to go towards white or black
		if(reading < 0) {
			reading *= -1;
			modifier = -1.0;
		}

		//a flex sensor reading is normally between -200 and +150
		//Red is the three lease significant bits, Green is the middle 3 etc
		double stepRed = (reading & 4);
		double stepGreen = ((reading>>2) & 4 );
		double stepBlue = ((reading>>5) & 4);

		//we don't want the color to jump from 255 -> 0 as it will make a big jump in the color of the sphere
		//as such we will leave the old color if it goes beyond these values
		double newBlue, newRed, newGreen;

		//System.out.println("red " + (stepRed * modifier) + " green " + (stepGreen * modifier) + " blue " + (stepBlue * modifier));

		newRed = oldRed + (modifier * stepRed);
		if(newRed < 70 || newRed > 253) {
			newRed = oldRed;
		}
		newGreen = oldGreen + (modifier * stepGreen);
		if(newGreen < 70 || newGreen > 253) {
			newGreen = oldGreen;
		}
		newBlue = oldBlue + (modifier * stepBlue);
		if(newBlue < 70 || newBlue > 253) {
			newBlue = oldBlue;
		}

		//insert the new values into the color history
		colorHistory.add(new double[] {newRed, newGreen, newBlue});
	}

	/* (non-Javadoc)
	 * @see visualiser.Canvas#getPointIndex()
	 */
	@Override
	public int getPointIndex(){
		return pointIndex;
	}
}
