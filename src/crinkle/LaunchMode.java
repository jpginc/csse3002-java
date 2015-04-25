package crinkle;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 *
 * @author ToanHo
 */
public class LaunchMode extends javax.swing.JFrame {

    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnOpen;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblFileName;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextField txfFileName;
    private javax.swing.JLabel pnlTop; // contain btnConnect
    private javax.swing.JLabel pnlBottom; // contain lblFileName, txfFileName, btnOpen

    /**
     * Creates new form LaunchMode
     */
    public LaunchMode() {
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
    	
        btnConnect = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        txfFileName = new javax.swing.JTextField();
        btnOpen = new javax.swing.JButton();
        lblFileName = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        pnlTop = new JLabel();
        pnlBottom = new JLabel();
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Crinkle Viewer");
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        btnConnect.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnConnect.setText("Sync");
        btnConnect.setPreferredSize(new java.awt.Dimension(140, 40));
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        txfFileName.setEnabled(false);
        txfFileName.setPreferredSize(new java.awt.Dimension(300, 20));

        btnOpen.setText("Open");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        lblFileName.setText("File Name");

        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setText("Status");
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
        pnlBottom.add(lblFileName);
        pnlBottom.add(txfFileName);
        pnlBottom.add(btnOpen);
        
        setSize(510, 200);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        
        validate();
    }
    
    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    	
    }

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        //JFileChooser fc = new JFileChooser();
        //fc.showOpenDialog(this);
        this.setVisible(false);
        PlaybackMode playbackMode = new PlaybackMode(this);
        playbackMode.setVisible(true);
        
    }


}
