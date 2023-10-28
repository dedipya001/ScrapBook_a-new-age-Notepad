import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JToggleButton;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class FontStyling2 {
    private JTextComponent textComponent;
    private Map<TextAttribute, Object> fontAttributes;

    public FontStyling2(JTextComponent textComponent) {
        this.textComponent = textComponent;
        this.fontAttributes = new HashMap<TextAttribute, Object>();

        // Set default font attributes
        this.fontAttributes.put(TextAttribute.FAMILY, Font.SANS_SERIF);
        this.fontAttributes.put(TextAttribute.SIZE, 14);

        // Add bold, italic, and underline toggles
        JToggleButton boldToggle = new JToggleButton("B");
        JToggleButton italicToggle = new JToggleButton("I");
        JToggleButton underlineToggle = new JToggleButton("U");

        boldToggle.addActionListener(e -> {
            boolean isBold = boldToggle.isSelected();
            setFontAttribute(TextAttribute.WEIGHT, isBold ? TextAttribute.WEIGHT_BOLD : TextAttribute.WEIGHT_REGULAR);
        });

        italicToggle.addActionListener(e -> {
            boolean isItalic = italicToggle.isSelected();
            setFontAttribute(TextAttribute.POSTURE, isItalic ? TextAttribute.POSTURE_OBLIQUE : TextAttribute.POSTURE_REGULAR);
        });

        underlineToggle.addActionListener(e -> {
            boolean isUnderline = underlineToggle.isSelected();
            setFontAttribute(TextAttribute.UNDERLINE, isUnderline ? TextAttribute.UNDERLINE_ON : TextAttribute.UNDERLINE);
        });

        // Add the toggles to the text component's toolbar
        textComponent.add(boldToggle);
        textComponent.add(italicToggle);
        textComponent.add(underlineToggle);
    }

    private void setFontAttribute(TextAttribute attribute, Object value) {
        StyledDocument doc = (StyledDocument) textComponent.getDocument();
        int start = textComponent.getSelectionStart();
        int end = textComponent.getSelectionEnd();

        if (start == end) {
            // If no text is selected, apply attribute to next typed characters
            SimpleAttributeSet attr = new SimpleAttributeSet();
            attr.addAttribute(attribute, value);
            doc.setCharacterAttributes(start, 0, attr, true);
        } else {
            // Apply attribute to selected text
            MutableAttributeSet attr = new SimpleAttributeSet(doc.getCharacterElement(start).getAttributes());
            attr.addAttribute(attribute, value);
            doc.setCharacterAttributes(start, end - start, attr, true);
        }
    }
}
