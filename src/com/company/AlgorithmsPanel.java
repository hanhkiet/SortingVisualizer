package com.company;

import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

public class AlgorithmsPanel extends JPanel {

    private ButtonGroup group = new ButtonGroup();

    private void initialize() {
        createRadioButton("Bubble sort");
        createRadioButton("Selection sort");
        createRadioButton("Merged sort");
        createRadioButton("Quick sort");
        createRadioButton("Radix sort");
    }

    private void createRadioButton(String title) {
        JRadioButton button = new JRadioButton(title, false);
        button.setMargin(new Insets(10, 20, 0, 0));
        button.setFont(FontManager.radioButtonFont);
        button.setOpaque(false);
        button.setFocusPainted(false);
        group.add(button);
        add(button);
    }

    public AlgorithmsPanel(JFrame frame) {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        TitledBorder border = BorderFactory.createTitledBorder("Thuật toán");
        border.setTitleFont(FontManager.titleFont);
        setBorder(border);

        initialize();
    }
}
