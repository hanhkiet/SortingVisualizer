package com.company;

import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

public class AlgorithmsPanel extends JPanel {

    private ButtonGroup group = new ButtonGroup();

    private MainFrame parent;

    AlgorithmsEnum selectedAlgorithm = null;

    private void initialize() {
        createRadioButton("Bubble sort", AlgorithmsEnum.BUBBLE_SORT);
        createRadioButton("Selection sort", AlgorithmsEnum.SELECTION_SORT);
        createRadioButton("Merged sort", AlgorithmsEnum.MERGE_SORT);
        createRadioButton("Quick sort", AlgorithmsEnum.QUICK_SORT);
        createRadioButton("Radix sort", AlgorithmsEnum.RADIX_SORT);
    }

    public AlgorithmsEnum getSelectedAlgorithm() {
        return selectedAlgorithm;
    }

    private void createRadioButton(String title, AlgorithmsEnum algorithm) {
        JRadioButton button = new JRadioButton(title, false);
        button.setMargin(new Insets(10, 20, 0, 0));
        button.setFont(FontManager.radioButtonFont);
        button.setOpaque(false);
        button.setFocusPainted(false);

        button.addActionListener(l -> {
            selectedAlgorithm = algorithm;
            parent.getCodePanel().changeAlgorithm(algorithm);
        });

        group.add(button);
        add(button);
    }

    public AlgorithmsPanel(MainFrame frame) {
        super();

        parent = frame;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        TitledBorder border = BorderFactory.createTitledBorder("Thuật toán");
        border.setTitleFont(FontManager.titleFont);
        setBorder(border);

        initialize();
    }
}
