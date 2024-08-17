package ParserOperations;

import Tokens.Token;
import Tokens.TokenType;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class ExpressionParser {

    private Utilities tool;

    public ExpressionParser() {
        tool = new Utilities();
    }

    public void parseIf(ArrayList<Token> tokenList, int index, int currentRow, JTextArea console) {
        if (tool.verifyToken(TokenType.IF, tokenList, index)) {
            index++;
            if (isSimpleExpression(tokenList, index, currentRow, console)) {
                System.out.println("IF SIMPLE");
            }
        }
    }

    private boolean isSimpleExpression(ArrayList<Token> tokenList, int index, int currentRow, JTextArea console) {
        boolean result = false;
        if (tool.verifyToken(TokenType.PARENTESIS_APERTURA, tokenList, index)) {
            index++;
            if (tool.isValueToken(tokenList.get(index))) {
                index++;
                if (tool.isRelationalOperator(tokenList.get(index))) {
                    index++;
                    if (tool.isValueToken(tokenList.get(index))) {
                        index++;
                        if (tool.verifyToken(TokenType.PARENTESIS_CIERRE, tokenList, index)) {
                            // FIN
                            result = true;
                        } else {
                            tool.showError("Se esperaba ')'", currentRow, console);
                        }
                    } else {
                        tool.showError("Se esperaba valor", currentRow, console);
                    }
                } else {
                    tool.showError("Se esperaba operador relacional", currentRow, console);
                }
            } else {
                tool.showError("Se esperaba valor", currentRow, console);
            }
        } else if (tool.isValueToken(tokenList.get(index))) {
            index++;
            if (tool.isRelationalOperator(tokenList.get(index))) {
                index++;
                if (tool.isValueToken(tokenList.get(index))) {
                    // FIN
                    result = true;
                } else {
                    tool.showError("Se esperaba valor", currentRow, console);
                }
            } else {
                tool.showError("Se esperaba un operador relacional", currentRow, console);
            }
        } else {
            tool.showError("Se esperaba expresión condicional", currentRow, console);
        }
        return result;
    }
}
