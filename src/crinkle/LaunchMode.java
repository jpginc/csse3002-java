package crinkle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

import org.zu.ardulink.Link;
import org.zu.ardulink.RawDataListener;

import data.MovementData;

/**
 *
 * @author ToanHo
 */
public class LaunchMode extends javax.swing.JFrame {

	//private javax.swing.JButton btnSync;
	private javax.swing.JButton btnOpen;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JLabel lblRecentFile;
	private javax.swing.JLabel lblStatus;
	private javax.swing.JTextField txfRecentFile;
	private javax.swing.JLabel pnlTop; // contain btnConnect
	private javax.swing.JLabel pnlBottom; // contain lblRecentFile, txfRecentFile, btnOpen
	private javax.swing.JButton btnConnect;
/*	private javax.swing.JButton btnReceive; //button that starts receiving live data from the crinkle
	private javax.swing.JButton btnStop; 
	private javax.swing.JButton btnConnect;
	private Link link;
	private List<String> portList = new ArrayList<String>();
	private List<String> dataArray = new ArrayList<String>();
	private JFileChooser sfc = new JFileChooser();
	private boolean connectedFlag = false;
	//private String debugArray = "1,0,0,0,0"; //change for debug purposes
	
	// the movemnt data object to load realtime data into
	private MovementData realtimeData;
	// the gui for watching the visualisation
	RealTimeMode realTimeMode;*/


	/**
	 * Creates new form LaunchMode
	 */
	public LaunchMode() {

/*		link = Link.getDefaultInstance();

		//Add data received from crinkle to data array or store the data as a file
		link.addRawDataListener(new RawDataListener() {
			@Override
			public void parseInput(String id, int numBytes, int[] message) {
				String received = "";
				for (int i = 0; i < numBytes; i++) {
					received += (char) message[i];
				}
				System.out.println(received.trim());
				//load the recieved data into the realtime data object
				if ("$_STOP_$".equals(received.trim())) {
					if(dataArray.size() < 10) {
						//not enough data, probably a mistake
						return;
					}
					System.out.println("Saving data");
					saveData();
					dataArray.clear();
				} else if ("$_YES_$".equals(received.trim())) {
					connectedFlag = true;
				} else if ("$_START_$".equals(received.trim())) {
					//no longer userd
					//dataArray.add(debugArray);
				} else if (!("$_START_$".equals(received.trim()))) {
					if(! connectedFlag || realtimeData == null) {
						//the crinkle is still sending data from the last time it was connected
                        return;
					}
					dataArray.add(received.trim());
					realtimeData.recieve(received.trim());
				}
			}
		});*/

		initComponents();
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
			javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();;
		}

		//btnSync = new javax.swing.JButton();
		jSeparator1 = new javax.swing.JSeparator();
		txfRecentFile = new javax.swing.JTextField();
		btnOpen = new javax.swing.JButton();
		lblRecentFile = new javax.swing.JLabel();
		lblStatus = new javax.swing.JLabel();
		pnlTop = new JLabel();
		pnlBottom = new JLabel();
		btnConnect = new javax.swing.JButton();
/*		btnReceive = new javax.swing.JButton();
		btnStop = new javax.swing.JButton();
		btnConnect = new javax.swing.JButton();*/


		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Crinkle Viewer");
		setResizable(false);

/*		btnSync.setFont(new java.awt.Font("Tahoma", 1, 14));
		btnSync.setText("Sync");
		btnSync.setPreferredSize(new java.awt.Dimension(140, 40));
		btnSync.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSyncActionPerformed(evt);
			}
		});*/

/*		btnReceive.setFont(new java.awt.Font("Tahoma", 1, 14));
		btnReceive.setText("Receive");
		btnReceive.setPreferredSize(new java.awt.Dimension(140, 40));
		btnReceive.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnReceiveActionPerformed(evt);
			}
		});*/

/*		btnStop.setFont(new java.awt.Font("Tahoma", 1, 14));
		btnStop.setText("Stop");
		btnStop.setEnabled(false);
		btnStop.setPreferredSize(new java.awt.Dimension(140, 40));
		btnStop.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnStopActionPerformed(evt);
			}
		});*/

