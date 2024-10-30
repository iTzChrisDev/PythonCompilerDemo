package ParserOperations;

import java.util.ArrayList;

import Tokens.Token;
import Tokens.TokenType;

public class Assignments {
    private Utilities tool;
    private ArrayList<Integer> openParent, closeParent;

    public Assignments() {
        tool = new Utilities();
        openParent = new ArrayList<>();
        closeParent = new ArrayList<>();
    }

    public boolean isAssignment() {
        return isAssignExpression();
    }

    public boolean isAssignExpression() {
        Token aux = tool.getCurrentToken();
        openParent.clear();
        closeParent.clear();
        boolean flag = false;
        if (tool.verifyToken(TokenType.IDENTIFICADOR)) {
            tool.incrementIndex();
            if (tool.verifyToken(TokenType.ASIGNACION)) {
                tool.incrementIndex();
                if (isExpression()) {
                    flag = true;
                    if (tool.verifyToken(TokenType.PUNTO_Y_COMA)) {
                        tool.incrementIndex();
                    }
                } else {
                    if (tool.isValueToken(tool.getCurrentToken())) {
                        flag = true;
                        tool.incrementIndex();
                        if (tool.verifyToken(TokenType.PUNTO_Y_COMA)) {
                            tool.incrementIndex();
                        }
                    } else {
                        tool.showError("[NORMAL] Se esperaba valor");
                    }
                }
            } else if (tool.isAssignment(tool.getCurrentToken())) {
                tool.incrementIndex();
                if (isExpression()) {
                    flag = true;
                    if (tool.verifyToken(TokenType.PUNTO_Y_COMA)) {
                        tool.incrementIndex();
                    }
                } else {
                    if (tool.isValueToken(tool.getCurrentToken())) {
                        flag = true;
                        // System.out.println("VARIABLE AUGGASING");
                        tool.incrementIndex();
                        if (tool.verifyToken(TokenType.PUNTO_Y_COMA)) {
                            tool.incrementIndex();
                        }
                    } else {
                        tool.showError("[AUG] Se esperaba valor");
                    }
                }
            } else {
                tool.showError("[MAIN] Se esperaba operador");
            }
        }

        boolean val = false;
        for (Token tkn : tool.getTokenList()) {
            if (tkn.getRow() == aux.getRow()) {
                if (tkn.getToken() == TokenType.ASIGNACION) {
                    val = true;
                }
                if (val) {
                    if (tkn.getToken() == TokenType.PARENTESIS_APERTURA) {
                        openParent.add(tkn.getColumn());
                    } else if (tkn.getToken() == TokenType.PARENTESIS_CIERRE) {
                        closeParent.add(tkn.getColumn());
                    }
                }
            }
        }

        if (openParent.size() < closeParent.size()) {
            tool.showError("Se esperaba '('");
        } else if (openParent.size() > closeParent.size()) {
            tool.showError("Se esperaba ')'");
        }
        return flag;
    }

    public boolean isFactor() {
        boolean flag = false;
        // System.out.println(tool.getCurrentToken().getLexeme());
        if (tool.isValueFactor(tool.getCurrentToken())) {
            tool.incrementIndex();
            flag = true;
        } else if (tool.verifyToken(TokenType.RESTA)) {
            tool.incrementIndex();
            if (tool.verifyToken(TokenType.ENTERO) || tool.verifyToken(TokenType.DECIMAL)) {
                tool.incrementIndex();
                flag = true;
            } else {
                tool.showError("Se esperaba valor númerico");
            }
        } else if (tool.verifyToken(TokenType.PARENTESIS_APERTURA)) {
            tool.incrementIndex();
            if (isExpression()) {
                if (tool.verifyToken(TokenType.PARENTESIS_CIERRE)) {
                    tool.incrementIndex();
                    flag = true;
                }
            }
        }
        return flag;
    }

