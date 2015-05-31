
package crinkle;

import java.awt.Image;
import java.lang.reflect.Method;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author ToanHo
 */
public class CrinkleViewer {

	public static final String FILE_EXTENSION = "crvf"; 
	public static final String PLAY_ICON = "/icons/playIcon_40x40.png";
	public static final String REWIND_ICON = "/icons/rewindIcon_40x40.png";
	public static final String FAST_FORWARD_ICON = "/icons/fastForwardIcon_40x40.png";
	public static final String PAUSE_ICON = "/icons/pauseIcon_40x40.png";
	public static final String CAMERA_ICON = "/icons/cameraIcon_30x30.png";
	public static final String CRINKLE_FILE_ICON = "/icons/crinkleFileIcon_16x16.png";
	public static final String CRINKLE_ICON_MAC = "/icons/crinkleIcon_Mac_256x256.png";
	public static final String CRINKLE_ICON_WIN = "/icons/crinkleIcon_Win_32x32.png";

	private LaunchMode launchMode;

	public CrinkleViewer() {
		launchMode = new LaunchMode();
		launchMode.setVisible(true);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new CrinkleViewer();
			}
		});
	}

	/** Set an icon for application
	 * @return */
	public static void setAppIcon(String macIconPath, String winIconPath, JFrame frame) {
		Image macIcon = (new ImageIcon(frame.getClass().getResource(macIconPath))).getImage();
		Image winIcon = (new ImageIcon(frame.getClass().getResource(winIconPath))).getImage();
		String os = System.getProperty("os.name");
		if (os.contains("Mac")) {
			try {
				Class<?> applicationClass = Class.forName("com.apple.eawt.Application");
				Method getAppMethod = applicationClass.getMethod("getApplication");
				Method setDockIconMethod = applicationClass.getMethod("setDockIconImage", Image.class);
				Object app = getAppMethod.invoke(applicationClass);
				setDockIconMethod.invoke(app, macIcon);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			frame.setIconImage(winIcon);
		}

	}
}
