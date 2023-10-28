import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

class FindAndReplace {
    
    private JTextComponent textComponent;
    private static JDialog Dialog;


    public FindAndReplace(JTextComponent textComponent) {
        this.textComponent = textComponent;
    }
    
    public static void showFindAndReplaceDialog() {
        if (Dialog == null) {
            Dialog = new JDialog();
            Dialog.setTitle("Find / Replace");
            Dialog.setSize(400, 150);
            Dialog.setLocationRelativeTo(null);
            Dialog.setLayout(new GridLayout(3, 1));
        }
        Dialog.setVisible(true);
    }

    public void find() {
        String searchString = JOptionPane.showInputDialog(null, "Enter text to find:", "Find", JOptionPane.PLAIN_MESSAGE);
        if (searchString != null && searchString.length() > 0) {
            String text = textComponent.getText();
            int index = text.indexOf(searchString);
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Text not found.", "Find", JOptionPane.INFORMATION_MESSAGE);
            } else {
                textComponent.select(index, index + searchString.length());
            }
        }
    }
    
    public void replace() {
        String searchString = JOptionPane.showInputDialog(null, "Enter text to find:", "Replace", JOptionPane.PLAIN_MESSAGE);
        if (searchString != null && searchString.length() > 0) {
            String text = textComponent.getText();
            int index = text.indexOf(searchString);
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Text not found.", "Replace", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String replaceString = JOptionPane.showInputDialog(null, "Replace with:", "Replace", JOptionPane.PLAIN_MESSAGE);
    if (replaceString != null) {
        
        textComponent.replaceSelection(replaceString);
    }
    }
    }
    }
    
    
    public void replaceAll() {
        String searchString = JOptionPane.showInputDialog(null, "Enter text to find:", "Replace All", JOptionPane.PLAIN_MESSAGE);
        if (searchString != null && searchString.length() > 0) {
            String replaceString = JOptionPane.showInputDialog(null, "Replace with:", "Replace All", JOptionPane.PLAIN_MESSAGE);
            if (replaceString != null) {
                String text = textComponent.getText();
                text = text.replaceAll(searchString, replaceString);
                textComponent.setText(text);
            }
        }
    }
    }