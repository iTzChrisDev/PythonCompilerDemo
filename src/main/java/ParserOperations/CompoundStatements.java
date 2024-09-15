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
        boolean flag = false;
        tool.setCurrentRow(tool.getCurrentToken().getRow());
        if (isClassDeclaration()
                || isIfStatement() || isElifStatement() || isElseStatement()
                || isForStatement()
                || isWhileStatement()
                || isMatchStatement() || isCaseStatement()) {
            flag = true;
        }
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
                    flag = true;
                    tool.incrementIndex();
                    // System.out.println("CLASS CORRECTO");
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
        if (tool.verifyToken(TokenType.IF)) {
            tool.incrementIndex();
            // Verificar si la expresión condicional es válida
            if (expParser.isValidExpression()) {
                if (tool.getIndex() < tool.getTokenList().size()) {
                    if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
                        flag = true;
                        tool.incrementIndex();
                        // System.out.println("IF CORRECTO");
                    } else if (tool.verifyToken(TokenType.PARENTESIS_CIERRE)) {
                        tool.showError("No se encontró '('");
                    } else {
                        tool.showError("Se esperaban ':'");
                    }
                }
            } else {
                tool.showError("Expresión condicional no válida");
            }
        }
        return flag;
    }

    public boolean isElifStatement() {
        boolean flag = false;
        if (tool.verifyToken(TokenType.ELIF)) {
            tool.incrementIndex();
            // Verificar si la expresión condicional es válida
            if (expParser.isValidExpression()) {
                if (tool.getIndex() < tool.getTokenList().size()) {
                    if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
                        flag = true;
                        tool.incrementIndex();
                        // System.out.println("ELIF CORRECTO");
                    } else if (tool.verifyToken(TokenType.PARENTESIS_CIERRE)) {
                        tool.showError("No se encontró '('");
                    } else {
                        tool.showError("Se esperaban ':'");
                    }
                }
            } else {
                tool.showError("Expresión condicional no válida");
            }
        }
        return flag;
    }

    public boolean isElseStatement() {
        boolean flag = false;
        if (tool.verifyToken(TokenType.ELSE)) {
            tool.incrementIndex();
            if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
                flag = true;
                tool.incrementIndex();
                // System.out.println("ELSE CORRECTO");
            } else {
                tool.showError("Se esperaban ':'");
            }
        }
        return flag;
    }

    public boolean isWhileStatement() {
        boolean flag = false;
        if (tool.verifyToken(TokenType.WHILE)) {
            tool.incrementIndex();
            if (expParser.isValidExpression()) {
                if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
                    flag = true;
                    tool.incrementIndex();
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
                                        flag = true;
                                        tool.incrementIndex();
                                        // System.out.println("FOR CORRECTO");
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
                    } else if (tool.verifyToken(TokenType.CADENA) || tool.verifyToken(TokenType.IDENTIFICADOR_CONJUNTO)
                            || tool.verifyToken(TokenType.IDENTIFICADOR_LISTA)
                            || tool.verifyToken(TokenType.IDENTIFICADOR_TUPLA)) {
                        tool.incrementIndex();
                        if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
                            // FIN DEL ARBOL SINTACTICO
                            flag = true;
                            tool.incrementIndex();
                            System.out.println("FOR (LISTA, TUPLA, CADENA, CONJUNTO) CORRECTO");
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
        if (tool.verifyToken(TokenType.MATCH)) {
            // int matchCol = tool.getCurrentToken().getColumn();
            tool.incrementIndex();
            if (tool.isValueToken(tool.getCurrentToken())) {
                tool.incrementIndex();
                if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
                    tool.incrementIndex();
                    flag = true;
                } else {
                    tool.showError("Se esperaban ':'");
                }
            } else {
                tool.showError("Se esperaba valor a comparar");
            }
        }
        return flag;
    }

    public boolean isCaseStatement() {
        boolean flag = false;
        if (tool.verifyToken(TokenType.CASE)) {
            tool.incrementIndex();
            if (tool.isValueToken(tool.getCurrentToken()) || tool.verifyToken(TokenType.NONE)) {
                tool.incrementIndex();
                if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
                    flag = true;
                    tool.incrementIndex();
                } else {
                    tool.showError("Se esperaban ':'");
                }
            } else {
                tool.showError("Se esperaba valor o patrón");
            }
        }
        return flag;
    }

    // public boolean isFunctionDef() {
    // boolean flag = false;
    // // TO-DO
    // if (tool.verifyToken(TokenType.DEF)) {
    // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.IDENTIFICADOR_FUNCION)) {
    // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.PARENTESIS_APERTURA)) {
    // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.PARENTESIS_CIERRE)) {
    // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
    // tool.incrementIndex();
    // flag = true;
    // }
    // }
    // }
    // }
    // }
    // return flag;
    // }

    // public boolean isTryStatement() {
    // boolean flag = false;
    // if (tool.verifyToken(TokenType.TRY)) {
    // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
    // tool.incrementIndex();
    // System.out.println("TRY CORRECTO");
    // }
    // }
    // return flag;
    // }

    // public boolean isExceptStatement() {
    // boolean flag = false;
    // if (tool.verifyToken(TokenType.EXCEPT)) {
    // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.IDENTIFICADOR_EXCEPCION)) {
    // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
    // tool.incrementIndex();
    // System.out.println("EXCEPT CORRECTO");
    // }
    // }
    // }
    // return flag;
    // }

    // public boolean isFinallyStatement() {
    // boolean flag = false;
    // if (tool.verifyToken(TokenType.FINALLY)) {
    // tool.incrementIndex();
    // if (tool.verifyToken(TokenType.DOS_PUNTOS)) {
    // tool.incrementIndex();
    // System.out.println("FINALLY CORRECTO");
    // }
    // }
    // return flag;
    // }
}
