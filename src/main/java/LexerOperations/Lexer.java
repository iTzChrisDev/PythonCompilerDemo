package LexerOperations;

import Tokens.Constants;
import Tokens.Token;
import Tokens.TokenType;
import java.util.ArrayList;
import java.util.Map;

public class Lexer {

    private ArrayList<Token> tokenList;
    private ArrayList<String> linesCode;
    private TokenType tokenType;
    private boolean stringActive;
    private int row, column;

    public Lexer() {
        tokenList = new ArrayList<>();
        linesCode = new ArrayList<>();
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
            if (c == '\n') {
                linesCode.add(aux);
                aux = "";
            } else if (c == '\t') {
                for (int i = 0; i < 4; i++) {
                    aux += " ";
                }
            } else {
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

    }

    /**
     * Este método compara un lexema con una lista de palabras reservadas.
     *
     * @param lexeme El lexema a comparar con las palabras reservadas
     * @return 'True' si el lexema actual es una palabra reservada, 'False' si
     * no lo es.
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
     * si no lo es.
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
     * es.
     */
    private boolean isDelimiter(String lexeme) {
        return checkAndSetTokenType(lexeme, Constants.DELIMITERS);
    }

    /**
     * Este método verifica si el lexema se encuentra en alguno de los mapas de
     * tokens y establece su tipo de token una vez lo haya comparado.
     *
     * @param lexeme el lexema a comparar con el mapa seleccionado.
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
     * @return True si el lexema actual es un valor númerico, False si no lo es.
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
        tokenType = (dots == 1 && cont == lexeme.length()) ? TokenType.DECIMAL : TokenType.ENTERO;
        return (cont == lexeme.length()) && (dots <= 1);
    }

    /**
     * Este método verifica si un lexema es una cadena de caracteres.
     *
     * @param lexeme El lexema a verificar
     * @param index el indice actual del proceso de analisis
     * @return True si el lexema actual es una cadena de caracteres, False si no
     * lo es.
     */
    private boolean isString(String lexeme, int index) {
        if (lexeme == null || lexeme.length() < 2) {
            return false;
        }
        if (lexeme.charAt(0) == '"') {
            stringActive = true;
        }
        if (lexeme.charAt(0) == '"' && lexeme.charAt(lexeme.length() - 1) == '"') {
            index += lexeme.length();
            stringActive = false;
            return true;
        } else {
            return false;
        }
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
    }
}
