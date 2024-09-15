package ParserOperations;

import Tokens.Token;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Parser {

    private Utilities tool;
    private CompoundStatements cs;
    private SimpleStatements ss;
    private static int currentLine;

    public Parser(ArrayList<Token> tokenList, JTextArea console) {
        tool = new Utilities();
        cs = new CompoundStatements();
        ss = new SimpleStatements();
        currentLine = 0;
        tool.setIndex(0);
        tool.setCurrentIndent(1);
        tool.setLine(1);
        tool.setTokenList(tokenList);
        tool.setConsole(console);
        tool.clearConsole();
    }

    public void parseCode() {
        System.out.println("\nEjecución");
        statements();
    }

    public void statements() {
        // System.out.println("INDENTACION -> " + tool.getCurrentIndent() + " - " +
        if (tool.getTokenList().get(tool.getTokenList().size() - 1).getRow() != tool.getCurrentToken().getRow()) {
            if (cs.isCompoundStatement()) {
                System.out.println("Procesando declaración compuesta...");
                System.out.println(tool.getCurrentToken().getLexeme());
                statements();
                // Aquí debes incluir la lógica que cambia el estado de cs
            } else if (ss.isSimpleStatement()) {
                System.out.println("Procesando declaración simple...");
                System.out.println(tool.getCurrentToken().getLexeme());
                statements();
                // Aquí debes incluir la lógica que cambia el estado de ss
            } else {
                System.out.println("Fin de Ejecución");
            }
        }
    }
}
