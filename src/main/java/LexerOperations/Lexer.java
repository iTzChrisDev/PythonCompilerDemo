package LexerOperations;

import Tokens.Constants;
import Tokens.Token;
import Tokens.TokenType;
import java.util.ArrayList;
import java.util.Map;

public class Lexer {

    private ArrayList<Token> tokenList;
    private ArrayList<String> linesCode, variableNames;
    private TokenType tokenType;
    private boolean stringActive;
    private int row, column;

    public Lexer() {
        tokenList = new ArrayList<>();
        linesCode = new ArrayList<>();
        variableNames = new ArrayList<>();
        tokenType = TokenType.DESCONOCIDO;
    }

    /**
     * Este método separa el codigo de entrada en lineas
     *
     * @param code El codigo a separar por filas
     */
    private void splitCode(String code) {
        String aux = "";
        char codeChars[] = code.toCharArray();
        for (char c : codeChars) {
            switch (c) {
                case '\n' -> {
                    linesCode.add(aux);
                    aux = "";
                }
                case '\t' -> {
                    for (int i = 0; i < 4; i++) {
                        aux += " ";
                    }
                }
                default ->
                    aux += c;
            }
        }
        linesCode.add(aux);
    }

    /**
     * Este método realiza una llamada al metodo 'analizeLine' para analizar
     * cada linea del codigo proporcionado en el metodo 'splitCode'
     *
     * @param code El codigo a analizar
     */
    public void analizeCode(String code) {
        row = 1;
        column = 1;
        splitCode(code);
        for (String line : linesCode) {
            analizeLine(line);
            row++;
        }
    }

    /**
     * Este metodo separa caracter por caracter la linea de codigo para encontar
     * los tokens existentes en el codigo
     *
     * @param line La linea de codigo a analizar
     */
    private void analizeLine(String line) {
        int index = 0;
        String lexeme = "";
        char[] chars = line.toCharArray();
        stringActive = false;

        // Separamos la linea en lexemas(palabras) para poder obtener el token de una
        // manera mas sencilla
        while (index != chars.length) {
            // Ignoramos los comentarios en linea que comienzan con '#'
            if (!stringActive && chars[index] == '#') {
                break;
            }

            // Activamos y desactivamos la bandera de cadenas de caracteres
            if (chars[index] == '"' || chars[index] == '\'') {
                stringActive = !stringActive;
            }

            // Separamos los lexemas cada que encontramos los caracteres de espacio en
            // blanco, delimitadores u operadores
            if (!stringActive && (Character.isWhitespace(chars[index]) || isOperator(Character.toString(chars[index]))
                    || isDelimiter(Character.toString(chars[index])))) {
                if (!lexeme.isBlank()) {
                    column = index + 1 - lexeme.length();
                    tokenList.add(new Token(lexeme, TokenType.DESCONOCIDO, row, column));
                    lexeme = "";
                }
                if (isOperator(Character.toString(chars[index]))) {
                    column = index + 1;
                    String lex;
                    if (index < chars.length - 1 && isOperator(Character.toString(chars[index + 1]))) {
                        lex = Character.toString(chars[index]) + Character.toString(chars[index + 1]);
                        index++;
                    } else {
                        lex = Character.toString(chars[index]);
                    }
                    tokenList.add(new Token(lex, TokenType.DESCONOCIDO, row, column));
                } else if (isDelimiter(Character.toString(chars[index]))) {
                    column = index + 1;
                    tokenList.add(new Token(Character.toString(chars[index]), TokenType.DESCONOCIDO, row, column));
                }
            } else {
                lexeme += chars[index];
            }
            index++;
        }

        // Se verifica una vez final por el salto de linea
        if (!lexeme.isBlank()) {
            column = index + 1 - lexeme.length();
            tokenList.add(new Token(lexeme, tokenType, row, column));
            lexeme = "";
        }

        // Asignación de tokens a cada lexema
        int tknIndex = 0;
        for (Token tkn : tokenList) {
            if (isReservedWord(tkn.getLexeme())) {
                tkn.setToken(tokenType);
            } else if (isControlStructure(tkn.getLexeme())) {
                tkn.setToken(tokenType);
            } else if (isDelimiter(tkn.getLexeme())) {
                tkn.setToken(tokenType);
            } else if (isOperator(tkn.getLexeme())) {
                tkn.setToken(tokenType);
            } else if (isNumber(tkn.getLexeme())) {
                tkn.setToken(tokenType);
            } else if (isString(tkn.getLexeme())) {
                tkn.setToken(tokenType);
            } else if (isIdentifier(tkn.getLexeme(), tknIndex)) {
                tkn.setToken(tokenType);
            } else if (isClassIdentifier(tkn.getLexeme(), tknIndex)) {
                tkn.setToken(tokenType);
            } else if (tkn.getToken() == TokenType.DESCONOCIDO) {
                if (variableNames.contains(tkn.getLexeme())) {
                    tkn.setToken(TokenType.IDENTIFICADOR);
                }
            }
            tknIndex++;
        }
    }

