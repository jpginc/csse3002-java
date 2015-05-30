package visualiser;

import java.awt.Color;
import java.io.File;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import data.MovementData;
import data.SensorReading;
import de.jreality.tutorial.projects.darboux.DarbouxTransform;
import de.jreality.tutorial.projects.darboux.StartPoint;
import de.jreality.tutorial.util.polygon.DragPointSet;
import de.jreality.tutorial.util.polygon.PointSequenceView;
import de.jreality.tutorial.util.polygon.SubdividedPolygon;

public class RodCanvas extends GenericCanvas implements Canvas{

	
	SubdividedPolygon subPoly;
	DarbouxTransform dt_plus, dt_minus;
	
	public RodCanvas(int maxStepsPerMutation) {
		
		super(maxStepsPerMutation);
		MovementData currentMovementData = new MovementData(new File("src/sampleData/sample.crvf"));

		DragPointSet dps = new DragPointSet(circle(40, 1));
		
		dps.setClosed(false);
		world.addChild(dps.getBase());
		subPoly = new SubdividedPolygon(dps);
	
		double[] startPoint = new double[3];
		dt_plus = new DarbouxTransform(dps, startPoint);
		dt_minus = new DarbouxTransform(dps, startPoint);
		
		final StartPoint sp = new StartPoint(dps);
		sp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				dt_plus.setStartPoint(sp.getPoint());
				dt_minus.setStartPoint(sp.getPoint());
			}

		});
		world.addChild(sp.getRoot());
		
		PointSequenceView curveView = new PointSequenceView(subPoly);
		curveView.setPointRadius(0.03);
		curveView.setLineRadius(0.02);
		//curveView.setPointColor(Color.red);
		curveView.setLineColor(Color.red);
		world.addChild(curveView.getBase());
	}
	
	
	static double[][] circle(int n, double r) {
		double[][] verts = new double[n][3];
		double dphi = 2.0*Math.PI/n;
		for (int i=0; i<n; i++) {
			verts[i][0]=r*Math.cos(i*dphi);
			verts[i][1]=r*Math.sin(i*dphi);
		}
		
		
		return verts;
	}


	@Override
	public void appendCache(SensorReading reading) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean next(int steps) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean previous(int steps) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int getPointIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

}
