package com.company;

import java.awt.*;


import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
    private JLabel iText,jText,pavotText;
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

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    quickSort(arr,0,arr.length-1 );
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }


            }
        });

        stopButton = new JButton(stopIcon);
        stopButton.setFocusable(false);
        stopButton.setBounds(WIDTH / 2 + 30, 30, 40, 40);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, Arrays.toString(arr));
            }
        });

        add(playButton);
        add(stopButton);
        iText =  new JLabel();
        jText = new JLabel();

    }


    void quickSort(int[] arr, int low, int high) throws InterruptedException {
        if (low < high)
        {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    int partition(int[] arr, int low, int high)
    {
            int[] arr1 = arr;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    int i = (low - 1);
                    int pivot = arr[high];
                    try{
                        for(int j = low; j <= high - 1; j++)
                        {
                            if (arr[j] < pivot)
                            {
                                i++;
                                int temp = arr[i];
                                arr[i] = arr[j];
                                arr[j] = temp;
                                int time = changePos(buttons[j], buttons[j + 1]);
                                JButton temp1 = buttons[i];
                                buttons[i] = buttons[j];
                                buttons[j] = temp1;
                                Thread.sleep(time * 10);
                            }
                        }
                        int temp = arr[i+1];
                        arr[i+1] = arr[high];
                        arr[high] = temp;
                        int time = changePos(buttons[i+1], buttons[high]);
                        JButton temp1 = buttons[i+1];
                        buttons[i+1] = buttons[high];
                        buttons[high] = temp1;
                        Thread.sleep(time * 15);

                    }
                    catch (Exception ex){

                    }
                }
            });
            thread.start();
            int i = (low - 1);
            int pivot = arr1[high];
            for(int j = low; j <= high - 1; j++)
            {
                if (arr1[j] < pivot)
                {
                    i++;
                }
            }
            return (i + 1);
    }
    public void setArr(int[] arr) {
        if(buttons != null) {
            for (int i = 0; i < buttons.length; i++) {
                remove(buttons[i]);
            }
        }
        this.arr = arr;
         initArray();
        // JButton button = new JButton("button");
//        initialize();
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
    }

    private int changePos(JButton b1,JButton b2){
        int x1 = b1.getX();
        int speed= 3;
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
                        timer1.schedule(task1,0,speed);
                        timer.cancel();
                    }

                }
            };
            timer.schedule(task,0,speed);


        return Math.abs(x2 - x1) *speed * 2 + Math.abs(x2 - x1) * speed;
    }

    private void addText(JButton bt,JLabel lb,String name, int index,int height){
        lb.setText(name + '=' + Integer.toString(index));
        lb.setBounds(bt.getX() + 20,bt.getY()+height,40,40);
    }
}
