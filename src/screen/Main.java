
package screen;

import connection.FileRemove;
import connection.Ports;
import controller.FileController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import model.FileMessage;
import model.ServerStatus;
import model.UserMessage;
import utils.FileUtils;
import utils.SessionUser;
import utils.ImageRenderer;

public class Main extends javax.swing.JFrame {

    private final ArrayList<FileMessage> fileMessageList;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        Main.this.setVisible(true);
        
        centralizeScreen();

        UserMessage um = SessionUser.getInstance();
        System.out.println("Usuario logado: " + um.toString());
        
        ServerStatus.handleLabel(server1Label, server2Label);
        
        setButtonIcons();
        setColumnIcons();

        fileMessageList = FileController.ReceiveFiles(um);
        System.out.println(fileMessageList.toString());

        configureTable();
        configureFileFilter();

        reloadFileList();
    }

    private void filterTable(String inputValue) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        model.setRowCount(0); // clear table

        for (FileMessage fm : fileMessageList) {
            String filename = fm.getFilename();
            if (filename.toUpperCase().contains(inputValue.toUpperCase()) || inputValue.equals("") || inputValue.equals("Digite o nome do arquivo")) {
                Object[] row = {filename, null, null, null};
                model.addRow(row);
            }
        }
    }

    private void configureFileFilter() {
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable(jTextField1.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(jTextField1.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable(jTextField1.getText());
            }

        });
    }

    private void centralizeScreen() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    private void configureTable() {
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(350);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.rowAtPoint(evt.getPoint());
                int col = jTable1.columnAtPoint(evt.getPoint());
                FileMessage selectedFile = fileMessageList.get(row);
                switch (col) {
                    case 1: // info
                        System.out.println("Info");
                        onFileInfoClick(selectedFile);
                        break;
                    case 2: // download
                        System.out.println("Download");
                         {
                            try {
                                onDownloadClick(selectedFile);
                            } catch (IOException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        break;
                    case 3: // delete
                        System.out.println("Delete");
                         {
                            try {
                                onRemoveClick(selectedFile, row);
                                reloadFileList();
                            } catch (IOException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                }

            }
        });
    }

    private void onFileInfoClick(FileMessage selectedFile) {
        new FileProperties(selectedFile);
    }

    private void onRemoveClick(FileMessage selectedFile, int row) throws IOException {
        FileRemove fr = new FileRemove(Ports.REMOVE);
        fr.run(selectedFile);
        fileMessageList.remove(row);
    }

    private void onDownloadClick(FileMessage selectedFile) throws IOException {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        fc.setVisible(true);
        fc.setApproveButtonText("Enviar");
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File folder = fc.getSelectedFile();
            File file = new File(folder.getAbsolutePath() + "/"
                    + selectedFile.getFilename() + "." + selectedFile.getFileType());
            String filePath = file.getAbsolutePath();
            file.createNewFile();
            System.out.println(filePath);
            Files.write(Paths.get(filePath), selectedFile.getContent());
        }
    }

    private void reloadFileList() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        model.setRowCount(0); // clear table

        for (FileMessage fm : fileMessageList) {
            Object[] row = {fm.getFilename(), null, null, null};
            model.addRow(row);
        }
    }

    private void setButtonIcons() {
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add-16.png")));
        infoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/information.png")));
    }

    private void setColumnIcons() {
        ImageIcon infoIcon = new javax.swing.ImageIcon(getClass().getResource("/images/information.png"));
        jTable1.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer(infoIcon));

        ImageIcon downloadIcon = new javax.swing.ImageIcon(getClass().getResource("/images/download.png"));
        jTable1.getColumnModel().getColumn(2).setCellRenderer(new ImageRenderer(downloadIcon));

        ImageIcon removeIcon = new javax.swing.ImageIcon(getClass().getResource("/images/clear-button.png"));
        jTable1.getColumnModel().getColumn(3).setCellRenderer(new ImageRenderer(removeIcon));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        infoButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        server1Label = new javax.swing.JLabel();
        server2Label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(254, 254, 254));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        jLabel1.setText("Femyvi");

        jTextField1.setForeground(new java.awt.Color(136, 136, 136));
        jTextField1.setText("Digite o nome do arquivo");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTextField1InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Info", "Baixar", "Excluir"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(22);
        jScrollPane1.setViewportView(jTable1);

        infoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Servidor 1:");

        jLabel3.setText("Servidor 2:");

        server1Label.setForeground(new java.awt.Color(40, 166, 83));
        server1Label.setText("Online");

        server2Label.setForeground(new java.awt.Color(221, 25, 25));
        server2Label.setText("Offline");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(infoButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(147, 147, 147)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(server1Label))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(server2Label)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(server1Label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(server2Label))))
                .addGap(21, 21, 21)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        JFileChooser fc = new JFileChooser(new File("./"));
        fc.setVisible(true);
        fc.setApproveButtonText("Inserir");

        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            File file = fc.getSelectedFile();

            try {
                FileController.SendFile(file);
                FileMessage newFileMessage = FileUtils.fileToFileMessage(file);
                newFileMessage.setOwner(SessionUser.getInstance().getLogin());
                this.fileMessageList.add(newFileMessage);
                reloadFileList();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        if (jTextField1.getText().equals("Digite o nome do arquivo")) {
            jTextField1.setText("");
            jTextField1.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        if (jTextField1.getText().equals("")) {
            jTextField1.setText("Digite o nome do arquivo");
            jTextField1.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void infoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoButtonActionPerformed
        new RepoProperties(fileMessageList);
    }//GEN-LAST:event_infoButtonActionPerformed

    private void jTextField1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTextField1InputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField1InputMethodTextChanged

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton infoButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel server1Label;
    private javax.swing.JLabel server2Label;
    // End of variables declaration//GEN-END:variables
}
