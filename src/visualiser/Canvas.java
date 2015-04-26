package visualiser;

import java.awt.Component;

import crinkle.PlaybackMode;
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
        points[pointsIndex++][(int) Math.floor(Math.random() * 3)] += Math.random() * 10;
        if(pointsIndex > 99) {
        	pointsIndex = 0;
        }

        test.setVertexAttributes(Attribute.COORDINATES,StorageModel.DOUBLE_ARRAY.array(3).createReadOnly(points));			
	}
	
	public void changeColor(AccelReading accelReading) {
		
	}

	public Viewer getViewer() {
		return viewer;
	}

	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}
}
