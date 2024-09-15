package ParserOperations;

import java.util.ArrayList;
import java.util.Stack;

import Tokens.Token;

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
        for (int i = 0; i < listIndent.size(); i++) {
            Token token = listIndent.get(i);
            System.out.println(
                    "Row: " + token.getRow() + " | Col: " + token.getColumn() + " | Exp.Indent: " + expectedIndent
                            + " | " + token.getLexeme());
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
                    checkElseOrElif(token, ifColumn);
                    break;
                case MATCH:
                case CASE:
                    checkIndentation(token);
                    indentStack.push(expectedIndent);
                    expectedIndent += 4;
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

    private void checkIndentation(Token token) {
        if (!indentStack.isEmpty() && token.getColumn() < expectedIndent) {
            // Realizar dedentación
            expectedIndent = indentStack.pop();
        }

        if (token.getColumn() != expectedIndent) {
            showIndentError(token);
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
