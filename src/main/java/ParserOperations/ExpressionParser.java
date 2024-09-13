package ParserOperations;

import Tokens.TokenType;

public class ExpressionParser {

    private Utilities tool;

    public ExpressionParser() {
        tool = new Utilities();
    }

    protected boolean isValidExpression() {
        // Iniciar el análisis de una expresion valida
        boolean val = false;
        if (!isExpression()) {
            tool.showError("Se esperaba una expresión válida");
        } else if (tool.getIndex() < tool.getTokenList().size()
                && tool.verifyToken(TokenType.PARENTESIS_CIERRE)) {
            tool.showError("Se esperaba '('");
        } else if (tool.getIndex() < tool.getTokenList().size()
                && tool.verifyToken(TokenType.PARENTESIS_APERTURA)) {
            tool.showError("Se esperaba ')'");
        } else {
            val = true;
        }
        return val;
    }

    private boolean isExpression() {
        // Verificar si hay una expresión válida
        if (tool.getIndex() >= tool.getTokenList().size()) {
            // Evitar el desbordamiento del index
            return false;
        }

        return isLogicalExpression();
    }

    private boolean isLogicalExpression() {
        // Analizar las expresiones con AND y OR.
        boolean result = isComparison();
        if (!result) {
            return false;
        }

        while (tool.getIndex() < tool.getTokenList().size()
                && (tool.verifyToken(TokenType.AND) || tool.verifyToken(TokenType.OR))) {
            tool.incrementIndex();
            result = isComparison();
            if (!result) {
                tool.showError("Se esperaba una expresión después del operador lógico");
                return false;
            }
        }

        return result;
    }

    private boolean isComparison() {
        // Analizar comparaciones (igual, mayor que, menor que, etc.)
        boolean result = isSimpleExpression();
        if (!result) {
            return false;
        }

        while (tool.getIndex() < tool.getTokenList().size() && tool.isRelationalOperator(tool.getCurrentToken())) {
            tool.incrementIndex();
            result = isSimpleExpression();
            if (!result) {
                tool.showError("Se esperaba un valor después del operador relacional");
                return false;
            }
        }

        return result;
    }

    private boolean isSimpleExpression() {
        // Analizar expresiones simples (valores, paréntesis)
        boolean result = false;

        if (tool.getIndex() >= tool.getTokenList().size()) {
            return false;
        }

        // Manejo del operador NOT
        if (tool.verifyToken(TokenType.NOT)) {
            tool.incrementIndex();
            // NOT debe ir seguido de una expresión simple o una expresión entre parentesis
            result = isSimpleExpression();
            if (!result) {
                tool.showError("Se esperaba una expresión después de 'not'");
                return false;
            }
        } else if (tool.verifyToken(TokenType.PARENTESIS_APERTURA)) {
            tool.incrementIndex();
            result = isExpression();
            if (!result) {
                tool.showError("Se esperaba una expresión dentro de los paréntesis");
                return false;
            }
            if (tool.getIndex() < tool.getTokenList().size()
                    && tool.verifyToken(TokenType.PARENTESIS_CIERRE)) {
                tool.incrementIndex();
                result = true;
            } else {
                tool.showError("Se esperaba ')'");
                return false;
            }
        } else if (tool.isValueToken(tool.getCurrentToken())) {
            tool.incrementIndex();
            result = true;
        } else {
            tool.showError("Se esperaba un valor");
        }

        return result;
    }
}
