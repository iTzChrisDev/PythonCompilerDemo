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
    private boolean condition;

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
                    condition = true;
                    break;
                case WHILE:
                case FOR:
                    checkIndentation(token);
                    indentStack.push(expectedIndent);
                    expectedIndent += 4;
                    condition = true;
                    break;
                case IF:
                    ifColumn = token.getColumn();
                    checkIndentation(token);
                    indentStack.push(expectedIndent);
                    expectedIndent += 4;
                    condition = true;
                    break;
                case ELSE:
                case ELIF:
                    checkElseOrElif(token, ifColumn);
                    condition = true;
                    break;
                case MATCH:
                case CASE:
                    if (searchMatch()) {
                        checkIndentation(token);
                        indentStack.push(expectedIndent);
                        expectedIndent += 4;
                        condition = true;
                    } else {
                        tool.getConsole().setText(tool.getConsole().getText() +
                                "Declaracion de 'CASE' no valida en la linea " + token.getRow()
                                + ". Falta sentencia 'MATCH'\n");
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

    private void checkIndentation(Token token) {
        if (!indentStack.isEmpty() && token.getColumn() < expectedIndent) {
            // Realizar dedentación
            expectedIndent = indentStack.pop();
        }

        if (token.getColumn() != expectedIndent) {
            showIndentError(token);
        }

        if (condition) {
            for (int i = 0; i < listIndent.size(); i++) {
                if (token == listIndent.get(i)) {
                    Token nextToken = null;

                    if (i < listIndent.size() - 1) {
                        nextToken = listIndent.get(i + 1);
                    }

                    if (token.getToken() == TokenType.CLASS || token.getToken() == TokenType.WHILE
                            || token.getToken() == TokenType.FOR || token.getToken() == TokenType.IF
                            || token.getToken() == TokenType.ELSE || token.getToken() == TokenType.ELIF
                            || token.getToken() == TokenType.MATCH || token.getToken() == TokenType.CASE) {
                        if (nextToken != null && nextToken.getColumn() == token.getColumn()) {
                            showIndentError(nextToken);
                        } else {
                            condition = false;
                            break;
                        }
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

    private void checkElseOrElif(Token token, int ifColumn) {
        if (token.getColumn() != ifColumn && ifColumn == 0) {
            tool.getConsole().setText(tool.getConsole().getText() +
                    "Sintaxis invalida, no se encontró 'IF' anterior a la linea " + token.getRow() + "\n");
        } else if (token.getColumn() != ifColumn) {
            showIndentError(token);
        }
    }

    private void showIndentError(Token token) {
        // Mostrar un error de indentación en la consola
        tool.getConsole().setText(tool.getConsole().getText() +
                "Indentación no válida en la línea " + token.getRow() + "\n");
    }
}
