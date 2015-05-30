package crinkle;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import visualiser.Visualiser;

/**
 *
 * @author ToanHo
 */
public class PlaybackMode extends javax.swing.JFrame {

	private JButton btnForward;
	private JButton btnPlay;
	private JButton btnRewind;
	private JButton btnSnapshot;
	private JLabel lblTimer;
	private JLabel lblStyle;
	private JPanel pnlPlayback; // contain pnlPlaybackTop, Middle, Bottom
	private JPanel pnlViewer;
	private JPanel pnlLeft; // contain pnlPlayback;
	private JPanel pnlRight; // contain pnlViewer;
	private JPanel pnlPlaybackTop;
	private JPanel pnlPlaybackMiddle;
	private JPanel pnlPlaybackBottom;
	private JComboBox cbStyle;

	private LaunchMode launchMode;

	private Visualiser visualiser;
	private boolean isPlay = false; // default false
	private File crinkleViewerFile;

	/**
	 * Creates new form PlaybackMode
	 */
	public PlaybackMode() {
		initComponents();
		setVisualiser(new Visualiser());
	}


	public PlaybackMode(LaunchMode launchMode, File crinkleViewerFile) {
		initComponents();
		this.setTitle("Crinkle Viewer - " + crinkleViewerFile.getAbsolutePath());
		this.launchMode = launchMode;
		this.crinkleViewerFile = crinkleViewerFile;
		setVisualiser(new Visualiser(crinkleViewerFile, this));
		this.addComponentToPnlViewer(visualiser.getViewerComponent());
		CrinkleViewer.setAppIcon(CrinkleViewer.CRINKLE_ICON_MAC, CrinkleViewer.CRINKLE_ICON_WIN, this);
		initPlaybackButtons();
		this.validate();
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
		pnlPlayback = new javax.swing.JPanel();
		btnRewind = new javax.swing.JButton();
		btnPlay = new javax.swing.JButton();
		btnForward = new javax.swing.JButton();
		lblTimer = new javax.swing.JLabel();
		lblStyle = new javax.swing.JLabel();
		btnSnapshot = new javax.swing.JButton();
		pnlLeft = new javax.swing.JPanel();
		pnlRight = new javax.swing.JPanel();
		pnlPlaybackTop = new javax.swing.JPanel();
		pnlPlaybackMiddle = new javax.swing.JPanel();
		pnlPlaybackBottom = new javax.swing.JPanel();
		cbStyle = new javax.swing.JComboBox(new String[] {"Jagged", "Round"});

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Crinkle Viewer");
		setMinimumSize(new Dimension(960, 720));
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(java.awt.event.WindowEvent evt) {
				formWindowClosed(evt);
			}
		});

		pnlViewer.setBorder(BorderFactory.createTitledBorder("Viewer"));

		pnlPlayback.setBorder(BorderFactory.createTitledBorder("Playback"));
		pnlPlayback.setPreferredSize(new java.awt.Dimension(210, 190));

		btnRewind.setIcon(new ImageIcon(getClass().getResource(CrinkleViewer.REWIND_ICON)));
		btnRewind.setPreferredSize(new java.awt.Dimension(40, 40));
		btnRewind.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRewindActionPerformed(evt);
			}
		});

		btnPlay.setIcon(new ImageIcon(getClass().getResource(CrinkleViewer.PLAY_ICON)));
		btnPlay.setPreferredSize(new java.awt.Dimension(50, 40));
		btnPlay.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPlayActionPerformed(evt);
			}
		});

		btnForward.setIcon(new ImageIcon(getClass().getResource(CrinkleViewer.FAST_FORWARD_ICON)));
		btnForward.setPreferredSize(new java.awt.Dimension(40, 40));
		btnForward.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnForwardActionPerformed(evt);
			}
		});

		lblTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblTimer.setText("Paused");
		lblTimer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		lblTimer.setPreferredSize(new java.awt.Dimension(175, 16));
		
		lblStyle.setText("Style: ");
		
		cbStyle.setPreferredSize(new java.awt.Dimension(120, 25));
		cbStyle.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cbStyleActionPerformed(evt);
			}
			
		});

		btnSnapshot.setIcon(new ImageIcon(getClass().getResource(CrinkleViewer.CAMERA_ICON))); // NOI18N
		btnSnapshot.setPreferredSize(new java.awt.Dimension(40, 40));
		btnSnapshot.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSnapshotActionPerformed(evt);
			}
		});

		pnlLeft.setPreferredSize(new Dimension(220, 150));

		pnlPlaybackTop.setPreferredSize(new Dimension(190, 50));
		pnlPlaybackMiddle.setPreferredSize(new Dimension(190, 60));
		pnlPlaybackBottom.setPreferredSize(new Dimension(190, 50));

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pnlLeft, BorderLayout.LINE_START);
		getContentPane().add(pnlRight, BorderLayout.CENTER);

		pnlLeft.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		pnlLeft.add(pnlPlayback);
		pnlRight.setLayout(new BorderLayout());
		pnlRight.add(pnlViewer);
		
		pnlViewer.setLayout(new BorderLayout());

		pnlPlayback.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		pnlPlayback.add(pnlPlaybackTop);
		pnlPlayback.add(pnlPlaybackMiddle);
		pnlPlayback.add(pnlPlaybackBottom);

		pnlPlaybackTop.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		pnlPlaybackTop.add(btnSnapshot);

		pnlPlaybackMiddle.add(lblStyle);
		pnlPlaybackMiddle.add(cbStyle);
		pnlPlaybackMiddle.add(lblTimer);
		
		pnlPlaybackBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
		pnlPlaybackBottom.add(btnRewind);
		pnlPlaybackBottom.add(btnPlay);
		pnlPlaybackBottom.add(btnForward);

		setSize(960, 720);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, 0);


		//validate();
	}


	private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {
		if(isPlay == false) {
			btnPlay.setIcon(new ImageIcon(getClass().getResource(CrinkleViewer.PAUSE_ICON)));
			btnForward.setEnabled(true);
			btnRewind.setEnabled(true);
			updateLabel(visualiser.play());
			isPlay = true;
		} else { // isPlay == true;
			btnPlay.setIcon(new ImageIcon(getClass().getResource(CrinkleViewer.PLAY_ICON)));
			visualiser.pause();
			isPlay = false;
		}
	}

	private void updateLabel(int speed) {
		lblTimer.setText(speed + "x Speed");
	}
	private void formWindowClosed(java.awt.event.WindowEvent evt) {
		launchMode.setEnabled(true);
		launchMode.setVisible(true);
	}

	private void addComponentToPnlViewer(Component component) {
		pnlViewer.add(component);
		validate();
	}
	
	private void removeComponentFromPnlViewer(Component component) {
		pnlViewer.remove(component);
		validate();
	}

	private void btnRewindActionPerformed(ActionEvent evt) {
		updateLabel(visualiser.rewind());
		setBtnPlayIcon(CrinkleViewer.PAUSE_ICON);
		isPlay = true;
		btnPlay.setEnabled(true);
		btnForward.setEnabled(true);
	}

	private void btnSnapshotActionPerformed(ActionEvent evt) {
		visualiser.snapshot();
	}

	private void btnForwardActionPerformed(ActionEvent evt) {
		updateLabel(visualiser.fastForward());
		setBtnPlayIcon(CrinkleViewer.PAUSE_ICON);
		isPlay = true;
		btnPlay.setEnabled(true);
		btnRewind.setEnabled(true);
	}
	
	private void cbStyleActionPerformed(ActionEvent evt) {
		int visualiseMode = cbStyle.getSelectedIndex();
		initPlaybackButtons();
		removeComponentFromPnlViewer(visualiser.getViewerComponent()); 
		setVisualiser(new Visualiser(crinkleViewerFile, this, visualiseMode));
		addComponentToPnlViewer(visualiser.getViewerComponent());
	}

	public Visualiser getVisualiser() {
		return visualiser;
	}

	public void setVisualiser(Visualiser visualiser) {
		this.visualiser = visualiser;
	}

	public void setBtnForwardEnabled(boolean b) {
		btnForward.setEnabled(b);
	}

	public void setBtnRewindEnabled(boolean b) {
		btnRewind.setEnabled(b);
	}

	public void setBtnPlayEnabled(boolean b) {
		btnPlay.setEnabled(b);
	}

	public void setBtnPlayIcon(String icon) {
		btnPlay.setIcon(new ImageIcon(getClass().getResource(icon)));
	}
	
	public void setIsPlay(boolean b) {
		this.isPlay = b;
	}
	
	private void initPlaybackButtons() {
		isPlay = false;
		btnPlay.setIcon(new ImageIcon(getClass().getResource(CrinkleViewer.PLAY_ICON)));
		btnPlay.setEnabled(true);
		btnForward.setEnabled(false);
		btnRewind.setEnabled(false);
	}
}
