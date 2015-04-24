
package crinkle;

/**
 *
 * @author ToanHo
 */
public class CrinkleViewer {

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
    	//hello Josh
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrinkleViewer();
            }
        });
    }
    
}