		btnConnect.setFont(new java.awt.Font("Tahoma", 1, 14));
		btnConnect.setText("Connect");
		btnConnect.setPreferredSize(new java.awt.Dimension(140, 40));
		btnConnect.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnConnectActionPerformed(evt);
			}
		});

		txfRecentFile.setEnabled(false);
		txfRecentFile.setPreferredSize(new java.awt.Dimension(300, 20));

		btnOpen.setText("Open");
		btnOpen.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnOpenActionPerformed(evt);
			}
		});

		lblRecentFile.setText("Recent File:");

		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setText("");
		lblStatus.setForeground(Color.BLUE);
		lblStatus.setPreferredSize(new Dimension(440, 15));

		jSeparator1.setPreferredSize(new Dimension(430, 10));

		pnlTop.setPreferredSize(new Dimension(500, 100));
		pnlBottom.setPreferredSize(new Dimension(500, 30));

		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		getContentPane().add(pnlTop);
		pnlTop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
		pnlTop.add(btnConnect);
		pnlTop.add(lblStatus);
		getContentPane().add(jSeparator1);
		getContentPane().add(pnlBottom);
		pnlBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
		pnlBottom.add(lblRecentFile);
		pnlBottom.add(txfRecentFile);
		pnlBottom.add(btnOpen);

		setSize(510, 200);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		CrinkleViewer.setAppIcon(CrinkleViewer.CRINKLE_ICON_MAC, CrinkleViewer.CRINKLE_ICON_WIN, this);

		validate();
	}

/*	private void btnSyncActionPerformed(java.awt.event.ActionEvent evt) {
		if (link.isConnected()) {
			lblStatus.setText("Synchronising...");
			link.writeSerial("$_SYNC_$");
			lblStatus.setText("Finished Sync");
		} else {
			lblStatus.setText("Crinkle disconnected");
			pnlTop.remove(btnSync);
			pnlTop.remove(btnReceive);
			pnlTop.remove(btnStop);
			pnlTop.remove(lblStatus);
			pnlTop.add(btnConnect);
			pnlTop.add(lblStatus);
			pnlTop.repaint();
		}
	}*/

/*	private void btnReceiveActionPerformed(java.awt.event.ActionEvent evt) {
		if (link.isConnected()) {
			link.writeSerial("$_TRANSMIT_$");
			lblStatus.setText("Receiving Data");
			btnReceive.setEnabled(false);
			btnStop.setEnabled(true);
		} else {
			lblStatus.setText("Crinkle disconnected");
			pnlTop.remove(btnSync);
			pnlTop.remove(btnReceive);
			pnlTop.remove(btnStop);
			pnlTop.remove(lblStatus);
			pnlTop.add(btnConnect);
			pnlTop.add(lblStatus);
			pnlTop.repaint();
		}
	}*/

