
package crinkle;

/**
 *
 * @author ToanHo
 */
public class CrinkleViewer {

	// We should choose a new file extension for crinkle viewer. e.g "crvf"
	public static final String FILE_EXTENSION = "txt"; 
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
    
}
