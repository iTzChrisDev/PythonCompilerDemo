package ParserOperations;

import Tokens.Token;
import Tokens.TokenType;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Parser {

    private ArrayList<Token> tokenList;
    private JTextArea console;
    private int index, currentRow;
    private Utilities tool;
    private ExpressionParser expParser;

    public Parser(ArrayList<Token> tokenList, JTextArea console) {
        this.tokenList = tokenList;
        this.console = console;
        tool = new Utilities();
        expParser = new ExpressionParser();
    }

    public void parseCode() {
        index = 0;
        console.setText("");
        while (index < tokenList.size()) {
            Token tkn = tokenList.get(index);
            currentRow = tkn.getRow();
            switch (tkn.getToken()) {
                case IDENTIFICADOR:
                    parseAssignment();
                    break;
                case CLASS:
                    parseClassDeclaration();
                    break;
                case IF:
                    expParser.parseIf(tokenList, index, currentRow, console);
                    break;
            }
            index++;
        }
    }

    private void parseClassDeclaration() {
        if (tool.verifyToken(TokenType.CLASS, tokenList, index)) {
            index++;
            if (tool.verifyToken(TokenType.IDENTIFICADOR_CLASE, tokenList, index)) {
                index++;
                if (tool.verifyToken(TokenType.DOS_PUNTOS, tokenList, index)) {
                    //Fin del arbol sintáctico
                } else {
                    tool.showError("Se esperaba ':'", currentRow, console);
                }
            } else {
                tool.showError("Se esperaba identificador de clase", currentRow, console);
            }
        }
    }

    private void parseAssignment() {
        if (tool.verifyToken(TokenType.IDENTIFICADOR, tokenList, index)) {
            index++;
            if (tool.isAssignment(tokenList.get(index))) {
                index++;
                if (tool.isValueToken(tokenList.get(index))) { //CHECAR ERROR DE DESBORDAMIENTO DE INDEX
                    // Fin del arbol sintáctico
                } else {
                    tool.showError("Se esperaba un valor", currentRow, console);
                }
            } else {
                tool.showError("Se esperaba '='", currentRow, console);
            }
        }
    }

}
