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
		pointHistory.add(new double[] {temp[0], temp[1], temp[2], temp[3], temp[0], temp[1], temp[2]});
		//generatePoint(temp, temp[0], temp[1], temp[3]);
	}


	/**
	 * 
	 */
	@Override
	public void appendCache(SensorReading reading) {
		for(int i = 0; i < maxStepsPerMutation; i++) {
			prevCachedPoint = generatePoint(prevCachedPoint, reading.getFlex1(), 
					reading.getAccel().getX(), reading.getAccel().getY());
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
			setPoint(historyIndex, true);
			historyIndex--;
			if(historyIndex < 0) {
				cachedPoints = originalSphere.clone();
				reDraw();
				setColor(0);
				return false;
			}
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
		double[] rewindPoint = pointHistory.get(pointHistory.size() - 1) ;
		double[] point = {prev[0] + (x / maxStepsPerMutation),
				prev[1] + (y / maxStepsPerMutation),
				prev[2] + (z / maxStepsPerMutation),
				pointIndex,
				rewindPoint[0], 
				rewindPoint[1], 
				rewindPoint[2]
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
		double [] pointPos = null;
		double [] toSet;

		int index = (int) pointHistory.get(historyIndex)[3];
		if(historyIndex % maxStepsPerMutation == 1) {
			if(historyIndex < maxStepsPerMutation * pointMax) {
				pointPos = originalSphere[index];
			} else {
                toSet = pointHistory.get(historyIndex - (maxStepsPerMutation * pointMax));
                pointPos = new double[] {toSet[4], toSet[5], toSet[6]};
			}
		} else {
            toSet = pointHistory.get(historyIndex);
            pointPos = new double[] {toSet[4], toSet[5], toSet[6]};
		}
			
		cachedPoints[index] = pointPos;
	}

}