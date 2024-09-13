package ParserOperations;

import Tokens.Token;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Parser {

    private Utilities tool;
    private CompoundStatements comp;
    private SimpleStatements simp;
    private boolean printFlag;

    public Parser(ArrayList<Token> tokenList, JTextArea console) {
        tool = new Utilities();
        comp = new CompoundStatements();
        simp = new SimpleStatements();
        tool.setIndex(0);
        tool.setCurrentIndent(1);
        tool.setTokenList(tokenList);
        tool.setConsole(console);
        tool.clearConsole();
    }

    public void parseCode() {
        System.out.println("INDENTACION -> " + tool.getCurrentIndent());
        while (comp.isCompoundStatement()) {
            if (simp.isSimpleStatement()) {
            } else if (tool.getCurrentIndent() != tool.getCurrentToken().getColumn()) {
                tool.getConsole().setText(tool.getConsole().getText() + "Indentacion no valida en la linea "
                        + tool.getCurrentToken().getRow() + "\n");
            }
            tool.indent();
            parseCode();
        }
        tool.dedent();
        // if (tool.getCurrentIndent() == tool.getCurrentToken().getColumn()) {
        // tool.indent();
        // if (comp.isCompoundStatement()) {
        // if (simp.isSimpleStatement()) {
        // parseCode();
        // } else {
        // tool.dedent();
        // }
        // } // Fin de la ejecucion
        // } else if (simp.isSimpleStatement()) {
        // // No hay cambio en la indentacion.
        // } else {
        // tool.getConsole().setText(tool.getConsole().getText() + "Indentacion no
        // valida en la linea "
        // + tool.getCurrentToken().getRow() + "\n");
        // }
    }

    public void statement() {
    }
}
