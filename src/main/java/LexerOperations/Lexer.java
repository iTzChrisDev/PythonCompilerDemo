package LexerOperations;

import Tokens.Constants;
import Tokens.Token;
import java.util.ArrayList;

public class Lexer {

    private ArrayList<Tokens.Token> tokenList;

    public Lexer() {
        tokenList = new ArrayList<>();
    }

    public boolean isReservedWord(String lexeme) {
        return Constants.RESERVED_WORDS.contains(lexeme);
    }

    public boolean isControlStructure(String lexeme) {
        return Constants.CONTROL_STRUCT.contains(lexeme);
    }

    public boolean isOperator(String lexeme) {
        return Constants.OPERATORS.contains(lexeme);
    }

    public boolean isDelimiter(String lexeme) {
        return Constants.DELIMITERS.contains(lexeme);
    }

    public boolean isNumber(String lexeme) {
        int cont = 0, dots = 0;
        for (char c : lexeme.toCharArray()) {
            if (Constants.NUMBER_CHARS.contains(String.valueOf(c))) {
                if (c == '.') {
                    dots++;
                }
                cont++;
            }
        }
        return (cont == lexeme.length()) && (dots <= 1);
    }

    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

    public void clearLexemes() {
        tokenList.clear();
    }
}
