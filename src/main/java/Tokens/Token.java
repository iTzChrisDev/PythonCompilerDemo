package Tokens;

public class Token {
    private String lexeme;
    private TokenType token;
    private int row, column;

    public Token(String lexeme, TokenType token, int row, int column) {
        this.lexeme = lexeme;
        this.token = token;
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "{" + lexeme + ", " + token + ", " + row + ", " + column + "}";
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public TokenType getToken() {
        return token;
    }

    public void setToken(TokenType token) {
        this.token = token;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
