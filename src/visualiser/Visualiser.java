package visualiser;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import crinkle.CrinkleViewer;
import crinkle.PlaybackMode;
import data.MovementData;
import data.SensorReading;
import de.jreality.scene.Viewer;
import de.jreality.ui.viewerapp.FileFilter;
import de.jreality.ui.viewerapp.FileLoaderDialog;
import de.jreality.util.ImageUtility;

public class Visualiser {


	private PlaybackMode playbackMode;
	private Canvas canvas;
	private MovementData currentMovementData;

	private Timer timer = new Timer();

	//this value is how many sensor readings to play displays per second 
	private int playSpeed = 1;
	//this value is how many sensor readings are currently being displayed per second
	private int currentSpeed = playSpeed;
	//the maximum playback speed
	private int maxPlaySpeed = 8;
	//how many frames per second
	private int fps = 24;

	//TODO 
	//ensure thread safety
	private boolean playing;

	//indicates whether to get the next sensor reading or the previous one
	private boolean isReverse = false;



	public Visualiser() {
		canvas = new JaggeredCanvas(maxPlaySpeed * fps);
		initialise();
	}

	public Visualiser(File crinkleViewerFile, PlaybackMode playbackMode) {
		this.playbackMode = playbackMode;
		currentMovementData = new MovementData(crinkleViewerFile);
		canvas = new JaggeredCanvas(maxPlaySpeed * fps);
		initialise();
		/*
		System.out.println("<<<Test in Visualiser constructor>>>");
		while(currentMovementData.hasNext()) {
			SensorReading sr = currentMovementData.getNext();
			System.out.println(sr.toString());
		}
		System.out.println("Print backward");
		while(currentMovementData.hasPrevious()) {
			SensorReading sr = currentMovementData.getPrevious();
			System.out.println(sr.toString());
		}
		*/
	}

	/** Load data**/
	public void Load(MovementData movementData) {
		canvas = new JaggeredCanvas(maxPlaySpeed * fps);
		currentMovementData = movementData;
		initialise();
	}

	/**
	 * resets the play settings
	 */
	public void initialise(){
		currentSpeed = playSpeed;
		SensorReading next;
		while((next = currentMovementData.getNext()) != null) {
			canvas.appendCache(next);
		}
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
		//mutate using movement data 
		if(isReverse) {
			return canvas.previous(maxPlaySpeed);
		} else {
			return canvas.next(maxPlaySpeed);
		}
	}	

	/**
	 * This function starts the image being mutated and calls itself recursively
	 * until there are no more sensor readings to mutate with
	 * 
	 */
	public void play() {
		isReverse = false;
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
			}, 1000 / fps / currentSpeed);
			return true;
		} else {
			if(isReverse) {
				playbackMode.setBtnRewindEnabled(false);
				playbackMode.setBtnForwardEnabled(false);
				playbackMode.setBtnPlayIcon(CrinkleViewer.PLAY_ICON);
				playbackMode.setBtnPlayEnabled(true);
				playbackMode.setIsPlay(false);
			} else {
				playbackMode.setBtnForwardEnabled(false);
				playbackMode.setBtnPlayEnabled(false);
			}
		}	
		playing = false;
		currentSpeed = playSpeed;
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
		currentSpeed = Math.min(currentSpeed * 2, maxPlaySpeed);
		isReverse = false;
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
		currentSpeed = Math.min(currentSpeed * 2, maxPlaySpeed);
		isReverse = true;
		if(! playing) {
			startTimer();
		}
		return currentSpeed;
	}

	/** Snapshot **/
	public void snapshot() {
		Viewer viewer = canvas.getViewer();
		Component parentComp = getViewerComponent();
		BufferedImage image = ImageUtility.captureScreenshot(viewer);
		if(image != null) {
			File fileImage = FileLoaderDialog.selectTargetFile(parentComp, null, false, 
					FileFilter.createImageWriterFilters());
			if(fileImage != null) {
				try {
					ImageUtility.writeBufferedImage(fileImage, image);
					System.out.println("Screenshot saved at " + fileImage.getPath());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			System.err.println("<<<Capture screenshot failed!>>>");
		}
	}

	public Component getViewerComponent() {
		return (Component) this.canvas.getViewer().getViewingComponent();
	}

}