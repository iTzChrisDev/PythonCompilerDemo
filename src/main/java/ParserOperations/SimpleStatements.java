package ParserOperations;

import Tokens.TokenType;

public class SimpleStatements {

    private Utilities tool;
    private Assignments assigns;

    public SimpleStatements() {
        tool = new Utilities();
        assigns = new Assignments();
    }

    public boolean isSimpleStatement() {
        tool.setCurrentRow(tool.getCurrentToken().getRow());
        if (assigns.isAssignment() || isImportStatement()
                || tool.verifyToken(TokenType.PASS) || tool.verifyToken(TokenType.BREAK)
                || tool.verifyToken(TokenType.CONTINUE)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isImportStatement() {
        boolean flag = false;

        // Caso 'from ... import ...'
        if (tool.verifyToken(TokenType.FROM)) {
            tool.incrementIndex();
            if (tool.verifyToken(TokenType.IDENTIFICADOR_MODULO)) {
                tool.incrementIndex();
                if (tool.verifyToken(TokenType.IMPORT)) {
                    tool.incrementIndex();
                    if (tool.verifyToken(TokenType.IDENTIFICADOR_CLASE)) {
                        tool.incrementIndex();
                        while (tool.verifyToken(TokenType.COMA)) {
                            tool.incrementIndex();
                            if (tool.verifyToken(TokenType.IDENTIFICADOR_CLASE)) {
                                tool.incrementIndex();
                            } else {
                                tool.showError("Se esperaba especificación de clase después de ','");
                                return false;
                            }
                        }
                        flag = true; // Declaración válida
                    } else if (tool.verifyToken(TokenType.MULTIPLICACION)) {
                        tool.incrementIndex();
                        flag = true; // Declaración válida
                    } else {
                        tool.showError("Se esperaba especificación de clase(s)");
                    }
                } else {
                    tool.showError("Se esperaba 'import'");
                }
            } else {
                tool.showError("Se esperaba módulo");
            }
        }
        // Caso 'import ... as ...'
        else if (tool.verifyToken(TokenType.IMPORT)) {
            tool.incrementIndex();
            if (tool.verifyToken(TokenType.IDENTIFICADOR_MODULO)) {
                tool.incrementIndex();
                if (tool.verifyToken(TokenType.AS)) {
                    tool.incrementIndex();
                    if (tool.verifyToken(TokenType.ALIAS)) {
                        tool.incrementIndex();
                        flag = true; // Declaración válida
                    } else {
                        tool.showError("Se esperaba alias después de 'as'");
                    }
                } else {
                    // Importación sin alias, declaración válida
                    flag = true;
                }
            } else {
                tool.showError("Se esperaba módulo después de 'import'");
            }
        }

        return flag;
    }

}
