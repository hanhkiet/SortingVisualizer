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
    private Color HIGHLIGHT_COLOR = new Color(245, 207, 103);
    private final int WIDTH = 1280, HEIGHT = 360;

    private Icon playIcon;
    private Icon stopIcon;
    private CodePanel codePanel;
    private MainFrame parent;
    private int[] arr;
    private JButton[] buttons,countBtns,outBtns;
    private JLabel iText,jText,pavotText;
    private JLabel[] countText;
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
        iText = new JLabel();
        jText = new JLabel();
        pavotText = new JLabel();
        add(iText);
        add(jText);
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



    public void setArr(int[] arr) {
        if(buttons != null) {
            for (int i = 0; i < buttons.length; i++) {
                remove(buttons[i]);
            }
        }
        this.arr = arr;
        initArrayRadixSort();
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
                buttons[i].setBounds(WIDTH / 2 - arr.length * 80 / 2 + i * 80, HEIGHT / 2, 50, 50);
                add(buttons[i]);
            }
        } else {
            buttons = null;
        }
    }


    private void initArrayRadixSort() {
        if (arr != null && arr.length > 0) {
            arr = new int[] { 10, 80, 30, 90, 40, 50, 70 };
            buttons = new JButton[arr.length];
            countBtns = new JButton[10];
            outBtns = new JButton[arr.length];
            countText = new JLabel[10];
            for (int i = 0; i < arr.length; i++) {
                buttons[i] = new JButton(Integer.toString(arr[i]));
                outBtns[i] = new JButton();
                buttons[i].setFocusable(false);
                buttons[i].setBounds(WIDTH / 2 - arr.length * 80 / 2 + i * 80, HEIGHT / 2, 50, 50);
                outBtns[i].setBounds(WIDTH / 2 - arr.length * 80 / 2 + i * 80, HEIGHT / 2-150, 50, 50);
                add(buttons[i]);
                add(outBtns[i]);
            }
            for (int i = 0; i <10; i++){
                countBtns[i] = new JButton("0");
                countText[i] = new JLabel();
                add(countText[i]);
                countBtns[i].setFocusable(false);
                countBtns[i].setBounds(WIDTH / 2 - arr.length * 80 / 2 + i * 80, HEIGHT / 2-80, 50, 50);
                addText(countBtns[i],countText[i],"",i,40,20);
                countText[i].setText(Integer.toString(i));
                add(countBtns[i]);
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

    public void addHighlightTargetPart(int start, int end){
        for (int i = start; i <= end; i++){
            buttons[i].setBackground(HIGHLIGHT_COLOR);
        }
        if (start >= 0 && end < buttons.length && end >= 0 && start <= end){
            addText(buttons[start],iText,"low",start,-40,10);
            if (start == end){
                addText(buttons[end],jText,"high",end,-30,10);
            }
            else
                addText(buttons[end],jText,"high",end,-40,10);
        }

    }
    public void removeHighlightTargetPart(int start, int end){
        for (int i = start; i <= end; i++){
            buttons[i].setBackground(new JButton().getBackground());
        }
        iText.setText("");
        jText.setText("");

    }

    public void removeHighlightSwapPart(int i,int j){
        buttons[i].setBackground(new JButton().getBackground());
        buttons[j].setBackground(new JButton().getBackground());
        iText.setText("");
        jText.setText("");
    }

    public void addHighlightSwapPart(int i,int j){
        buttons[i].setBackground(HIGHLIGHT_COLOR);
        buttons[j].setBackground(HIGHLIGHT_COLOR);
        addText(buttons[i],iText,"i",i,-40,20);
        addText(buttons[j],jText,"j",j,-40,20);
    }
    public void addText(JButton bt,JLabel lb,String name, int index,int height,int width){
        lb.setText(name + '=' + Integer.toString(index));
        lb.setBounds(bt.getX() + width,bt.getY()+height,40,40);

    }


    public void highlightBtn(int index){

    }
    public void removeText(){
        remove(iText);
        remove(jText);
        remove(pavotText);
    }


}
