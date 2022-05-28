package com.company;

import com.company.model.*;

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
    private VisualizerPanel visualizerPanel;
    private FunctionPanel functionPanel;
    private AlgorithmsPanel algorithmsPanel;
    private CodePanel codePanel;

    public CodePanel getCodePanel() {
        return codePanel;
    }

    private int[] arr;
    boolean flat;

    // private JButton playButton;
    // private JButton stopButton;
    // private Icon playIcon;
    // private Icon stopIcon;

    public void setArr(int[] newArr) {
        arr = newArr;
        visualizerPanel.setArr(arr,1);
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

        visualizerPanel.setBounds(0, 0, WIDTH * 2 / 3, HEIGHT / 2);
        mainPanel.add(visualizerPanel);

        functionPanel.setBounds(0, HEIGHT / 2, WIDTH * 4 / 9, HEIGHT / 2 - 18);
        mainPanel.add(functionPanel);

        algorithmsPanel.setBounds(WIDTH * 4 / 9, HEIGHT / 2, WIDTH * 2 / 9 + 1, HEIGHT / 2 - 18);
        mainPanel.add(algorithmsPanel);

        codePanel.setBounds(WIDTH * 2 / 3, 0, WIDTH * 1 / 3 - 13, HEIGHT);
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
                var selectedAlgorithms =  algorithmsPanel.getSelectedAlgorithm();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int sleepTime = 1200;
                        try{
                            switch (selectedAlgorithms){
                                case QUICK_SORT:{
                                    QuickSortValue data = (QuickSortValue)codePanel.next();
                                    int count = 100;
                                    //
                                    while(data.getTypeAction() != "SORT_SUCCESS") {
                                        int i = data.getI();
                                        int j = data.getJ();
                                        int time = 0;
                                        switch (data.getTypeAction()){
                                            case "SWAP_IJ":{
                                                if (i < j){
                                                    visualizerPanel.addHighLightBtnInSelectionSort(j,2);
                                                    visualizerPanel.addHighLightBtnInSelectionSort(i,2);
                                                    Thread.sleep(500);

                                                    time = visualizerPanel.changeBtnPosition(i,j);
                                                }
                                                else if (i > j){
                                                    visualizerPanel.addHighLightBtnInSelectionSort(j,2);
                                                    visualizerPanel.addHighLightBtnInSelectionSort(i,2);
                                                    Thread.sleep(500);
                                                    time = visualizerPanel.changeBtnPosition(j,i);
                                                }
                                                Thread.sleep(time + 200);
                                                visualizerPanel.removeHighLightBtnInSelectionSort(j);
                                                visualizerPanel.removeHighLightBtnInSelectionSort(i);
                                                break;
                                            }
                                            case "SWAP_IH":{
                                                int High = data.getHigh();
                                                if (i+1 != High){
                                                    visualizerPanel.updatePivot(data.getPivot());
                                                    visualizerPanel.addHighLightBtnInSelectionSort(High,2);
                                                    visualizerPanel.addHighLightBtnInSelectionSort(i+1,2);
                                                    time = visualizerPanel.changeBtnPosition(i+1,High);
                                                    Thread.sleep(time + 200);
                                                    visualizerPanel.removeHighLightBtn(i+1,1);
                                                    visualizerPanel.removeHighLightBtn(High,1);
                                                }
                                                break;
                                            }
                                            case "TARGER_PART":{
                                                int l = data.getLow();
                                                int h = data.getHigh();
                                                int pivot = data.getPivot();
                                                visualizerPanel.addHighlightTargetPart(l,h);
                                                if (l >= 0 && h >= 0)
                                                    visualizerPanel.updateQuickSort(l,h);
                                                Thread.sleep(1000);
                                                break;
                                            }
                                            case "PARTITION_SUCCESS":{
                                                int l = data.getLow();
                                                int h = data.getHigh();
                                                int pivot = data.getPivot();
                                                visualizerPanel.removeHighlightTargetPart(l,h);
                                                Thread.sleep(200);

                                                break;
                                            }
                                            case "LOAD_DATA":{
                                                int h = data.getHigh();

                                                if (i >= 0 && j>=0 && j != h && i!=j){
                                                    visualizerPanel.addHighLightBtnInSelectionSort(j,1);
                                                    visualizerPanel.addHighLightBtnInSelectionSort(i,1);
                                                    visualizerPanel.addHighLightBtnInSelectionSort(h,1);
                                                    Thread.sleep(1200);
                                                    if (visualizerPanel.getArr()[j] >= data.getPivot()){
                                                        visualizerPanel.addHighLightBtnInSelectionSort(j,3);
                                                        visualizerPanel.addHighLightBtnInSelectionSort(i,3);
                                                    }
                                                    Thread.sleep(1200);
                                                    visualizerPanel.removeHighLightBtnInSelectionSort(j);
                                                    visualizerPanel.removeHighLightBtnInSelectionSort(i);
                                                    visualizerPanel.removeHighLightBtnInSelectionSort(h);
                                                    Thread.sleep(1200);
                                                }
                                                break;
                                            }

                                            default:{
                                                Thread.sleep(200);
                                                break;
                                            }


                                        }
                                        data = (QuickSortValue)codePanel.next();

                                    }
                                    break;
                                }

                                case BUBBLE_SORT:{
                                    BubbleSortValue data = (BubbleSortValue)codePanel.next();
                                    int count = 100;
                                    //
                                    while(data.getTypeAction() != "SORT_SUCCESS") {
                                        int i = data.getI();
                                        int j = data.getJ();
                                        int time = 0;
                                        int step = 800;
                                        switch (data.getTypeAction()){
                                            case "SWAP":{
                                                visualizerPanel.highlightBtnInBubbleSort(j,j+1);
                                                time = visualizerPanel.changeBtnPosition(j,j+1);
                                                Thread.sleep(step);
                                                visualizerPanel.removehighlightBtnInBubbleSort(j,j+1);
                                                data = (BubbleSortValue)codePanel.next();
                                                Thread.sleep(100);
                                                break;
                                            }
                                            case "LOAD_DATA":{
                                               if (i >= 0 && j >=0){
                                                    visualizerPanel.updateBubbleSort(i,j);
                                                    visualizerPanel.addHighLightBtnInBubbleSort(j,1);
                                                    visualizerPanel.addHighLightBtnInBubbleSort(j+1,1);
                                                    Thread.sleep(500);
                                                    if (visualizerPanel.getArr()[j] > visualizerPanel.getArr()[j+1]){
                                                        visualizerPanel.addHighLightBtnInBubbleSort(j,2);
                                                        visualizerPanel.addHighLightBtnInBubbleSort(j+1,2);
                                                    }
                                                    else{
                                                        visualizerPanel.addHighLightBtnInBubbleSort(j,3);
                                                        visualizerPanel.addHighLightBtnInBubbleSort(j+1,3);
                                                    }
                                                   Thread.sleep(500);
                                                   visualizerPanel.removeHighLightBtnInBubbleSort(j);
                                                   visualizerPanel.removeHighLightBtnInBubbleSort(j+1);

                                                }
                                                data = (BubbleSortValue)codePanel.next();
                                               Thread.sleep(100);

                                                break;
                                            }
                                            default:{
                                                Thread.sleep(step);
                                                data = (BubbleSortValue)codePanel.next();
                                                Thread.sleep(100);
                                                break;
                                            }
                                        }

                                    }
                                    break;
                                }

                                case SELECTION_SORT:{
                                    SelectSortValue data = (SelectSortValue)codePanel.next();
                                    while(data.getTypeAction() != "SORT_SUCCESS") {
                                        int i = data.getI();
                                        int j = data.getJ();
                                        int min = data.getMin();
                                        int time = 0;
                                        switch (data.getTypeAction()){
                                            case "SWAP_I_MIN":{
                                                if (i > min){
                                                    visualizerPanel.highlightBtnInSelectionSort(min,i);
                                                    time = visualizerPanel.changeBtnPosition(min,i);
                                                    Thread.sleep(sleepTime + time );
                                                    visualizerPanel.removehighlightBtnInSelectSort(min,i);
                                                    data = (SelectSortValue)codePanel.next();

                                                }
                                                else if (i < min){
                                                    visualizerPanel.highlightBtnInSelectionSort(i,min);
                                                    time = visualizerPanel.changeBtnPosition(i,min);
                                                    Thread.sleep(sleepTime + time);
                                                    data = (SelectSortValue)codePanel.next();
                                                    visualizerPanel.removehighlightBtnInSelectSort(i,min);
                                                }
                                                break;
                                            }
                                            case "LOAD_DATA":{
                                                if (i >=0 && j >= 0 && min >= 0){
                                                    visualizerPanel.updateSelectionSort(i,j,min);

                                                    if (min != j){
                                                        visualizerPanel.addHighLightBtnInSelectionSort(j,1);
                                                        visualizerPanel.addHighLightBtnInSelectionSort(min,1);
                                                        Thread.sleep(300);
                                                        if (visualizerPanel.getArr()[j] < visualizerPanel.getArr()[min]){
                                                            visualizerPanel.addHighLightBtnInSelectionSort(j,2);
                                                            visualizerPanel.addHighLightBtnInSelectionSort(min,2);
                                                        }
                                                        else {
                                                            visualizerPanel.addHighLightBtnInSelectionSort(j,3);
                                                            visualizerPanel.addHighLightBtnInSelectionSort(min,3);
                                                        }
                                                        Thread.sleep(300);
                                                        visualizerPanel.removeHighLightBtnInSelectionSort(j);
                                                        visualizerPanel.removeHighLightBtnInSelectionSort(min);
                                                        Thread.sleep(100);
                                                    }
                                                }
                                                break;
                                            }
                                            default:{
                                                Thread.sleep( sleepTime);
                                                break;
                                            }
                                        }
                                        data = (SelectSortValue)codePanel.next();
                                    }

                                    Thread.sleep( 700);
                                    break;
                                }

                                case RADIX_SORT:{
                                    RadixSortValue data = (RadixSortValue)codePanel.next();
                                    int count = 100;
                                    //
                                    flat =true;
                                    while(data.getTypeAction() != "SORT_SUCCESS" && flat) {
                                        int time = 0;
                                        switch (data.getTypeAction()){
                                            case "LOAD_COUNT_ARRAY":{
                                                if (data.getCountI() >= 0 ){
                                                    int i = data.getCountI();
                                                    if (data.getMainI()>=0) visualizerPanel.addHighLightBtn(data.getMainI(),1);
                                                    Thread.sleep(200);
                                                    visualizerPanel.addHighLightBtn(i,2);
                                                    visualizerPanel.setCountBtnText(i,data.getCount()[i]);
                                                    Thread.sleep(200);
                                                    if (data.getMainI()>=0) visualizerPanel.removeHighLightBtn(data.getMainI(),1);
                                                    Thread.sleep(200);
                                                    visualizerPanel.removeHighLightBtn(i,2);
                                                }
                                                Thread.sleep(500);
                                                break;
                                            }
                                            case "RESET_COUNT":{
                                                for (int i = 0 ; i< 10; i++){
                                                    visualizerPanel.setCountBtnText(i,0);
                                                    Thread.sleep(500);
                                                }
                                                visualizerPanel.updateRadixSort(data.getMainI(),data.getExp(),data.getMax());
                                                break;
                                            }
                                            case "CREATE_OUTPUT_ARRAY":{
                                                visualizerPanel.resetOutputBtns();
                                                Thread.sleep(500);
                                                break;

                                            }
                                            case "LOAD_OUTPUT_ARRAY":{
                                                if (data.getOutput() != null && data.getOutputI() >= 0){
                                                    int i = data.getOutputI();
                                                    visualizerPanel.addHighLightBtn(i,3);
                                                    visualizerPanel.setOutBtnText(i,data.getOutput()[i]);
                                                    Thread.sleep(200);
                                                    visualizerPanel.removeHighLightBtn(i,3);
                                                }
                                                Thread.sleep(500);
                                                break;
                                            }

                                            case "MODIFIDE_MAIN_ARRAY":{
                                                if (data.getOutput() != null && data.getMainI() >= 0){
                                                    int i =  data.getMainI();
                                                    visualizerPanel.addHighLightBtn(i,3);
                                                    Thread.sleep(200);
                                                    visualizerPanel.addHighLightBtn(i,1);
                                                    Thread.sleep(200);
                                                    visualizerPanel.setBtns(i,data.getOutput()[i]);
                                                    visualizerPanel.removeHighLightBtn(i,3);
                                                    Thread.sleep(200);
                                                    visualizerPanel.removeHighLightBtn(i,1);
                                                    Thread.sleep(200);
                                                }
                                                Thread.sleep(500);
                                                break;
                                            }
                                            case "LOAD_DATA":{
                                                visualizerPanel.updateRadixSort(data.getMainI(),data.getExp(),data.getMax());
                                                Thread.sleep(500);
                                                break;
                                            }
                                            default:{
                                                Thread.sleep(500);
                                                break;
                                            }


                                        }
                                        data = (RadixSortValue)codePanel.next();

                                    }
                                    //visualizerPanel.removeText();
                                    break;
                                }

                                case MERGE_SORT:{
                                    MergeSortValue data = (MergeSortValue)codePanel.next();
                                    while(data.getTypeAction() != "SORT_SUCCESS") {
                                        int i = data.getI();
                                        int j = data.getJ();
                                        int time = 0;
                                        int step = 800;
                                        switch (data.getTypeAction()){
                                            case "TARGET_PART":{
                                                int l = data.getLeft();
                                                int r = data.getRight();
                                                visualizerPanel.addHighlightTargetPart(l,r);
                                                Thread.sleep(500);
                                                visualizerPanel.removeHighlightTargetPart(l,r);
                                                break;
                                            }
                                            case "LOAD_TEMP_ARRAY":{
                                                //load 2 cai mang ra
                                                Thread.sleep(300);
                                                visualizerPanel.updateBtnsArrays(data.getLArr(),data.getMArr());
                                                Thread.sleep(300);
                                                break;
                                            }
                                            case "MODIFIDE_MAIN_ARRAY":{
                                                //thay doi gia tri mang chinh
                                                //visualizerPanel.clearForModify(data.getLeft(),data.getRight());
                                                int n1 = data.getMid() - data.getLeft() + 1;
                                                int n2 = data.getRight() - data.getMid();

                                                if (data.getI() < n1 && data.getJ() < n2  ){
                                                    if (data.getLArr()[data.getI()] <= data.getMArr()[data.getJ()]){
                                                        visualizerPanel.setBtnsText(data.getK(), Integer.toString(data.getLArr()[data.getI()] ));
                                                    }
                                                    else{
                                                        visualizerPanel.setBtnsText(data.getK(), Integer.toString(data.getMArr()[data.getJ()]));
                                                    }
                                                }
                                                else{
                                                    if (data.getI() < n1){
                                                        visualizerPanel.setBtnsText(data.getK(), Integer.toString(data.getLArr()[data.getI()] ));
                                                    }
                                                    if (data.getJ() < n2){
                                                        visualizerPanel.setBtnsText(data.getK(), Integer.toString(data.getMArr()[data.getJ()]));
                                                    }
                                                }

                                                Thread.sleep(500);
                                                break;
                                            }
                                            case "MERGE_SUCCESS":{
                                                //xoa mang

                                                //visualizerPanel.removeBtnsArrays();

                                                break;
                                            }
                                            case "LOAD_DATA":{
                                                //hien data i j k, kiem tra mang trai phai
                                            }
                                            default:{
                                                break;
                                            }
                                        }
                                        data = (MergeSortValue) codePanel.next();

                                    }
                                    break;
                                }
                            }
                        }
                        catch (Exception ex){
                            System.out.println(ex.toString());
                        }
                        default:
                            break;
                    }
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        });

        visualizerPanel.setEnablePlayButton(false);
        thread.start();
    }

}