    /**
     * Este método compara un lexema con una lista de palabras reservadas.
     *
     * @param lexeme El lexema a comparar con las palabras reservadas
     * @return 'True' si el lexema actual es una palabra reservada, 'False' si
     *         no lo es.
     */
    private boolean isReservedWord(String lexeme) {
        return checkAndSetTokenType(lexeme, Constants.RESERVED_WORDS);
    }

    /**
     * Este método compara un lexema con una lista de las estructuras de
     * control.
     *
     * @param lexeme El lexema a comparar con las estructuras de control
     * @return 'True' si el lexema actual es una estructura de control, 'False'
     *         si no lo es.
     */
    private boolean isControlStructure(String lexeme) {
        return checkAndSetTokenType(lexeme, Constants.CONTROL_STRUCTURES);
    }

    /**
     * Este método compara un lexema con una lista de operadores.
     *
     * @param lexeme El lexema a comparar con los operadores
     * @return 'True' si el lexema actual es un operador, 'False' si no lo es.
     */
    private boolean isOperator(String lexeme) {
        return checkAndSetTokenType(lexeme, Constants.OPERATORS);
    }

    /**
     * Este método compara un lexema con una lista de delimitadores.
     *
     * @param lexeme El lexema a comparar con los delimitadores
     * @return 'True' si el lexema actual es un delimitador, 'False' si no lo
     *         es.
     */
    private boolean isDelimiter(String lexeme) {
        return checkAndSetTokenType(lexeme, Constants.DELIMITERS);
    }

    /**
     * Este método compara los valores anteriores o posteriores de un token en
     * base a su posicion de modo que asi puede determinar el tipo de
     * identificador
     *
     * @param lexeme   El lexema a comparar
     * @param tknIndex La posicion del token a comparar
     * @return 'True' si el lexema actual es un identificador, 'False' si no lo
     *         es.
     */
    private boolean isIdentifier(String lexeme, int tknIndex) {
        boolean val = false;
        if (tknIndex >= 1 && tokenList.get(tknIndex - 1).getToken() == TokenType.EXCEPT) {
            tokenType = TokenType.IDENTIFICADOR_EXCEPCION;
            val = !val;
        }
        if (tknIndex < tokenList.size() - 1 && tokenList.get(tknIndex + 1).getLexeme().equals("=")) {
            if (tknIndex < tokenList.size() - 2 && tokenList.get(tknIndex + 2).getLexeme().equals("{")) {
                tokenType = TokenType.IDENTIFICADOR_CONJUNTO;
            } else if (tknIndex < tokenList.size() - 2 && tokenList.get(tknIndex + 2).getLexeme().equals("(")) {
                tokenType = TokenType.IDENTIFICADOR_TUPLA;
            } else if (tknIndex < tokenList.size() - 2 && tokenList.get(tknIndex + 2).getLexeme().equals("[")) {
                tokenType = TokenType.IDENTIFICADOR_LISTA;
            } else {
                tokenType = TokenType.IDENTIFICADOR;
            }
            variableNames.add(lexeme);
            val = !val;
        } else if (variableNames.contains(lexeme)) {
            tokenType = TokenType.IDENTIFICADOR;
            val = !val;
        }
        return val;
    }

