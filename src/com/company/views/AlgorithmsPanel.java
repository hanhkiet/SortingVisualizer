package com.company.views;

import java.awt.Insets;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import com.company.utils.AlgorithmsEnum;
import com.company.utils.FontManager;

public class AlgorithmsPanel extends JPanel {

    private ButtonGroup group = new ButtonGroup();

    private MainFrame parent;

    AlgorithmsEnum selectedAlgorithm = AlgorithmsEnum.NOT_SELECTED;

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

    public void setEnabledWhenAnimating(boolean enabled) {
        Enumeration<AbstractButton> enumeration = group.getElements();
        while (enumeration.hasMoreElements()) {
            enumeration.nextElement().setEnabled(enabled);
        }
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

            if (parent.getArr() != null) {
                parent.getVisualizerPanel().reload();
            }
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
