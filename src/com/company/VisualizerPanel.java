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
    private Color ACCEPT_COLOR = new Color(68, 140, 89);
    private Color DENY_COLOR = new Color(255, 64, 26);
    private final int WIDTH = 1280, HEIGHT = 360;

    private Icon playIcon;
    private Icon stopIcon;
    private CodePanel codePanel;
    private MainFrame parent;
    private int[] arr;
    private JButton[] buttons, countBtns, outBtns, leftBtns, rightBtns;
    private JLabel iText, jText, pivotText,statusText;
    private JLabel[] countText;
    private JLabel iIndex,jIndex,maxVal,minVal,iVal,jVal,sortName,status,minIndex;
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
        pivotText = new JLabel();
        statusText = new JLabel();
        add(iText);
        add(jText);
        add(pivotText);
        add(statusText);
        getIcon();
        playButton = new JButton(playIcon);
        playButton.setFocusable(false);
        playButton.setBounds(10, HEIGHT - 60, 40, 40);
        playButton.addActionListener(l -> {
            System.out.println("animation");
            parent.animate();
        });

        stopButton = new JButton(stopIcon);
        stopButton.setFocusable(false);
        stopButton.setBounds(70, HEIGHT - 60, 40, 40);

        add(playButton);
        add(stopButton);

    }

    public void setArr(int[] arr, int type) {
        if (buttons != null) {
            for (int i = 0; i < buttons.length; i++) {
                remove(buttons[i]);
            }
        }
        this.arr = arr;
        switch (type){
            case 1:{
                initArray();
                break;
            }
            case 2:{
                initArrayRadixSort();
                break;
            }
        }
        repaint();
    }

    private void initArray() {
        if (arr != null && arr.length > 0) {
            arr = new int[] { 11, 83, 32, 35, 19, 95, 47 };
            buttons = new JButton[arr.length];
            for (int i = 0; i < arr.length; i++) {
                buttons[i] = new JButton(Integer.toString(arr[i]));
                buttons[i].setFocusable(false);
                buttons[i].setBounds(WIDTH / 2 - arr.length * 80 / 2 + i * 80, HEIGHT / 2 -40, 50, 50);
                add(buttons[i]);
            }
            initBtnsArrays();
        } else {
            buttons = null;
        }
        //quickSortInit();
        //bubbleSortInit();

    }

    private void initArrayRadixSort() {
        if (arr != null && arr.length > 0) {
            arr = new int[] { 11, 83, 32, 35, 19, 95, 47 };
            buttons = new JButton[arr.length];
            countBtns = new JButton[10];
            outBtns = new JButton[arr.length];
            countText = new JLabel[10];
            for (int i = 0; i < arr.length; i++) {
                buttons[i] = new JButton(Integer.toString(arr[i]));
                outBtns[i] = new JButton();
                buttons[i].setFocusable(false);
                buttons[i].setBounds(WIDTH / 2 - arr.length * 80 / 2 + i * 80, HEIGHT / 2, 50, 50);
                outBtns[i].setBounds(WIDTH / 2 - arr.length * 80 / 2 + i * 80, HEIGHT / 2 - 150, 50, 50);
                add(buttons[i]);
                add(outBtns[i]);
            }
            for (int i = 0; i < 10; i++) {
                countBtns[i] = new JButton("0");
                countText[i] = new JLabel();
                add(countText[i]);
                countBtns[i].setFocusable(false);
                countBtns[i].setBounds(WIDTH / 2 - arr.length * 80 / 2 + i * 80, HEIGHT / 2 - 80, 50, 50);
                addText(countBtns[i], countText[i], "", i, 40, 20);
                countText[i].setText(Integer.toString(i));
                add(countBtns[i]);
            }
        } else {
            buttons = null;
        }
        radixSortInit();
    }

    //bubble sort
    private void bubbleSortInit(){
        sortName = new JLabel("Bubble Sort Visualization");
        iIndex = new JLabel("i = 0");
        jIndex = new JLabel("j = 0");
        iVal = new JLabel();
        jVal = new JLabel();
        status = new JLabel();
        sortName.setBounds(15,40,200,20);
        iIndex.setBounds(15,60,100,20);
        jIndex.setBounds(15,80,100,20);
        iVal.setBounds(15,100,100,20);
        jVal.setBounds(15,120,100,20);
        status.setBounds(15,140,300,20);
        add(sortName);
        add(status);
        add(iIndex);
        add(jIndex);
        add(iVal);
        add(jVal);
    }

    public void updateBubbleSort(int i, int j){
        iIndex.setText("i = " + Integer.toString(i));
        jIndex.setText("j = " + Integer.toString(j));
        iVal.setText("arr[j] = " + Integer.toString(arr[j]));
        jVal.setText("arr[j+1] = " + Integer.toString(arr[j+1]));
        if (arr[j] > arr[j+1]) status.setText("Swap arr[j] and arr[j+1]");
        else status.setText("");
    }

    //selection sort
    private void selectionSortInit(){
        sortName = new JLabel("Selection Sort Visualization");
        iIndex = new JLabel("i = 0");
        jIndex = new JLabel("j = 0");
        minIndex = new JLabel("min = 0");
        iVal = new JLabel("arr[i] = " + Integer.toString(arr[0]));
        jVal = new JLabel("arr[min] = " + Integer.toString(arr[0]));
        status = new JLabel();
        sortName.setBounds(15,40,200,20);
        iIndex.setBounds(15,60,100,20);
        jIndex.setBounds(15,80,100,20);
        minIndex.setBounds(15,100,100,20);
        iVal.setBounds(15,120,100,20);
        jVal.setBounds(15,140,100,20);
        add(sortName);
        add(iIndex);
        add(jIndex);
        add(minIndex);
        add(iVal);
        add(jVal);
    }

    public void updateSelectionSort(int i, int j, int min){
        iIndex.setText("i = " + Integer.toString(i));
        jIndex.setText("j = " + Integer.toString(j));
        minIndex.setText("min = " + Integer.toString(min));
        iVal.setText("arr[i] = " + Integer.toString(arr[i]));
        jVal.setText("arr[min] = " + Integer.toString(arr[min]));
    }

    //Quicksort
    private void quickSortInit(){
        sortName = new JLabel("Quicksort Visualization");
        iIndex = new JLabel("low = 0");
        jIndex = new JLabel("high = 0");
        minIndex = new JLabel("pivot = 0");
        iVal = new JLabel("arr[pivot] = " + Integer.toString(arr[0]));
        //JOptionPane.showMessageDialog(null,Arrays.toString(arr));
        //jVal = new JLabel("arr[min] = " + Integer.toString(arr[0]));
        status = new JLabel();
        sortName.setBounds(15,40,200,20);
        iIndex.setBounds(15,60,100,20);
        jIndex.setBounds(15,80,100,20);
        minIndex.setBounds(15,100,100,20);
        iVal.setBounds(15,120,100,20);
        //jVal.setBounds(15,140,100,20);
        add(sortName);
        add(iIndex);
        add(jIndex);
        add(minIndex);
        add(iVal);
        //add(jVal);
    }

    public void updateQuickSort(int low, int high){
        iIndex.setText("low = " + Integer.toString(low));
        jIndex.setText("high = " + Integer.toString(high));
        minIndex.setText("pivot = " + Integer.toString(high));
        iVal.setText("arr[pivot] = " + Integer.toString(arr[high]));
        //jVal.setText("arr[min] = " + Integer.toString(arr[min]));
    }

    //radix sort
    private void radixSortInit(){
        sortName = new JLabel("Radix Visualization");
        iIndex = new JLabel("i = 0");
        jIndex = new JLabel("exp = 0");
        minIndex = new JLabel("max = 0");
        //iVal = new JLabel("arr[pivot] = " + Integer.toString(arr[0]));
        //JOptionPane.showMessageDialog(null,Arrays.toString(arr));
        //jVal = new JLabel("arr[min] = " + Integer.toString(arr[0]));
        status = new JLabel();
        sortName.setBounds(15,40,200,20);
        iIndex.setBounds(15,60,100,20);
        jIndex.setBounds(15,80,100,20);
        minIndex.setBounds(15,100,100,20);
        //iVal.setBounds(15,120,100,20);
        //jVal.setBounds(15,140,100,20);
        add(sortName);
        add(iIndex);
        add(jIndex);
        add(minIndex);
        //add(iVal);
        //add(jVal);
    }

    public void updateRadixSort(int i,int exp, int max){
        iIndex.setText("i = " + Integer.toString(i));
        jIndex.setText("exp = " + Integer.toString(exp));
        minIndex.setText("max = " + Integer.toString(max));
    }

    public void updatePivot(int pivot){
        iVal.setText("arr[pivot] = " + Integer.toString(pivot));
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

    public void swap(int a, int b) {
        JButton temp = buttons[a];
        buttons[a] = buttons[b];
        buttons[b] = temp;

    }

    public int[] getArr() {
        return arr;
    }

    public int changeBtnPosition(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        int x1 = buttons[i].getX();
        int speed = 3;
        int y1 = buttons[i].getY();
        int x2 = buttons[j].getX();
        int y2 = buttons[j].getY();
        java.util.Timer timer = new java.util.Timer();
        java.util.Timer timer1 = new java.util.Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                buttons[i].setBounds(buttons[i].getX(), buttons[i].getY() - 1, buttons[i].getWidth(),
                        buttons[i].getHeight());
                buttons[j].setBounds(buttons[j].getX(), buttons[j].getY() + 1, buttons[j].getWidth(),
                        buttons[j].getHeight());
                if (buttons[i].getY() == y2) {
                    timer1.cancel();
                    swap(i, j);
                }

            }
        };
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (buttons[i].getY() - y1 < buttons[i].getHeight() + 5) {
                    buttons[i].setBounds(buttons[i].getX(), buttons[i].getY() + 1, buttons[i].getWidth(),
                            buttons[i].getHeight());
                    buttons[j].setBounds(buttons[j].getX(), buttons[j].getY() - 1, buttons[j].getWidth(),
                            buttons[j].getHeight());
                } else {
                    buttons[i].setBounds(buttons[i].getX() + 1, buttons[i].getY(), buttons[i].getWidth(),
                            buttons[i].getHeight());
                    buttons[j].setBounds(buttons[j].getX() - 1, buttons[j].getY(), buttons[j].getWidth(),
                            buttons[j].getHeight());
                }
                if (buttons[i].getX() == x2) {
                    timer1.schedule(task1, 0, speed);
                    timer.cancel();
                }

            }
        };
        timer.schedule(task, 0, speed);
        return Math.abs(x2 - x1) * speed * 2 + Math.abs(x2 - x1) * speed;
    }
    //Quicksort
    public void addHighlightTargetPart(int start, int end) {
        for (int i = start; i <= end; i++) {
            buttons[i].setBorder(BorderFactory.createLineBorder(HIGHLIGHT_COLOR));
        }
        if (start >= 0 && end < buttons.length && end >= 0 && start <= end) {
            addText(buttons[start], iText, "low", start, -40, 10);
            if (start == end) {
                addText(buttons[end], jText, "high", end, -30, 10);
            } else
                addText(buttons[end], jText, "high", end, -40, 10);
        }

    }

    public void removeHighlightTargetPart(int start, int end) {
        for (int i = start; i <= end; i++) {
            buttons[i].setBorder(new JButton().getBorder());
        }
        iText.setText("");
        jText.setText("");

    }

    public void setCountBtnText(int i, int val){
        if ( i>= 0 && i < 10){
            countBtns[i].setText(Integer.toString(val));
        }

    }

    public void setOutBtnText(int i, int val){
        if ( i>= 0 ){
            outBtns[i].setText(Integer.toString(val));
        }

    }

    public void setBtns(int i,int val){
        if ( i>= 0 ){
            buttons[i].setText(Integer.toString(val));
        }
    }

    public void addHighLightBtn(int index,int BtnChoice){
        switch (BtnChoice){
            case 1:{
                buttons[index].setBackground(HIGHLIGHT_COLOR);
                break;
            }
            case 2:{
                countBtns[index].setBackground(HIGHLIGHT_COLOR);
                break;
            }
            case 3:{
                outBtns[index].setBackground(HIGHLIGHT_COLOR);
                break;
            }
        }
    }

    public void removeHighLightBtn(int index,int BtnChoice){
        switch (BtnChoice){
            case 1:{
                buttons[index].setBackground(new JButton().getBackground());
                break;
            }
            case 2:{
                countBtns[index].setBackground(new JButton().getBackground());
                break;
            }
            case 3:{
                outBtns[index].setBackground(new JButton().getBackground());
                break;
            }
        }
    }

    //bubble sort
    public void removehighlightBtnInBubbleSort(int i,int j){
        buttons[i].setBackground(new JButton().getBackground());
        buttons[j].setBackground(new JButton().getBackground());
        iText.setText("");
        jText.setText("");
    }

    public void highlightBtnInBubbleSort(int i, int j) {
        buttons[i].setBackground(HIGHLIGHT_COLOR);
        buttons[j].setBackground(HIGHLIGHT_COLOR);
        addText(buttons[i], iText, "j", i, -40, 20);
        addText(buttons[j], jText, "j+1", j, -40, 20);
    }

    public void addHighLightBtnInBubbleSort(int index, int colorChoice){
        switch (colorChoice){
            case 1:{
                buttons[index].setBackground(HIGHLIGHT_COLOR);
                break;
            }
            case 2:{
                buttons[index].setBackground(ACCEPT_COLOR);
                break;
            }
            case 3:{
                buttons[index].setBackground(DENY_COLOR);
                break;
            }
        }
    }

    public void removeHighLightBtnInBubbleSort(int index){
        buttons[index].setBackground(new JButton().getBackground());
    }

    //selection sort
    public void removehighlightBtnInSelectSort(int i,int j){
        buttons[i].setBackground(new JButton().getBackground());
        buttons[j].setBackground(new JButton().getBackground());
        iText.setText("");
        jText.setText("");
    }

    public void highlightBtnInSelectionSort(int i, int j) {
        buttons[i].setBackground(HIGHLIGHT_COLOR);
        buttons[j].setBackground(HIGHLIGHT_COLOR);
        addText(buttons[i], iText, "i", i, -40, 20);
        addText(buttons[j], jText, "min", j, -40, 20);
    }

    public void addHighLightBtnInSelectionSort(int index, int colorChoice){
        switch (colorChoice){
            case 1:{
                buttons[index].setBackground(HIGHLIGHT_COLOR);
                break;
            }
            case 2:{
                buttons[index].setBackground(ACCEPT_COLOR);
                break;
            }
            case 3:{
                buttons[index].setBackground(DENY_COLOR);
                break;
            }
        }
    }

    public void removeHighLightBtnInSelectionSort(int index){
        buttons[index].setBackground(new JButton().getBackground());
    }

    //quick sort

    public void addText(JButton bt, JLabel lb, String name, int index, int height, int width) {
        lb.setText(name + '=' + Integer.toString(index));
        lb.setBounds(bt.getX() + width, bt.getY() + height, 200, 40);

    }

    public void highlightBtn(int index) {

    }

    public void resetOutputBtns(){
        for (int i = 0; i < outBtns.length; i++){
            outBtns[i].setText("");
        }
    }

    public void setTextForRadixSort(int exp, int max){
        addText(buttons[0],iText,"exp: ",exp,50,0);
        addText(buttons[0],jText,"max: ",max,100,0);
    }

    //merge sort

    public void initBtnsArrays(){
        leftBtns = new JButton[arr.length/2 + 1];
        rightBtns = new JButton[arr.length/2 + 1];
        for (int i = 0 ; i < leftBtns.length; i++){
            leftBtns[i] = new JButton("");
            leftBtns[i].setBounds(WIDTH / 2 - arr.length * 80 / 2 + i * 80, HEIGHT / 2 + 30, 50, 50);
            leftBtns[i].setVisible(true);
            add(leftBtns[i]);

        }
        for (int i = 0 ; i < rightBtns.length; i++){
            rightBtns[i] = new JButton("");
            rightBtns[i].setBounds(WIDTH / 2 - arr.length * 80 / 2 + i * 80, HEIGHT / 2 + 110, 50, 50);
            rightBtns[i].setVisible(true);
            add(rightBtns[i]);
        }
    }

    public void updateBtnsArrays(int[] left, int [] right){
        if (left != null){
            for (int i = 0 ; i < left.length; i++){
                leftBtns[i].setText(Integer.toString(left[i]));
            }
            for (int i = left.length ; i < leftBtns.length; i++){
                leftBtns[i].setText("");
            }
        }
        if (right != null){
            for (int i = 0 ; i < right.length; i++){
                rightBtns[i].setText(Integer.toString(right[i]));
            }
            for (int i = right.length ; i < rightBtns.length; i++){
                rightBtns[i].setText("");
            }
        }
    }

    public void clearForModify(int start,int end){
        for (int i = start; i  <= end ; i++){
            buttons[i].setText("");
        }
    }

    public void setBtnsText(int index, String val){
        if (index >= 0){
            buttons[index].setText(val);
        }
    }

    public void removeBtnsArrays(){
//        for (int i = 0; i < leftBtns.length;i++){
//            remove(leftBtns[i]);
//            revalidate();
//            setVisible(true);
//        }
//        for (int i = 0; i < rightBtns.length;i++){
//            remove(rightBtns[i]);
//            revalidate();
//            setVisible(true);
//        }
    }


}
