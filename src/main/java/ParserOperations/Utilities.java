package ParserOperations;

import Tokens.Token;
import Tokens.TokenType;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Utilities {

    private static int index = 0;

    public static void setIndex(int newIndex) {
        index = newIndex;
    }
    
    public static int getIndex() {
        return index;
    }

    public static void incrementIndex() {
        index++;
    }

    protected boolean verifyToken(TokenType tkn, ArrayList<Token> tokenList) {
        return index < tokenList.size() && tokenList.get(index).getToken() == tkn;
    }

    protected void showError(String errorMessage, int currentRow, JTextArea console) {
        console.setText(console.getText() + errorMessage + " en la linea " + currentRow + "\n");
    }

    protected boolean isAssignment(Token tkn) {
        return tkn.getToken().equals(TokenType.ASIGNACION)
                || tkn.getToken().equals(TokenType.SUMA_ASIGNACION)
                || tkn.getToken().equals(TokenType.RESTA_ASIGNACION)
                || tkn.getToken().equals(TokenType.DIVISION_ASIGNACION)
                || tkn.getToken().equals(TokenType.MODULO_ASIGNACION)
                || tkn.getToken().equals(TokenType.MULTIPLICACION_ASIGNACION);
    }

    protected boolean isValueToken(Token tkn) {
        // Verificar si el token es un valor válido
        return tkn.getToken().equals(TokenType.IDENTIFICADOR)
                || tkn.getToken().equals(TokenType.ENTERO)
                || tkn.getToken().equals(TokenType.DECIMAL)
                || tkn.getToken().equals(TokenType.CADENA)
                || tkn.getToken().equals(TokenType.BOOLEAN_FALSE)
                || tkn.getToken().equals(TokenType.BOOLEAN_TRUE);
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
}
