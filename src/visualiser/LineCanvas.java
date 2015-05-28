/**
 * 
 */
package visualiser;

import java.util.ArrayList;

import data.SensorReading;
import de.jreality.geometry.IndexedLineSetFactory;
import de.jreality.plugin.JRViewer;
import de.jreality.scene.Appearance;
import de.jreality.scene.IndexedLineSet;
import de.jreality.shader.DefaultGeometryShader;
import de.jreality.shader.DefaultLineShader;
import de.jreality.shader.LineShader;
import de.jreality.shader.ShaderUtility;
import static de.jreality.shader.CommonAttributes.*;

/**
 * This visualisation starts of as a single point and adds points depending on
 * the sensor values passed in
 * @author joshua
 *
 */
public class LineCanvas extends GenericCanvas implements Canvas {
	
	//the shape to form the visualisation
	IndexedLineSetFactory factory = new IndexedLineSetFactory();
	ArrayList<int[]> lines = new ArrayList<int[]>();
	ArrayList<double[]> vertices = new ArrayList<double[]>();
	
	int pointIndex = 1;
	
	
	public LineCanvas(int maxStepsPerMutation) {
		super(maxStepsPerMutation);

        vertices.add(new double[] {0, 0, 0});
		Appearance ap = world.getAppearance();
		DefaultGeometryShader dgs = ShaderUtility.createDefaultGeometryShader(ap, true);
		DefaultLineShader dls = (DefaultLineShader) dgs.createLineShader("default");
		dls.setTubeRadius(5.0);
        reDraw(pointIndex);
	}
	
	private double[][] toDoubleArrArr(ArrayList<double[]> a, int splicePoint) {
		double[][] d = new double[splicePoint][3];
		for(int i = 0; i < splicePoint; i++) {
			d[i] = a.get(i);
		}
		return d;
	}
	private int[][] toIntArrArr(ArrayList<int[]> a, int splicePoint) {
		int[][] d = new int[splicePoint][2];
		for(int i = 0; i < splicePoint; i++) {
			d[i] = a.get(i);
		}
		return d;
	}
	
	

	/* (non-Javadoc)
	 * @see visualiser.Canvas#appendCache(data.SensorReading)
	 */
	@Override
	public void appendCache(SensorReading reading) {
		for(int i = 0; i < maxStepsPerMutation; i++) {
            vertices.add(generatePoint(vertices.get(vertices.size() -1), reading.getFlex1(), reading.getAccel().getX(), reading.getAccel().getY()));
            lines.add(new int[] {vertices.size() - 2, vertices.size() - 1});
		}
	}
	
	protected double[] generatePoint(double[] prevPoint, double x, double y, double z) {
		return new double[] {prevPoint[0] + x / maxStepsPerMutation, prevPoint[1] + y / maxStepsPerMutation, prevPoint[2] + z / maxStepsPerMutation};
	}

	/* (non-Javadoc)
	 * @see visualiser.Canvas#next(int)
	 */
	@Override
	public boolean next(int steps) {
		pointIndex += steps;
		if(pointIndex >= vertices.size()) {
			pointIndex = vertices.size() - 1;
            reDraw(pointIndex);
			return false;
		}
		reDraw(pointIndex);
		return true;
	}

	/* (non-Javadoc)
	 * @see visualiser.Canvas#previous(int)
	 */
	@Override
	public boolean previous(int steps) {
		pointIndex -= steps;
		if(pointIndex < 0) {
			pointIndex = 0;
			reDraw(pointIndex);
			return false;
		}
		reDraw(pointIndex);
		return false;
	}

	@Override
	public int getPointIndex() {
		return pointIndex;
	}
	
	void reDraw(int size) {
		//System.out.println("size: " + size);
        factory.setVertexCount(size);
        factory.setVertexCoordinates(toDoubleArrArr(vertices, size)); 
        factory.setEdgeCount(size - 1);
        factory.setEdgeIndices(toIntArrArr(lines, size - 1)); 
        factory.update();
		world.setGeometry(factory.getIndexedLineSet());
	}
}
