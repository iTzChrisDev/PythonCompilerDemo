package SemanticOperations;

import Tokens.TokenType;

public class Variable {
    private String name;
    private TokenType type;
    private String value;
    private int row;
    private State state;

    public Variable(String name, TokenType type, String value, int row, State state) {
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

    public String getVarName() {
        return name;
    }

    public void setVarName(String name) {
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
