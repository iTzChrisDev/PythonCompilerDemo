package SemanticOperations;

import java.util.ArrayList;

import javax.swing.JTextArea;

import ParserOperations.Utilities;
import Tokens.Constants;
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
        console.setText("");
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

        for (Variable var : variables) {
            if (var.getType().equals(TokenType.NONE)) {
                var.setType(setVarType(var.getValue(), var.getRow()));
            }
        }
    }

    private TokenType setVarType(String expression, int row) {
        TokenType type = TokenType.DESCONOCIDO;
        char chars[] = expression.toCharArray();
        ArrayList<String> values = new ArrayList<>();
        String currentString = "";
        int contInt = 0, contFloat = 0, contBool = 0, contString = 0;

        for (char c : chars) {
            if (isOperator(c)) {
                values.add(String.valueOf(c));
                if (!currentString.isBlank()) {
                    values.add(currentString);
                    currentString = "";
                }
            } else {
                currentString += c;
            }
        }

        int index = 0;
        for (String val : values) {
            for (Variable variable : variables) {
                if (val.equals(variable.getName())) {
                    values.set(index, val.replace(val, variable.getValue()));
                    break;
                }
            }
            index++;
        }

        //VERIFICAR EL ESTILO DE ASIGNACION DE TIPO A CADA EXPRESION COMPLEJA
        
        // System.out.println("\nValores de la expresion");
        // for (String val : values) {
        // System.out.println(val);
        // }

        // for (String value : values) {
        //     if (value.contains("\"")) {
        //         contString++;
        //     } else if (value.equals("True") || value.equals("False")) {
        //         contBool++;
        //     } else {
        //         int aux = 0;
        //         int dots = 0;
        //         for (char c : value.toCharArray()) {
        //             if (Constants.NUMBER_CHARS.contains(String.valueOf(c))) {
        //                 if (c == '.') {
        //                     dots++;
        //                 }
        //                 aux++;
        //             }
        //         }

        //         if (aux != 0 && dots == 0) {
        //             contInt++;
        //         } else if (aux != 0 && dots > 0) {
        //             contFloat++;
        //         }
        //     }
        // }

        // if (contInt > 0 && contBool == 0 && contString == 0 && contFloat >= 0) {
        //     type = (contFloat == 0) ? TokenType.ENTERO : TokenType.DECIMAL;
        // } else if (contString > 0 && contBool == 0 && contFloat == 0 && contInt == 0) {
        //     type = TokenType.CADENA;
        // } else if (contBool > 0 && contString == 0 && contFloat == 0 && contInt == 0) {
        //     type = TokenType.BOOLEAN;
        // } else {
        //     type = TokenType.DESCONOCIDO;
        //     showError("[SEMANTICO] Asignación de diferentes tipos", row);
        // }

        return type;
    }

    private boolean isOperator(char c) {
        char operadores[] = { '+', '-', '*', '/', '(', ')' };
        boolean flag = false;
        for (char op : operadores) {
            if (c == op) {
                flag = true;
                break;
            }
        }
        return flag;
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
                // System.out.println("Expression aritmetica");
                for (int i = 2; i < row.size(); i++) {
                    var.setName(row.get(0).getLexeme());
                    var.setRow(row.get(0).getRow());
                    expression += row.get(i).getLexeme();
                }
                // Comprobar y remplazar numeros y variables
                for (Variable variable : variables) {
                    if (expression.contains(variable.getName())) {
                        // Validar operaciones aritmeticas
                        expression.replace(variable.getName(), variable.getValue());
                    }
                }
                if (expression.length() > 0) {
                    var.setType(TokenType.NONE);
                    var.setState("DECLARADA");
                    var.setValue(expression);
                }
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
            for (int i = 0; i < row.size(); i++) {
                if (row.size() == 2) {
                    var.setName(row.get(0).getLexeme());
                    var.setRow(row.get(0).getRow());
                }
            }
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
