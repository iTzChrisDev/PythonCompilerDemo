package ParserOperations;

import Tokens.Token;
import Tokens.TokenType;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Utilities {

    private static int index, currentRow, currentIndent, line;
    private static ArrayList<Token> tokenList;
    private static JTextArea console;

    protected boolean verifyToken(TokenType tkn) {
        return index < tokenList.size() && tokenList.get(index).getToken() == tkn;
    }

    protected void showError(String errorMessage) {
        console.setText(console.getText() + errorMessage + " en la linea " + currentRow + "\n");
    }

    protected boolean isAssignment(Token tkn) {
        return tkn.getToken().equals(TokenType.SUMA_ASIGNACION)
                || tkn.getToken().equals(TokenType.RESTA_ASIGNACION)
                || tkn.getToken().equals(TokenType.DIVISION_ASIGNACION)
                || tkn.getToken().equals(TokenType.MODULO_ASIGNACION)
                || tkn.getToken().equals(TokenType.MULTIPLICACION_ASIGNACION);
    }

    public boolean isOperator(Token tkn) {
        return tkn.getToken().equals(TokenType.SUMA)
                || tkn.getToken().equals(TokenType.RESTA)
                || tkn.getToken().equals(TokenType.DIVISION)
                || tkn.getToken().equals(TokenType.MODULO)
                || tkn.getToken().equals(TokenType.MULTIPLICACION);
    }

    protected boolean isValueFactor(Token tkn) {
        // Verificar si el token es un valor válido
        return tkn.getToken().equals(TokenType.IDENTIFICADOR)
                || tkn.getToken().equals(TokenType.ENTERO)
                || tkn.getToken().equals(TokenType.DECIMAL);
    }

    protected boolean isValueToken(Token tkn) {
        // Verificar si el token es un valor válido
        return tkn.getToken().equals(TokenType.IDENTIFICADOR)
                || tkn.getToken().equals(TokenType.ENTERO)
                || tkn.getToken().equals(TokenType.DECIMAL)
                || tkn.getToken().equals(TokenType.CADENA)
                || tkn.getToken().equals(TokenType.BOOLEAN);
    }

    protected boolean isRelationalOperator(Token tkn) {
        // Verificar si el token es un operador relacional
        return tkn.getToken().equals(TokenType.IGUALDAD)
                || tkn.getToken().equals(TokenType.MAYOR_QUE)
                || tkn.getToken().equals(TokenType.MENOR_QUE)
                || tkn.getToken().equals(TokenType.MAYOR_IGUAL_QUE)
                || tkn.getToken().equals(TokenType.MENOR_IGUAL_QUE)
                || tkn.getToken().equals(TokenType.DIFERENCIA);
    }

    protected static int getCurrentIndent() {
        return currentIndent;
    }

    protected static void setCurrentIndent(int indent) {
        currentIndent = indent;
    }

    protected static void indent() {
        currentIndent += 4;
    }

    protected static void dedent() {
        currentIndent -= 4;
    }

    protected static void setIndex(int newIndex) {
        Utilities.index = newIndex;
    }

    protected static int getIndex() {
        return index;
    }

    public static void setTokenList(ArrayList<Token> newTokenList) {
        Utilities.tokenList = newTokenList;
    }

    public static ArrayList<Token> getTokenList() {
        return tokenList;
    }

    protected static Token getCurrentToken() {
        return tokenList.get(index);
    }

    public static JTextArea getConsole() {
        return console;
    }

    public static void setConsole(JTextArea console) {
        Utilities.console = console;
    }

    public static void clearConsole() {
        console.setText("");
    }

    protected static void incrementIndex() {
        if (index < tokenList.size() - 1) {
            index++;
        }
    }

    protected static void decrementIndex() {
        index--;
    }

    public static void setCurrentRow(int currentRow) {
        Utilities.currentRow = currentRow;
    }

    public static int getCurrentRow() {
        return currentRow;
    }

    public static void incrementLine() {
        line++;
    }

    public static void setLine(int line) {
        Utilities.line = line;
    }

    public static int getLine() {
        return line;
    }
}
