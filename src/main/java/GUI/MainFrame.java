package GUI;

import GUI.CustomComponents.NumeroLinea;
import Lexer.Lexer;
import Parser.Parser;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends javax.swing.JFrame {

    private NumeroLinea numLine;
    private DefaultTableModel tbModel;
    private Lexer lexer;
    private Parser parser;

    public MainFrame() {
        initComponents();
        this.setTitle("Compilador");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("./src/main/java/Resources/python.png"));
        btnAnalize.setIcon(new ImageIcon("./src/main/java/Resources/run.png"));
        btnOpenFile.setIcon(new ImageIcon("./src/main/java/Resources/cargar.png"));
        tbModel = new DefaultTableModel();
        numLine = new NumeroLinea(txtCode);
        scrollCode.setRowHeaderView(numLine);
    }

    public void fillTable() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlNavBar = new GUI.CustomComponents.PanelRound();
        btnOpenFile = new GUI.CustomComponents.Button();
        btnAnalize = new GUI.CustomComponents.Button();
        pnlCodeArea = new GUI.CustomComponents.PanelRound();
        scrollCode = new javax.swing.JScrollPane();
        txtCode = new javax.swing.JTextArea();
        pnlLexer = new GUI.CustomComponents.PanelRound();
        scrollTokens = new javax.swing.JScrollPane();
        tableTokens = new javax.swing.JTable();
        pnlParser = new GUI.CustomComponents.PanelRound();
        scrollCode1 = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlMain.setLayout(new java.awt.BorderLayout(5, 5));

        pnlNavBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        btnOpenFile.setForeground(new java.awt.Color(255, 255, 255));
        btnOpenFile.setText("Open");
        btnOpenFile.setBorderColor(new java.awt.Color(40, 137, 199));
        btnOpenFile.setColor(new java.awt.Color(40, 137, 199));
        btnOpenFile.setColorClick(new java.awt.Color(28, 98, 143));
        btnOpenFile.setColorOver(new java.awt.Color(111, 176, 217));
        btnOpenFile.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        btnOpenFile.setIconTextGap(10);
        btnOpenFile.setRadius(15);
        btnOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenFileActionPerformed(evt);
            }
        });
        pnlNavBar.add(btnOpenFile);

        btnAnalize.setForeground(new java.awt.Color(255, 255, 255));
        btnAnalize.setText("Run");
        btnAnalize.setBorderColor(new java.awt.Color(61, 140, 65));
        btnAnalize.setColor(new java.awt.Color(61, 140, 65));
        btnAnalize.setColorClick(new java.awt.Color(35, 89, 41));
        btnAnalize.setColorOver(new java.awt.Color(116, 180, 117));
        btnAnalize.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        btnAnalize.setIconTextGap(10);
        btnAnalize.setRadius(15);
        btnAnalize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizeActionPerformed(evt);
            }
        });
        pnlNavBar.add(btnAnalize);

        pnlMain.add(pnlNavBar, java.awt.BorderLayout.NORTH);

        pnlCodeArea.setBackground(new java.awt.Color(23, 21, 23));
        pnlCodeArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlCodeArea.setRound(10);
        pnlCodeArea.setLayout(new java.awt.GridLayout());

        scrollCode.setBackground(new java.awt.Color(23, 21, 23));
        scrollCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 137, 199)));
        scrollCode.setPreferredSize(new java.awt.Dimension(800, 400));

        txtCode.setBackground(new java.awt.Color(23, 21, 23));
        txtCode.setColumns(20);
        txtCode.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        txtCode.setRows(5);
        txtCode.setTabSize(4);
        txtCode.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        scrollCode.setViewportView(txtCode);

        pnlCodeArea.add(scrollCode);

        pnlMain.add(pnlCodeArea, java.awt.BorderLayout.CENTER);

        pnlLexer.setBackground(new java.awt.Color(23, 21, 23));
        pnlLexer.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlLexer.setRound(10);
        pnlLexer.setLayout(new java.awt.GridLayout());

        scrollTokens.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 137, 199)));
        scrollTokens.setPreferredSize(new java.awt.Dimension(452, 200));

        tableTokens.setBackground(new java.awt.Color(23, 21, 23));
        tableTokens.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tableTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Lexema", "Token", "Fila", "Columna"
            }
        ));
        tableTokens.setEnabled(false);
        scrollTokens.setViewportView(tableTokens);

        pnlLexer.add(scrollTokens);

        pnlMain.add(pnlLexer, java.awt.BorderLayout.EAST);

        pnlParser.setBackground(new java.awt.Color(23, 21, 23));
        pnlParser.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlParser.setPreferredSize(new java.awt.Dimension(980, 200));
        pnlParser.setRound(10);
        pnlParser.setLayout(new java.awt.BorderLayout(0, 5));

        scrollCode1.setBackground(new java.awt.Color(23, 21, 23));
        scrollCode1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 96, 96)));
        scrollCode1.setPreferredSize(new java.awt.Dimension(800, 400));

        txtConsole.setEditable(false);
        txtConsole.setBackground(new java.awt.Color(23, 21, 23));
        txtConsole.setColumns(20);
        txtConsole.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        txtConsole.setForeground(new java.awt.Color(255, 96, 96));
        txtConsole.setLineWrap(true);
        txtConsole.setRows(5);
        txtConsole.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        scrollCode1.setViewportView(txtConsole);

        pnlParser.add(scrollCode1, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Output");
        pnlParser.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        pnlMain.add(pnlParser, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenFileActionPerformed

    }//GEN-LAST:event_btnOpenFileActionPerformed

    private void btnAnalizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizeActionPerformed

    }//GEN-LAST:event_btnAnalizeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.CustomComponents.Button btnAnalize;
    private GUI.CustomComponents.Button btnOpenFile;
    private javax.swing.JLabel jLabel1;
    private GUI.CustomComponents.PanelRound pnlCodeArea;
    private GUI.CustomComponents.PanelRound pnlLexer;
    private javax.swing.JPanel pnlMain;
    private GUI.CustomComponents.PanelRound pnlNavBar;
    private GUI.CustomComponents.PanelRound pnlParser;
    private javax.swing.JScrollPane scrollCode;
    private javax.swing.JScrollPane scrollCode1;
    private javax.swing.JScrollPane scrollTokens;
    private javax.swing.JTable tableTokens;
    private javax.swing.JTextArea txtCode;
    private javax.swing.JTextArea txtConsole;
    // End of variables declaration//GEN-END:variables
}
