package ParserOperations;

import Tokens.TokenType;

public class SimpleStatements {

    private Utilities tool;

    public SimpleStatements() {
        tool = new Utilities();
    }

    public boolean isSimpleStatement() {
        if (isAssignment() || isReturnStatement() || isImportStatement()
                || tool.verifyToken(TokenType.PASS) || tool.verifyToken(TokenType.BREAK)
                || tool.verifyToken(TokenType.CONTINUE)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAssignment() {
        boolean flag = false;
        // TO-DO
        return flag;
    }

    public boolean isReturnStatement() {
        boolean flag = false;
        // TO-DO
        return flag;
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
