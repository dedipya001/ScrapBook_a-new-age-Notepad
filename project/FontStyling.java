import javax.swing.*;
import java.awt.font.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;

public class FontStyling {
    public static void setFontFamily(JTextArea textArea, String fontFamily) {
        Font font = textArea.getFont();
        Font newFont = new Font(fontFamily, font.getStyle(), font.getSize());
        textArea.setFont(newFont);
    }
    
    public static void setFontSize(JTextArea textArea, int fontSize) {
        Font font = textArea.getFont();
        Font newFont = new Font(font.getFontName(), font.getStyle(), fontSize);
        textArea.setFont(newFont);
    }
}