    /**
     * Este método compara los valores anteriores o posteriores de un token en
     * base a su posicion de modo que asi puede determinar si es un
     * identificador de clase
     *
     * @param lexeme   El lexema a comparar
     * @param tknIndex La posicion del token a comparar
     * @return 'True' si el lexema actual es un identificador de clase, 'False'
     *         si no lo es.
     */
    private boolean isClassIdentifier(String lexeme, int tknIndex) {
        boolean val = false;
        if (tknIndex > 0 && tknIndex < tokenList.size() - 1 && tokenList.get(tknIndex - 1).getToken() == TokenType.CLASS) {
            if (!variableNames.contains(lexeme)) {
                tokenType = TokenType.IDENTIFICADOR_CLASE;
                val = !val;
            }
        } else if (tknIndex > 0 && tknIndex < tokenList.size() - 1 && tokenList.get(tknIndex - 1).getToken() == TokenType.FOR) {
            variableNames.add(lexeme);
            tokenType = TokenType.IDENTIFICADOR;
            val = !val;
        } else if (tknIndex > 0 && tknIndex < tokenList.size() - 1 && tokenList.get(tknIndex - 1).getToken() == TokenType.DEF) {
            tokenType = TokenType.IDENTIFICADOR_FUNCION;
            val = !val;
        }
        return val;
    }

    /**
     * Este método verifica si el lexema se encuentra en alguno de los mapas de
     * tokens y establece su tipo de token una vez lo haya comparado.
     *
     * @param lexeme   el lexema a comparar con el mapa seleccionado.
     * @param tokenMap el mapa a utilizar para la busqueda y asignación.
     * @return Un booleano dependiendo de si se ha encontrado en el mapa o no.
     */
    private boolean checkAndSetTokenType(String lexeme, Map<String, TokenType> tokenMap) {
        TokenType type = tokenMap.get(lexeme);
        tokenType = (type != null) ? type : TokenType.DESCONOCIDO;
        return type != null;
    }

    /**
     * Este método verifica si el lexema actual es un conjunto de números.
     *
     * @param lexeme El lexema a comparar si es un valor númerico
     * @return 'True' si el lexema actual es un valor númerico, 'False' si no lo
     *         es.
     */
    private boolean isNumber(String lexeme) {
        int cont = 0, dots = 0;
        for (char c : lexeme.toCharArray()) {
            if (Constants.NUMBER_CHARS.contains(String.valueOf(c))) {
                if (c == '.') {
                    dots++;
                }
                cont++;
            }
        }

        boolean val;
        if (dots == 1 && cont == lexeme.length()) {
            tokenType = TokenType.DECIMAL;
            val = true;
        } else if (dots == 0 && cont == lexeme.length()) {
            tokenType = TokenType.ENTERO;
            val = true;
        } else {
            val = false;
        }
        return val;
    }

    /**
     * Este método verifica si un lexema es una cadena de caracteres.
     *
     * @param lexeme El lexema a verificar
     * @return 'True' si el lexema actual es una cadena de caracteres, 'False'
     *         si no lo es.
     */
    private boolean isString(String lexeme) {
        char[] aux = lexeme.toCharArray();
        tokenType = (aux[0] == '"' && aux[aux.length - 1] == '"') || (aux[0] == '\'' && aux[aux.length - 1] == '\'')
                ? TokenType.CADENA
                : TokenType.DESCONOCIDO;
        return (aux[0] == '"' && aux[aux.length - 1] == '"') || (aux[0] == '\'' && aux[aux.length - 1] == '\'');
    }

    /**
     * Este método retorna la lista de los lexemas encontrados con su respectivo
     * Token y sus posiciones en fila y columna.
     *
     * @return La lista de todos los Tokens encontrados.
     */
    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

    /**
     * Este método limpia las listas utilizadas en el proceso del analisis
     * léxico.
     */
    public void clearLexemes() {
        tokenList.clear();
        linesCode.clear();
        variableNames.clear();
    }
}
