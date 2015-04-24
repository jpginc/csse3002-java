package crinkle;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.UIManager;


/**
 *
 * @author ToanHo
 */
public class PlaybackMode extends javax.swing.JFrame {

	private javax.swing.JButton btnForward;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnRewind;
    private javax.swing.JButton btnSnapshot;
    private javax.swing.JLabel lblTimer;
    private javax.swing.JPanel pnlPlayBack;
    private javax.swing.JPanel pnlViewer;
	
    private LaunchMode launchMode;

    /**
     * Creates new form PlaybackMode
     */
    public PlaybackMode() {
        initComponents();
    }

    public PlaybackMode(LaunchMode launchMode) {
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
        pnlPlayBack = new javax.swing.JPanel();
        btnRewind = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        btnForward = new javax.swing.JButton();
        lblTimer = new javax.swing.JLabel();
        btnSnapshot = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crinkle Viewer");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pnlViewer.setBorder(BorderFactory.createTitledBorder("Viewer"));
        pnlViewer.setPreferredSize(new java.awt.Dimension(750, 705));

        GroupLayout pnlViewerLayout = new GroupLayout(pnlViewer);
        pnlViewer.setLayout(pnlViewerLayout);
        pnlViewerLayout.setHorizontalGroup(
            pnlViewerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 738, Short.MAX_VALUE)
        );
        pnlViewerLayout.setVerticalGroup(
            pnlViewerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
        );

        pnlPlayBack.setBorder(BorderFactory.createTitledBorder("Playback"));
        pnlPlayBack.setPreferredSize(new java.awt.Dimension(200, 150));

        btnRewind.setIcon(new ImageIcon(getClass().getResource("/icons/1427489580_backward-40.png"))); // NOI18N
        btnRewind.setPreferredSize(new java.awt.Dimension(40, 40));

        btnPlay.setIcon(new ImageIcon(getClass().getResource("/icons/1427489511_icon-play-128.png"))); // NOI18N
        btnPlay.setPreferredSize(new java.awt.Dimension(50, 40));
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        btnForward.setIcon(new ImageIcon(getClass().getResource("/icons/1427489580_fast_forward_128.png"))); // NOI18N
        btnForward.setPreferredSize(new java.awt.Dimension(40, 40));

        lblTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimer.setText("00:00:00");
        lblTimer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTimer.setPreferredSize(new java.awt.Dimension(175, 16));

        btnSnapshot.setIcon(new ImageIcon(getClass().getResource("/icons/1427962660_camera_-30.png"))); // NOI18N
        btnSnapshot.setPreferredSize(new java.awt.Dimension(40, 40));

        GroupLayout pnlPlayBackLayout = new GroupLayout(pnlPlayBack);
        pnlPlayBack.setLayout(pnlPlayBackLayout);
        pnlPlayBackLayout.setHorizontalGroup(
            pnlPlayBackLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlayBackLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnRewind, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnForward, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, pnlPlayBackLayout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(pnlPlayBackLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, pnlPlayBackLayout.createSequentialGroup()
                        .addComponent(lblTimer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(GroupLayout.Alignment.TRAILING, pnlPlayBackLayout.createSequentialGroup()
                        .addComponent(btnSnapshot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );
        pnlPlayBackLayout.setVerticalGroup(
            pnlPlayBackLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, pnlPlayBackLayout.createSequentialGroup()
                .addComponent(btnSnapshot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(lblTimer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPlayBackLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnForward, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRewind, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPlayBack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlViewer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(pnlViewer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPlayBack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        // TODO add your handling code here:
        btnPlay.setIcon(new ImageIcon(getClass().getResource("/icons/1427489556_icon-pause-40.png")));
    }

    private void formWindowClosed(java.awt.event.WindowEvent evt) {
        // TODO add your handling code here:
        launchMode.setVisible(true);
    }

}
