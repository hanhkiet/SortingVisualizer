package com.company;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class CodePanel extends JPanel {

    public CodePanel(JFrame frame) {
        super();

        TitledBorder border = BorderFactory.createTitledBorder("Code");
        border.setTitleFont(FontManager.titleFont);

        setBorder(border);
    }
}
