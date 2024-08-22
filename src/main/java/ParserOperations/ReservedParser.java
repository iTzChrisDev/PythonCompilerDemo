package ParserOperations;

import java.util.ArrayList;

import javax.swing.JTextArea;

import Tokens.Token;
import Tokens.TokenType;

public class ReservedParser {
    private Utilities tool;
    private ExpressionParser expParser;

    public ReservedParser() {
        tool = new Utilities();
        expParser = new ExpressionParser();
    }

    public void parseAssignment(ArrayList<Token> tokenList, int currentRow, JTextArea console) {
        
    }

    public void parseClassDeclaration(ArrayList<Token> tokenList, int currentRow, JTextArea console) {
        if (tool.verifyToken(TokenType.CLASS, tokenList)) {
            tool.incrementIndex();
            if (tool.verifyToken(TokenType.IDENTIFICADOR_CLASE, tokenList)) {
                tool.incrementIndex();
                if (tool.verifyToken(TokenType.DOS_PUNTOS, tokenList)) {
                    // Fin del arbol sintáctico
                } else {
                    tool.showError("Se esperaba ':'", currentRow, console);
                }
            } else {
                tool.showError("Se esperaba identificador de clase", currentRow, console);
            }
        }
    }
}
