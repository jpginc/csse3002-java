package visualiser;

import java.awt.Color;
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
import de.jreality.util.SceneGraphUtility;


public class Canvas {
	IndexedFaceSet test = Primitives.sphere(10);
	Integer pointsIndex = 0;
	Integer pointsYIndex = 0;
	Integer timer = 0;
	
	//@ Josh G
	//when moving a point this value indicates which axis to move along
	private int axis = 0;
	//there are 3 axis a point can move along
	private int maxAxis = 3;
	//when moving a point this value indicates which point will be moved
	private int point = 0;
	//this is the number of points in the sphere
	private int pointMax = test.getNumPoints();

	double random;
	double diff;
	private Viewer viewer;
	private SceneGraphComponent world = SceneGraphUtility.createFullSceneGraphComponent("test");
	//Initialise the color of the sphere to black. also used to keep track of the color of the sphere
	private Color base = new Color(0,0,0);
	
	public Canvas() {
		//SceneGraphComponent world = new SceneGraphComponent();
		world.setGeometry(test);
		JRViewer jrViewer = JRViewer.createJRViewer(world);
		jrViewer.startupLocal();
		viewer = jrViewer.getViewer();
		//System.out.println(a == null);
		this.changeColor(new AccelReading(0,0,0));
		//this.changeColor(null);
		
	}
	
	/*
	 * This function is called by the visualizer to mutate the image one step
	 * 
	 * Whenever you want to use the next sensor reading call next()
	 * 
	 * @param reading
	 *  The sensor reading to mutate with
	 * 
	 * @param stepCount
	 *  The number of steps to break the sensor readings into
	 *  
	 */
	public void mutate(SensorReading reading, int stepCount) {
		movePoint(((double) reading.getFlex1()) / stepCount);
		movePoint(((double) reading.getFlex2()) / stepCount);
		changeColor(reading.getAccel(), stepCount);
	}
	
	/*
	 * This function changes the point that the mutate function works on
	 * 
	 * Call this whenever you change sensor reading
	 * 
	 * @param reverse
	 * if set to true it changes to the previous point instead of the next one (for rewind)
	 */
	public void next(boolean reverse) {
		point += reverse ? -1 : 1;
		if(point == -1){
			point = pointMax -1 ;
		} else if(point == pointMax) {
			point = 0;
		}
		
		axis += reverse ? -1 : 1;
		if(axis == -1) {
			axis = maxAxis - 1;
		} else if(axis == maxAxis) {
			axis = 0;
		}
	}
	
	/*
	 * Moves the point value distance on the current axis
	 * 
	 * @param value
	 *  The distance to move the point
	 */
	private void movePoint(double value) {
        double[][] points=new double[test.getNumPoints()][];
		test.getVertexAttributes(Attribute.COORDINATES).toDoubleArrayArray(points);
		points[point][axis] += value;
		test.setVertexAttributes(Attribute.COORDINATES,StorageModel.DOUBLE_ARRAY.array(3).createReadOnly(points));

	}

	/*
	 * changes the color of the sphere easing the color change according to the number of steps
	 * 
	 * todo this functions doesn't scale as steps gets larger!
	 */
	public void changeColor(AccelReading accelReading, int steps) {
		Appearance ap = world.getAppearance();
		
		int stepZ = accelReading.getZ() / steps;
		int stepX = accelReading.getX() / steps;
		int stepY = accelReading.getY() / steps;

        int oldBlue = base.getBlue();
		int oldRed = base.getRed();
		int oldGreen = base.getGreen();	
		int newBlue, newRed, newGreen;

		newBlue = (oldBlue + stepZ) % 255;
        newRed = (oldRed + stepX) % 255;
        newGreen = (oldGreen + stepY) % 255;					
		
		base = new Color(newRed, newGreen, newBlue);
		 
		ap.setAttribute(POLYGON_SHADER+"."+DIFFUSE_COLOR, base);
	}

