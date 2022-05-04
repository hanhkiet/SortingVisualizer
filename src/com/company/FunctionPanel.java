package com.company;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class FunctionPanel extends JPanel {

    private JLabel label;
    private JTextField textField;
    private JButton generateButton;
    private JButton deleteButton;

    private void initialize() {
        label = new JLabel("Số phần tử mảng");
        textField = new JTextField();
        textField.setColumns(10);

        generateButton = new JButton("Tạo mảng");
        deleteButton = new JButton("Xóa mảng");

        add(label);
        add(textField);
        add(generateButton);
        add(deleteButton);
    }

    public FunctionPanel(JFrame frame) {
        super();

        initialize();

        TitledBorder border = BorderFactory.createTitledBorder("Tùy chỉnh");
        border.setTitleFont(FontManager.titleFont);

        setBorder(border);
    }
}
