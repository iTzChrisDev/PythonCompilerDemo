package ParserOperations;

import Tokens.TokenType;

public class Assignments {
    private Utilities tool;

    public Assignments() {
        tool = new Utilities();
    }

    public boolean isAssignment() {
        if (isArithmeticOperation() || isAugAssign() || isValueAssign() || isDataCollection() || isCallFunction()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isArithmeticOperation() {
        boolean flag = false;
        //TO-DO
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
                } else {
                    tool.showError("Se esperaba valor");
                }
            } else {
                tool.showError("La variable no se encuentra definida");
            }
        }
        return flag;
    }

    public boolean isAugAssign() {
        boolean flag = false;
        if (tool.verifyToken(TokenType.IDENTIFICADOR)) {
            tool.incrementIndex();
            if (tool.isAssignment(tool.getCurrentToken())) {
                tool.incrementIndex();
                if (tool.isValueToken(tool.getCurrentToken())) {
                    flag = true;
                } else {
                    tool.showError("Se esperaba valor");
                }
            } else {
                tool.showError("Se esperaba operador de asignacion");
            }
        }
        return flag;
    }

    public boolean isCallFunction() {
        boolean flag = false;
        // TO-DO
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
                            tool.incrementIndex();
                            if (tool.verifyToken(TokenType.PARENTESIS_CIERRE)) {
                                flag = true;
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
                            tool.incrementIndex();
                            if (tool.verifyToken(TokenType.LLAVE_CIERRE)) {
                                flag = true;
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
                            tool.incrementIndex();
                            if (tool.verifyToken(TokenType.CORCHETE_CIERRE)) {
                                flag = true;
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
