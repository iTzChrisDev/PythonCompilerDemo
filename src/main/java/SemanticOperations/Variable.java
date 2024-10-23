package SemanticOperations;

import Tokens.TokenType;

public class Variable {
    private String name;
    private TokenType type;
    private String value;
    private int row;
    private String state;

    public Variable(String name, TokenType type, String value, int row, String state) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.row = row;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Variable [name=" + name + ", type=" + type + ", value=" + value + ", row=" + row + ", state=" + state
                + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
