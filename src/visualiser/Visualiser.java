package visualiser;

import java.awt.Component;
import java.util.Timer;
import java.util.TimerTask;

import data.MovementData;

public class Visualiser {

	private Canvas canvas;

	public Visualiser() {
		canvas = new Canvas();
	}
	
	/** Load data**/
	public void Load(MovementData movementData) {
		
	}
	
	/** Play **/
	public void play() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
			  canvas.mutate(1);
		  }
		}, 100, 100);
	}
	
	/** Pause **/
	public void pause() {
		
	}
	
	/** Fast Forward **/
	public void fastForward() {
		
	}
	
	/** Rewind **/
	public void rewind() {
		
	}
	
	/** Snapshot **/
	public void snapshot() {
		
	}
	
	public Component getViewerComponent() {
		return (Component) this.canvas.getViewer().getViewingComponent();
	}
	
}