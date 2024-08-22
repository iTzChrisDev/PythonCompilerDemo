package ParserOperations;

import Tokens.Token;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Parser {

    private Utilities tool;
    private CompoundStatements comp;

    public Parser(ArrayList<Token> tokenList, JTextArea console) {
        tool = new Utilities();
        comp = new CompoundStatements();
        tool.setIndex(0);
        tool.setTokenList(tokenList);
        tool.setConsole(console);
        tool.clearConsole();
    }

    public void parseCode() {
        while (tool.getIndex() < tool.getTokenList().size()) {
            Token tkn = tool.getTokenList().get(tool.getIndex());
            switch (tkn.getToken()) {
                case IF:
                    comp.isIfStatement();
                    break;
            }
            tool.incrementIndex();
        }
    }

}
