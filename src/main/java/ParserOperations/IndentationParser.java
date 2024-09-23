package ParserOperations;

import java.util.ArrayList;
import java.util.Stack;

import Tokens.Token;
import Tokens.TokenType;

public class IndentationParser {
    private Stack<Integer> indentStack = new Stack<>();
    private static ArrayList<Token> listIndent;
    private int classCount = 0, expectedIndent = 1;
    private Utilities tool;

    public IndentationParser(ArrayList<Token> list) {
        tool = new Utilities();
        IndentationParser.listIndent = list;
    }

    protected void checkIndent() {
        int ifColumn = 0;
        for (Token token : listIndent) {
            switch (token.getToken()) {
                case CLASS:
                    checkClassDeclaration(token, classCount);
                    classCount++;
                    break;
                case WHILE:
                case FOR:
                    checkIndentation(token);
                    indentStack.push(expectedIndent);
                    expectedIndent += 4;
                    break;
                case IF:
                    ifColumn = token.getColumn();
                    checkIndentation(token);
                    indentStack.push(expectedIndent);
                    expectedIndent += 4;
                    break;
                case ELSE:
                case ELIF:
                    // Verificar como otros compound
                    if (token.getColumn() == ifColumn) {
                        checkIndentation(token);
                        indentStack.push(expectedIndent);
                        expectedIndent += 4;
                    } else if (ifColumn == 0) {
                        tool.getConsole().setText(tool.getConsole().getText() +
                                "Sintaxis invalida, no se encontró 'IF' anterior a la linea " + token.getRow() + "\n");
                    } else {
                        showIndentError(token);
                    }

                    break;
                case MATCH:
                case CASE:
                    if (searchMatch()) {
                        checkIndentation(token);
                        indentStack.push(expectedIndent);
                        expectedIndent += 4;
                    } else {
                        tool.getConsole().setText(tool.getConsole().getText() +
                                "Instruccion 'MATCH/CASE' no valida en la linea " + token.getRow() + "\n");
                    }
                    break;
                case IDENTIFICADOR:
                    checkIndentation(token);
                    break;
                case PRINT:
                    checkIndentation(token);
                    break;
            }
        }
    }

    private boolean searchMatch() {
        boolean flag = false, result = false;
        Token aux = new Token("", TokenType.DESCONOCIDO, 0, 0);
        for (Token token : listIndent) {
            if (token.getToken() == TokenType.MATCH) {
                aux = token;
                flag = true;
            } else if (token.getToken() == TokenType.CASE) {
                if (flag) {
                    if (token.getRow() > aux.getRow()) {
                        result = true;
                        break;
                    } else {
                        result = false;
                    }
                }
            }
        }
        flag = false;
        return result;
    }

    int caseCol = 0;

    private void checkIndentation(Token token) {
        if (!indentStack.isEmpty() && token.getColumn() < expectedIndent) {
            // Realizar dedentación
            expectedIndent = indentStack.pop();
        }

        if (token.getColumn() != expectedIndent) {
            showIndentError(token);
        }

        for (int i = 0; i < listIndent.size(); i++) {
            if (token == listIndent.get(i)) {
                Token nextToken = null;

                if (i < listIndent.size() - 1) {
                    nextToken = listIndent.get(i + 1);
                }

                if (token.getToken() == TokenType.CASE) {
                    if (token.getToken() == TokenType.CASE && token.getColumn() == caseCol) {
                        showIndentError(token);
                    }
                }

                if (token.getToken() == TokenType.MATCH) {
                    caseCol = token.getColumn();
                }

                if (token.getToken() == TokenType.CLASS || token.getToken() == TokenType.WHILE
                        || token.getToken() == TokenType.FOR || token.getToken() == TokenType.IF
                        || token.getToken() == TokenType.ELSE || token.getToken() == TokenType.ELIF
                        || token.getToken() == TokenType.CASE) {
                    if (nextToken != null && nextToken.getColumn() == token.getColumn()) {
                        showIndentError(nextToken);
                    } else {
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < listIndent.size(); i++) {
            if (token == listIndent.get(i)) {
                Token lastToken = tool.getTokenList().get(tool.getTokenList().size() - 1);

                if (token.getToken() == TokenType.CLASS || token.getToken() == TokenType.WHILE
                        || token.getToken() == TokenType.FOR || token.getToken() == TokenType.IF
                        || token.getToken() == TokenType.ELSE || token.getToken() == TokenType.ELIF
                        || token.getToken() == TokenType.CASE) {
                    if (token.getRow() == lastToken.getRow()) {
                        tool.getConsole().setText(tool.getConsole().getText()
                                + "Se esperaba bloque de codigo en la linea " + token.getRow() + "\n");
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private void checkClassDeclaration(Token token, int classCount) {
        if (classCount > 0) {
            tool.getConsole().setText(tool.getConsole().getText() +
                    "Declaracion de clase no valida en la linea " + token.getRow()
                    + ". Ya se ha declarado una clase\n");
        } else if (token.getColumn() != 1) {
            tool.getConsole().setText(tool.getConsole().getText() +
                    "Declaracion de clase incorrecta en la linea " + token.getRow() + "\n");
        } else {
            expectedIndent += 4;
        }
    }

    private void showIndentError(Token token) {
        // Mostrar un error de indentación en la consola
        tool.getConsole().setText(tool.getConsole().getText() +
                "Indentación no válida en la línea " + token.getRow() + "\n");
    }
}
