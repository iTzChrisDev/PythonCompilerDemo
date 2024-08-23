package ParserOperations;

import Tokens.Token;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Parser {

    private Utilities tool;
    private CompoundStatements comp;
    private SimpleStatements simp;

    public Parser(ArrayList<Token> tokenList, JTextArea console) {
        tool = new Utilities();
        comp = new CompoundStatements();
        simp = new SimpleStatements();
        tool.setIndex(0);
        tool.setTokenList(tokenList);
        tool.setConsole(console);
        tool.clearConsole();
    }

    public void parseCode() {
        if (simp.isSimpleStatement()) {
            parseCode();
        } else if (comp.isCompoundStatement()) {
            parseCode();
        } else {
            System.out.println("FIN DE EJECUCIÓN\n");
        }
    }

}
