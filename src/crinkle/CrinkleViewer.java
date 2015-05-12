
package crinkle;

/**
 *
 * @author ToanHo
 */
public class CrinkleViewer {

	// We should choose a new file extension for crinkle viewer. e.g "crvf"
	public static final String FILE_EXTENSION = "txt"; 
	
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
