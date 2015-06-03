package crinkle;

import static org.junit.Assert.fail;

import org.junit.Test;

public class TestPlaybackMode {

	@Test
	public void testPlaybackMode() {
		try {
			PlaybackMode sampleMode = new PlaybackMode();
			sampleMode.initPlaybackButtons();
			sampleMode.setBtnForwardEnabled(true);
			sampleMode.setBtnRewindEnabled(true);
			sampleMode.setBtnPlayEnabled(true);
			sampleMode.setBtnPlayIcon(CrinkleViewer.PAUSE_ICON);
			sampleMode.setIsPlay(true);
			sampleMode.setLblStatus("Good");
		} catch(Exception e) {
			fail("Should not have thrown any exception.");
		}
	}
	
	@Test(expected=Exception.class)
	public void testSetBtnPlayIconFail1() {
		PlaybackMode sampleMode = new PlaybackMode();
		sampleMode.setBtnPlayIcon("Anything");
	}
	
	@Test(expected=Exception.class)
	public void testSetBtnPlayIconFail2() {
		PlaybackMode sampleMode = new PlaybackMode();
		sampleMode.setBtnPlayIcon(null);
	}
}
