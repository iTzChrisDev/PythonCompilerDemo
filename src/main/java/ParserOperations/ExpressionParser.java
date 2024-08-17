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

    public boolean isSimpleExpression(ArrayList<Token> tokenList, int currentRow, JTextArea console) {
        boolean result = false;

        // Manejo del operador NOT
        if (tool.verifyToken(TokenType.NOT, tokenList)) {
            tool.incrementIndex();
        }

        if (tool.verifyToken(TokenType.PARENTESIS_APERTURA, tokenList)) {
            tool.incrementIndex();

            result = isCompoundExpression(tokenList, currentRow, console);
            if (result) {
                tool.setIndex(skipExpression(tokenList));// Avanzar el índice hasta el cierre de paréntesis
                if (tool.verifyToken(TokenType.PARENTESIS_CIERRE, tokenList)) {
                    tool.incrementIndex();
                    result = true;
                } else {
                    tool.showError("Se esperaba ')'", currentRow, console);
                    result = false;
                }
            } else {
                tool.showError("Expresión dentro de paréntesis no válida", currentRow, console);
                result = false;
            }
        } else if (tool.isValueToken(tokenList.get(tool.getIndex()))) {
            tool.incrementIndex();
            if (tool.isRelationalOperator(tokenList.get(tool.getIndex()))) {
                tool.incrementIndex();

                if (tool.isValueToken(tokenList.get(tool.getIndex()))) {
                    tool.incrementIndex();
                    result = true;
                } else {
                    tool.showError("Se esperaba valor", currentRow, console);
                    result = false;
                }
            } else {
                tool.showError("Se esperaba operador relacional", currentRow, console);
                result = false;
            }
        } else {
            tool.showError("Se esperaba expresión condicional o valor", currentRow, console);
        }
        return result;
    }

    public boolean isCompoundExpression(ArrayList<Token> tokenList, int currentRow, JTextArea console) {
        boolean result = isSimpleExpression(tokenList, currentRow, console);
        while (result && (tool.verifyToken(TokenType.AND, tokenList)
                || tool.verifyToken(TokenType.OR, tokenList))) {
            tool.incrementIndex();
            result = isSimpleExpression(tokenList, currentRow, console);
            if (!result) {
                tool.showError("Expresión después de operador lógico no válida", currentRow, console);
                result = false;
            }
        }
        return result;
    }

    private int skipExpression(ArrayList<Token> tokenList) {
        while (tool.getIndex() < tokenList.size() && !tool.verifyToken(TokenType.PARENTESIS_CIERRE, tokenList)) {
            tool.incrementIndex();
        }
        return tool.getIndex();
    }
}
