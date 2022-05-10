package com.company;

import com.company.model.BubbleSortValue;
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

    private void getIcon() {
        playIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("./images/play-solid.png"))
                        .getImage().getScaledInstance(10, 12,
                                Image.SCALE_AREA_AVERAGING));

        stopIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("./images/stop-solid.png")).getImage()
                .getScaledInstance(10, 12, Image.SCALE_SMOOTH));
    }

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

        start();
    }

    private void initialize() {
        mainPanel = new JPanel();
        visualizerPanel = new VisualizerPanel(this);
        functionPanel = new FunctionPanel(this);
        codePanel = new CodePanel(this);
        algorithmsPanel = new AlgorithmsPanel(this);
        mainPanel.setLayout(null);
        mainPanel.setBackground(ColorManager.BACKGROUND);

        // mainPanel.add(codePanel);
        // codePanel.setBounds(0, 0, WIDTH, HEIGHT);
        // codePanel.setBackground(ColorManager.BAR_RED);

        visualizerPanel.setBounds(0, 0, WIDTH - 13, HEIGHT / 3);
        // visualizePanel.setBackground(ColorManager.BAR_CYAN);
        mainPanel.add(visualizerPanel);

        functionPanel.setBounds(0, HEIGHT / 2, WIDTH / 3+ WIDTH / 6, HEIGHT / 2 - 18);
        // functionPanel.setBackground(ColorManager.BAR_ORANGE);
        mainPanel.add(functionPanel);

        algorithmsPanel.setBounds(WIDTH / 3, HEIGHT / 2, WIDTH * 2 / 9 + 1, HEIGHT / 2 - 18);
        // algorithmsPanel.setBackground(ColorManager.BAR_WHITE);
        mainPanel.add(algorithmsPanel);

        codePanel.setBounds(WIDTH * 5 / 9, HEIGHT / 2, WIDTH * 4 / 9 - 13, HEIGHT / 2 - 18);
        //codePanel.setBackground(ColorManager.BAR_RED);
        mainPanel.add(codePanel);

        add(mainPanel);

        getIcon();
        playButton = new JButton(playIcon);
        playButton.setFocusable(false);
        playButton.setBounds(10,300, 40, 40);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try{
                            playButton.setEnabled(false);
                            SelectSortValue data = (SelectSortValue)codePanel.next();
                            int count = 100;
                            //
                            while(data.getTypeAction() != "SORT_SUCCESS") {
                                if (data.getTypeAction() == "SWAP_I_MIN"){
                                    int i = data.getI();
                                    int j = data.getMin();
                                    int time = 0;
                                    if ( i < j) {
                                        time = visualizerPanel.changePos(i, j);
                                    }

                                    Thread.sleep(time);
                                }
                                data = (SelectSortValue)codePanel.next();

                            }

                            playButton.setEnabled(true);
                        }
                        catch (Exception ex){

                        }
                    }
                });
                thread.start();
            }
        });

        stopButton = new JButton(stopIcon);
        stopButton.setFocusable(false);
        stopButton.setBounds(60,300, 40, 40);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, Arrays.toString(visualizerPanel.getArr()));
            }
        });


        pack();
        mainPanel.add(playButton);
        mainPanel.add(stopButton);
    }


    public void start(){


    }
}