package Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class FileManager {

    public static ArrayList<String> lines;
    private File file;

    public FileManager(File fileSelected) {
        lines = new ArrayList<>();
        file = fileSelected;
    }

    public void cargarArchivo(JTextArea txt) {
        String code = "";
        txt.setText("");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                code += line + "\n";
                line = br.readLine();
            }
            br.close();
            txt.setText(code);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
