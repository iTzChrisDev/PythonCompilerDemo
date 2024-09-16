package ParserOperations;

import Tokens.Token;
import Tokens.TokenType;

import java.util.ArrayList;
import javax.swing.JTextArea;

public class Parser {

    private Utilities tool;
    private CompoundStatements cs;
    private SimpleStatements ss;
    private IndentationParser ip;
    private ArrayList<Token> listIndent;

    public Parser(ArrayList<Token> tokenList, JTextArea console) {
        listIndent = new ArrayList<>();
        tool = new Utilities();
        cs = new CompoundStatements();
        ss = new SimpleStatements();
        ip = new IndentationParser(listIndent);
        tool.setIndex(0);
        tool.setCurrentIndent(1);
        tool.setLine(1);
        tool.setTokenList(tokenList);
        tool.setConsole(console);
        tool.clearConsole();
    }

    public void parseCode() {
        System.out.println("\nInicio Ejecución");
        statements();
        ip.checkIndent();
        System.out.println("Fin Ejecución");
    }

    public void statements() {
        if (tool.getIndex() != tool.getTokenList().size() - 1) {
            listIndent.add(tool.getCurrentToken());
            if (cs.isCompoundStatement()) {
                // CompoundStatement correcto
                statements();
            } else if (ss.isSimpleStatement()) {
                // SimpleStatement correcto
                statements();
            } else if (tool.verifyToken(TokenType.DESCONOCIDO)) {
                tool.showError("No se reconoce '" + tool.getCurrentToken().getLexeme() + "'");
            }
        }
        // else {
        // System.out.println("Fin de Ejecución");
        // }
    }
}
