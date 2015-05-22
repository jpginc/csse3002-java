import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import crinkle.LaunchMode;
import crinkle.PlaybackMode;
import visualiser.Visualiser;


public class TestVisualiser {
	@Test
	public void testFastforward() {
		int maxPlaySpeed = 8;
		int playSpeed = 1;
		int currentSpeed = playSpeed;
		File file = new File("src/sampleData/sample.crvf");
		LaunchMode launchMode = new LaunchMode();
		PlaybackMode playbackMode = new PlaybackMode(launchMode, file);		
		Visualiser sampleVisualiser = new Visualiser(file, playbackMode);
		
		sampleVisualiser.play();
		assertEquals(Math.min(currentSpeed * 2, maxPlaySpeed), sampleVisualiser.fastForward());
	}
	
	@Test
	public void testRewind() {
		int maxPlaySpeed = 8;
		int playSpeed = 1;
		int currentSpeed = playSpeed;
		File file = new File("src/sampleData/sample.crvf");
		LaunchMode launchMode = new LaunchMode();
		PlaybackMode playbackMode = new PlaybackMode(launchMode, file);
		Visualiser sampleVisualiser = new Visualiser(file, playbackMode);
		
		sampleVisualiser.play();
		assertEquals(Math.min(currentSpeed * 2, maxPlaySpeed), sampleVisualiser.rewind());
	}
	
}
