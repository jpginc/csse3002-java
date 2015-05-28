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
import static de.jreality.shader.CommonAttributes.*;
import de.jreality.util.CameraUtility;
import de.jreality.util.SceneGraphUtility;

/*
 * TODO 
 * overview of the classes.
 * 
 * 
 */
public class Canvas {
	static final boolean FORWARD = false;
	static final boolean REVERSE = true;

	//the shape to form the visualisation
	IndexedFaceSet sphere = Primitives.sphere(10);
	
	//when moving a point this value indicates which point will be moved
	private int pointIndex = 0;
	//this is the number of points in the sphere
	private int pointMax = sphere.getNumPoints();
	
	//the canvas that the sphere is painted on
	private Viewer viewer;
	private SceneGraphComponent world = SceneGraphUtility.createFullSceneGraphComponent("Crinkle");
	
	//an history of color values for the sphere
	private ArrayList<double[]> colorHistory = new ArrayList<double[]>();
	//an history of point values for the sphere
	private ArrayList<double[]> pointHistory = new ArrayList<double[]>();
	private int historyIndex = 0;
	
	//the current sphere points
    double[][] cachedPoints = new double[sphere.getNumPoints()][];
    double[][] originalSphere = new double[sphere.getNumPoints()][];
    double[] prevCachedPoint;
    
    //indicates how many steps to break up a sensor reading into
    private int maxStepsPerMutation;

	
	public Canvas(int maxStepsPerMutation) {
		this.maxStepsPerMutation = maxStepsPerMutation;

	    //setup the jreality enviroment 
		world.setGeometry(sphere);
		JRViewer jrViewer = JRViewer.createJRViewer(world);
		jrViewer.startupLocal();
		viewer = jrViewer.getViewer();

        //Initialise the color of the sphere to black. (must be after initialising the enviroment?)
	    double[] base = {70, 70, 70};
	    colorHistory.add(base);
	    this.setColor(historyIndex);
	    
	    //populate the cached points
		sphere.getVertexAttributes(Attribute.COORDINATES).toDoubleArrayArray(cachedPoints);
		sphere.getVertexAttributes(Attribute.COORDINATES).toDoubleArrayArray(originalSphere);
		//make the point hisotry array the same size as the color hisotry
		double [] temp = cachedPoints[historyIndex];
        prevCachedPoint = new double[] {temp[0], temp[1], temp[2], pointIndex};
		pointHistory.add(prevCachedPoint);
    }
	
	
	public void appendCache(SensorReading reading) {
        prevCachedPoint = generatePoint(prevCachedPoint, reading.getFlex1(), 
        		reading.getAccel().getX(), reading.getAccel().getY());
        generateColor(colorHistory.get(colorHistory.size() - 1), reading.getFlex2(), 
        		reading.getFlex1() % 2 == 0);
	}
	
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
	 * This function changes the point that the mutate function works on
	 * 
	 * Call this whenever you change sensor reading
	 * 
	 */
	public void next() {
		pointIndex++;
		if(pointIndex == pointMax) {
			pointIndex = 0;
		}
		prevCachedPoint = cachedPoints[pointIndex];
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
	 * sets the spheres point based on the 
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
	 */
	private void reDraw() {
		sphere.setVertexAttributes(Attribute.COORDINATES,StorageModel.DOUBLE_ARRAY.array(3).createReadOnly(cachedPoints));
		CameraUtility.encompass(viewer);
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
