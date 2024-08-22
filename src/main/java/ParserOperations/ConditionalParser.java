package ParserOperations;

import java.util.ArrayList;
import javax.swing.JTextArea;
import Tokens.Token;
import Tokens.TokenType;

public class ConditionalParser {
    private Utilities tool;
    private ExpressionParser expParser;

    public ConditionalParser() {
        tool = new Utilities();
        expParser = new ExpressionParser();
    }

    public void parseIf(ArrayList<Token> tokenList, int currentRow, JTextArea console) {
        if (tool.verifyToken(TokenType.IF, tokenList)) {
            tool.incrementIndex();
            if (expParser.isCompoundExpression(tokenList, currentRow, console)) {
                if (tool.verifyToken(TokenType.DOS_PUNTOS, tokenList)) {
                    System.out.println("IF CORRECTO");
                } else if (tool.verifyToken(TokenType.PARENTESIS_CIERRE, tokenList)) {
                    tool.showError("No se encontró '('", currentRow, console);
                } else {
                    tool.showError("Se esperaban ':'", currentRow, console);
                }
            }
        }
    }
}