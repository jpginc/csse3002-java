package visualiser;

import java.awt.Component;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import data.MovementData;
import data.SensorReading;

public class Visualiser {


	private Canvas canvas;
	private MovementData currentMovementData;
	
	private Timer timer = new Timer();
	
	//this value is how many sensor readings to play displays per second 
	private int playSpeed = 1;
	//this value is how many sensor readings are currently being displayed per second
	private int currentSpeed = playSpeed;
	//how many frames per second
	private int fps = 24;
	//each sensor reading is split up fps times to make it look smooth
	//currentFrame indicates how many times the sensor reading has ben split up already
	private int currentFrame = fps;
	
	//this is the sensor reading that we are using to mutate the sphere
	private SensorReading currentReading;
	
	//TODO 
	//ensure thread safety
	private boolean playing;
	
	//indicates whether to get the next sensor reading or the previous one
	private boolean isReverse = false;
	
	

	public Visualiser() {
		canvas = new Canvas();
		initialise();
	}
	
	public Visualiser(File crinkleViewerFile) {
		currentMovementData = new MovementData(crinkleViewerFile);
		canvas = new Canvas();
		initialise();
		System.out.println("<<<Test in Visualiser constructor>>>");
		while(currentMovementData.hasNext()) {
			SensorReading sr = currentMovementData.getNext();
			System.out.println(sr.toString());
		}
	}
	
	/** Load data**/
	public void Load(MovementData movementData) {
        currentMovementData = movementData;
        initialise();
	}
	
	/**
	 * resets the play settings
	 */
	public void initialise(){
		currentSpeed = playSpeed;
		currentFrame = fps;
	}
	
	/**
	 * runs one mutation on the canvas. It will get the next/previous sensor reading and
	 * run fps mutations on it before getting the next/previous reading etc.
	 * 
	 * TODO
	 * currently if no movement data is loaded, random mutations occur infinitely
	 * 
	 * @return
	 *  true if a mutation occurred false if there are no mutations left
	 */
	private boolean run() {
		if(currentMovementData == null) {
			//do random mutations
			canvas.mutate(1);
			return true;
		} else {
			if(currentFrame++ == fps) {
				canvas.next();
				currentFrame = 1;
				if(isReverse) { 
					currentReading = currentMovementData.getNext();
				} else {
					currentReading = currentMovementData.getPrevious();
				}
					
			}	
			if(currentReading == null) {
				//stop
				return false;
			} else {
				canvas.mutate(currentReading, fps * currentSpeed);
				return true;
			}
		}
	}	
	
	/**
	 * This function starts the image being mutated and calls itself recursively
	 * until there are no more sensor readings to mutate with
	 * 
	 */
	public void play() {
		isReverse = false;
		canvas.setDirection(Canvas.FORWARD);
		currentSpeed = playSpeed;
		if(! startTimer()) {
			//TODO
			//the play button was pushed but it's the end of the visualisation 
			//should we start from the beginning again?
		}
	}
	
	/**
	 * does one mutation and starts the timer to call this function again
	 * 
	 * @return
	 * true if a step has occured false otherwise
	 */
	private boolean startTimer() {
		playing = true;
		//TODO
		//do we have to worry about threads?
		//this can be called by play, ffwd and rwnd so cancel the timer first. 
		if(run()) {
    		timer.schedule(new TimerTask() {
		    	@Override
		    	public void run() {
		    		//call itself after the timeout
		    		startTimer();
		    	}
    		}, 1000 / currentSpeed / fps);
    		return true;
		}	
		playing = false;
		return false;
	}
	
	/**
	 * stops the visualisation
	 */
	public void pause() {
		timer.cancel();
		timer = new Timer();
		playing = false;
	}
	
	/**
	 * sets the speed to forward * 4
	 * 
	 * @return
	 * 	the new speed
	 */
	public int fastForward() {
		currentSpeed = playSpeed * 4;
		isReverse = false;
		canvas.setDirection(Canvas.FORWARD);
		if(! playing) {
            startTimer();
		}
		return currentSpeed;
	}
	
	/**
	 * sets the speed to backwards * 4
	 * @return
	 *  the new speed
	 */
	public int rewind() {
		currentSpeed = playSpeed * 4;
		isReverse = true;
		canvas.setDirection(Canvas.REVERSE);
		if(! playing) {
			startTimer();
		}
		return currentSpeed;
	}
	
	/** Snapshot **/
	public void snapshot() {
		//TODO
	}
	
	public Component getViewerComponent() {
		return (Component) this.canvas.getViewer().getViewingComponent();
	}
	
}