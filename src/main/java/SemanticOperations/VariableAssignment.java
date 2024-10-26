package SemanticOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Tokens.Token;
import Tokens.TokenType;

public class VariableAssignment {
    private static List<Character> especialChars = List.of('+', '-', '*', '/', '(', ')');
    private ArrayList<Token> tokenList;
    private ArrayList<Variable> variables;

    public VariableAssignment(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public void analizeSemantic() {
        checkVars();
        evalExpressions();
    }

    private void evalExpressions() {
        Map<Integer, ArrayList<String>> expressions = new HashMap<>();
        for (Variable var : variables) {
            expressions.put(var.getRow(), splitExpression(var.getValue()));
        }

        for (var entry : expressions.entrySet()) {
            System.out.println(
                    entry.getKey() + " - " + entry.getValue().toString() + " - Size: " + entry.getValue().size());
        }
    }

    private ArrayList<String> splitExpression(String value) {
        ArrayList<String> exp = new ArrayList<>();
        String aux = "";
        
        for (char c : value.toCharArray()) {
            if (!Character.isWhitespace(c)) { 
                if (especialChars.contains(c)) {
                    if (aux.length() > 0) {
                        exp.add(aux.toString()); 
                        aux = "";
                    }
                    exp.add(String.valueOf(c)); 
                } else {
                    aux += c;
                }
            }
        }
    
        if (aux.length() > 0) {
            exp.add(aux);
        }
        
        return exp;
    }
    
    

    private void checkVars() {
        variables = new ArrayList<>();
        String expression = "";
        for (int i = 0; i < tokenList.size(); i++) {
            try {
                Token currentToken = tokenList.get(i);
                Token nextToken = tokenList.get(i + 1);
                if (currentToken.getToken() == TokenType.IDENTIFICADOR
                        && nextToken.getToken() == TokenType.ASIGNACION) {
                    for (Token token : tokenList) {
                        if (token.getRow() == currentToken.getRow()) {
                            if (token.getLexeme() != currentToken.getLexeme()
                                    && token.getLexeme() != nextToken.getLexeme()) {
                                expression += token.getLexeme();
                            }
                        }
                    }
                    State state = (!expression.isBlank()) ? State.ASIGNADO : State.NO_ASIGNADO;
                    variables.add(new Variable(currentToken.getLexeme(), TokenType.NONE, expression,
                            currentToken.getRow(), state));
                    expression = "";
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

}
