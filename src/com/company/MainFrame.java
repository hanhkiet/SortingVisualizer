package com.company;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    private static final int WIDTH = 1280, HEIGHT = 720;
    private JPanel mainPanel;
    private JPanel visualizePanel;
    private JPanel functionPanel;
    private JPanel algorithmsPanel;
    private JPanel codePanel;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(WIDTH, HEIGHT + 200));
        setMinimumSize(new Dimension(WIDTH, HEIGHT + 20));
        setPreferredSize(new Dimension(WIDTH, HEIGHT + 20));
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(ColorManager.BACKGROUND);
        setTitle("Sorting Visualizer");

        initialize();
    }

    private void initialize() {
        mainPanel = new JPanel();
        visualizePanel = new JPanel();
        functionPanel = new JPanel();
        codePanel = new JPanel();
        algorithmsPanel = new AlgorithmsPanel(this);

        mainPanel.setLayout(null);
        mainPanel.setBackground(ColorManager.BACKGROUND);

        visualizePanel.setBounds(0, 0, WIDTH, HEIGHT / 2);
        visualizePanel.setBackground(ColorManager.BAR_CYAN);
        mainPanel.add(visualizePanel);

        functionPanel.setBounds(0, HEIGHT / 2, WIDTH / 3, HEIGHT / 2);
        functionPanel.setBackground(ColorManager.BAR_ORANGE);
        mainPanel.add(functionPanel);

        algorithmsPanel.setBounds(WIDTH / 3, HEIGHT / 2, WIDTH * 2 / 9, HEIGHT / 2);
        algorithmsPanel.setBackground(ColorManager.BAR_WHITE);
        mainPanel.add(algorithmsPanel);

        codePanel.setBounds(WIDTH * 5 / 9, HEIGHT / 2, WIDTH * 4 / 9, HEIGHT / 2);
        codePanel.setBackground(ColorManager.BAR_RED);
        mainPanel.add(codePanel);

        add(mainPanel);
        pack();
    }
}