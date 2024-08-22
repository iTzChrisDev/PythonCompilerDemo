package ParserOperations;

import Tokens.TokenType;

public class CompoundStatements {

    private Utilities tool;
    private ExpressionParser expParser;

    public CompoundStatements() {
        tool = new Utilities();
        expParser = new ExpressionParser();
    }

    public boolean isCompoundStatement() {
        if (isFunctionDef() || isClassDeclaration() || isIfStatement() || isForStatement()
                || isWhileStatement() || isMatchStatement() || isTryStatement()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFunctionDef() {
        boolean flag = false;
        // TO-DO
        return flag;
    }

    public boolean isClassDeclaration() {
        boolean flag = false;
        if (tool.verifyToken(TokenType.CLASS)) {
            tool.incrementIndex();
            if (tool.verifyToken(TokenType.IDENTIFICADOR_CLASE)) {
                tool.incrementIndex();
                if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
                    // Fin del arbol sintáctico
                } else {
                    tool.showError("Se esperaba ':'");
                }
            } else {
                tool.showError("Se esperaba identificador de clase");
            }
        }
        return flag;
    }

    public boolean isIfStatement() {
        boolean flag = false;

        // Verificar si el token actual es 'IF'
        if (tool.verifyToken(TokenType.IF)) {
            tool.incrementIndex();
            // Verificar si la expresión condicional es válida
            if (expParser.isCompoundExpression()) {
                // Verificar el token siguiente
                if (tool.getIndex() < tool.getTokenList().size()) {
                    if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
                        flag = true; // La sentencia IF es correcta
                        System.out.println("IF CORRECTO");
                    } else if (tool.verifyToken(TokenType.PARENTESIS_CIERRE)) {
                        tool.showError("No se encontró '('");
                    } else {
                        tool.showError("Se esperaban ':'");
                    }
                } else {
                    tool.showError("Se esperaban ':'");
                }
            } else {
                tool.showError("Expresión condicional no válida");
            }
        }
        return flag;
    }

    public boolean isWhileStatement() {
        boolean flag = false;
        if (tool.verifyToken(TokenType.WHILE)) {
            tool.incrementIndex();
            if (expParser.isCompoundExpression()) {
                if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
                    System.out.println("WHILE CORRECTO");
                } else {
                    tool.showError("Se esperaban ':'");
                }
            }
        }
        return flag;
    }

    public boolean isForStatement() {
        boolean flag = false;
        if (tool.verifyToken(TokenType.FOR)) {
            tool.incrementIndex();
            if (tool.verifyToken(TokenType.IDENTIFICADOR)) {
                tool.incrementIndex();
                if (tool.verifyToken(TokenType.IN)) {
                    tool.incrementIndex();
                    if (tool.verifyToken(TokenType.RANGE)) {
                        tool.incrementIndex();
                        if (tool.verifyToken(TokenType.PARENTESIS_APERTURA)) {
                            tool.incrementIndex();
                            if (tool.verifyToken(TokenType.ENTERO)) {
                                tool.incrementIndex();
                                if (tool.verifyToken(TokenType.PARENTESIS_CIERRE)) {
                                    tool.incrementIndex();
                                    if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
                                        // FIN DEL ARBOL SINTACTICO
                                    } else {
                                        tool.showError("Se esperaban ':'");
                                    }
                                } else {
                                    tool.showError("Se esperaban ')'");
                                }
                            } else {
                                tool.showError("Se esperaban valor iterable");
                            }
                        } else {
                            tool.showError("Se esperaban '('");
                        }
                    } else if (tool.verifyToken(TokenType.CADENA)) {
                        tool.incrementIndex();
                        if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
                            // FIN DEL ARBOL SINTACTICO
                        }
                    } else {
                        tool.showError("Se esperaban expresión");
                    }
                } else {
                    tool.showError("Se esperaban palabra reservada");
                }
            } else {
                tool.showError("Se esperaban iterador");
            }
        }
        return flag;
    }

    public boolean isMatchStatement() {
        boolean flag = false;
        // TO-DO
        return flag;
    }

    public boolean isTryStatement() {
        boolean flag = false;
        // TO-DO
        return flag;
    }
}
