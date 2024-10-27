package ParserOperations;

import Tokens.Constants;
import Tokens.Token;
import Tokens.TokenType;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTextArea;

public class Parser {

    private Utilities tool;
    private CompoundStatements cs;
    private SimpleStatements ss;
    private IndentationParser ip;
    private ArrayList<Token> listIndent;

    public Parser(ArrayList<Token> tokenList, JTextArea console) {
        listIndent = new ArrayList<>();
        tool = new Utilities();
        cs = new CompoundStatements();
        ss = new SimpleStatements();
        ip = new IndentationParser(listIndent);
        tool.setIndex(0);
        tool.setCurrentIndent(1);
        tool.setLine(1);
        tool.setTokenList(tokenList);
        tool.setConsole(console);
        tool.clearConsole();
    }

    public void parseCode() {
        // System.out.println("\nInicio Ejecución");
        statements();
        ip.checkIndent();
        // System.out.println("Fin Ejecución");
    }

    public void statements() {

        // System.out.println(tool.getCurrentToken());
        listIndent.add(tool.getCurrentToken());
        if (cs.isCompoundStatement()) {
            // CompoundStatement correcto
            if (tool.getCurrentToken().getRow() != 1
                    && tool.getTokenList().get(tool.getIndex() - 1).getRow() != tool.getCurrentToken().getRow()) {
                statements();
            } else {
                if (tool.verifyToken(TokenType.DESCONOCIDO)) {
                    tool.showError("No se reconoce '" + tool.getCurrentToken().getLexeme() + "'");
                } else if (tool.getCurrentToken().getRow() != tool.getTokenList().get(tool.getTokenList().size() - 1)
                        .getRow()) {
                    tool.showError("Se esperaba un salto de linea");
                }
            }

        } else if (ss.isSimpleStatement()) {
            // SimpleStatement correcto
            if (tool.getCurrentToken().getRow() != 1
                    && tool.getTokenList().get(tool.getIndex() - 1).getRow() != tool.getCurrentToken().getRow()) {
                statements();
            } else {
                if (tool.verifyToken(TokenType.DESCONOCIDO)) {
                    tool.showError("No se reconoce '" + tool.getCurrentToken().getLexeme() + "'");
                } else if (tool.getCurrentToken().getRow() != tool.getTokenList().get(tool.getTokenList().size() - 1)
                        .getRow()) {
                    tool.showError("Se esperaba un salto de linea");
                }
            }

        } else if (tool.verifyToken(TokenType.DESCONOCIDO)) {
            tool.showError("No se reconoce '" + tool.getCurrentToken().getLexeme() + "'");
        } else if (!tool.verifyToken(TokenType.PUNTO_Y_COMA)) {
            checkTokensUnfound(Constants.DELIMITERS);
            checkTokensUnfound(Constants.OPERATORS);
            checkTokensUnfound(Constants.RESERVED_WORDS);
        }
    }

    private void checkTokensUnfound(Map<String, TokenType> tokenMap) {
        for (TokenType tkn : tokenMap.values()) {
            if (tkn == tool.getCurrentToken().getToken()) {
                tool.showError("Uso de '" + tool.getCurrentToken().getLexeme()
                        + "' incorrecto");
                tool.incrementIndex();
            }
        }
    }
}
