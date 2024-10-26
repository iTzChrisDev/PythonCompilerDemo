// package SemanticOperations;

// import java.util.ArrayList;
// import javax.swing.JTextArea;
// import Tokens.Constants;
// import Tokens.Token;
// import Tokens.TokenType;

// public class VariableCheck {

//     private ArrayList<Token> tokenList;
//     private ArrayList<ArrayList<Token>> rowsVariables;
//     private ArrayList<Variable> varAux;
//     private ArrayList<Variable> variables;
//     private JTextArea console;
//     private int contInt, contFloat, contBool, contString, plus, minus, div, multi;

//     public VariableCheck(ArrayList<Token> tokenList, JTextArea console) {
//         this.tokenList = tokenList;
//         this.console = console;
//     }

//     public void check() {
//         console.setText("");
//         varAux = new ArrayList<>();
//         variables = new ArrayList<>();
//         rowsVariables = new ArrayList<>();

//         for (Token token : tokenList) {
//             if (token.getToken() == TokenType.IDENTIFICADOR) {
//                 boolean exists = false;

//                 for (Variable variable : varAux) {
//                     if (variable.getVarName().equals(token.getLexeme())) {
//                         exists = true;
//                         break;
//                     }
//                 }

//                 if (!exists) {
//                     // varAux.add(new Variable(token.getLexeme(), TokenType.IDENTIFICADOR, "null", token.getRow(),
//                             // "NO DECLARADA"));
//                 }
//             }
//         }

//         for (Variable variable : varAux) {
//             ArrayList<Token> aux = new ArrayList<>();
//             for (Token tkn : tokenList) {
//                 if (tkn.getRow() == variable.getRow()) {
//                     aux.add(tkn);
//                 }
//             }
//             if (!aux.isEmpty()) {
//                 rowsVariables.add(aux);
//             }
//         }

//         for (ArrayList<Token> row : rowsVariables) {
//             assignData(row);
//         }

//         for (Variable var : variables) {
//             if (var.getType().equals(TokenType.NONE)) {
//                 var.setType(setVarType(var.getValue(), var.getRow()));
//             }
//         }
//     }

//     private TokenType setVarType(String expression, int row) {
//         TokenType type = TokenType.DESCONOCIDO;
//         char chars[] = expression.toCharArray();
//         ArrayList<String> values = new ArrayList<>();
//         String currentString = "";
//         contInt = 0;
//         contFloat = 0;
//         contBool = 0;
//         contString = 0;
//         plus = 0;
//         minus = 0;
//         div = 0;
//         multi = 0;

//         for (char c : chars) {
//             if (isOperator(c)) {
//                 if (!currentString.isBlank()) {
//                     values.add(currentString);
//                     currentString = "";
//                 }
//                 values.add(String.valueOf(c)); // Agrega el operador
//             } else {
//                 currentString += c;
//             }
//         }

//         // Agregar el último valor en currentString si es que hay algo
//         if (!currentString.isBlank()) {
//             values.add(currentString);
//         }

//         int index = 0;
//         for (String val : values) {
//             for (Variable variable : variables) {
//                 if (val.equals(variable.getVarName())) {
//                     values.set(index, val.replace(val, variable.getValue()));
//                     break;
//                 }
//             }
//             index++;
//         }

//         // System.out.println(values.toString());
//         // System.out.println("\n");

//         for (String value : values) {
//             if (value.contains("\"")) {
//                 contString++;
//             } else if (value.equals("True") || value.equals("False")) {
//                 contBool++;
//             } else {
//                 int aux = 0;
//                 int dots = 0;
//                 for (char c : value.toCharArray()) {
//                     if (Constants.NUMBER_CHARS.contains(String.valueOf(c))) {
//                         if (c == '.') {
//                             dots++;
//                         }
//                         aux++;
//                     }
//                 }

//                 if (aux != 0 && dots == 0) {
//                     contInt++;
//                 } else if (aux != 0 && dots > 0) {
//                     contFloat++;
//                 }
//             }

//             switch (value) {
//                 case "+":
//                     plus++;
//                     break;
//                 case "-":
//                     minus++;
//                     break;
//                 case "*":
//                     multi++;
//                     break;
//                 case "/":
//                     div++;
//                     break;

//             }
//         }

