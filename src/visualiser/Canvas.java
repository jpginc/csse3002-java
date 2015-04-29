package visualiser;

import data.AccelReading;
import de.jreality.geometry.Primitives;
import de.jreality.plugin.JRViewer;
import de.jreality.scene.IndexedFaceSet;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.Viewer;
import de.jreality.scene.data.Attribute;
import de.jreality.scene.data.StorageModel;

public class Canvas {
	
	IndexedFaceSet test = Primitives.sphere(10);
	Integer pointsIndex = 0;
	Integer pointsYIndex = 0;
	Integer timer = 0;
	double random;
	double diff;
	private Viewer viewer;
	
	public Canvas() {
		
		SceneGraphComponent world = new SceneGraphComponent();
		world.setGeometry(test);

		JRViewer jrViewer = JRViewer.createJRViewer(world);
		jrViewer.startupLocal();
		viewer = jrViewer.getViewer();
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
		//TODO
	}

	public Viewer getViewer() {
		return viewer;
	}

	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}
}
