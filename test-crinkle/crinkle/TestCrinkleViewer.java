package crinkle;

import static org.junit.Assert.*;

import org.junit.Test;

import crinkle.CrinkleViewer;
import crinkle.LaunchMode;

public class TestCrinkleViewer {

	@Test
	public void testCrinkleViewer() {
		LaunchMode exampleLaunchMode = new LaunchMode();
		try {
			CrinkleViewer.setAppIcon(CrinkleViewer.CRINKLE_ICON_MAC, 
					CrinkleViewer.CRINKLE_ICON_WIN, exampleLaunchMode);
		} catch (Exception e) {
			fail("Should not have thrown any exception.");
		}
		
	}
	
	@Test(expected=Exception.class)
	public void testFailCrinkleViewer1() {
		CrinkleViewer.setAppIcon(CrinkleViewer.CRINKLE_ICON_MAC,
				CrinkleViewer.CRINKLE_ICON_WIN, null);
	}
	
	@Test(expected=Exception.class)
	public void testFailCrinkleViewer2() {
		LaunchMode exampleLaunchMode = new LaunchMode();
		CrinkleViewer.setAppIcon(null, null, exampleLaunchMode);
	}
}
