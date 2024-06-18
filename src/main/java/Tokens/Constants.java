package Tokens;

import java.util.List;
import java.util.Map;

public class Constants {

    public static final Map<String, TokenType> RESERVED_WORDS = Map.ofEntries(
            Map.entry("False", TokenType.BOOLEAN_FALSE),
            Map.entry("None", TokenType.NONE),
            Map.entry("True", TokenType.BOOLEAN_TRUE),
            Map.entry("as", TokenType.AS),
            Map.entry("break", TokenType.BREAK),
            Map.entry("class", TokenType.CLASS),
            Map.entry("continue", TokenType.CONTINUE),
            Map.entry("def", TokenType.DEF),
            Map.entry("del", TokenType.DEL),
            Map.entry("except", TokenType.EXCEPT),
            Map.entry("finally", TokenType.FINALLY),
            Map.entry("from", TokenType.FROM),
            Map.entry("global", TokenType.GLOBAL),
            Map.entry("import", TokenType.IMPORT),
            Map.entry("in", TokenType.IN),
            Map.entry("is", TokenType.IS),
            Map.entry("pass", TokenType.PASS),
            Map.entry("return", TokenType.RETURN),
            Map.entry("print", TokenType.PRINT),
            Map.entry("try", TokenType.TRY),
            Map.entry("yield", TokenType.YIELD),
            Map.entry("with", TokenType.WITH),
            Map.entry("input", TokenType.INPUT),
            Map.entry("range", TokenType.RANGE),
            Map.entry("and", TokenType.AND),
            Map.entry("or", TokenType.OR),
            Map.entry("not", TokenType.NOT)
    );

    public static final Map<String, TokenType> CONTROL_STRUCTURES = Map.ofEntries(
            Map.entry("for", TokenType.FOR),
            Map.entry("while", TokenType.WHILE),
            Map.entry("if", TokenType.IF),
            Map.entry("else", TokenType.ELSE),
            Map.entry("elif", TokenType.ELIF),
            Map.entry("match", TokenType.MATCH),
            Map.entry("case", TokenType.CASE)
    );

    public static final Map<String, TokenType> OPERATORS = Map.ofEntries(
            Map.entry("+", TokenType.SUMA),
            Map.entry("-", TokenType.RESTA),
            Map.entry("*", TokenType.MULTIPLICACION),
            Map.entry("%", TokenType.MODULO),
            Map.entry("!", TokenType.NEGACION),
            Map.entry("/", TokenType.DIVISION),
            Map.entry("&", TokenType.AND),
            Map.entry("|", TokenType.OR),
            Map.entry("=", TokenType.ASIGNACION),
            Map.entry("==", TokenType.IGUALDAD),
            Map.entry("!=", TokenType.DIFERENCIA),
            Map.entry("<", TokenType.MENOR_QUE),
            Map.entry(">", TokenType.MAYOR_QUE),
            Map.entry("<=", TokenType.MENOR_IGUAL_QUE),
            Map.entry(">=", TokenType.MAYOR_IGUAL_QUE),
            Map.entry("+=", TokenType.SUMA_ASIGNACION),
            Map.entry("-=", TokenType.RESTA_ASIGNACION),
            Map.entry("*=", TokenType.MULTIPLICACION_ASIGNACION),
            Map.entry("/=", TokenType.DIVISION_ASIGNACION),
            Map.entry("%=", TokenType.MODULO_ASIGNACION)
    );

    public static final Map<String, TokenType> DELIMITERS = Map.ofEntries(
            Map.entry("(", TokenType.PARENTESIS_APERTURA),
            Map.entry(")", TokenType.PARENTESIS_CIERRE),
            Map.entry("[", TokenType.CORCHETE_APERTURA),
            Map.entry("]", TokenType.CORCHETE_CIERRE),
            Map.entry("{", TokenType.LLAVE_APERTURA),
            Map.entry("}", TokenType.LLAVE_CIERRE),
            Map.entry(",", TokenType.COMA),
            Map.entry(":", TokenType.DOS_PUNTOS),
            Map.entry(";", TokenType.PUNTO_Y_COMA)
    );

    public static final List<String> NUMBER_CHARS = List.of(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".");
}
