package Lexer;

import Tokens.Constants;

public class Lexer {
    public boolean isReservedWord(String word) {
        return Constants.PALABRAS_RESERVADAS.contains(word);
    }

    public boolean isControlStructure(String word) {
        return Constants.ESTRUCTURAS_CONTROL.contains(word);
    }

    public boolean isOperator(String symbol) {
        return Constants.OPERADORES.contains(symbol);
    }

    public boolean isDelimiter(String symbol) {
        return Constants.DELIMITADORES.contains(symbol);
    }
}
