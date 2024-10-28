package SemanticOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextArea;
import Tokens.*;

public class ConditionalCheck {
    private ArrayList<Token> tokenList;
    private JTextArea console;

    public ConditionalCheck(JTextArea console, ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
        this.console = console;
    }

    public void check() {
        Map<String, ArrayList<Token>> conditions = new HashMap<>();

        int iteration = 0;
        for (int i = 0; i <= tokenList.size() - 3; i++) {
            Token token1 = tokenList.get(i);
            Token token2 = tokenList.get(i + 1);
            Token token3 = tokenList.get(i + 2);

            if (isValueToken(token1) && isRelationalOperator(token2) && isValueToken(token3)) {
                ArrayList<Token> tokens = new ArrayList<>();
                tokens.add(token1);
                tokens.add(token2);
                tokens.add(token3);
                String key = String.valueOf(token1.getRow()) + "-" + String.valueOf(iteration);
                conditions.put(key, tokens);
                iteration++;
            }
        }

        for (var entry : conditions.entrySet()) {
            int row = Integer.parseInt(String.valueOf(entry.getKey().toCharArray()[0]));
            TokenType tkn1 = entry.getValue().get(0).getToken();
            TokenType tkn2 = entry.getValue().get(2).getToken();
        
            if (tkn1.equals(TokenType.IDENTIFICADOR)) {
                for (Variable var : VariableAssignment.variables) {
                    if (var.getVarName().equals(entry.getValue().get(0).getLexeme())) {
                        tkn1 = var.getType();
                        break;
                    }
                }
            }
        
            if (tkn2.equals(TokenType.IDENTIFICADOR)) {
                for (Variable var : VariableAssignment.variables) {
                    if (var.getVarName().equals(entry.getValue().get(2).getLexeme())) {
                        tkn2 = var.getType();
                        break;
                    }
                }
            }
        
            if (!tkn1.equals(tkn2)) {
                showError("[SEMANTICO] Comparación entre diferentes tipos", row);
            }
        }        
    }

    private void showError(String errorMessage, int row) {
        console.setText(console.getText() + errorMessage + " en la linea " + row + "\n");
    }

    protected boolean isValueToken(Token tkn) {
        return tkn.getToken().equals(TokenType.IDENTIFICADOR)
                || tkn.getToken().equals(TokenType.ENTERO)
                || tkn.getToken().equals(TokenType.DECIMAL)
                || tkn.getToken().equals(TokenType.CADENA)
                || tkn.getToken().equals(TokenType.BOOLEAN);
    }

    protected boolean isRelationalOperator(Token tkn) {
        return tkn.getToken().equals(TokenType.IGUALDAD)
                || tkn.getToken().equals(TokenType.MAYOR_QUE)
                || tkn.getToken().equals(TokenType.MENOR_QUE)
                || tkn.getToken().equals(TokenType.MAYOR_IGUAL_QUE)
                || tkn.getToken().equals(TokenType.MENOR_IGUAL_QUE)
                || tkn.getToken().equals(TokenType.DIFERENCIA);
    }
}
