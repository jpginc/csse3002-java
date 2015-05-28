package visualisation;

import org.junit.Test;

import crinkle.CrinkleViewer;
import crinkle.LaunchMode;

public class TestCrinkleViewer {

	@Test
	public void testCrinkleViewer() {
		CrinkleViewer exampleCrinkleViewer = new CrinkleViewer();
		LaunchMode exampleLaunchMode = new LaunchMode();
		exampleCrinkleViewer.setAppIcon(CrinkleViewer.CRINKLE_ICON_MAC,
				CrinkleViewer.CRINKLE_ICON_WIN, exampleLaunchMode);
		
	}
}
