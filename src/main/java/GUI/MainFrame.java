package GUI;

import Files.FileManager;
import GUI.CustomComponents.NumeroLinea;
import LexerOperations.Lexer;
import ParserOperations.Parser;
import SemanticOperations.Variable;
import SemanticOperations.VariableAssignment;
import SemanticOperations.VariableCheck;
import Tokens.Token;
import java.awt.Component;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class MainFrame extends javax.swing.JFrame {

    private NumeroLinea numLine;
    private DefaultTableModel tbModel;
    private Lexer lexer;
    private Parser parser;
    // private VariableCheck var;
    private VariableAssignment semantic;

    public MainFrame() {
        initComponents();
        this.setTitle("Compilador");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("./src/main/java/Resources/python.png"));
        btnAnalize.setIcon(new ImageIcon("./src/main/java/Resources/run.png"));
        btnClear.setIcon(new ImageIcon("./src/main/java/Resources/clear.png"));
        btnOpenFile.setIcon(new ImageIcon("./src/main/java/Resources/cargar.png"));
        tbModel = new DefaultTableModel();
        numLine = new NumeroLinea(txtCode);
        scrollCode.setRowHeaderView(numLine);
        lexer = new Lexer();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlNavBar = new GUI.CustomComponents.PanelRound();
        jPanel2 = new javax.swing.JPanel();
        btnOpenFile = new GUI.CustomComponents.Button();
        btnAnalize = new GUI.CustomComponents.Button();
        btnClear = new GUI.CustomComponents.Button();
        jPanel1 = new javax.swing.JPanel();
        btnBasico = new GUI.CustomComponents.Button();
        btnCompleto = new GUI.CustomComponents.Button();
        btnIF = new GUI.CustomComponents.Button();
        btnFOR = new GUI.CustomComponents.Button();
        btnWHILE = new GUI.CustomComponents.Button();
        btnSWITCH = new GUI.CustomComponents.Button();
        jPanel3 = new javax.swing.JPanel();
        pnlLexer = new GUI.CustomComponents.PanelRound();
        scrollTokens = new javax.swing.JScrollPane();
        tableTokens = new javax.swing.JTable();
        pnlSemantic = new GUI.CustomComponents.PanelRound();
        scrollVariables = new javax.swing.JScrollPane();
        tableVariables = new javax.swing.JTable();
        pnlParser = new GUI.CustomComponents.PanelRound();
        scrollCode1 = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        pnlCodeArea = new GUI.CustomComponents.PanelRound();
        scrollCode = new javax.swing.JScrollPane();
        txtCode = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlMain.setLayout(new java.awt.BorderLayout(5, 5));

        pnlNavBar.setLayout(new java.awt.BorderLayout());

        btnOpenFile.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
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
        jPanel2.add(btnOpenFile);

        btnAnalize.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
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
        jPanel2.add(btnAnalize);

        btnClear.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setIcon(new javax.swing.ImageIcon(
                "C:\\Users\\iTzChrisDev\\Documents\\GitHub\\PythonCompilerDemo\\src\\main\\java\\Resources\\clear.png")); // NOI18N
        btnClear.setText("Clear");
        btnClear.setBorderColor(new java.awt.Color(102, 102, 102));
        btnClear.setColor(new java.awt.Color(102, 102, 102));
        btnClear.setColorClick(new java.awt.Color(70, 70, 70));
        btnClear.setColorOver(new java.awt.Color(140, 140, 140));
        btnClear.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        btnClear.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnClear.setIconTextGap(10);
        btnClear.setRadius(15);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel2.add(btnClear);

        pnlNavBar.add(jPanel2, java.awt.BorderLayout.WEST);

        jPanel1.setOpaque(false);

        btnBasico.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnBasico.setForeground(new java.awt.Color(255, 255, 255));
        btnBasico.setText("Básico");
        btnBasico.setBorderColor(new java.awt.Color(248, 59, 59));
        btnBasico.setColor(new java.awt.Color(248, 59, 59));
        btnBasico.setColorClick(new java.awt.Color(160, 20, 20));
        btnBasico.setColorOver(new java.awt.Color(255, 96, 96));
        btnBasico.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        btnBasico.setRadius(15);
        btnBasico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBasicoActionPerformed(evt);
            }
        });
        jPanel1.add(btnBasico);

        btnCompleto.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnCompleto.setForeground(new java.awt.Color(255, 255, 255));
        btnCompleto.setText("Completo");
        btnCompleto.setBorderColor(new java.awt.Color(248, 59, 59));
        btnCompleto.setColor(new java.awt.Color(248, 59, 59));
        btnCompleto.setColorClick(new java.awt.Color(160, 20, 20));
        btnCompleto.setColorOver(new java.awt.Color(255, 96, 96));
        btnCompleto.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        btnCompleto.setRadius(15);
        btnCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompletoActionPerformed(evt);
            }
        });
        jPanel1.add(btnCompleto);

        btnIF.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnIF.setForeground(new java.awt.Color(255, 255, 255));
        btnIF.setText("IF");
        btnIF.setBorderColor(new java.awt.Color(248, 59, 59));
        btnIF.setColor(new java.awt.Color(248, 59, 59));
        btnIF.setColorClick(new java.awt.Color(160, 20, 20));
        btnIF.setColorOver(new java.awt.Color(255, 96, 96));
        btnIF.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        btnIF.setRadius(15);
        btnIF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIFActionPerformed(evt);
            }
        });
        jPanel1.add(btnIF);

        btnFOR.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnFOR.setForeground(new java.awt.Color(255, 255, 255));
        btnFOR.setText("FOR");
        btnFOR.setBorderColor(new java.awt.Color(248, 59, 59));
        btnFOR.setColor(new java.awt.Color(248, 59, 59));
        btnFOR.setColorClick(new java.awt.Color(160, 20, 20));
        btnFOR.setColorOver(new java.awt.Color(255, 96, 96));
        btnFOR.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        btnFOR.setRadius(15);
        btnFOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFORActionPerformed(evt);
            }
        });
        jPanel1.add(btnFOR);

        btnWHILE.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnWHILE.setForeground(new java.awt.Color(255, 255, 255));
        btnWHILE.setText("WHILE");
        btnWHILE.setBorderColor(new java.awt.Color(248, 59, 59));
        btnWHILE.setColor(new java.awt.Color(248, 59, 59));
        btnWHILE.setColorClick(new java.awt.Color(160, 20, 20));
        btnWHILE.setColorOver(new java.awt.Color(255, 96, 96));
        btnWHILE.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        btnWHILE.setRadius(15);
        btnWHILE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWHILEActionPerformed(evt);
            }
        });
        jPanel1.add(btnWHILE);

        btnSWITCH.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnSWITCH.setForeground(new java.awt.Color(255, 255, 255));
        btnSWITCH.setText("SWITCH");
        btnSWITCH.setBorderColor(new java.awt.Color(248, 59, 59));
        btnSWITCH.setColor(new java.awt.Color(248, 59, 59));
        btnSWITCH.setColorClick(new java.awt.Color(160, 20, 20));
        btnSWITCH.setColorOver(new java.awt.Color(255, 96, 96));
        btnSWITCH.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        btnSWITCH.setRadius(15);
        btnSWITCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSWITCHActionPerformed(evt);
            }
        });
        jPanel1.add(btnSWITCH);

        pnlNavBar.add(jPanel1, java.awt.BorderLayout.EAST);

        pnlMain.add(pnlNavBar, java.awt.BorderLayout.NORTH);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(2, 1, 5, 5));

        pnlLexer.setBackground(new java.awt.Color(23, 21, 23));
        pnlLexer.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlLexer.setRound(10);
        pnlLexer.setLayout(new java.awt.GridLayout(1, 0));

        scrollTokens.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 137, 199)));
        scrollTokens.setPreferredSize(new java.awt.Dimension(452, 200));

        tableTokens.setBackground(new java.awt.Color(23, 21, 23));
        tableTokens.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tableTokens.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null }
                },
                new String[] {
                        "Lexema", "Token", "Fila", "Columna"
                }));
        tableTokens.setEnabled(false);
        scrollTokens.setViewportView(tableTokens);

        pnlLexer.add(scrollTokens);

        jPanel3.add(pnlLexer);

        pnlSemantic.setBackground(new java.awt.Color(23, 21, 23));
        pnlSemantic.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlSemantic.setPreferredSize(new java.awt.Dimension(200, 220));
        pnlSemantic.setRound(10);
        pnlSemantic.setLayout(new java.awt.GridLayout());

        scrollVariables.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 137, 199)));
        scrollVariables.setPreferredSize(new java.awt.Dimension(452, 200));

        tableVariables.setBackground(new java.awt.Color(23, 21, 23));
        tableVariables.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tableVariables.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null }
                },
                new String[] {
                        "Variable", "Tipo", "Valor", "Estado", "Fila"
                }));
        tableVariables.setEnabled(false);
        scrollVariables.setViewportView(tableVariables);

        pnlSemantic.add(scrollVariables);

        jPanel3.add(pnlSemantic);

        pnlMain.add(jPanel3, java.awt.BorderLayout.EAST);

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

        pnlCodeArea.setBackground(new java.awt.Color(23, 21, 23));
        pnlCodeArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlCodeArea.setRound(10);
        pnlCodeArea.setLayout(new java.awt.GridLayout(1, 0));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnClearActionPerformed
        txtCode.setText("");
    }// GEN-LAST:event_btnClearActionPerformed

    private void btnOpenFileActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnOpenFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./src/main/java/Files/TestFiles"));
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            FileManager fm = new FileManager(file);
            fm.cargarArchivo(txtCode);
            tbModel.setRowCount(0);
            jLabel1.setText("Output - " + fileChooser.getSelectedFile().getName());
        }
    }// GEN-LAST:event_btnOpenFileActionPerformed

    private void btnAnalizeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAnalizeActionPerformed
        String code = txtCode.getText();
        if (!code.isBlank()) {
            // ANALIZADOR LEXICO
            lexer.clearLexemes();
            lexer.analizeCode(code);
            fillTable();

            // ANALIZADOR SINTACTICO
            parser = new Parser(lexer.getTokenList(), txtConsole);
            parser.parseCode();

            // ANALIZADOR SEMANTICO
            // var = new VariableCheck(lexer.getTokenList(), txtConsole);
            // var.check();
            semantic = new VariableAssignment(lexer.getTokenList());
            semantic.checkVars();
            fillVar();
        } else {
            jLabel1.setText("Output");
            JOptionPane.showMessageDialog(null, "No hay código en el editor", "Atención!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }// GEN-LAST:event_btnAnalizeActionPerformed

    private void btnBasicoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBasicoActionPerformed
        File file = new File("./src/main/java/Files/TestFiles/basico.py");
        FileManager fm = new FileManager(file);
        fm.cargarArchivo(txtCode);
        tbModel.setRowCount(0);
        jLabel1.setText("Output - " + file.getName());
    }// GEN-LAST:event_btnBasicoActionPerformed

    private void btnCompletoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCompletoActionPerformed
        File file = new File("./src/main/java/Files/TestFiles/completo.py");
        FileManager fm = new FileManager(file);
        fm.cargarArchivo(txtCode);
        tbModel.setRowCount(0);
        jLabel1.setText("Output - " + file.getName());
    }// GEN-LAST:event_btnCompletoActionPerformed

    private void btnIFActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnIFActionPerformed
        File file = new File("./src/main/java/Files/TestFiles/if.py");
        FileManager fm = new FileManager(file);
        fm.cargarArchivo(txtCode);
        tbModel.setRowCount(0);
        jLabel1.setText("Output - " + file.getName());
    }// GEN-LAST:event_btnIFActionPerformed

    private void btnFORActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFORActionPerformed
        File file = new File("./src/main/java/Files/TestFiles/for.py");
        FileManager fm = new FileManager(file);
        fm.cargarArchivo(txtCode);
        tbModel.setRowCount(0);
        jLabel1.setText("Output - " + file.getName());
    }// GEN-LAST:event_btnFORActionPerformed

    private void btnWHILEActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnWHILEActionPerformed
        File file = new File("./src/main/java/Files/TestFiles/while.py");
        FileManager fm = new FileManager(file);
        fm.cargarArchivo(txtCode);
        tbModel.setRowCount(0);
        jLabel1.setText("Output - " + file.getName());
    }// GEN-LAST:event_btnWHILEActionPerformed

    private void btnSWITCHActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSWITCHActionPerformed
        File file = new File("./src/main/java/Files/TestFiles/switch.py");
        FileManager fm = new FileManager(file);
        fm.cargarArchivo(txtCode);
        tbModel.setRowCount(0);
        jLabel1.setText("Output - " + file.getName());
    }// GEN-LAST:event_btnSWITCHActionPerformed

    public void fillTable() {
        tbModel = new DefaultTableModel();
        tbModel.setRowCount(0);
        tbModel.addColumn("Lexema");
        tbModel.addColumn("Token");
        tbModel.addColumn("Fila");
        tbModel.addColumn("Columna");

        for (Token lex : lexer.getTokenList()) {
            tbModel.addRow(new Object[] { lex.getLexeme(), lex.getToken(), lex.getRow(), lex.getColumn() });
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableTokens.setDefaultRenderer(Object.class, centerRenderer);

        tableTokens.setModel(tbModel);

        TableColumnModel columnModel = tableTokens.getColumnModel();
        for (int column = 0; column < tableTokens.getColumnCount(); column++) {
            TableColumn tableColumn = columnModel.getColumn(column);
            int preferredWidth = 0;

            for (int i = 0; i < tableTokens.getRowCount(); i++) {
                TableCellRenderer cellRenderer = tableTokens.getCellRenderer(i, column);
                Component c = tableTokens.prepareRenderer(cellRenderer, i, column);
                int width = c.getPreferredSize().width + tableTokens.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);
            }

            tableColumn.setPreferredWidth(preferredWidth);
        }
    }

    public void fillVar() {
        tbModel = new DefaultTableModel();
        tbModel.setRowCount(0);
        tbModel.addColumn("Variable");
        tbModel.addColumn("Tipo");
        tbModel.addColumn("Valor");
        tbModel.addColumn("Estado");
        tbModel.addColumn("Fila");

        for (Variable var : semantic.getVariables()) {
            tbModel.addRow(new Object[] { var.getName(), var.getType(), var.getValue(), var.getState(), var.getRow() });
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableVariables.setDefaultRenderer(Object.class, centerRenderer);

        tableVariables.setModel(tbModel);

        TableColumnModel columnModel = tableVariables.getColumnModel();
        for (int column = 0; column < tableVariables.getColumnCount(); column++) {
            TableColumn tableColumn = columnModel.getColumn(column);
            int preferredWidth = 0;

            for (int i = 0; i < tableVariables.getRowCount(); i++) {
                TableCellRenderer cellRenderer = tableVariables.getCellRenderer(i, column);
                Component c = tableVariables.prepareRenderer(cellRenderer, i, column);
                int width = c.getPreferredSize().width + tableVariables.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);
            }

            tableColumn.setPreferredWidth(preferredWidth);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
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
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.CustomComponents.Button btnAnalize;
    private GUI.CustomComponents.Button btnBasico;
    private GUI.CustomComponents.Button btnClear;
    private GUI.CustomComponents.Button btnCompleto;
    private GUI.CustomComponents.Button btnFOR;
    private GUI.CustomComponents.Button btnIF;
    private GUI.CustomComponents.Button btnOpenFile;
    private GUI.CustomComponents.Button btnSWITCH;
    private GUI.CustomComponents.Button btnWHILE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private GUI.CustomComponents.PanelRound pnlCodeArea;
    private GUI.CustomComponents.PanelRound pnlLexer;
    private javax.swing.JPanel pnlMain;
    private GUI.CustomComponents.PanelRound pnlNavBar;
    private GUI.CustomComponents.PanelRound pnlParser;
    private GUI.CustomComponents.PanelRound pnlSemantic;
    private javax.swing.JScrollPane scrollCode;
    private javax.swing.JScrollPane scrollCode1;
    private javax.swing.JScrollPane scrollTokens;
    private javax.swing.JScrollPane scrollVariables;
    private javax.swing.JTable tableTokens;
    private javax.swing.JTable tableVariables;
    private javax.swing.JTextArea txtCode;
    private javax.swing.JTextArea txtConsole;
    // End of variables declaration//GEN-END:variables
}
