package ParserOperations;

import Tokens.Token;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Parser {

    private ArrayList<Token> tokenList;
    private JTextArea console;

    public Parser(ArrayList<Token> tokenList, JTextArea console) {
        this.tokenList = tokenList;
        this.console = console;
    }

}
