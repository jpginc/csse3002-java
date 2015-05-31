package visualiser;

import data.SensorReading;

/*
 * This visualisation style is the same as the Jaggered Canvas only it moves the next point
 * based on the previous point.
 * 
 */
public class RoundCanvas extends JaggeredCanvas {

	public RoundCanvas(int maxStepsPerMutation) {
		super(maxStepsPerMutation);
    }
	
	
	/**
	 * 
	 */
	@Override
	public void appendCache(SensorReading reading) {
		for(int i = 0; i < maxStepsPerMutation; i++) {
			prevCachedPoint = generatePoint(prevCachedPoint, reading.getFlex1(), 
					reading.getAccel().getX(), reading.getAccel().getY());
			if(i % (maxStepsPerMutation / 3) == 0) {
				generateColor(colorHistory.get(colorHistory.size() - 1), reading.getFlex2());
			}
		}
        pointIndex++;
		if(pointIndex == pointMax) {
			pointIndex = 0;
		}
	}
}