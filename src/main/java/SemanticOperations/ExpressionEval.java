package SemanticOperations;

import java.util.Stack;

import javax.swing.JTextArea;

public class ExpressionEval {
    private static JTextArea console;
    private static int row;

    public ExpressionEval(JTextArea console, int row) {
        this.console = console;
        this.row = row;
    }

    public static double eval(String expresion) {
        Stack<Double> operandos = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        int i = 0;
        while (i < expresion.length()) {
            char c = expresion.charAt(i);

            if (Character.isWhitespace(c)) {
                i++; // Ignorar espacios en blanco
                continue;
            }

            if (Character.isDigit(c) || c == '.') {
                // Leer el número completo (incluyendo decimales)
                StringBuilder numero = new StringBuilder();
                while (i < expresion.length() &&
                        (Character.isDigit(expresion.charAt(i)) || expresion.charAt(i) == '.')) {
                    numero.append(expresion.charAt(i));
                    i++;
                }
                operandos.push(Double.parseDouble(numero.toString())); // Empujar el número completo
                continue; // Saltar al siguiente carácter
            }

            if (c == '(') {
                operadores.push(c); // Empujar paréntesis de apertura
            } else if (c == ')') {
                // Evaluar todo hasta encontrar el paréntesis de apertura
                while (operadores.peek() != '(') {
                    aplicarOperacion(operandos, operadores.pop());
                }
                operadores.pop(); // Eliminar '('
            } else if (esOperador(c)) {
                // Procesar operadores con mayor o igual precedencia
                while (!operadores.isEmpty() &&
                        operadores.peek() != '(' &&
                        precedencia(operadores.peek()) >= precedencia(c)) {
                    aplicarOperacion(operandos, operadores.pop());
                }
                operadores.push(c); // Empujar el operador actual
            }
            i++;
        }

        // Evaluar las operaciones restantes
        while (!operadores.isEmpty()) {
            aplicarOperacion(operandos, operadores.pop());
        }

        return operandos.pop(); // El resultado final
    }

    // Aplica una operación usando los operandos de la pila
    private static void aplicarOperacion(Stack<Double> operandos, char operador) {
        double b = operandos.pop();
        double a = operandos.pop();
        switch (operador) {
            case '+' -> operandos.push(a + b);
            case '-' -> operandos.push(a - b);
            case '*' -> operandos.push(a * b);
            case '/' -> {
                if (b == 0) {
                    showError("División por cero"); // Muestra el error
                    throw new IllegalArgumentException("División por cero"); // Lanza la excepción
                }
                operandos.push(a / b); // Solo se ejecuta si b no es 0

            }
            default -> {
                showError("Operador no soportado: " + operador); // Muestra el mensaje de error
                throw new IllegalArgumentException("Operador no soportado: " + operador); // Lanza la excepción
            }

        }
    }

    private static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedencia(char operador) {
        return switch (operador) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> -1;
        };
    }

    public static String evalStrings(String expresion) {
        StringBuilder resultado = new StringBuilder();
        StringBuilder parteActual = new StringBuilder();
        boolean inQuote = false;

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (c == '"') {
                inQuote = !inQuote;
                if (!inQuote) {
                    String cadena = parteActual.toString();
                    resultado.append(cadena);
                    parteActual.setLength(0);
                }
            } else if (c == '+' && !inQuote) {
                continue;
            } else if (inQuote) {
                parteActual.append(c);
            }
        }

        return resultado.toString();
    }

    private static void showError(String errorMessage) {
        console.setText(console.getText() + errorMessage + " en la linea " + row + "\n");
    }
}
