package visualiser;

import java.util.Timer;
import java.util.TimerTask;

public class Visualiser {

	Canvas canvas = new Canvas();

	public Visualiser() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
			  canvas.move(1);
		  }
		}, 100, 100);
	}
}