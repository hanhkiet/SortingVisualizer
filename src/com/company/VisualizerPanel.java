package com.company;

import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class VisualizerPanel extends JPanel {

    private JButton playButton;
    private JButton stopButton;

    private final int WIDTH = 1280, HEIGHT = 360;

    private Icon playIcon;
    private Icon stopIcon;

    private MainFrame parent;
    private int[] arr;

    private void getIcon() {
        playIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("./images/play-solid.png"))
                        .getImage().getScaledInstance(10, 12,
                                Image.SCALE_AREA_AVERAGING));

        stopIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("./images/stop-solid.png")).getImage()
                .getScaledInstance(10, 12, Image.SCALE_SMOOTH));
    }

    private void initialize() {
        getIcon();
        playButton = new JButton(playIcon);
        playButton.setFocusable(false);
        playButton.setBounds(WIDTH / 2 - 30, 30, 40, 40);
        stopButton = new JButton(stopIcon);
        stopButton.setFocusable(false);
        stopButton.setBounds(WIDTH / 2 + 30, 30, 40, 40);
        stopButton.setEnabled(false);

        initArray();

        add(playButton);
        add(stopButton);
    }

    public void setArr(int[] arr) {
        this.arr = arr;
        // initArray();
        // JButton button = new JButton("button");
        removeAll();
        initialize();
        repaint();
    }

    private void initArray() {
        if (arr != null && arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
                JButton button = new JButton(Integer.toString(arr[i]));
                button.setFocusable(false);
                button.setBounds(WIDTH / 2 - arr.length * 80 / 2 + i * 80, HEIGHT / 2, 60, 60);
                add(button);
            }
        }
    }

    public VisualizerPanel(MainFrame frame) {
        super();

        parent = frame;

        initialize();

        setLayout(null);

        TitledBorder border = BorderFactory.createTitledBorder("Minh họa thuật toán");
        border.setTitleFont(FontManager.titleFont);

        setBorder(border);
    }
}
