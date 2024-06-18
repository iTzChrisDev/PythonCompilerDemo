package Tokens;

import java.util.List;

public class Constants {

    public static final List<String> PALABRAS_RESERVADAS = List.of(
            "False", "None", "True", "as", "break", "class", "continue",
            "def", "del", "except", "finally", "from", "global", "import",
            "in", "is", "pass", "return", "print", "try", "yield",
            "with", "input", "range", "and", "or", "not"
    );

    public static final List<String> ESTRUCTURAS_CONTROL = List.of(
            "elif", "else", "for", "if", "while"
    );

    public static final List<String> OPERADORES = List.of(
            "+", "-", "*", "%", "!", "/", "&", "|", "=", "==",
            "!=", "<", ">", "<=", ">=", "+=", "-=", "*=", "/=", "%="
    );

    public static final List<String> DELIMITADORES = List.of(
            "(", ")", "[", "]", "{", "}", ",", ":", ";"
    );
}