    public boolean isExpression() {
        boolean flag = false;
        // Verificar si es concatenación de cadenas primero
        if (tool.isStringIdentifier()) {
            if (tool.verifyToken(TokenType.CADENA) || tool.verifyToken(TokenType.IDENTIFICADOR)) {
                if (isStringConcatenation()) {
                    flag = true;
                }
            }
        }
        // Verificar el primer término en caso de ser aritmético
        else if (isTerm()) {
            flag = true;
            // Verificar operadores de suma o resta para términos aritméticos
            while (tool.verifyToken(TokenType.SUMA) || tool.verifyToken(TokenType.RESTA)) {
                tool.incrementIndex();
                if (!isTerm()) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    public boolean isStringConcatenation() {
        boolean flag = false;
        if (tool.verifyToken(TokenType.CADENA) || tool.verifyToken(TokenType.IDENTIFICADOR)) {
            tool.incrementIndex();
            flag = true;
            while (tool.verifyToken(TokenType.SUMA)) {
                tool.incrementIndex();
                if (!(tool.verifyToken(TokenType.CADENA) || tool.verifyToken(TokenType.IDENTIFICADOR))) {
                    tool.showError("Se esperaba cadena o identificador en concatenación");
                    flag = false;
                    break;
                }
                tool.incrementIndex();
            }
        }
        return flag;
    }

    public boolean isTerm() {
        boolean flag = false;
        // Verificar el primer factor
        if (isFactor()) {
            flag = true;
            // Verificar operadores de multiplicación o división
            while (tool.verifyToken(TokenType.MULTIPLICACION) || tool.verifyToken(TokenType.DIVISION)
                    || tool.verifyToken(TokenType.MODULO)) {
                tool.incrementIndex(); // Avanzar el operador
                if (!isFactor()) {
                    // tool.showError("[TERM] Se esperaba valor después del operador aritmético");
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
    // public boolean isDataCollection() {
    // boolean flag = false;
    // if (tool.verifyToken(TokenType.IDENTIFICADOR_TUPLA)) {
    // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.ASIGNACION)) {
    // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.PARENTESIS_APERTURA)) {
    // tool.incrementIndex();
    // if (tool.isValueToken(tool.getCurrentToken())) {
    // tool.incrementIndex();
    // if (checkValuesList()) {
    // // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.PARENTESIS_CIERRE)) {
    // flag = true;
    // System.out.println("ESTRUCTURA DE DATOS DECLARADA");
    // } else {
    // tool.showError("Se esperaba ')'");
    // }
    // }
    // } else {
    // tool.showError("Se esperaba valor");
    // }
    // }
    // }
    // } else if (tool.verifyToken(TokenType.IDENTIFICADOR_CONJUNTO)) {
    // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.ASIGNACION)) {
    // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.LLAVE_APERTURA)) {
    // tool.incrementIndex();
    // if (tool.isValueToken(tool.getCurrentToken())) {
    // tool.incrementIndex();
    // if (checkValuesList()) {
    // // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.LLAVE_CIERRE)) {
    // flag = true;
    // System.out.println("ESTRUCTURA DE DATOS DECLARADA");
    // } else {
    // tool.showError("Se esperaba '}'");
    // }
    // }
    // } else {
    // tool.showError("Se esperaba valor");
    // }
    // }
    // }
    // } else if (tool.verifyToken(TokenType.IDENTIFICADOR_LISTA)) {
    // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.ASIGNACION)) {
    // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.CORCHETE_APERTURA)) {
    // tool.incrementIndex();
    // if (tool.isValueToken(tool.getCurrentToken())) {
    // tool.incrementIndex();
    // if (checkValuesList()) {
    // // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.CORCHETE_CIERRE)) {
    // flag = true;
    // System.out.println("ESTRUCTURA DE DATOS DECLARADA");
    // } else {
    // tool.showError("Se esperaba ']'");
    // }
    // }
    // } else {
    // tool.showError("Se esperaba valor");
    // }
    // }
    // }
    // }
    // return flag;
    // }

    // private boolean checkValuesList() {
    // boolean flag = true;
    // while (tool.verifyToken(TokenType.COMA)) {
    // tool.incrementIndex();
    // if (tool.isValueToken(tool.getCurrentToken())) {
    // tool.incrementIndex();
    // } else {
    // tool.showError("Se esperaba valor después de ','");
    // flag = false;
    // }
    // }
    // return flag;
    // }

}
