package com.company;

import java.awt.Image;


import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.border.TitledBorder;
public class VisualizerPanel extends JPanel {

    private JButton playButton;
    private JButton stopButton;

    private final int WIDTH = 1280, HEIGHT = 360;

    private Icon playIcon;
    private Icon stopIcon;

    private MainFrame parent;
    private int[] arr;
    private JButton[] buttons;

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
            buttons = new JButton[arr.length];
            for (int i = 0; i < arr.length; i++) {
                buttons[i] = new JButton(Integer.toString(arr[i]));
                buttons[i].setFocusable(false);
                buttons[i].setBounds(WIDTH / 2 - arr.length * 80 / 2 + i * 80, HEIGHT / 2, 60, 60);
                add(buttons[i]);
            }
        } else {
            buttons = null;
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

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePos(playButton,stopButton);
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePos(stopButton,playButton);
            }
        });
    }

    private void changePos(JButton b1,JButton b2){
        int x1 = b1.getX();
        int y1 = b1.getY();
        int x2 = b2.getX();
        int y2 = b2.getY();
        java.util.Timer timer = new java.util.Timer();
        java.util.Timer timer1 = new java.util.Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                b1.setBounds(b1.getX(),b1.getY()-1, b1.getWidth(),b1.getHeight());
                b2.setBounds(b2.getX(),b2.getY()+1, b2.getWidth(),b2.getHeight());
                if (b1.getY() == y2){
                    timer1.cancel();
                }

            }
        };
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (b1.getY() - y1 < b1.getHeight() + 3){
                    b1.setBounds(b1.getX(),b1.getY()+1, b1.getWidth(),b1.getHeight());
                    b2.setBounds(b2.getX(),b2.getY()-1, b2.getWidth(),b2.getHeight());
                }else{
                    b1.setBounds(b1.getX()+1,b1.getY(), b1.getWidth(),b1.getHeight());
                    b2.setBounds(b2.getX()-1,b2.getY(), b2.getWidth(),b2.getHeight());
                }
                if (b1.getX() == x2){
                    timer1.schedule(task1,0,15);
                    timer.cancel();
                }

            }
        };
        timer.schedule(task,0,15);
    }
}
