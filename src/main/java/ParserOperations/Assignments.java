package ParserOperations;

import Tokens.TokenType;

public class Assignments {
    private Utilities tool;

    public Assignments() {
        tool = new Utilities();
    }

    public boolean isAssignment() {
        if (/* isArithmeticOperation() || */isValueAssign() /*|| isDataCollection()*/) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isArithmeticOperation() {
        if (isExpression() || isTerm()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isExpression() {
        boolean flag = false;
        if (isTerm()) {
            tool.incrementIndex();
            if (tool.verifyToken(TokenType.SUMA) || tool.verifyToken(TokenType.RESTA)) {
                tool.incrementIndex();
                if (isExpression()) {
                    flag = true;
                } else if (tool.getIndex() < tool.getTokenList().size() - 1
                        && tool.getTokenList().get(tool.getIndex() + 1).getRow() != tool.getCurrentToken().getRow()) {
                    flag = true;
                } else {
                    tool.showError("Se esperaba una expresión");
                }
            } else {
                tool.showError("Se esperaba operador (+, -)");
            }
        } else {
            tool.showError("Se esperaba termino");
        }
        return flag;
    }

    private boolean isTerm() {
        boolean flag = false;
        if (isFactor()) {
            tool.incrementIndex();
            if (tool.verifyToken(TokenType.MULTIPLICACION) || tool.verifyToken(TokenType.DIVISION)) {
                tool.incrementIndex();
                if (isTerm()) {
                    flag = true;
                } else if (tool.getIndex() < tool.getTokenList().size() - 1
                        && tool.getTokenList().get(tool.getIndex() + 1).getRow() != tool.getCurrentToken().getRow()) {
                    flag = true;
                } else {
                    tool.showError("Se esperaba un termino");
                }
            } else {
                tool.showError("Se esperaba operador (*, /)");
            }
        }
        return flag;
    }

    private boolean isFactor() {
        boolean flag = false;
        if (tool.verifyToken(TokenType.ENTERO) || tool.verifyToken(TokenType.DECIMAL)) {
            flag = true;
        } else if (tool.verifyToken(TokenType.IDENTIFICADOR)) {
            flag = true;
        } else if (tool.verifyToken(TokenType.PARENTESIS_APERTURA)) {
            tool.incrementIndex();
            if (isExpression()) {
                if (tool.verifyToken(TokenType.PARENTESIS_CIERRE)) {
                    tool.incrementIndex();
                    flag = true;
                } else {
                    tool.showError("Se esperaba ')");
                }
            }
        } else {
            tool.showError("Se esperaba un factor");
        }
        return flag;
    }

    public boolean isValueAssign() {
        boolean flag = false;
        if (tool.verifyToken(TokenType.IDENTIFICADOR)) {
            tool.incrementIndex();
            if (tool.verifyToken(TokenType.ASIGNACION)) {
                tool.incrementIndex();
                if (tool.isValueToken(tool.getCurrentToken())) {
                    flag = true;
                    // System.out.println("VARIABLE DECLARADA");
                    tool.incrementIndex();
                } else {
                    tool.showError("Se esperaba valor");
                }
            } else if (tool.isAssignment(tool.getCurrentToken())) {
                tool.incrementIndex();
                if (tool.isValueToken(tool.getCurrentToken())) {
                    // System.out.println("VARIABLE AUGASSIGN");
                    flag = true;
                    tool.incrementIndex();
                } else {
                    tool.showError("Se esperaba valor");
                }
            } else {
                tool.showError("Se esperaba operador");
            }
        }
        return flag;
    }

    public boolean isDataCollection() {
        boolean flag = false;
        if (tool.verifyToken(TokenType.IDENTIFICADOR_TUPLA)) {
            tool.incrementIndex();
            if (tool.verifyToken(TokenType.ASIGNACION)) {
                tool.incrementIndex();
                if (tool.verifyToken(TokenType.PARENTESIS_APERTURA)) {
                    tool.incrementIndex();
                    if (tool.isValueToken(tool.getCurrentToken())) {
                        tool.incrementIndex();
                        if (checkValuesList()) {
                            // tool.incrementIndex();
                            if (tool.verifyToken(TokenType.PARENTESIS_CIERRE)) {
                                flag = true;
                                System.out.println("ESTRUCTURA DE DATOS DECLARADA");
                            } else {
                                tool.showError("Se esperaba ')'");
                            }
                        }
                    } else {
                        tool.showError("Se esperaba valor");
                    }
                }
            }
        } else if (tool.verifyToken(TokenType.IDENTIFICADOR_CONJUNTO)) {
            tool.incrementIndex();
            if (tool.verifyToken(TokenType.ASIGNACION)) {
                tool.incrementIndex();
                if (tool.verifyToken(TokenType.LLAVE_APERTURA)) {
                    tool.incrementIndex();
                    if (tool.isValueToken(tool.getCurrentToken())) {
                        tool.incrementIndex();
                        if (checkValuesList()) {
                            // tool.incrementIndex();
                            if (tool.verifyToken(TokenType.LLAVE_CIERRE)) {
                                flag = true;
                                System.out.println("ESTRUCTURA DE DATOS DECLARADA");
                            } else {
                                tool.showError("Se esperaba '}'");
                            }
                        }
                    } else {
                        tool.showError("Se esperaba valor");
                    }
                }
            }
        } else if (tool.verifyToken(TokenType.IDENTIFICADOR_LISTA)) {
            tool.incrementIndex();
            if (tool.verifyToken(TokenType.ASIGNACION)) {
                tool.incrementIndex();
                if (tool.verifyToken(TokenType.CORCHETE_APERTURA)) {
                    tool.incrementIndex();
                    if (tool.isValueToken(tool.getCurrentToken())) {
                        tool.incrementIndex();
                        if (checkValuesList()) {
                            // tool.incrementIndex();
                            if (tool.verifyToken(TokenType.CORCHETE_CIERRE)) {
                                flag = true;
                                System.out.println("ESTRUCTURA DE DATOS DECLARADA");
                            } else {
                                tool.showError("Se esperaba ']'");
                            }
                        }
                    } else {
                        tool.showError("Se esperaba valor");
                    }
                }
            }
        }
        return flag;
    }

    private boolean checkValuesList() {
        boolean flag = true;
        while (tool.verifyToken(TokenType.COMA)) {
            tool.incrementIndex();
            if (tool.isValueToken(tool.getCurrentToken())) {
                tool.incrementIndex();
            } else {
                tool.showError("Se esperaba valor después de ','");
                flag = false;
            }
        }
        return flag;
    }

}
