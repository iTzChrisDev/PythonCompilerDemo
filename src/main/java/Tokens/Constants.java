package Tokens;

import java.util.List;

public class Constants {

    public static final List<String> RESERVED_WORDS = List.of(
            "False", "None", "True", "as", "break", "class", "continue",
            "def", "del", "except", "finally", "from", "global", "import",
            "in", "is", "pass", "return", "print", "try", "yield",
            "with", "input", "range", "and", "or", "not");

    public static final List<String> CONTROL_STRUCT = List.of(
            "elif", "else", "for", "if", "while");

    public static final List<String> OPERATORS = List.of(
            "+", "-", "*", "%", "!", "/", "&", "|", "=", "==",
            "!=", "<", ">", "<=", ">=", "+=", "-=", "*=", "/=", "%=");

    public static final List<String> DELIMITERS = List.of(
            "(", ")", "[", "]", "{", "}", ",", ":", ";");

    public static final List<String> NUMBER_CHARS = List.of(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".");
}
