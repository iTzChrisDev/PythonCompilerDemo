package SemanticOperations;

import java.util.ArrayList;

import javax.swing.JTextArea;

import ParserOperations.Utilities;
import Tokens.Token;
import Tokens.TokenType;

public class VariableCheck {

    private ArrayList<Token> tokenList;
    private ArrayList<ArrayList<Token>> rowsVariables;
    private ArrayList<Variable> varAux;
    private ArrayList<Variable> variables;
    private JTextArea console;

    public VariableCheck(ArrayList<Token> tokenList, JTextArea console) {
        this.tokenList = tokenList;
        this.console = console;
    }

    public void check() {
        varAux = new ArrayList<>();
        variables = new ArrayList<>();
        rowsVariables = new ArrayList<>();

        for (Token token : tokenList) {
            if (token.getToken() == TokenType.IDENTIFICADOR) {
                boolean exists = false;

                for (Variable variable : varAux) {
                    if (variable.getName().equals(token.getLexeme())) {
                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    varAux.add(new Variable(token.getLexeme(), TokenType.IDENTIFICADOR, "null", token.getRow(),
                            "NO DECLARADA"));
                }
            }
        }

        for (Variable variable : varAux) {
            ArrayList<Token> aux = new ArrayList<>();
            for (Token tkn : tokenList) {
                if (tkn.getRow() == variable.getRow()) {
                    aux.add(tkn);
                }
            }
            if (!aux.isEmpty()) {
                rowsVariables.add(aux);
            }
        }

        for (ArrayList<Token> row : rowsVariables) {
            assignData(row);
        }

        // for (Variable var : variables) {
        // System.out.println(var.toString());
        // }
        // System.out.println("\n");
    }

    private void assignData(ArrayList<Token> row) {
        Variable var = new Variable("", TokenType.DESCONOCIDO, "", 0, "NO DECLARADA");
        TokenType type = TokenType.DESCONOCIDO;
        String expression = "";
        for (Token tkn : row) {
            if (row.indexOf(tkn) == 0) {
                type = tkn.getToken();
            }
        }
        if (type == TokenType.IDENTIFICADOR) {
            if (row.size() == 3) {
                for (Token tkn : row) {
                    if (row.indexOf(tkn) == 0) {
                        var.setState("DECLARADA");
                        var.setName(tkn.getLexeme());
                        var.setRow(tkn.getRow());
                    } else if (row.indexOf(tkn) == 2) {
                        var.setType(tkn.getToken());
                        var.setValue(tkn.getLexeme());
                    }
                }
            } else {
                System.out.println("Expression aritmetica");
                for (int i = 2; i < row.size(); i++) {
                    expression += row.get(i).getLexeme();
                }
                //Comprobar y remplazar numeros y variables
                for (Variable variable : variables) {
                    if (expression.contains(variable.getName())) {
                        // Validar operaciones aritmeticas
                    }
                }
                System.out.println(expression);
            }

        } else if (type == TokenType.FOR) {
            for (Token tkn : row) {
                if (row.indexOf(tkn) == 1) {
                    var.setState("DECLARADA");
                    var.setName(tkn.getLexeme());
                    var.setRow(tkn.getRow());
                    var.setType(TokenType.ITERADOR);
                    var.setValue("{}");
                }
            }
        }

        if (var.getType() == TokenType.DESCONOCIDO) {
            var.setState("NO DECLARADA");
            var.setValue("?");
            showError("[SEMANTICO] Variable no declarada", var.getRow());
        }

        variables.add(var);
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    private void showError(String errorMessage, int row) {
        console.setText(console.getText() + errorMessage + " en la linea " + row + "\n");
    }
}
