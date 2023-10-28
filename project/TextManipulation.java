
import javax.swing.*;
import javax.swing.undo.*;

public class TextManipulation {
    private UndoManager undoManager;
    private JTextArea textArea;
    public static void cut(JTextArea textArea) {
        textArea.cut();
    }
    
    public static void copy(JTextArea textArea) {
        textArea.copy();
    }
    
    public static void paste(JTextArea textArea) {
        textArea.paste();
    }
    
    public static void replace(JTextArea textArea, String searchText, String replaceText) {
        String text = textArea.getText();
        text = text.replaceAll(searchText, replaceText);
        textArea.setText(text);
    }

    public TextManipulation(JTextArea textArea) {
        this.textArea = textArea;
        this.undoManager = new UndoManager();

        // Add the undo manager to the text area
        textArea.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit()));
    }

    public void undo() {
        if (undoManager.canUndo()) {
            undoManager.undo();
        }
    }

    public void redo() {
        if (undoManager.canRedo()) {
            undoManager.redo();
        }
    }
}
