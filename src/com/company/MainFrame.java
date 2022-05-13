package com.company;

import com.company.model.BubbleSortValue;
import com.company.model.QuickSortValue;
import com.company.model.SelectSortValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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
    private VisualizerPanel visualizerPanel;
    private FunctionPanel functionPanel;
    private AlgorithmsPanel algorithmsPanel;
    private CodePanel codePanel;
    private int[] arr;

    private JButton playButton;
    private JButton stopButton;
    private Icon playIcon;
    private Icon stopIcon;

    public void setArr(int[] newArr) {
        arr = newArr;
        visualizerPanel.setArr(arr);
    }

    public int[] getArr() {
        return arr;
    }

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(WIDTH, HEIGHT + 200));
        setMinimumSize(new Dimension(WIDTH, HEIGHT + 20));
        setPreferredSize(new Dimension(WIDTH, HEIGHT + 20));
        setLocationRelativeTo(null);
        setResizable(true);
        setBackground(ColorManager.BACKGROUND);
        setTitle("Sorting Visualizer");

        initialize();
    }

    private void initialize() {
        mainPanel = new JPanel();
        visualizerPanel = new VisualizerPanel(this);
        functionPanel = new FunctionPanel(this);
        codePanel = new CodePanel(this);
        algorithmsPanel = new AlgorithmsPanel(this);
        mainPanel.setLayout(null);
        mainPanel.setBackground(ColorManager.BACKGROUND);

        visualizerPanel.setBounds(0, 0, WIDTH - 13, HEIGHT / 2);
        mainPanel.add(visualizerPanel);

        functionPanel.setBounds(0, HEIGHT / 2, WIDTH / 3, HEIGHT / 2 - 18);
        mainPanel.add(functionPanel);

        algorithmsPanel.setBounds(WIDTH / 3, HEIGHT / 2, WIDTH * 2 / 9 + 1, HEIGHT / 2 - 18);
        mainPanel.add(algorithmsPanel);

        codePanel.setBounds(WIDTH * 5 / 9, HEIGHT / 2, WIDTH * 4 / 9 - 13, HEIGHT / 2 - 18);
        mainPanel.add(codePanel);

        add(mainPanel);

        // stopButton = new JButton(stopIcon);
        // stopButton.setFocusable(false);
        // stopButton.setBounds(60, 300, 40, 40);
        // stopButton.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // JOptionPane.showMessageDialog(null,
        // Arrays.toString(visualizerPanel.getArr()));
        // }
        // });

        // mainPanel.add(playButton);
        // mainPanel.add(stopButton);
    }

    public void animate() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread");
                try {
                    switch ("quicksort") {
                        case "quicksort": {
                            QuickSortValue data = (QuickSortValue) codePanel.next();
                            int count = 100;
                            //
                            while (data.getTypeAction() != "SORT_SUCCESS") {
                                int i = data.getI();
                                int j = data.getJ();
                                int time = 0;
                                switch (data.getTypeAction()) {
                                    case "SWAP_IJ": {
                                        if (i < j) {
                                            time = visualizerPanel.changePos(i, j);
                                        } else if (i > j) {
                                            time = visualizerPanel.changePos(j, i);
                                        }

                                        Thread.sleep(time + 200);
                                        break;
                                    }
                                    case "SWAP_IH": {
                                        int High = data.getHigh();
                                        time = visualizerPanel.changePos(i + 1, High);
                                        Thread.sleep(time + 200);
                                        break;
                                    }
                                    case "TARGER_PART": {
                                        int l = data.getLow();
                                        int h = data.getHigh();
                                        int pivot = data.getPivot();
                                        visualizerPanel.addHighlightTargetPart(l, h);
                                        Thread.sleep(1000);
                                        break;
                                    }
                                    case "PARTITION_SUCCESS": {
                                        int l = data.getLow();
                                        int h = data.getHigh();
                                        int pivot = data.getPivot();
                                        visualizerPanel.removeHighlightTargetPart(l, h);
                                        Thread.sleep(200);
                                        break;
                                    }

                                    default: {
                                        Thread.sleep(200);
                                        break;
                                    }

                                }
                                data = (QuickSortValue) codePanel.next();

                            }
                            visualizerPanel.removeText();
                            break;
                        }
                        case "bubblesort": {
                            BubbleSortValue data = (BubbleSortValue) codePanel.next();
                            int count = 100;
                            //
                            while (data.getTypeAction() != "SORT_SUCCESS") {
                                int i = data.getI();
                                int j = data.getJ();
                                int time = 0;
                                switch (data.getTypeAction()) {
                                    case "SWAP": {
                                        visualizerPanel.addHighlightSwapPart(j, j + 1);
                                        time = visualizerPanel.changePos(j, j + 1);
                                        Thread.sleep(time + 700);
                                        visualizerPanel.removeHighlightSwapPart(j, j + 1);
                                        data = (BubbleSortValue) codePanel.next();
                                        break;
                                    }

                                    default: {
                                        data = (BubbleSortValue) codePanel.next();
                                        Thread.sleep(time + 300);
                                        break;
                                    }
                                }
                            }
                            visualizerPanel.removeText();
                            break;
                        }

                        case "selectionsort": {
                            SelectSortValue data = (SelectSortValue) codePanel.next();
                            while (data.getTypeAction() != "SORT_SUCCESS") {
                                int i = data.getI();
                                int j = data.getMin();
                                int time = 0;
                                switch (data.getTypeAction()) {
                                    case "SWAP_I_MIN": {
                                        if (i > j) {
                                            visualizerPanel.addHighlightSwapPart(j, i);
                                            time = visualizerPanel.changePos(j, i);
                                            Thread.sleep(time + 700);
                                            visualizerPanel.removeHighlightSwapPart(j, i);
                                            // data = (SelectSortValue)codePanel.next();

                                        } else if (i < j) {
                                            visualizerPanel.addHighlightSwapPart(i, j);
                                            time = visualizerPanel.changePos(i, j);
                                            Thread.sleep(time + 700);
                                            // data = (SelectSortValue)codePanel.next();
                                            visualizerPanel.removeHighlightSwapPart(i, j);
                                        }
                                        break;
                                    }
                                    default: {
                                        Thread.sleep(300);
                                        break;
                                    }
                                }
                                data = (SelectSortValue) codePanel.next();
                            }

                            Thread.sleep(700);
                            visualizerPanel.removeText();
                            break;
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        });
        thread.start();
    }
}