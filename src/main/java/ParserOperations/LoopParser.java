package ParserOperations;

import java.util.ArrayList;
import javax.swing.JTextArea;
import Tokens.Token;
import Tokens.TokenType;

public class LoopParser {
    private Utilities tool;
    private ExpressionParser expParser;

    public LoopParser() {
        tool = new Utilities();
        expParser = new ExpressionParser();
    }

    public void parseWhile(ArrayList<Token> tokenList, int currentRow, JTextArea console) {
        if (tool.verifyToken(TokenType.WHILE, tokenList)) {
            tool.incrementIndex();
            if (expParser.isCompoundExpression(tokenList, currentRow, console)) {
                if (tool.verifyToken(TokenType.DOS_PUNTOS, tokenList)) {
                    System.out.println("WHILE CORRECTO");
                } else {
                    tool.showError("Se esperaban ':'", currentRow, console);
                }
            }
        }
    }

    public void parseFor(ArrayList<Token> tokenList, int currentRow, JTextArea console) {
        if (tool.verifyToken(TokenType.FOR, tokenList)) {
            tool.incrementIndex();
            if (tool.verifyToken(TokenType.IDENTIFICADOR, tokenList)) {
                tool.incrementIndex();
                if (tool.verifyToken(TokenType.IN, tokenList)) {
                    tool.incrementIndex();
                    if (tool.verifyToken(TokenType.RANGE, tokenList)) {
                        tool.incrementIndex();
                        if (tool.verifyToken(TokenType.PARENTESIS_APERTURA, tokenList)) {
                            tool.incrementIndex();
                            if (tool.verifyToken(TokenType.ENTERO, tokenList)) {
                                tool.incrementIndex();
                                if (tool.verifyToken(TokenType.PARENTESIS_CIERRE, tokenList)) {
                                    tool.incrementIndex();
                                    if (tool.verifyToken(TokenType.DOS_PUNTOS, tokenList)) {
                                        // FIN DEL ARBOL SINTACTICO
                                    } else {
                                        tool.showError("Se esperaban ':'", currentRow, console);
                                    }
                                } else {
                                    tool.showError("Se esperaban ')'", currentRow, console);
                                }
                            } else {
                                tool.showError("Se esperaban valor iterable", currentRow, console);
                            }
                        } else {
                            tool.showError("Se esperaban '('", currentRow, console);
                        }
                    } else if (tool.verifyToken(TokenType.CADENA, tokenList)) {
                        tool.incrementIndex();
                        if (tool.verifyToken(TokenType.DOS_PUNTOS, tokenList)) {
                            // FIN DEL ARBOL SINTACTICO
                        }
                    } else {
                        tool.showError("Se esperaban expresión", currentRow, console);
                    }
                } else {
                    tool.showError("Se esperaban palabra reservada", currentRow, console);
                }
            } else {
                tool.showError("Se esperaban iterador", currentRow, console);
            }
        }
    }
}
