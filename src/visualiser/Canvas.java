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
		double random = Math.random() * 6 - 3; 
        points[pointsIndex++][(int) Math.floor(Math.random() * 3)] = random;
        if(pointsIndex > 99) {
        	pointsIndex = 0;
        }

        test.setVertexAttributes(Attribute.COORDINATES,StorageModel.DOUBLE_ARRAY.array(3).createReadOnly(points));			
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
