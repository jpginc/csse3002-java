package crinkle;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.zu.ardulink.Link;
import org.zu.ardulink.RawDataListener;

import data.MovementData;
import visualiser.Visualiser;

/**
 *
 * @author ToanHo
 */
public class RealTimeMode extends VisualizingFrame {

	private JButton btnReceive;
	private JButton btnStop;
	private JPanel pnlRealTime;
	private JLabel lblStatus;
	private JLabel lblDesc;

	private Link link;
	private List<String> portList = new ArrayList<String>();
	private List<String> dataArray = new ArrayList<String>();
	private JFileChooser sfc = new JFileChooser();
	private JComboBox cbStyle;
	private boolean connectedFlag = false;
	MovementData realtimeData;
	
	
	
	/**
	 * Creates new form PlaybackMode
	 */
	public RealTimeMode() {
		initComponents();
		setVisualiser(new Visualiser());
	}

	
	/**
	 * This constructor creates a player that displays the data in real time
	 * @param launchMode
	 * @param m
	 */
	public RealTimeMode(LaunchMode launchMode) {
		super(launchMode);
		initComponents();
		//TODO the third paramater to new Visualiser needs to be the selected index of 
		//the drop down box
		realtimeData = new MovementData();
		setVisualiser(new Visualiser(realtimeData, this, 0));
		this.addComponentToPnlViewer(visualiser.getViewerComponent());

		link = Link.getDefaultInstance();

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
						//too small
						return;
					}
					System.out.println("Saving data");
					saveData();
					dataArray.clear();
				} else if ("$_YES_$".equals(received.trim())) {
					connectedFlag = true;
				} else if ("$_START_$".equals(received.trim())) {
				} else if (!("$_START_$".equals(received.trim()))) {
					if(! connectedFlag || realtimeData == null) {
						//TODO fix the crinkle _STOP_ thing
						//the crinkle is still sending data from the last time it was connected
                        return;
					}
                    lblStatus.setText("Receiving Data");
					dataArray.add(received.trim());
					realtimeData.recieve(received.trim());
				}
			}
		});
		
		boolean status = connect();
		if(status == true) {
			launchMode.setVisible(false);
			this.setVisible(true);
		} else {
			launchMode.setEnabled(true);
			launchMode.setVisible(true);
			this.dispose();
		}
	}
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		pnlRealTime = new javax.swing.JPanel();
		btnReceive = new javax.swing.JButton();
		btnStop = new javax.swing.JButton();
		lblStatus = new javax.swing.JLabel();
		lblDesc = new javax.swing.JLabel();
		cbStyle = new javax.swing.JComboBox(new String[] {"Jagged", "Rod", "Round", "Jaggered Greyscale"});
		lblDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblDesc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		lblDesc.setPreferredSize(new java.awt.Dimension(175, 16));
		lblDesc.setText("Select Visualisation Style");
		
		lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblStatus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		lblStatus.setPreferredSize(new java.awt.Dimension(175, 16));
		lblStatus.setText("Status");

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(960, 720));

		pnlRealTime.setBorder(BorderFactory.createTitledBorder("RealTime"));
		pnlRealTime.setPreferredSize(new java.awt.Dimension(210, 210));

		btnReceive.setText("Receive");
		btnReceive.setPreferredSize(new java.awt.Dimension(80, 40));
		btnReceive.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnReceiveActionPerformed(evt);
			}
		});
		
		btnStop.setText("Stop");
		btnStop.setPreferredSize(new java.awt.Dimension(80, 40));
		btnStop.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnStopActionPerformed(evt);
			}
		});
        cbStyle.setPreferredSize(new java.awt.Dimension(120, 25));
		cbStyle.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cbStyleActionPerformed(evt);
			}
			
		});

		pnlRealTime.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		pnlRealTime.add(lblStatus);
		pnlRealTime.add(btnReceive);
		pnlRealTime.add(btnStop);
		pnlRealTime.add(lblDesc);
		pnlRealTime.add(cbStyle);
		addComponentToPnlLeft(pnlRealTime);
	}

    private void cbStyleActionPerformed(ActionEvent evt) {
		int visualiseMode = cbStyle.getSelectedIndex();
		removeComponentFromPnlViewer(visualiser.getViewerComponent()); 
		setVisualiser(new Visualiser(realtimeData, this, visualiseMode));
		addComponentToPnlViewer(visualiser.getViewerComponent());
	}


	
	private void btnStopActionPerformed(ActionEvent evt) {
		link.writeSerial("$_STOP_$");
		btnStop.setEnabled(false);
		btnReceive.setEnabled(true);
		lblStatus.setText("Receive Completed");
	}

	private void btnReceiveActionPerformed(ActionEvent evt) {
		cbStyle.setEnabled(false);
		lblDesc.setText("Visualisation style locked");
		if (link.isConnected()) {
			link.writeSerial("$_TRANSMIT_$");
            lblStatus.setText("Move Crinkle to start data transfer");
			btnReceive.setEnabled(false);
			btnStop.setEnabled(true);
		} else {
			launchMode.setEnabled(true);
			launchMode.setVisible(true);
			this.dispose();
		}
	}
	
	
	/**
	 * Saves the data received from the crinkle to a .crvf file
	 */
	private void saveData() {
		if(dataArray.size() == 0) {
			return;
		}
		String filePath = "";
		PrintWriter writer = null;
		System.out.println("Size of stored array = " + dataArray.size());
		sfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int status = sfc.showSaveDialog(this);
		System.out.println("status =" + status);
		if (status == JFileChooser.APPROVE_OPTION) {
			filePath = sfc.getSelectedFile().getAbsolutePath();
			// add file extension ".crvf" to file path
			filePath += ".";
			filePath += CrinkleViewer.FILE_EXTENSION;
			File fileToSave = new File(filePath);
			launchMode.setTxfRecentFile(filePath);
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
		//after displaying the save message return to launch mode
		launchMode.setEnabled(true);
		launchMode.setVisible(true);
		launchMode.setFeedback(status == JFileChooser.APPROVE_OPTION ? "Data Saved" : "Cancelled - Data not saved");
		this.dispose();
	}

	/**
	 * Establishes a link to the Crinkle device on the specified port
	 * @return
	 */
	private boolean connect() {
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
                            dataArray.clear();
							link.writeSerial("$_STOP_$");
							lblStatus.setText("Connected");
                            dataArray.clear();
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
	}
}
