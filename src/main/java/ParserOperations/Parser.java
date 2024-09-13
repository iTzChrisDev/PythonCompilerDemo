package ParserOperations;

import Tokens.Token;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Parser {

    private Utilities tool;
    private CompoundStatements comp;
    private SimpleStatements simp;
    public static boolean flag;

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
        System.out.println("\nEjecución");
        statements();
    }

    public void statements() {
        System.out.println("INDENTACION -> " + tool.getCurrentIndent() + " - " + tool.getCurrentToken().getLexeme());

        if (comp.isCompoundStatement() || simp.isSimpleStatement()) {
            if (flag) {
                if (tool.getCurrentToken().getColumn() <= tool.getCurrentIndent()) {
                    flag = false;
                    statements();
                }
            } else {
                if (tool.getCurrentToken().getColumn() != tool.getCurrentIndent()) {
                    tool.getConsole().setText(tool.getConsole().getText() + "Indentacion no valida en la linea "
                            + tool.getCurrentToken().getRow() + "\n");
                } else {
                    flag = true;
                    statements();
                }
            }
        }
    }
}
