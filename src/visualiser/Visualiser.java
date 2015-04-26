package visualiser;

import java.awt.Component;
import java.util.Timer;
import java.util.TimerTask;

import data.MovementData;

public class Visualiser {

	private Canvas canvas;
	
	private Timer timer = new Timer();

	public Visualiser() {
		canvas = new Canvas();
	}
	
	/** Load data**/
	public void Load(MovementData movementData) {
		
	}
	
	/** Play **/
	public void play() {
		timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
			  canvas.mutate(1);
		  }
		}, 1000/24, 1000/24);
	}
	
	/** Pause **/
	public void pause() {
		timer.cancel();
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