//         if (checkIntOrFloat()) {
//             type = (contFloat == 0) ? TokenType.ENTERO : TokenType.DECIMAL;
//         } else if (checkString()) {
//             type = TokenType.CADENA;
//             checkOperators(type, row);
//         } else if (checkBoolean()) {
//             type = TokenType.BOOLEAN;
//             checkOperators(type, row);
//         } else {
//             type = TokenType.DESCONOCIDO;
//             showError("[SEMANTICO] Asignación de diferentes tipos", row);
//         }

//         return type;
//     }

//     private void checkOperators(TokenType type, int row) {
//         if (minus > 0 || multi > 0 || div > 0) {
//             type = TokenType.DESCONOCIDO;
//             showError("Operacion compuesta invalida", row);
//         }
//     }

//     private boolean checkIntOrFloat() {
//         return contInt > 0 && contBool == 0 && contString == 0 && contFloat >= 0;
//     }

//     private boolean checkString() {
//         return contString > 0 && contBool == 0 && contFloat == 0 && contInt == 0;
//     }

//     private boolean checkBoolean() {
//         return contBool > 0 && contString == 0 && contFloat == 0 && contInt == 0;
//     }

//     private boolean isOperator(char c) {
//         char operadores[] = { '+', '-', '*', '/', '(', ')' };
//         for (char op : operadores) {
//             if (c == op) {
//                 return true;
//             }
//         }
//         return false;
//     }

//     private void assignData(ArrayList<Token> row) {
//         // Variable var = new Variable("", TokenType.DESCONOCIDO, "", 0, "NO DECLARADA");
//         TokenType type = TokenType.DESCONOCIDO;
//         String expression = "";
//         for (Token tkn : row) {
//             if (row.indexOf(tkn) == 0) {
//                 type = tkn.getToken();
//             }
//         }
//         if (type == TokenType.IDENTIFICADOR) {
//             if (row.size() == 3) {
//                 for (Token tkn : row) {
//                     if (row.indexOf(tkn) == 0) {
//                         // var.setState("DECLARADA");
//                         var.setVarName(tkn.getLexeme());
//                         var.setRow(tkn.getRow());
//                     } else if (row.indexOf(tkn) == 2) {
//                         var.setType(tkn.getToken());
//                         var.setValue(tkn.getLexeme());
//                     }
//                 }
//             } else {
//                 // Expresiones aritmeticas y concatenacion de Strings
//                 for (int i = 2; i < row.size(); i++) {
//                     var.setVarName(row.get(0).getLexeme());
//                     var.setRow(row.get(0).getRow());
//                     expression += row.get(i).getLexeme();
//                 }
//                 // Comprobar y remplazar numeros y variables
//                 for (Variable variable : variables) {
//                     if (expression.contains(variable.getVarName())) {
//                         // Validar operaciones aritmeticas
//                         System.out.println("VarExp: " + var.getRow());
//                         System.out.println("VarList: " + variable.getRow());
//                         expression.replace(variable.getVarName(), variable.getValue());
//                     }
//                 }
//                 if (expression.length() > 0) {
//                     var.setType(TokenType.NONE);
//                     // var.setState("DECLARADA");
//                     var.setValue(expression);
//                 }
//             }

//         } else if (type == TokenType.FOR) {
//             for (Token tkn : row) {
//                 if (row.indexOf(tkn) == 1) {
//                     // var.setState("DECLARADA");
//                     var.setVarName(tkn.getLexeme());
//                     var.setRow(tkn.getRow());
//                     var.setType(TokenType.ITERADOR);
//                     var.setValue("{}");
//                 }
//             }
//         }

//         if (var.getType() == TokenType.DESCONOCIDO) {
//             for (int i = 0; i < row.size(); i++) {
//                 if (row.size() == 2) {
//                     var.setVarName(row.get(0).getLexeme());
//                     var.setRow(row.get(0).getRow());
//                 }
//             }
//             // var.setState("NO DECLARADA");
//             var.setValue("?");
//             showError("[SEMANTICO] Variable no declarada", var.getRow());
//         }
//         variables.add(var);
//     }

//     public ArrayList<Variable> getVariables() {
//         return variables;
//     }

//     private void showError(String errorMessage, int row) {
//         console.setText(console.getText() + errorMessage + " en la linea " + row + "\n");
//     }
// }
