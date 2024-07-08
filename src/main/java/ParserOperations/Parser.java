package ParserOperations;

import Tokens.Token;
import Tokens.TokenType;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Parser {

    private ArrayList<Token> tokenList;
    private JTextArea console;
    private int index, currentRow;

    public Parser(ArrayList<Token> tokenList, JTextArea console) {
        this.tokenList = tokenList;
        this.console = console;
    }

    public void parseCode() {
        index = 0;
        console.setText("");
        while (index < tokenList.size()) {
            Token tkn = tokenList.get(index);
            currentRow = tkn.getRow();
            switch (tkn.getToken()) {
                case IDENTIFICADOR:
                    parseAssignment();
                    break;
                case CLASS:
                    parseClassDeclaration();
                    break;
            }
            index++;
        }
    }

    private void parseClassDeclaration() {
        if (verifyToken(TokenType.CLASS)) {
            index++;
            if (verifyToken(TokenType.IDENTIFICADOR_CLASE)) {
                index++;
                if (verifyToken(TokenType.DOS_PUNTOS)) {
                    //Fin del arbol sintáctico
                } else {
                    showError("Se esperaba ':'");
                }
            } else {
                showError("Se esperaba identificador de clase");
            }
        }
    }

    private void parseAssignment() {
        if (verifyToken(TokenType.IDENTIFICADOR)) {
            index++;
            if (isAssignment(tokenList.get(index))) {
                index++;
                if (isValueToken(tokenList.get(index))) {
                    // Fin del arbol sintáctico
                } else {
                    showError("Se esperaba un valor");
                }
            } else {
                showError("Se esperaba '='");
            }
        }
    }

    private boolean verifyToken(TokenType tkn) {
        return index < tokenList.size() && tokenList.get(index).getToken() == tkn;
    }

    private boolean isAssignment(Token tkn) {
        return tkn.getToken().equals(TokenType.ASIGNACION)
                || tkn.getToken().equals(TokenType.SUMA_ASIGNACION)
                || tkn.getToken().equals(TokenType.RESTA_ASIGNACION)
                || tkn.getToken().equals(TokenType.DIVISION_ASIGNACION)
                || tkn.getToken().equals(TokenType.MODULO_ASIGNACION)
                || tkn.getToken().equals(TokenType.MULTIPLICACION_ASIGNACION);
    }

    private boolean isValueToken(Token tkn) {
        // Verificar si el token es un valor válido
        return tkn.getToken().equals(TokenType.IDENTIFICADOR)
                || tkn.getToken().equals(TokenType.ENTERO)
                || tkn.getToken().equals(TokenType.DECIMAL)
                || tkn.getToken().equals(TokenType.CADENA)
                || tkn.getToken().equals(TokenType.BOOLEAN_FALSE)
                || tkn.getToken().equals(TokenType.BOOLEAN_TRUE);
    }

    private boolean isRelationalOperator(Token tkn) {
        // Verificar si el token es un operador relacional
        return tkn.getToken().equals(TokenType.IGUALDAD)
                || tkn.getToken().equals(TokenType.MAYOR_QUE)
                || tkn.getToken().equals(TokenType.MENOR_QUE)
                || tkn.getToken().equals(TokenType.MAYOR_IGUAL_QUE)
                || tkn.getToken().equals(TokenType.MENOR_IGUAL_QUE)
                || tkn.getToken().equals(TokenType.DIFERENCIA);
    }

    private void showError(String errorMessage) {
        console.setText(console.getText() + errorMessage + " en la linea " + currentRow + "\n");
    }
}
