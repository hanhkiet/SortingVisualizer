package com.company;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class AlgorithmsPanel extends JPanel {

    private ButtonGroup group = new ButtonGroup();

    private void initialize() {
        JRadioButton bubbleButton = new JRadioButton("Bubble sort", false);
        group.add(bubbleButton);
        add(bubbleButton);

        JRadioButton selectionButton = new JRadioButton("Selection sort", false);
        group.add(selectionButton);
        add(selectionButton);

        JRadioButton mergeButton = new JRadioButton("Merge sort", false);
        group.add(mergeButton);
        add(mergeButton);

        JRadioButton quickButton = new JRadioButton("Quick sort", false);
        group.add(quickButton);
        add(quickButton);

        JRadioButton radixButton = new JRadioButton("Radix sort", false);
        group.add(radixButton);
        add(radixButton);
    }

    public AlgorithmsPanel(JFrame frame) {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initialize();
    }
}
