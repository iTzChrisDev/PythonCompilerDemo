package SemanticOperations;

import java.util.ArrayList;

import Tokens.Token;
import Tokens.TokenType;

public class VariableAssignment {
    private ArrayList<Token> tokenList;
    private ArrayList<Variable> variables;

    public VariableAssignment(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public void checkVars() {
        variables = new ArrayList<>();
        int currentVarRow = 0;
        for (int i = 0; i < tokenList.size(); i++) {
            String expression = "";
            Token currentToken = tokenList.get(i);
            if (currentToken.getToken() == TokenType.IDENTIFICADOR
                    && tokenList.get(i).getToken() == TokenType.ASIGNACION) {
                currentVarRow = currentToken.getRow();
                for (Token token : tokenList) {
                    if (token.getRow() == currentVarRow) {
                        expression += token.getLexeme();
                    }
                }
                variables.add(
                        new Variable(currentToken.getLexeme(), TokenType.NONE, expression, currentVarRow, "DECLARADA"));
            }
        }
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

}
