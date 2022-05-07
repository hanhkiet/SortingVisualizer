package com.company;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.border.TitledBorder;
public class VisualizerPanel extends JPanel {

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

    private void initialize() {
        getIcon();
        playButton = new JButton(playIcon);
        stopButton = new JButton(stopIcon);
        playButton.setBounds(10,50,20,20);
        stopButton.setBounds(100,50,20,20);
        add(playButton);
        add(stopButton);
    }

    public VisualizerPanel(JFrame frame) {
        super();
        setLayout(null);
        initialize();

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
                if (b1.getY() - y1 < b1.getHeight() + 5){
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
