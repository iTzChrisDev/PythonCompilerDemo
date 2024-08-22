package ParserOperations;

import Tokens.TokenType;

public class ExpressionParser {

    private Utilities tool;

    public ExpressionParser() {
        tool = new Utilities();
    }

    public boolean isSimpleExpression() {
        boolean result = false;

        // Manejo del operador NOT
        if (tool.verifyToken(TokenType.NOT)) {
            tool.incrementIndex();
        }

        if (tool.verifyToken(TokenType.PARENTESIS_APERTURA)) {
            tool.incrementIndex();

            result = isCompoundExpression();
            if (result) {
                // Solo avanza el índice si el cierre de paréntesis es válido
                if (tool.verifyToken(TokenType.PARENTESIS_CIERRE)) {
                    tool.incrementIndex();
                    result = true;
                } else {
                    tool.showError("Se esperaba ')'");
                    result = false;
                }
            } else {
                tool.showError("Expresión dentro de paréntesis no válida");
                result = false;
            }
        } else if (tool.isValueToken(tool.getCurrentToken())) {
            tool.incrementIndex();
            if (tool.isRelationalOperator(tool.getCurrentToken())) {
                tool.incrementIndex();
                if (tool.isValueToken(tool.getCurrentToken())) {
                    tool.incrementIndex();
                    result = true;
                } else {
                    tool.showError("Se esperaba valor");
                    result = false;
                }
            } else {
                tool.showError("Se esperaba operador relacional");
                result = false;
            }
        } else {
            tool.showError("Se esperaba expresión condicional o valor");
        }
        return result;
    }

    public boolean isCompoundExpression() {
        boolean result = isSimpleExpression();
        while (result && (tool.verifyToken(TokenType.AND) || tool.verifyToken(TokenType.OR))) {
            tool.incrementIndex();
            result = isSimpleExpression();
            if (!result) {
                tool.showError("Expresión después de operador lógico no válida");
                return false;
            }
        }
        return result;
    }
}
