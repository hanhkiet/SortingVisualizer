package com.company;

import com.company.codeCpn.BBSort;
import com.company.model.BubbleSortValue;

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
    private CodePanel codePanel;
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
//        getIcon();
//        playButton = new JButton(playIcon);
//        playButton.setFocusable(false);
//        playButton.setBounds(WIDTH / 2 - 30, 30, 40, 40);
//        stopButton = new JButton(stopIcon);
//        stopButton.setFocusable(false);
//        stopButton.setBounds(WIDTH / 2 + 30, 30, 40, 40);
//        stopButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, buttons[0].getX() + ":" + buttons[1].getX());
//            }
//        });
//
//        add(playButton);
//        add(stopButton);
//        iText =  new JLabel();
//        jText = new JLabel();

    }


    public void setAnimation(BubbleSortValue bubbleSortValue){
            if (bubbleSortValue.getTypeAction() != "SWAP") return;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try{
                        playButton.setEnabled(false);
                        pavotText = new JLabel();
                        pavotText.setBounds(buttons[0].getX(),buttons[0].getY() - 100,40,40);
                        add(iText);
                        add(jText);
                        add(pavotText);
                        int temp1 = arr[bubbleSortValue.getI()];
                        arr[bubbleSortValue.getJ()] = arr[bubbleSortValue.getI()];
                        arr[bubbleSortValue.getI()] = temp1;
                        int time = changePos(bubbleSortValue.getI(), bubbleSortValue.getJ());
                        JButton tempx = buttons[bubbleSortValue.getJ()];
                        buttons[bubbleSortValue.getJ()] = buttons[bubbleSortValue.getI()];
                        buttons[bubbleSortValue.getI()] = tempx;
                        Thread.sleep(time);
                        playButton.setEnabled(true);
                        remove(iText);
                        remove(jText);
                        remove(pavotText);
                    }
                    catch (Exception ex){

                    }
                }
            });
            thread.start();

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
            arr = new int[] { 10, 80, 30, 90, 40, 50, 70 };
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

    public void swap(int a,int b){
        JButton temp = buttons[a];
        buttons[a] = buttons[b];
        buttons[b] = temp;

    }

    public int[] getArr(){
        return arr;
    }
    public int changePos(int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        int x1 = buttons[i].getX();
        int speed= 3;
        int y1 = buttons[i].getY();
        int x2 = buttons[j].getX();
        int y2 = buttons[j].getY();
        java.util.Timer timer = new java.util.Timer();
        java.util.Timer timer1 = new java.util.Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                buttons[i].setBounds(buttons[i].getX(),buttons[i].getY()-1, buttons[i].getWidth(),buttons[i].getHeight());
                buttons[j].setBounds(buttons[j].getX(),buttons[j].getY()+1, buttons[j].getWidth(),buttons[j].getHeight());
                if (buttons[i].getY() == y2){
                    timer1.cancel();
                    swap(i,j);
                }


            }
        };
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (buttons[i].getY() - y1 < buttons[i].getHeight() + 5){
                    buttons[i].setBounds(buttons[i].getX(),buttons[i].getY()+1, buttons[i].getWidth(),buttons[i].getHeight());
                    buttons[j].setBounds(buttons[j].getX(),buttons[j].getY()-1, buttons[j].getWidth(),buttons[j].getHeight());
                }else{
                    buttons[i].setBounds(buttons[i].getX()+1,buttons[i].getY(), buttons[i].getWidth(),buttons[i].getHeight());
                    buttons[j].setBounds(buttons[j].getX()-1,buttons[j].getY(), buttons[j].getWidth(),buttons[j].getHeight());
                }
                if (buttons[i].getX() == x2){
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
