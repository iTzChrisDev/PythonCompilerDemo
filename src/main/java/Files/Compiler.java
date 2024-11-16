package Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JTextArea;

public class Compiler {

    private String code;
    private JTextArea console;

    public Compiler(String code, JTextArea txtConsole) {
        this.code = code;
        this.console = txtConsole;
    }

    public void compileCode() {
        String pythonFilePath = "./src/main/java/Files/code.py";

        // Crear .py
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pythonFilePath))) {
            writer.write(code);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo .py: " + e.getMessage());
            return;
        }

        ProcessBuilder processBuilder = new ProcessBuilder("python", pythonFilePath);
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();

            // Leer y mostrar la salida del script de python
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                console.append("--- INICIO DE EJECUCION ---\n");
                while ((line = reader.readLine()) != null) {
                    console.append(line + "\n");
                }
                console.append("--- FIN DE EJECUCION ---\n");
            }
        } catch (IOException e) {
            System.err.println("Error al ejecutar el script Python: " + e.getMessage());
        } finally {
            new File(pythonFilePath).delete();
        }
    }
}
