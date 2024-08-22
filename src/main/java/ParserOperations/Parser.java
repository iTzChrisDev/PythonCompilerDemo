package ParserOperations;

import Tokens.Token;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Parser {

    private ArrayList<Token> tokenList;
    private JTextArea console;
    private int currentRow;
    private Utilities tool;
    private ConditionalParser conditionalParser;
    private LoopParser loopParser;
    private ReservedParser resParser;

    public Parser(ArrayList<Token> tokenList, JTextArea console) {
        this.tokenList = tokenList;
        this.console = console;
        tool = new Utilities();
        conditionalParser = new ConditionalParser();
        loopParser = new LoopParser();
        resParser = new ReservedParser();
    }

    public void parseCode() {
        tool.setIndex(0);
        console.setText("");
        while (tool.getIndex() < tokenList.size()) {
            Token tkn = tokenList.get(tool.getIndex());
            currentRow = tkn.getRow();
            switch (tkn.getToken()) {
                case IDENTIFICADOR:
                    resParser.parseAssignment(tokenList, currentRow, console);
                    break;
                case CLASS:
                    resParser.parseClassDeclaration(tokenList, currentRow, console);
                    break;
                case IF:
                    conditionalParser.parseIf(tokenList, currentRow, console);
                    break;
                case WHILE:
                    loopParser.parseWhile(tokenList, currentRow, console);
                    break;
                case FOR:
                    loopParser.parseFor(tokenList, currentRow, console);
                    break;
            }
            tool.incrementIndex();
        }
    }
}
