package visualiser;

import java.awt.Color;

import data.SensorReading;
import static de.jreality.shader.CommonAttributes.*;
import de.jreality.scene.Appearance;

public class JaggedGreyCanvas extends JaggedCanvas {

	public JaggedGreyCanvas(int maxStepsPerMutation) {
		super(maxStepsPerMutation);
		Appearance ap = world.getAppearance();
		double[] rgb = colorHistory.get(0);
		Color newColor = new Color((int) rgb[0], (int) rgb[1], (int) rgb[2]);
		ap.setAttribute(POLYGON_SHADER+"."+DIFFUSE_COLOR, newColor);
		ap.setAttribute(POLYGON_SHADER+"."+DIFFUSE_COLOR, newColor);
	}

	@Override
	public void appendCache(SensorReading reading) {
		for(int i = 0; i < maxStepsPerMutation; i++) {
			prevCachedPoint = generatePoint(prevCachedPoint, reading.getFlex1(), 
					reading.getAccel().getX(), reading.getAccel().getY(), reading.getFlex2());
		}
		pointIndex++;
		if(pointIndex == pointMax) {
			pointIndex = 0;
		}
		prevCachedPoint = cachedPoints[pointIndex];
	}

	private double[] generatePoint(double[] prev, double x, double y,
			double z, double modifier) {
		double[] point = {prev[0] + ((x * modifier) / maxStepsPerMutation),
				prev[1] + ((y * modifier) / maxStepsPerMutation), 
				prev[2] + ((z * modifier) / maxStepsPerMutation), 
				pointIndex};
		pointHistory.add(point);
		return point;
	}

	/**
	 * 
	 * @param reading
	 */
	public void appendCacheOffline(SensorReading reading) {
		for(int i = 0; i < maxStepsPerMutation; i++) {
			prevCachedPoint = generatePoint(prevCachedPoint, reading.getFlex1(), 
					reading.getAccel().getX(), reading.getAccel().getY(), reading.getFlex2());
			setPoint(historyIndex++);
		}
		pointIndex++;
		if(pointIndex == pointMax) {
			pointIndex = 0;
		}
		prevCachedPoint = cachedPoints[pointIndex];
	}

	/* (non-Javadoc)
	 * @see visualiser.JaggedCanvas#setColor(int)
	 */
	@Override
	protected void setColor(int index) {
		//not needed in this class
		return;
	}

}