	/*
	 * moves a point and changes the color according to a random number
	 */
	public void mutate(int value) {
        double[][] points=new double[test.getNumPoints()][];
		test.getVertexAttributes(Attribute.COORDINATES).toDoubleArrayArray(points);
		// Needs both positive and negative random number 
		// and -3 <= random <= 3 (pattern is displayed in the canvas)
		if(timer == 0) {
			random = Math.random() * 6 - 3;
			diff = random / 24;
			random = diff;
		}
        //points[pointsIndex++][(int) Math.floor(Math.random() * 3)] = random;
		points[pointsIndex][pointsYIndex] = random;
		test.setVertexAttributes(Attribute.COORDINATES,StorageModel.DOUBLE_ARRAY.array(3).createReadOnly(points));
		random += diff;

		AccelReading a = new AccelReading((int) (Math.random() * 100),(int) (Math.random() * 100),(int) (Math.random() * 100));	
		changeColor(a);

		timer += 1;
		if(timer == 25) {
			pointsIndex += 1;
			pointsYIndex += 1;
			timer = 0;
		}
		if(pointsIndex > 99) {
        	pointsIndex = 0;
        }
        test.setVertexAttributes(Attribute.COORDINATES,StorageModel.DOUBLE_ARRAY.array(3).createReadOnly(points));			
		if(pointsYIndex >= 3) {
			pointsYIndex = 0;
		}
	}
	
	public void changeColor(AccelReading accelReading) {
		//test.getNumFaces();
		//System.out.println(test.getFaceAttributes(Attribute.COLORS));
		Appearance ap = world.getAppearance();
		/*
		ap.setAttribute(LINE_SHADER+"."+DIFFUSE_COLOR, Color.yellow);
		ap.setAttribute(LINE_SHADER+"."+TUBE_RADIUS, .05);
		ap.setAttribute(POINT_SHADER+"."+DIFFUSE_COLOR, Color.red);
		ap.setAttribute(POINT_SHADER+"."+POINT_RADIUS, .1);
		ap.setAttribute(POLYGON_SHADER+"."+SMOOTH_SHADING, false);
		// turn on transparency for faces but keep tubes and spheres opaque
		ap.setAttribute(TRANSPARENCY_ENABLED, true);
		ap.setAttribute(OPAQUE_TUBES_AND_SPHERES, true);
		ap.setAttribute(POLYGON_SHADER+"."+TRANSPARENCY, .4);
		//accelReading = new AccellReading(0,0,0);
		*/
		
		int stepZ = accelReading.getZ() / 24;
		int leftoverZ = accelReading.getZ() % 24;

		int stepX = accelReading.getX() / 24;
		int leftoverX = accelReading.getX() % 24;
		
		int stepY = accelReading.getY() / 24;
		int leftoverY = accelReading.getY() % 24;

        int oldBlue = base.getBlue();
		int oldRed = base.getRed();
		int oldGreen = base.getGreen();	
		int newBlue, newRed, newGreen;

		if(timer == 0) {
            newBlue = (oldBlue + leftoverZ) % 255;
            newRed = (oldRed + leftoverX) % 255;
            newGreen = (oldGreen + leftoverY) % 255;			
		} else {
			newBlue = (oldBlue + stepZ) % 255;
            newRed = (oldRed + stepX) % 255;
            newGreen = (oldGreen + stepY) % 255;					
		}
		
		

		/*
        System.out.println(newBlue);
        System.out.println(newRed);
        System.out.println(newGreen);
        */
		base = new Color(newRed, newGreen, newBlue);
		 
		//Color newColor = new Color(000, 000, 000);
		ap.setAttribute(POLYGON_SHADER+"."+DIFFUSE_COLOR, base);
		//ap.setAttribute(POLYGON_SHADER+"."+DIFFUSE_COLOR, newColor);
	}
	
	public Viewer getViewer() {
		return viewer;
	}

	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}
}
