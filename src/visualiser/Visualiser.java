package visualiser;

import java.awt.Component;
import java.util.Timer;
import java.util.TimerTask;

import data.MovementData;

public class Visualiser {

	private Canvas canvas;
	
	private Timer timer;

	public Visualiser() {
		canvas = new Canvas();
	}
	
	/** Load data**/
	public void Load(MovementData movementData) {
		
	}
	
	/** Play **/
	public void play() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
			  canvas.mutate(1);
		  }
		}, 100, 100);
	}
	
	/** Pause **/
	public void pause() {
		timer.cancel();
	}
	
	/** Fast Forward **/
	public void fastForward() {
		//TODO
	}
	
	/** Rewind **/
	public void rewind() {
		//TODO
	}
	
	/** Snapshot **/
	public void snapshot() {
		//TODO
	}
	
	public Component getViewerComponent() {
		return (Component) this.canvas.getViewer().getViewingComponent();
	}
	
}