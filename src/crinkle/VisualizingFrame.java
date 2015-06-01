package crinkle;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;

import visualiser.Visualiser;

/**
 *
 * @author ToanHo
 */
public class VisualizingFrame extends javax.swing.JFrame {

	private JPanel pnlViewer;
	private JPanel pnlLeft; // contain pnlPlayback or pnlRealTime
	private JPanel pnlRight; // contain pnlViewer;

	protected LaunchMode launchMode;
	protected Visualiser visualiser;

	public VisualizingFrame() {
		initComponents();
	}

	public VisualizingFrame(LaunchMode launchMode) {
		initComponents();
		this.launchMode = launchMode;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		pnlViewer = new javax.swing.JPanel();
		pnlLeft = new javax.swing.JPanel();
		pnlRight = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Crinkle Viewer");
		setMinimumSize(new Dimension(960, 720));
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(java.awt.event.WindowEvent evt) {
				formWindowClosed(evt);
			}
		});

		pnlViewer.setBorder(BorderFactory.createTitledBorder("Viewer"));

		pnlLeft.setPreferredSize(new Dimension(220, 150));

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pnlLeft, BorderLayout.LINE_START);
		getContentPane().add(pnlRight, BorderLayout.CENTER);

		pnlLeft.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		pnlRight.setLayout(new BorderLayout());
		pnlRight.add(pnlViewer);

		pnlViewer.setLayout(new BorderLayout());

		setSize(960, 720);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, 0);

	}


	private void formWindowClosed(java.awt.event.WindowEvent evt) {
		launchMode.setEnabled(true);
		launchMode.setVisible(true);
	}

	protected void addComponentToPnlViewer(Component component) {
		pnlViewer.add(component);
		validate();
	}

	protected void removeComponentFromPnlViewer(Component component) {
		pnlViewer.remove(component);
		validate();
	}

	protected void addComponentToPnlLeft(Component component) {
		pnlLeft.add(component);
		validate();
	}

	protected void removeComponentFromPnlLef(Component component) {
		pnlLeft.add(component);
		validate();
	}

	public Visualiser getVisualiser() {
		return visualiser;
	}

	public void setVisualiser(Visualiser visualiser) {
		this.visualiser = visualiser;
	}

	/**
	 * called when the window is destoryed
	 */
	public void destroy() {
	}
}
