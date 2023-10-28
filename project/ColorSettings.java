import javax.swing.*;
import java.awt.*;

public class ColorSettings {
    public static void setForegroundColor(JTextArea textArea, Color color) {
        textArea.setForeground(color);
    }
    
    public static void setBackgroundColor(JTextArea textArea, Color color) {
        textArea.setBackground(color);
    }
}
