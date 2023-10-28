import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;
import java.io.*;

public class FileIO {
    private static File currentFile;

    public void openFile(JTextComponent textComponent) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                textComponent.read(br, null);
                br.close();
                currentFile = file;
            } catch (IOException ex) {
                System.out.println("Error opening file: " + ex.getMessage());
            }
        }
    }

    public static void saveFile(JTextComponent textComponent) {
        if (currentFile == null) {
            saveFileAs(textComponent);
        } else {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(currentFile));
                textComponent.write(bw);
                bw.close();
            } catch (IOException ex) {
                System.out.println("Error saving file: " + ex.getMessage());
            }
        }
    }

    public static void saveFileAs(JTextComponent textComponent) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                textComponent.write(bw);
                bw.close();
                currentFile = file;
            } catch (IOException ex) {
                System.out.println("Error saving file: " + ex.getMessage());
            }
        }
    }

    public static File getOpenedFile() {
        return currentFile;
    }
}
