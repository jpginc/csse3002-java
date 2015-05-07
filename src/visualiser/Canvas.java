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
	
	IndexedFaceSet test = Primitives.sphere(10);
	Integer pointsIndex = 0;
	Integer pointsYIndex = 0;
	Integer timer = 0;
	double random;
	double diff;
	private Viewer viewer;
	private SceneGraphComponent world = SceneGraphUtility.createFullSceneGraphComponent("test");
	
	public Canvas() {
		SceneGraphComponent world = new SceneGraphComponent();
		world.setGeometry(test);
		JRViewer jrViewer = JRViewer.createJRViewer(world);
		jrViewer.startupLocal();
		viewer = jrViewer.getViewer();
		this.changeColor(null);
	}
		
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
		timer += 1;
		if(timer == 25) {
			pointsIndex += 1;
			pointsYIndex += 1;
			timer = 0;
		}
		if(pointsIndex > 99) {
        	pointsIndex = 0;
        }
		if(pointsYIndex >= 3) {
			pointsYIndex = 0;
		}
	}
	
	public void changeColor(AccelReading accelReading) {
		//test.getNumFaces();
		//System.out.println(test.getFaceAttributes(Attribute.COLORS));
		Appearance ap = world.getAppearance();
		ap.setAttribute(LINE_SHADER+"."+DIFFUSE_COLOR, Color.yellow);
		ap.setAttribute(LINE_SHADER+"."+TUBE_RADIUS, .05);
		ap.setAttribute(POINT_SHADER+"."+DIFFUSE_COLOR, Color.red);
		ap.setAttribute(POINT_SHADER+"."+POINT_RADIUS, .1);
		ap.setAttribute(POLYGON_SHADER+"."+SMOOTH_SHADING, false);
		// turn on transparency for faces but keep tubes and spheres opaque
		ap.setAttribute(TRANSPARENCY_ENABLED, true);
		ap.setAttribute(OPAQUE_TUBES_AND_SPHERES, true);
		ap.setAttribute(POLYGON_SHADER+"."+TRANSPARENCY, .4);
		Color newColor = new Color(000, 000, 000);
		ap.setAttribute(POLYGON_SHADER+"."+DIFFUSE_COLOR, newColor);
	}

	public Viewer getViewer() {
		return viewer;
	}

	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}
}
