package visualiser;

import java.awt.Color;
import java.awt.Component;

import crinkle.PlaybackMode;
import data.AccelReading;
import de.jreality.geometry.Primitives;
import de.jreality.plugin.JRViewer;
import de.jreality.scene.Appearance;
import de.jreality.scene.IndexedFaceSet;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.Viewer;
import de.jreality.scene.data.Attribute;
import de.jreality.scene.data.StorageModel;
import de.jreality.shader.ShaderUtility;
import static de.jreality.shader.CommonAttributes.*;
import de.jreality.util.SceneGraphUtility;


public class Canvas {

    private AccelReading a = new AccelReading(10,5,2);
	private IndexedFaceSet test = Primitives.sphere(10);
	private Integer pointsIndex = 0;
	private Viewer viewer;
	private SceneGraphComponent world = SceneGraphUtility.createFullSceneGraphComponent("test");
	//Initialise the color of the sphere to black. also used to keep track of the color of the sphere
	private Color base = new Color(0,0,0);
	
	public Canvas() {
		SceneGraphComponent world = new SceneGraphComponent();
		world.setGeometry(test);
		JRViewer jrViewer = JRViewer.createJRViewer(world);
		jrViewer.startupLocal();
		viewer = jrViewer.getViewer();
		//System.out.println(a == null);
		this.changeColor(a);
		//this.changeColor(null);
		
	}
		
	public void mutate(int value) {
        double[][] points=new double[test.getNumPoints()][];
		test.getVertexAttributes(Attribute.COORDINATES).toDoubleArrayArray(points);
		// Needs both positive and negative random number 
		// and -3 <= random <= 3 (pattern is displayed in the canvas)
		double random = Math.random() * 6 - 3; 
        points[pointsIndex++][(int) Math.floor(Math.random() * 3)] = random;
        if(pointsIndex > 99) {
        	pointsIndex = 0;
        }
        changeColor(a);
        test.setVertexAttributes(Attribute.COORDINATES,StorageModel.DOUBLE_ARRAY.array(3).createReadOnly(points));			
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
		int oldBlue = base.getBlue();
		int oldRed = base.getRed();
		int oldGreen = base.getGreen();
		int newBlue = (oldBlue + accelReading.getZ()) % 255;
		int newRed = (oldRed + accelReading.getX()) % 255;
		int newGreen = (oldGreen + accelReading.getY()) % 255;
System.out.println(newBlue);
System.out.println(newRed);
System.out.println(newGreen);
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
