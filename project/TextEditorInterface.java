import javax.swing.*;
import java.awt.*;

public class TextEditorInterface {
    JFrame frame;
    JTextArea textArea;
    JMenuBar menuBar;
    JToolBar toolBar;
    JLabel statusBar;
    
    public TextEditorInterface() {
        // Create and configure the frame, text area, menu bar, tool bar, and status bar
        frame = new JFrame("Text Editor");
        textArea = new JTextArea();
        menuBar = new JMenuBar();
        toolBar = new JToolBar();
        statusBar = new JLabel("Line: 1, Col: 1");
        
        // Add components to the frame
        frame.setJMenuBar(menuBar);
        frame.add(toolBar, BorderLayout.PAGE_START);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(statusBar, BorderLayout.PAGE_END);
        
        // Configure the frame
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
