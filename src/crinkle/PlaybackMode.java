package crinkle;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
public class PlaybackMode extends VisualizingFrame {

	private JButton btnForward;
	private JButton btnPlay;
	private JButton btnRewind;
	private JButton btnSnapshot;
	private JLabel lblStatus;
	private JLabel lblStyle;
	private JPanel pnlPlayback; // contain pnlPlaybackTop, Middle, Bottom
	private JPanel pnlPlaybackTop;
	private JPanel pnlPlaybackMiddle;
	private JPanel pnlPlaybackBottom;
	private JComboBox cbStyle;

	private boolean isPlay = false; // default false
	private File crinkleViewerFile;

	/**
	 * Creates new form PlaybackMode
	 */
	public PlaybackMode() {
		super();
		initComponents();
	}


	public PlaybackMode(LaunchMode launchMode, File crinkleViewerFile) {
		super(launchMode);
		initComponents();
		this.setTitle("Crinkle Viewer - " + crinkleViewerFile.getAbsolutePath());
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

		pnlPlayback = new javax.swing.JPanel();
		btnRewind = new javax.swing.JButton();
		btnPlay = new javax.swing.JButton();
		btnForward = new javax.swing.JButton();
		lblStatus = new javax.swing.JLabel();
		lblStyle = new javax.swing.JLabel();
		btnSnapshot = new javax.swing.JButton();
		pnlPlaybackTop = new javax.swing.JPanel();
		pnlPlaybackMiddle = new javax.swing.JPanel();
		pnlPlaybackBottom = new javax.swing.JPanel();
		cbStyle = new javax.swing.JComboBox(new String[] {"Jagged", "Rod", "Round"});

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Crinkle Viewer");
		setMinimumSize(new Dimension(960, 720));

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
		
		lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblStatus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		lblStatus.setPreferredSize(new java.awt.Dimension(175, 16));
		
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

		pnlPlaybackTop.setPreferredSize(new Dimension(190, 50));
		pnlPlaybackMiddle.setPreferredSize(new Dimension(190, 40));
		pnlPlaybackBottom.setPreferredSize(new Dimension(190, 70));

		pnlPlayback.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		pnlPlayback.add(pnlPlaybackTop);
		pnlPlayback.add(pnlPlaybackMiddle);
		pnlPlayback.add(pnlPlaybackBottom);

		pnlPlaybackTop.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		pnlPlaybackTop.add(btnSnapshot);

		pnlPlaybackMiddle.add(lblStyle);
		pnlPlaybackMiddle.add(cbStyle);
		
		pnlPlaybackBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
		pnlPlaybackBottom.add(lblStatus);
		pnlPlaybackBottom.add(btnRewind);
		pnlPlaybackBottom.add(btnPlay);
		pnlPlaybackBottom.add(btnForward);
		super.addComponentToPnlLeft(pnlPlayback);
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
			lblStatus.setText("Paused");
			isPlay = false;
		}
	}

	private void updateLabel(int speed) {
		lblStatus.setText(speed + "x Speed");
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
	
	public void setLblStatus(String status) {
		this.lblStatus.setText(status);
	}

	/** Set initializing state for buttons in playback panel */
	public void initPlaybackButtons() {
		isPlay = false;
		btnPlay.setIcon(new ImageIcon(getClass().getResource(CrinkleViewer.PLAY_ICON)));
		btnPlay.setEnabled(true);
		btnForward.setEnabled(false);
		btnRewind.setEnabled(false);
		lblStatus.setText("");
	}
}
