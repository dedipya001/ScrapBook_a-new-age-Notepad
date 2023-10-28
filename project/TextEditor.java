import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Font.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Map;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;
import javax.swing.text.*;

public class TextEditor implements ActionListener {

    private JFrame frame;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu fontMenu;
    private JMenu colorMenu;
    private JMenu helpMenu;
    private JMenuItem About;
    private JMenuItem newFile;
    private JMenuItem openFile;
    private JMenuItem saveFile;
    private JMenuItem saveAsFile;
    private JMenuItem exit;
    private JMenuItem undo;
    private JMenuItem redo;
    private JMenuItem cut;
    private JMenuItem copy;
    private JMenuItem paste;
    private JMenuItem selectAll;
    private JMenuItem find;
    private JMenuItem replace;
    private JMenuItem replaceAll;

    private JMenuItem fontfamily;
    private JMenuItem fontsize;
    private JRadioButtonMenuItem fontArial;
    private JRadioButtonMenuItem fontTimesNewRoman;
    private JRadioButtonMenuItem fontCourier;
    private JRadioButtonMenuItem fontCalibri;
    private JRadioButtonMenuItem fontComicSans;
    private JRadioButtonMenuItem fontAlgerian;
    private JRadioButtonMenuItem fontSize8;
    private JRadioButtonMenuItem fontSize12;
    private JRadioButtonMenuItem fontSize16;
    private JRadioButtonMenuItem fontSize20;
    private JRadioButtonMenuItem fontSize24;
    private JRadioButtonMenuItem fontSize28;
    private JRadioButtonMenuItem fontSize32;
    private JRadioButtonMenuItem fontSize36;
    private JCheckBoxMenuItem fontBold;
    private JCheckBoxMenuItem fontItalic;
    private JCheckBoxMenuItem fontUnderline;
    private JMenuItem fontColor;
    private JMenuItem backgroundColor;
    private JMenuItem changeThemeItem;
    private JPanel statusBar;
    private JLabel statusLabel;
    private UndoManager undoManager;

