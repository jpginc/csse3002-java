package visualiser;

import data.SensorReading;

/*
 * This visualization style is the same as the Jagged Canvas only it moves the next point
 * based on the previous point.
 * 
 */
public class RoundCanvas extends JaggedCanvas {

	public RoundCanvas(int maxStepsPerMutation) {
		super(maxStepsPerMutation);
		//the 
		double[] temp = pointHistory.remove(0);
		generatePoint(temp, temp[0], temp[1], temp[3]);
	}


	/**
	 * 
	 */
	@Override
	public void appendCache(SensorReading reading) {
		for(int i = 0; i < maxStepsPerMutation; i++) {
			prevCachedPoint = generatePoint(prevCachedPoint, reading.getFlex1(), 
					reading.getAccel().getX(), reading.getAccel().getY());
			setPoint(historyIndex++);
		}
		generateColor(colorHistory.get(colorHistory.size() - 1), reading.getFlex2());
		pointIndex++;
		if(pointIndex == pointMax) {
			pointIndex = 0;
		}
	}

	public void appendCacheOffline(SensorReading reading) {
		for(int i = 0; i < maxStepsPerMutation; i++) {
			prevCachedPoint = generatePoint(prevCachedPoint, reading.getFlex1(), 
					reading.getAccel().getX(), reading.getAccel().getY());
			setPoint(historyIndex++);
		}
		generateColor(colorHistory.get(colorHistory.size() - 1), reading.getFlex2());
		pointIndex++;
		if(pointIndex == pointMax) {
			pointIndex = 0;
		}
	}


	/* (non-Javadoc)
	 * @see visualiser.JaggedCanvas#previous(int)
	 */
	@Override
	public boolean previous(int steps) {
		for(int i = 0; i < steps; i++) {
			historyIndex--;
			if(historyIndex < 0) {
				cachedPoints = originalSphere.clone();
				reDraw();
				setColor(0);
				return false;
			}
			setPoint(historyIndex, true);
		}
		setColor(historyIndex);
		reDraw();
		return true;
	}


	/**
	 * generates the next position a point should be in based on the previous point 
	 * 
	 * @param prev
	 * 	previous point
	 * @param x
	 * 	the x distance to move (will be divided by maxstepsPerMutation)
	 * @param y
	 *  the y distance to move (will be divided by maxstepsPerMutation)
	 * @param z
	 *  the z distance to move (will be divided by maxstepsPerMutation)
	 * @return
	 *  the next position of the point
	 */
	protected double[] generatePoint(double[] prev, double x, double y, double z) {
		double[] point = {prev[0] + (x / maxStepsPerMutation),
				prev[1] + (y / maxStepsPerMutation),
				prev[2] + (z / maxStepsPerMutation),
				pointIndex,
				prev[1], 
				prev[2], 
				prev[3]
		};
		pointHistory.add(point);
		return point;
	}

	/**
	 * sets the spheres point based on the cache
	 * @param historyIndex
	 *  the part of the history we are up to 
	 */
	protected void setPoint(int historyIndex, boolean isBack) {
		double [] toSet = pointHistory.get(historyIndex);
		int index = (int) (toSet[3] + 1);
		if(index == pointMax) {
			index = 0;
		}
		double [] pointPos = {toSet[4], toSet[5], toSet[6]};
		cachedPoints[index] = pointPos;
	}

}