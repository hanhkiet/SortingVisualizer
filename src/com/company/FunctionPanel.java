package com.company;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class FunctionPanel extends JPanel {

    private JLabel label;

    private void initialize() {
        label = new JLabel("Số phần tử mảng");
        add(label);
    }

    public FunctionPanel(JFrame frame) {
        super();

        initialize();

        TitledBorder border = BorderFactory.createTitledBorder("Tùy chỉnh");
        border.setTitleFont(FontManager.titleFont);

        setBorder(border);
    }
}