    public TextEditor() {
        frame = new JFrame("Text Editor");
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        textArea = new JTextArea();
        /*
         * try {
         * UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
         * } catch (Exception e) {
         * e.printStackTrace();
         * }
         */
        /* 
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        */
        // UIManager.put("MenuBar.background", Color.LIGHT_GRAY);
        //UIManager.put("MenuBar.border", BorderFactory.createEmptyBorder(3, 3, 3, 3));
        // UIManager.put("Menu.background", Color.LIGHT_GRAY);
        // UIManager.put("Menu.foreground", Color.WHITE);
        // UIManager.put("Menu.selectionBackground", Color.DARK_GRAY);
        // UIManager.put("Menu.selectionForeground", Color.WHITE);
        // UIManager.put("Menu.border", BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // UIManager.put("MenuItem.background", Color.LIGHT_GRAY);
        // UIManager.put("MenuItem.foreground", Color.WHITE);
        // UIManager.put("MenuItem.selectionBackground", Color.DARK_GRAY);
        // UIManager.put("MenuItem.selectionForeground", Color.WHITE);

        textArea.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                int position = e.getDot();
                statusLabel.setText("Cursor Position: " + position);
                statusBar.add(statusLabel, BorderLayout.WEST);
            }
        });

        statusBar = new JPanel(new BorderLayout());
        statusBar.setPreferredSize(new Dimension(200, 30));

        // Create the status bar label
        statusLabel = new JLabel("Ready");
        statusBar.add(statusLabel, BorderLayout.WEST);

        // Add the status bar to the bottom of the main JFrame
        frame.add(statusBar, BorderLayout.SOUTH);
        frame.add(scrollPane);
        updateTime();

        // Create a timer to update the status bar every second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();
        /*
         * dialog = new JDialog(frame, "Find and Replace", true);
         * dialog.getContentPane().add(frame, BorderLayout.CENTER);
         * dialog.getContentPane().add(replaceAll, BorderLayout.SOUTH);
         * dialog.pack();
         * dialog.setLocationRelativeTo(frame);
         */

        // Initialize the JTextArea and add it to the center of the main JFrame
        frame.setTitle("SCraP BooK");
        frame.setLocationRelativeTo(null);
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(500, 40));
        fileMenu = new JMenu("File");
        fileMenu.setPreferredSize(new Dimension(100, 30));
        fileMenu.setFont(new Font("Times New Roman", Font.BOLD, 20));
        fileMenu.setBorder(BorderFactory.createRaisedBevelBorder());
        fileMenu.setHorizontalAlignment(SwingConstants.RIGHT);
        editMenu = new JMenu("Edit");
        editMenu.setPreferredSize(new Dimension(100, 30));
        editMenu.setFont(new Font("Times New Roman", Font.BOLD, 20));
        editMenu.setBorder(BorderFactory.createRaisedBevelBorder());
        fontMenu = new JMenu("Font");
        fontMenu.setPreferredSize(new Dimension(100, 30));
        fontMenu.setFont(new Font("Times New Roman", Font.BOLD, 20));
        fontMenu.setBorder(BorderFactory.createRaisedBevelBorder());
        colorMenu = new JMenu("Color");
        colorMenu.setPreferredSize(new Dimension(100, 30));
        colorMenu.setFont(new Font("Times New Roman", Font.BOLD, 20));
        colorMenu.setBorder(BorderFactory.createRaisedBevelBorder());
        helpMenu = new JMenu("Help");
        helpMenu.setPreferredSize(new Dimension(100, 30));
        helpMenu.setFont(new Font("Times New Roman", Font.BOLD, 20));
        helpMenu.setBorder(BorderFactory.createRaisedBevelBorder());
        About = new JMenuItem("About ScrapBook",new ImageIcon(new ImageIcon("Icons/about.png")
        .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        newFile = new JMenuItem("New", new ImageIcon(new ImageIcon("Icons/new.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        openFile = new JMenuItem("Open", new ImageIcon(new ImageIcon("Icons/open.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        saveFile = new JMenuItem("Save", new ImageIcon(new ImageIcon("Icons/save.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        saveAsFile = new JMenuItem("Save As", new ImageIcon(new ImageIcon("Icons/save as.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        exit = new JMenuItem("Exit", new ImageIcon(new ImageIcon("Icons/exit.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        undo = new JMenuItem("Undo", new ImageIcon(new ImageIcon("Icons/undo.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        redo = new JMenuItem("Redo", new ImageIcon(new ImageIcon("Icons/redo.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        cut = new JMenuItem("Cut", new ImageIcon(new ImageIcon("Icons/cut.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        copy = new JMenuItem("Copy", new ImageIcon(new ImageIcon("Icons/copy.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        paste = new JMenuItem("Paste", new ImageIcon(new ImageIcon("Icons/paste.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        selectAll = new JMenuItem("Select All", new ImageIcon(new ImageIcon("Icons/select.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        find = new JMenuItem("Find",new ImageIcon(new ImageIcon("Icons/find.png")
        .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        replace = new JMenuItem("Replace",new ImageIcon(new ImageIcon("Icons/replace.png")
        .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        replaceAll = new JMenuItem("ReplaceAll",new ImageIcon(new ImageIcon("Icons/replaceall.png")
        .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));

        fontfamily = new JMenuItem("Font Family", new ImageIcon(new ImageIcon("Icons/fontstyle.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        fontArial = new JRadioButtonMenuItem("Arial");
        fontTimesNewRoman = new JRadioButtonMenuItem("Times New Roman");
        fontCourier = new JRadioButtonMenuItem("Courier");
        fontCalibri = new JRadioButtonMenuItem("Calibri");
        fontComicSans = new JRadioButtonMenuItem("ComicSans");
        fontAlgerian = new JRadioButtonMenuItem("Algerian");
        fontsize = new JMenuItem("Font Size", new ImageIcon(new ImageIcon("Icons/fontsize.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        fontSize8 = new JRadioButtonMenuItem("8");
        fontSize12 = new JRadioButtonMenuItem("12");
        fontSize16 = new JRadioButtonMenuItem("16");
        fontSize20 = new JRadioButtonMenuItem("20");
        fontSize24 = new JRadioButtonMenuItem("24");
        fontSize28 = new JRadioButtonMenuItem("28");
        fontSize32 = new JRadioButtonMenuItem("32");
        fontSize36 = new JRadioButtonMenuItem("36");
        fontBold = new JCheckBoxMenuItem("Bold", new ImageIcon(new ImageIcon("Icons/bold.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        fontItalic = new JCheckBoxMenuItem("Italic", new ImageIcon(new ImageIcon("Icons/italic.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        fontUnderline = new JCheckBoxMenuItem("Underline", new ImageIcon(new ImageIcon("Icons/underline.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        fontColor = new JMenuItem("Font Color", new ImageIcon(new ImageIcon("Icons/fontcolour.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        backgroundColor = new JMenuItem("Background Color", new ImageIcon(new ImageIcon("Icons/backgroundcolor.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));

        undoManager = new UndoManager();
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        saveAsFile.addActionListener(this);
        exit.addActionListener(this);
        undo.addActionListener(this);
        redo.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);

        selectAll.addActionListener(this);
        fontArial.addActionListener(this);
        fontTimesNewRoman.addActionListener(this);
        fontCourier.addActionListener(this);
        fontCalibri.addActionListener(this);
        fontComicSans.addActionListener(this);
        fontAlgerian.addActionListener(this);
        fontSize8.addActionListener(this);
        fontSize12.addActionListener(this);
        fontSize16.addActionListener(this);
        fontSize20.addActionListener(this);
        fontSize24.addActionListener(this);
        fontSize28.addActionListener(this);
        fontSize32.addActionListener(this);
        fontSize36.addActionListener(this);
        fontBold.addActionListener(this);
        fontItalic.addActionListener(this);
        fontUnderline.addActionListener(this);
        fontColor.addActionListener(this);
        backgroundColor.addActionListener(this);
        //find.addActionListener(e -> FindAndReplace.showFindAndReplaceDialog());
        //replace.addActionListener(e -> FindAndReplace.showFindAndReplaceDialog());
       // replaceAll.addActionListener(e -> FindAndReplace.showFindAndReplaceDialog());

       changeThemeItem = new JMenuItem("Change Theme",new ImageIcon(new ImageIcon("Icons/theme.png")
       .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        changeThemeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JDialog themeDialog = new JDialog();
                JPanel contentPanel = new JPanel(new BorderLayout());

                // create combo box with available themes
                String[] themes = {"Metal", "Nimbus", "Windows", "Windows Classic", "Motif"};
                JComboBox<String> themeBox = new JComboBox<>(themes);

                // create label for combo box
                JLabel themeLabel = new JLabel("Select Theme:");

                // create panel for combo box and label
                JPanel themePanel = new JPanel(new FlowLayout());
                themePanel.add(themeLabel);
                themePanel.add(themeBox);

                // create OK button
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // set look and feel based on selected theme
                        String selectedTheme = (String) themeBox.getSelectedItem();
                        try {
                            switch (selectedTheme) {
                                case "Metal":
                                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                                    break;
                                case "Nimbus":
                                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                                    break;
                                case "Windows":
                                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                                    break;
                                case "Windows Classic":
                                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                                    break;
                                case "Motif":
                                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                                    break;
                            }
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                            ex.printStackTrace();
                        }

                        // update UI for new look and feel
                        SwingUtilities.updateComponentTreeUI(frame);
                        themeDialog.dispose();
                    }
                });

                // create cancel button
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        themeDialog.dispose();
                    }
                });

                // create panel for buttons
                JPanel buttonPanel = new JPanel(new FlowLayout());
                buttonPanel.add(okButton);
                buttonPanel.add(cancelButton);

                // add components to content panel
                contentPanel.add(themePanel, BorderLayout.NORTH);
                contentPanel.add(buttonPanel, BorderLayout.SOUTH);

                // set content pane for dialog
                themeDialog.setContentPane(contentPanel);
                themeDialog.pack();
                themeDialog.setLocationRelativeTo(frame);
                themeDialog.setVisible(true);
            }
        });

        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveAsFile);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        editMenu.add(undo);
        editMenu.add(redo);
        editMenu.addSeparator();
        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);
        editMenu.addSeparator();
        //editMenu.add(find);
        //editMenu.add(replace);
        //editMenu.add(replaceAll);
        editMenu.addSeparator();
        editMenu.add(selectAll);
        fontMenu.add(fontfamily);
        
        fontMenu.add(fontArial);
        fontMenu.add(fontTimesNewRoman);
        fontMenu.add(fontCourier);
        fontMenu.add(fontCalibri);
        fontMenu.add(fontComicSans);
        fontMenu.add(fontAlgerian);

        fontMenu.addSeparator();
        fontMenu.add(fontsize);
        
        fontMenu.add(fontSize8);
        fontMenu.add(fontSize12);
        fontMenu.add(fontSize16);
        fontMenu.add(fontSize20);
        fontMenu.add(fontSize24);
        fontMenu.add(fontSize28);
        fontMenu.add(fontSize32);
        fontMenu.add(fontSize36);
        fontMenu.addSeparator();
        fontMenu.add(fontBold);
        fontMenu.add(fontItalic);
        fontMenu.add(fontUnderline);

        colorMenu.add(fontColor);
        colorMenu.add(backgroundColor);
        colorMenu.add(changeThemeItem);

        helpMenu.add(About);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(fontMenu);
        menuBar.add(colorMenu);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);
        frame.add(new JScrollPane(textArea));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newFile) {
            textArea.setText("");
        } else if (e.getSource() == openFile) {
            FileIO fileIO = new FileIO();
            fileIO.openFile(textArea);
        } else if (e.getSource() == saveFile) {
            FileIO fileIO = new FileIO();
            File file = FileIO.getOpenedFile();
            if (file != null) {
                FileIO.saveFile(textArea);
            } else {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    FileIO.saveFile(textArea);
                }
            }
        } else if (e.getSource() == saveAsFile) {
            FileIO fileIO = new FileIO();
            fileIO.saveFile(textArea);
        } else if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == undo) {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        } else if (e.getSource() == redo) {
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
        } else if (e.getSource() == cut) {
            textArea.cut();
        } else if (e.getSource() == copy) {
            textArea.copy();
        } else if (e.getSource() == paste) {
            textArea.paste();
        } else if (e.getSource() == selectAll) {
            textArea.selectAll();
        } else if (e.getSource() == fontArial) {
            Font font = new Font("Arial", Font.PLAIN, textArea.getFont().getSize());
            textArea.setFont(font);
        } else if (e.getSource() == fontTimesNewRoman) {
            Font font = new Font("Times New Roman", Font.PLAIN, textArea.getFont().getSize());
            textArea.setFont(font);
        } else if (e.getSource() == fontCourier) {
            Font font = new Font("Courier", Font.PLAIN, textArea.getFont().getSize());
            textArea.setFont(font);
        } else if (e.getSource() == fontCalibri) {
            Font font = new Font("Calibri", Font.PLAIN, textArea.getFont().getSize());
            textArea.setFont(font);
        } else if (e.getSource() == fontComicSans) {
            Font font = new Font("ComicSans", Font.PLAIN, textArea.getFont().getSize());
            textArea.setFont(font);
        } else if (e.getSource() == fontAlgerian) {
            Font font = new Font("Algerian", Font.PLAIN, textArea.getFont().getSize());
            textArea.setFont(font);
        } else if (e.getSource() == fontSize8) {
            Font font = textArea.getFont().deriveFont(8f);
            textArea.setFont(font);
        } else if (e.getSource() == fontSize12) {
            Font font = textArea.getFont().deriveFont(12f);
            textArea.setFont(font);
        } else if (e.getSource() == fontSize16) {
            Font font = textArea.getFont().deriveFont(16f);
            textArea.setFont(font);
        } else if (e.getSource() == fontSize20) {
            Font font = textArea.getFont().deriveFont(20f);
            textArea.setFont(font);
        } else if (e.getSource() == fontSize24) {
            Font font = textArea.getFont().deriveFont(24f);
            textArea.setFont(font);
        } else if (e.getSource() == fontSize28) {
            Font font = textArea.getFont().deriveFont(28f);
            textArea.setFont(font);
        } else if (e.getSource() == fontSize32) {
            Font font = textArea.getFont().deriveFont(32f);
            textArea.setFont(font);
        } else if (e.getSource() == fontSize36) {
            Font font = textArea.getFont().deriveFont(36f);
            textArea.setFont(font);
        } else if (e.getSource() == fontBold) {
            Font currentFont = textArea.getFont();
            int style = currentFont.getStyle();
            if ((style & Font.BOLD) == Font.BOLD) {
                textArea.setFont(currentFont.deriveFont(style & ~Font.BOLD));
            } else {
                textArea.setFont(currentFont.deriveFont(style | Font.BOLD));
            }
        } else if (e.getSource() == fontItalic) {
            Font currentFont = textArea.getFont();
            int style = currentFont.getStyle();
            if ((style & Font.ITALIC) == Font.ITALIC) {
                textArea.setFont(currentFont.deriveFont(style & ~Font.ITALIC));
            } else {
                textArea.setFont(currentFont.deriveFont(style | Font.ITALIC));
            }
        } else if (e.getSource() == fontUnderline) {
            Font currentFont = textArea.getFont();
            Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) currentFont.getAttributes();
            int underline = attributes.containsKey(TextAttribute.UNDERLINE)
                    ? (int) attributes.get(TextAttribute.UNDERLINE)
                    : -1;
            attributes.put(TextAttribute.UNDERLINE, underline == -1 ? TextAttribute.UNDERLINE_ON : -1);
            textArea.setFont(currentFont.deriveFont(attributes));
        } else if (e.getSource() == fontColor) {
            Color color = JColorChooser.showDialog(null, "Choose Font Color", textArea.getForeground());
            if (color != null) {
                textArea.setForeground(color);
            }
        } else if (e.getSource() == backgroundColor) {
            Color color = JColorChooser.showDialog(null, "Choose Background Color", textArea.getBackground());
            if (color != null) {
                textArea.setBackground(color);
            }
        }

    }

    private void updateTime() {
        String dateTimeString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(" dd-MM-yyyy    HH:mm:ss "));
        statusLabel.setText(dateTimeString);
        statusBar.add(statusLabel, BorderLayout.EAST);

    }

    /*
     * public void showDialog() {
     * dialog.setVisible(true);
     * }
     */
    public static void main(String[] args) {
        new TextEditor();

    }
}
