package visualiser;

import java.awt.Color;
import java.util.ArrayList;

import data.AccelReading;
import data.SensorReading;
import de.jreality.geometry.Primitives;
import de.jreality.plugin.JRViewer;
import de.jreality.scene.Appearance;
import de.jreality.scene.IndexedFaceSet;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.Viewer;
import de.jreality.scene.data.Attribute;
import de.jreality.scene.data.StorageModel;
import de.jreality.tools.ClickWheelCameraZoomTool;
import static de.jreality.shader.CommonAttributes.*;
import de.jreality.util.CameraUtility;
import de.jreality.util.SceneGraphUtility;

/*
 * 
 * This class contains the details of the visualisation itself ie. the sphere
 * 
 * This class is initialised with a 100 point sphere that is a dark grey color. 
 * This sphere is then mutated depending on the SensorReading that are passed to it.
 * 
 * After you have created this class you should call appendCache with all of the 
 * sensor readings. This constructs a cache of mutations that will be applied to
 * the canvas whenever the next/previous functions are called.
 * 
 * 
 */
public class JaggeredCanvas {

	//the shape to form the visualisation
	IndexedFaceSet sphere = Primitives.sphere(10);
	
	//when moving a point this value indicates which point will be moved
	private int pointIndex = 0;
	//this is the number of points in the sphere
	private int pointMax = sphere.getNumPoints();
	
	//the canvas that the sphere is painted on
	private Viewer viewer;
	private SceneGraphComponent world = SceneGraphUtility.createFullSceneGraphComponent("Crinkle");

	//a history of color values for the sphere
	private ArrayList<double[]> colorHistory = new ArrayList<double[]>();
	//a history of point values for the sphere
	private ArrayList<double[]> pointHistory = new ArrayList<double[]>();
	private int historyIndex = 0;
	
	//the current sphere points
    double[][] cachedPoints = new double[sphere.getNumPoints()][];
    double[][] originalSphere = new double[sphere.getNumPoints()][];
    double[] prevCachedPoint;
    
    //indicates how many steps to break up a sensor reading into
    private int maxStepsPerMutation;

	
    /**
     * 
     * Create the canvas with a sphere
     * 
     * @param maxStepsPerMutation
     * 	the maximum number of mutations per second. ie fps * max play speed
     */
	public JaggeredCanvas(int maxStepsPerMutation) {
		this.maxStepsPerMutation = maxStepsPerMutation;

	    //setup the jreality enviroment 
		world.setGeometry(sphere);
		JRViewer jrViewer = JRViewer.createJRViewer(world);
		jrViewer.startupLocal();
		viewer = jrViewer.getViewer();
		
		//setup the canvas to allow mousewheel zoom
		viewer.getSceneRoot().addTool(new ClickWheelCameraZoomTool());

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
	 * This function accepts a sensor reading and breaks it up into small steps
	 * these steps are cached. you should cache all sensor readings before starting
	 * the visualisation.
	 * 
	 * @param reading
	 * the sensor reading to add to the cached points, must be a valid SensorReading
	 * <pre> reading != null
	 */
	public void appendCache(SensorReading reading) {
		for(int i = 0; i < maxStepsPerMutation; i++) {
			prevCachedPoint = generatePoint(prevCachedPoint, reading.getFlex1(), 
					reading.getAccel().getX(), reading.getAccel().getY());
			generateColor(colorHistory.get(colorHistory.size() - 1), reading.getFlex2(), 
					reading.getFlex1() % 2 == 0);
		}
        pointIndex++;
		if(pointIndex == pointMax) {
			pointIndex = 0;
		}
		prevCachedPoint = cachedPoints[pointIndex];
	}
	
	/**
	 * Applies the next mutation, if any, to the canvas. 
	 * 
	 * @param steps
	 * the number of steps to mutate
	 * @return
	 * 	True if a mutation has occurred
	 *  False if all mutations have occurred ie the end of the visualisation is reached
	 */
	public boolean next(int steps) {
		for(int i = 0; i < steps; i++) {
			historyIndex++;
			if(historyIndex >= colorHistory.size()) {
				return false;
			}
			setPoint(historyIndex);
		}
		setColor(historyIndex);
		reDraw();
		return true;
	}
		
	/**
	 * reverses a mutation
	 * 
	 * @param steps
	 * the number of steps to mutate
	 * @return
	 * 	True if a mutation has occurred
	 *  False if all mutations have occurred ie the start of the visualisation is reached
	 */
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
	private double[] generatePoint(double[] prev, double x, double y, double z) {
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
	private void setPoint(int historyIndex) {
		double [] toSet = pointHistory.get(historyIndex);
		int index = (int) toSet[3];
		double [] pointPos = {toSet[0], toSet[1], toSet[2]};
		cachedPoints[index] = pointPos;
	}
	
	/**
	 * re-draws the sphere using cachedPoints
	 * adjusts the camera to fit the visualisation.
	 */
	private void reDraw() {
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
	private void setColor(int index) {
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
	public void generateColor(double[] base, int reading, boolean direction) {
		
		double oldRed = base[0];
		double oldGreen = base[1];
		double oldBlue = base[2];
		
		//Red is the three lease significant bits, Green is the middle 3 etc
        double stepRed = (reading & 8);
        double stepGreen = ((reading>>3) & 8);
        double stepBlue = ((reading>>6) & 8);
        
        //we don't want the color to jump from 255 -> 0 as it will make a big jump in the color of the sphere
        //as such we will leave the old color if it goes beyond these values
		double newBlue, newRed, newGreen;
		
		//if we only add to the RGB values then every visualization will turn white
		//the direction variable tells the values to go towards white or black
		double modifier = direction ? 1 : -1;
		
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

	public Viewer getViewer() {
		return viewer;
	}

	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}
	
	public int getHistroyIndex(){
		return historyIndex;
	}
	
	public int getPointIndex(){
		return pointIndex;
	}
}