/*	private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {
		link.writeSerial("$_STOP_$");
		btnStop.setEnabled(false);
		btnReceive.setEnabled(true);
		lblStatus.setText("Receive Completed");
		//save the data set received
	}*/

	private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {
			this.setEnabled(false);
			RealTimeMode realTimeMode = new RealTimeMode(this);
/*			pnlTop.remove(lblStatus);
			pnlTop.remove(btnConnect);
			pnlTop.add(btnSync);
			pnlTop.add(btnReceive);
			pnlTop.add(btnStop);
			pnlTop.add(lblStatus);
			pnlTop.repaint();*/
	}

	private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {
		System.err.println("<<<Browse sample file: [Your Workspace]/csse3002-java/src/sampleData/sample.crvf>>>");
		String recentDir = getRecentDir(txfRecentFile.getText());
		File selectedFile = chooseFile(recentDir);
		if(selectedFile != null) {
			this.setEnabled(false);
			lblStatus.setText("");
			txfRecentFile.setText(selectedFile.getAbsolutePath());
			PlaybackMode playbackMode = new PlaybackMode(this, selectedFile);
			this.setVisible(false);
			playbackMode.setVisible(true);
		} 
	}

	/** Show file chooser dialog with crinkle viewer files displayed.
	 * @return file selected or null if user click cancel */
	private File chooseFile(String recentDir) {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setAcceptAllFileFilterUsed(false);
		fc.setCurrentDirectory(new File(recentDir));
		fc.addChoosableFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				String decription = "CrinkleViewer Files (." + CrinkleViewer.FILE_EXTENSION + ")";
				return decription;
			}

			@Override
			public boolean accept(File f) {
				if(f.isDirectory()) {
					return true;
				}
				String extension = getFileExtension(f);
				if(extension == null) {
					return false;
				} else {
					if(extension.equals(CrinkleViewer.FILE_EXTENSION)) {
						return true;
					} else {
						return false;
					}
				}
			}
		});
		fc.setFileView(new FileView() {
			ImageIcon crinkleIcon = new ImageIcon(getClass().getResource(CrinkleViewer.CRINKLE_FILE_ICON));

			public String getTypeDescription(File f) {
				String extension = getFileExtension(f);
				String type = null;
				if (extension != null) {
					if (extension.equals(CrinkleViewer.FILE_EXTENSION)) {
						type = "CrinkleViewer File";
					} 
				}
				return type;
			}	

			public Icon getIcon(File f) {
				String extension = getFileExtension(f);
				Icon icon = null;
				if (extension != null) {
					if (extension.equals(CrinkleViewer.FILE_EXTENSION)) {
						icon = crinkleIcon;
					}
				}
				return icon;
			}
		});
		int returnVal = fc.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if(file.exists()) {
				return file;
			} else {
				lblStatus.setText("Error: Selected file does not exist!");
				lblStatus.setForeground(Color.RED);
				return null;
			}
		} else {
			lblStatus.setText("");
			return null;
		}
	}

	/** Get the file extension
	 * @return file extension (lower case), otherwise null */
	private String getFileExtension(File f) {
		String extension = null;
		if(f != null) {
			String fileName = f.getName();
			int i = fileName.lastIndexOf(".");
			if(i > 0 && i < fileName.length() - 1) {
				extension = fileName.substring(i + 1).toLowerCase();
			}
		}
		return extension;
	}

	/** Get absolute path of the recent directory 
	 * @return absolute path of the recent directory, otherwise empty string */
	private String getRecentDir(String absolutePath) {
		String recentDir = "";
		File f = new File(absolutePath);
		if(f.exists()) {
			recentDir = f.getParent();
		}
		return recentDir;
	}
	
	/** Set text for txfRecentFile field */
	public void setTxfRecentFile(String string) {
		txfRecentFile.setText(string);
	}

	/**
	 * Saves the data received from the crinkle to a .crvf file
	 */
/*	private void saveData() {
		if(dataArray.size() == 0) {
			return;
		}
		String filePath = "";
		PrintWriter writer = null;
		System.out.println("Size of stored array = " + dataArray.size()
				+ " realtime data" + realtimeData.size());
		sfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int status = sfc.showSaveDialog(LaunchMode.this);
		System.out.println("status =" + status);
		if (status == JFileChooser.APPROVE_OPTION) {
			filePath = sfc.getSelectedFile().getAbsolutePath();
			// add file extension ".crvf" to file path
			filePath += ".";
			filePath += CrinkleViewer.FILE_EXTENSION;
			File fileToSave = new File(filePath);
			txfRecentFile.setText(filePath);
			System.out.println("Saving file as: " + filePath);
			try {
				writer = new PrintWriter(fileToSave);
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
				e.printStackTrace();
			}
			for (int i = 0; i < dataArray.size(); i++) {
				//save each line
				writer.println(dataArray.get(i));
				writer.flush();
			}
			writer.close();
		}
	}*/

	/**
	 * Establishes a link to the Crinkle device on the specified port
	 * @return
	 */
/*	private boolean connect() {
		boolean connected = false;
		String port = "";
		portList = link.getPortList();
		lblStatus.setText("Connecting");
		try {
			if (portList != null && portList.size() > 0) {
				//String port = comboPorts.getSelectedItem().toString();
				for (int i = 0; i < portList.size(); i++) {
					port = portList.get(i);
					if (port.startsWith("/dev/cu.usbmodem") || port.startsWith("COM")) {
						connected = link.connect(port);
						//Check to see if Crinkle connected on port
						link.writeSerial("$_CHECK_$");
						//see if response received
						try {
							Thread.sleep(1500);
						} catch (InterruptedException e) {
							 Thread.currentThread().interrupt();
						}
						if (connectedFlag) {
							//if the arduino wasn't stopped before the program closed last time it will
							//still be transmitting. Stop it now
							link.writeSerial("$_STOP_$");
							lblStatus.setText("Connected");
							realtimeData = new MovementData();
                            dataArray.clear();
                            playbackMode = new PlaybackMode(this, realtimeData);
                            playbackMode.setVisible(true);
							return true;
						}
					}
				}
				lblStatus.setText("Crinkle not connected");
				System.out.println("Crinkle not connected");
				connected = false;
			} else {
				lblStatus.setText("Crinkle not connected");
				System.out.println("Crinkle not connected");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connected;
	}*/

}
