package SemanticOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;

import Tokens.Constants;
import Tokens.Token;
import Tokens.TokenType;

public class VariableAssignment {
    private static List<Character> especialChars = List.of('+', '-', '*', '/', '(', ')');
    private ArrayList<Token> tokenList;
    public static ArrayList<Variable> variables;
    private ConditionalCheck conditions;
    private JTextArea console;

    public VariableAssignment(JTextArea console, ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
        this.console = console;
        conditions = new ConditionalCheck(console, tokenList);
        variables = new ArrayList<>();
    }

    public void analizeSemantic() {
        checkVars();
        evalExpressions();
        conditions.check();
    }

    private void evalExpressions() {
        int contInt = 0, contDec = 0, contBool = 0, contString = 0;
        Map<Integer, ArrayList<String>> expressions = new HashMap<>();
        for (Variable var : variables) {
            expressions.put(var.getRow(), splitExpression(var.getValue()));
        }

        for (var entry : expressions.entrySet()) {
            ArrayList<String> values = entry.getValue();
            for (int i = 0; i < values.size(); i++) {
                String value = values.get(i);
                for (Variable variable : variables) {
                    if (variable.getVarName().equals(value)) {
                        if (variable.getRow() < entry.getKey()) {
                            values.set(i, variable.getValue());
                            break;
                        } else {
                            showError("[SEMANTICO] No se reconoce '" + value + "'", entry.getKey());
                        }
                    }
                }
            }
        }
        // for (var entry : expressions.entrySet()) {
        // System.out.println(
        // entry.getKey() + " - " + entry.getValue().toString() + " - Size: " +
        // entry.getValue().size());
        // }

        for (Variable var : variables) {
            for (var entry : expressions.entrySet()) {
                if (var.getRow() == entry.getKey()) {
                    String expression = "";
                    for (String str : entry.getValue()) {
                        expression += str;
                    }
                    var.setValue(expression);
                    break;
                }
            }
        }

        for (var entry : expressions.entrySet()) {
            contInt = 0;
            contDec = 0;
            contBool = 0;
            contString = 0;
            int error = 0;
            for (String value : entry.getValue()) {
                if (!especialChars.contains(value)) {
                    switch (evalDataType(value)) {
                        case ENTERO:
                            contInt++;
                            break;
                        case DECIMAL:
                            contDec++;
                            break;
                        case BOOLEAN:
                            contBool++;
                            break;
                        case CADENA:
                            contString++;
                            break;
                        case ERROR:
                            error++;
                            break;
                    }
                }
            }
            // Asignar Tipo a cada variable
            assignDataTypes(entry.getKey(), contInt, contDec, contBool, contString, error);
        }

        for (Variable variable : variables) {
            if (variable.getType() == TokenType.CADENA) {
                ArrayList<String> row = splitExpression(variable.getValue());

                for (String item : row) {
                    if (item.equals("+")) {
                    } else if (item.equals("-") || item.equals("*") || item.equals("/")) {
                        showError("[SEMANTICO] Concatenación incorrecta", variable.getRow());
                    }
                }
            }
        }

    }

    private void assignDataTypes(int row, int contInt, int contDec, int contBool, int contString, int error) {
        TokenType type = TokenType.NONE;
        if (contInt > 0 && contBool == 0 && contString == 0) {
            if (contDec > 0) {
                type = TokenType.DECIMAL;
            } else {
                type = TokenType.ENTERO;
            }
        } else if (contDec > 0 && contBool == 0 && contString == 0 && contInt == 0) {
            type = TokenType.DECIMAL;
        } else if (contBool == 1 && contInt == 0 && contString == 0 && contDec == 0) {
            type = TokenType.BOOLEAN;
        } else if (contString > 0 && contInt == 0 && contBool == 0 && contDec == 0 && error == 0) {
            type = TokenType.CADENA;
        }

        for (Variable var : variables) {
            if (var.getRow() == row) {
                var.setType(type);
                if (type == TokenType.NONE) {
                    var.setState(State.INDEFINIDO);
                }
            }
        }

        if (type == TokenType.NONE) {
            showError("[SEMANTICO] Asignacion incorrecta", row);
        }
    }

    private TokenType evalDataType(String value) {
        TokenType type = TokenType.NONE;

        if (value.contains("\"")) {
            int cont = 0;
            boolean isString = false;
            for (char character : value.toCharArray()) {
                if (character == '"') {
                    cont++;
                }
            }

            if (value.toCharArray()[0] == '"' && value.toCharArray()[value.length() - 1] == '"') {
                isString = true;
            }

            if (cont == 2 && isString) {
                type = TokenType.CADENA;
            } else {
                type = TokenType.ERROR;
            }
        } else if (value.equals("True") || value.equals("False")) {
            type = TokenType.BOOLEAN;
        } else {
            int cont = 0, dots = 0;
            for (char c : value.toCharArray()) {
                if (Constants.NUMBER_CHARS.contains(String.valueOf(c))) {
                    if (c == '.') {
                        dots++;
                    }
                    cont++;
                }
            }

            if (dots == 1 && cont == value.length()) {
                type = TokenType.DECIMAL;
            } else if (dots == 0 && cont == value.length()) {
                type = TokenType.ENTERO;
            } else {
                type = TokenType.NONE;
            }
        }

        return type;
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

    private void showError(String errorMessage, int row) {
        console.setText(console.getText() + errorMessage + " en la linea " + row + "\n");
    }
}
