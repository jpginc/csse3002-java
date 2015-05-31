package visualiser;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import crinkle.CrinkleViewer;
import crinkle.PlaybackMode;
import data.MovementData;
import data.MovementListener;
import data.SensorReading;
import de.jreality.scene.Viewer;
import de.jreality.ui.viewerapp.FileFilter;
import de.jreality.ui.viewerapp.FileLoaderDialog;
import de.jreality.util.ImageUtility;

public class Visualiser implements MovementListener{
	
	// the different types of canvases
	public static int JAGGAREDCANVAS = 0;
	public static int ROUNTCANVAS = 1;


	private PlaybackMode playbackMode;
	private Canvas canvas;
	private MovementData currentMovementData;

	private Timer timer = new Timer();

	//this value is how many sensor readings to play displays per second 
	private int playSpeed = 10;
	//this value is how many sensor readings are currently being displayed per second
	private int currentSpeed = playSpeed;
	//the maximum playback speed
	private int maxPlaySpeed = 10;
	//The number of readings per second that are read by the crinkle device + 1
	private int realTimePlaySpeed = 11;
	//how many frames per second
	private int fps = 24;

	//TODO 
	//ensure thread safety
	private boolean playing;

	//indicates whether to get the next sensor reading or the previous one
	private boolean isReverse = false;



	public Visualiser() {
		initialise(0);
	}

	public Visualiser(File crinkleViewerFile, PlaybackMode playbackMode) {
		this.playbackMode = playbackMode;
		currentMovementData = new MovementData(crinkleViewerFile);
		initialise(0);
	}
	
	/**
	 * creates a Visualiser that displays the visualisation in real time
	 * @param m
	 *  the movement data to listen to
	 * @param p
	 *  the playback gui
	 * @param canvasType
	 *  the type of canvas to use
	 */
	public Visualiser(MovementData m, PlaybackMode p, int canvasType) {
		this.playbackMode = p;
		currentMovementData = m;
		initialise(canvasType);
		m.addListener(this);
	}
	
	/**
	 * Creates a visualisation using the given canvasType
	 * @param crinkleViewerFile
	 * 	the file to use for the movemnt data
	 * @param playbackMode
	 * 	they playback gui
	 * @param canvasType
	 * 	the type of canvas to use
	 */
	public Visualiser(File crinkleViewerFile, PlaybackMode playbackMode, int canvasType) {
		this.playbackMode = playbackMode;
		currentMovementData = new MovementData(crinkleViewerFile);
		initialise(canvasType);
	}
	
	/**
	 * assigns the appropriate canvas from the type given
	 * @param type
	 * 	The type of canvas (see public static int's at the top of this class)
	 */
	private void setCanvasFromType(int type) {
		switch (type) {
		case 0:
			canvas = new JaggeredCanvas(maxPlaySpeed * fps);
			break;
		case 1:
			canvas = new RodCanvas(maxPlaySpeed *fps);
			break;
		case 2:
			canvas = new RoundCanvas(maxPlaySpeed *fps);
			break;
		default:
			canvas = new JaggeredCanvas(maxPlaySpeed * fps);
			break;
		}	
	}

	/** Load data**/
	public void Load(MovementData movementData) {
		canvas = new JaggeredCanvas(maxPlaySpeed * fps);
		currentMovementData = movementData;
		initialise(0);
	}

	/**
	 * resets the play settings
	 */
	public void initialise(int canvasType){
		setCanvasFromType(canvasType);
		currentSpeed = playSpeed;
		SensorReading next;
		while(currentMovementData != null && ((next = currentMovementData.getNext())) != null) {
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
			return canvas.previous(fps * currentSpeed);
		} else {
			return canvas.next(fps * currentSpeed);
		}
	}	

	/**
	 * This function starts the image being mutated and calls itself recursively
	 * until there are no more sensor readings to mutate with
	 * 
	 */
	public int play() {
		isReverse = false;
		currentSpeed = playSpeed;
		if(! playing) {
			if(! startTimer()) {
			//TODO
			//the play button was pushed but it's the end of the visualisation 
			//should we start from the beginning again?
			}
		}
		return currentSpeed;
	}
	
	/**
	 * starts the visualsation playing in just above realtime speed
	 * 
	 * @return
	 */
	public int playRealtime() {
		isReverse = false;
		currentSpeed = realTimePlaySpeed; 
		if(! playing) {
            startTimer();
		}
		return currentSpeed;
	}
	
	/**
	 * does one mutation and starts the timer to call this function again
	 * 
	 * before calling this function check if the visualisation is already playing
	 * 
	 * @return
	 * 	true if a step has occured false otherwise
	 */
	private boolean startTimer() {
		playing = true;
		if(run()) {
			try {
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					//call itself after the timeout
					startTimer();
				}
			}, 1000 / fps);
			} catch (Exception e) {
				//the timer was already cancelled 
				return false;
			}
			return true;
		} else {
			if(isReverse) {
				playbackMode.initPlaybackButtons();
			} else {
				playbackMode.setBtnForwardEnabled(false);
				playbackMode.setBtnPlayEnabled(false);
				playbackMode.setLblStatus("End");
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
	
	/**
	 * 
	 * Called in real time mode when the currentMovementData object has recieved another reading
	 */
	@Override
	public void movementNotify(SensorReading s) {
		canvas.appendCache(s);
		playRealtime();
	}
}