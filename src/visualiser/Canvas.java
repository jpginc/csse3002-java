package visualiser;

import static de.jreality.plugin.icon.ImageHook.getIcon;

import java.awt.Component;

import javax.swing.JFrame;

import de.jreality.geometry.Primitives;
import de.jreality.plugin.JRViewer;
import de.jreality.plugin.basic.View;
import de.jreality.scene.IndexedFaceSet;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.Viewer;
import de.jreality.scene.data.Attribute;
import de.jreality.scene.data.DataItem;
import de.jreality.scene.data.DataListSet;
import de.jreality.scene.data.StorageModel;

public class Canvas {
	
	IndexedFaceSet test = Primitives.sphere(10);
	Integer pointsIndex = 0;
	
	public Canvas() {
		
		SceneGraphComponent world = new SceneGraphComponent();
		
		System.out.println("here: " + test.getFaceAttributes(null));
		world.setGeometry(test);

		JRViewer v = JRViewer.createJRViewer(world);
		v.startupLocal();
		Viewer viewer = v.getViewer();
		world.setGeometry(test);
		JFrame f = new JFrame();
        f.getContentPane().add((Component)viewer.getViewingComponent());
        f.setSize(512, 512);
        f.validate();
        f.setVisible(true);		
	}
		
	public void move(int value) {
        double[][] points=new double[test.getNumPoints()][];
		test.getVertexAttributes(Attribute.COORDINATES).toDoubleArrayArray(points);
        points[pointsIndex++][(int) Math.floor(Math.random() * 3)] += Math.random() * 10;
        if(pointsIndex > 99) {
        	pointsIndex = 0;
        }

        test.setVertexAttributes(Attribute.COORDINATES,StorageModel.DOUBLE_ARRAY.array(3).createReadOnly(points));			
	}
}
