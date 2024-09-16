package ParserOperations;

import Tokens.TokenType;

public class Assignments {
    private Utilities tool;
    private ArithmeticParser ap;

    public Assignments() {
        tool = new Utilities();
        ap = new ArithmeticParser();
    }

    public boolean isAssignment() {
        if (ap.isArithmeticOperation() || isValueAssign() /* || isDataCollection() */) {
            return true;
        } else {
            return false;
        }
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
