package com.company.codeCpn;

import javax.swing.*;
import com.company.components.MyJList;
import com.company.model.RadixSortValue;
import com.company.model.SortValue;

import java.awt.*;
import java.util.Arrays;

public class RadixSort extends Sort {
  private String[] lsCode = {
/*0*/    "static void radixsort(int arr[], int n)",
/*1*/    "{",
/*2*/    "    int max = getMax(arr, n);",
/*3*/    "    for (int exp = 1; max / exp > 0; exp *= 10)",
/*4*/    "        countSort(arr, exp);",
/*5*/    "}",
/*6*/    "static void countSort(int arr[],int n, int exp)",
/*7*/    "{",
/*8*/    "    int output[] = new int[n];",
/*9*/    "    int count[] = new int[10];",
/*10*/    "    Arrays.fill(count, 0);",
/*11*/    "    for (int i = 0; i < n; i++){",
/*12*/    "        count[(arr[i] / exp) % 10]++;",
/*13*/    "    }",
/*14*/    "    for (int i = 1; i < 10; i++)",
/*15*/    "        count[i] += count[i - 1];",
/*16*/    "    }",
/*17*/    "    for (int i = n - 1; i >= 0; i--) {",
/*18*/    "        output[count[(arr[i] / exp) % 10] - 1] = arr[i];",
/*19*/    "        count[(arr[i] / exp) % 10]--;",
/*20*/    "    }",
/*21*/    "    for (i = 0; i < n; i++) {",
/*22*/    "        arr[i] = output[i];",
/*23*/    "    }",
/*24*/    "}",
    };
  private int indexKeySwap = 23;
  private JLabel label;
  private int exp = -1;
  private int max = -999;
  private int i =-1;
  private int n = -1;
  private int[] count = new int[10];
  private int[] output;
  RadixSortValue currentValue;
  private String type;
  
  public RadixSort() {
    super();
    init();
    this.label = new JLabel("null");
    this.setBackground(Color.WHITE);
    this.jList = new MyJList(lsCode);
    this.add(label, BorderLayout.NORTH);
    this.add(jList, BorderLayout.CENTER);
  }

  public RadixSort(int[] arr) {
    super(arr);
    init();
    this.label = new JLabel(arr[0] + " - " + arr[1] +
        " - " + arr[2] + " - " + arr[3] +
        " - " + arr[4] + " - " + arr[5]);

    this.setBackground(Color.WHITE);
    JScrollPane scrollPane = new JScrollPane();

    this.jList = new MyJList(lsCode);
    scrollPane.setViewportView(jList);
    jList.setLayoutOrientation(JList.VERTICAL);

    this.add(label, BorderLayout.NORTH);
    this.add(scrollPane, BorderLayout.CENTER);

    n = arr.length;
    currentValue = new RadixSortValue( -1,-1,-1,-1, -999);
  }

  public void init() {
    this.setLayout(new BorderLayout());
    this.setBackground(Color.BLACK);
    this.setSize(new Dimension(100, 100));
    this.setVisible(true);
    this.type= "NONE";
  }

  @Override
  public void next() {
    this.type= "NONE";
    if (arr.length > 0 && this.isSuccess == false) {
      this.setIndex(index + 1);
      if (this.index > 24) {
        this.setIndex(3);

      }
      // if (this.index == 0 || this.index == 1|| this.index == 2) {
      // }
      if (this.index == 3) {
        max = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];
        this.type= "LOAD_DATA";
        currentValue.setMax(max);
      }
      if (this.index == 4) {
        if(exp == -1){
          exp = 1;
        }
        else{
          exp = exp * 10;
        }
        this.type= "LOAD_DATA";
        currentValue.setExp(exp);
        
      }
      if (this.index == 5) {
          if(max/exp > 0){
          this.setIndex(6);
        }
        else{
          this.jList.setSelectedIndex(index);
          this.isSuccess = true;
          this.type= "SORT_SUCCESS";
        }
      }
      // if (this.index == 6 && this.index == 7 && this.index == 8) {
      // }
      if (this.index == 9){
        output = new int[n];
        this.type= "LOAD_OUTPUT_ARRAY";
        currentValue.setOutput(output);
        
      }
      if (this.index == 10){
        count = new int[10];
        this.type= "LOAD_COUNT_ARRAY";
        currentValue.setCount(count);
      }
      if (this.index == 11){
        Arrays.fill(count, 0);
        this.type= "LOAD_COUNT_ARRAY";
        currentValue.setCount(count);
      }
      if (this.index == 12){
        this.setI(i+1);
        if(i < n){
        }
        else{
          this.setIndex(14);
          this.setI(-1);
        }
        this.type= "LOAD_DATA";
        currentValue.setCountI(i);
      }
      if (this.index == 13){
        this.type= "LOAD_COUNT_ARRAY";
        count[(arr[i] / exp) % 10]++;
        this.setIndex(11);
      }
      // if (this.index == 14){
        
      // }
      if (this.index == 15){
        if(i == -1){
          this.setI(1);
        }
        else{
          this.setI(i+1);
        }
        if(i >= 10){
          this.setIndex(17);
          this.setI(-1);
        }
        this.type= "LOAD_DATA";
        currentValue.setCountI(i);
      }
      if (this.index == 16){
        count[i] = count[i-1]+ count[i];
        this.setIndex(14);
        this.type= "LOAD_COUNT_ARRAY";
        currentValue.setCount(count);
      }
      // if (this.index == 17){
        
      // }
      if (this.index == 18){
        if(i == -1){
          this.setI(n-1);
        }
        else{
          this.setI(i-1);
        }
        if(i < 0){
          this.setIndex(21);
          this.setI(-1);
        }
        this.type= "LOAD_DATA";
        currentValue.setOutputI(i);;
      }
      if (this.index == 19){
        output[count[(arr[i] / exp) % 10] - 1] = arr[i];
        this.type= "LOAD_OUTPUT_ARRAY";
        currentValue.setOutput(output);;
      }
      if (this.index == 20){
        count[(arr[i] / exp) % 10]--;
        this.setIndex(17);
        this.type= "LOAD_COUNT_ARRAY";
        currentValue.setCount(count);
      }
      
      if (this.index == 22){
        this.setI(i+ 1);
        if(i >= n){
          this.setIndex(24);
          this.setI(-1);
        }
        this.type= "LOAD_DATA";
        currentValue.setMainI(i);;
      }
      
      if (this.index == this.indexKeySwap) {
        
        if(i >= n){
         
        }
        else{
          arr[i] = output[i];
         
          this.type = "MODIFIDE_MAIN_ARRAY";
        }
        this.setIndex(21);
      } 
      this.setValueCallBack();
      this.jList.setSelectedIndex(index);
      this.label.setText(arr[0] + " - " + arr[1] +
      " - " + arr[2] + " - " + arr[3] +
      " - " + arr[4] + " - " + arr[5] +" isS: " + isSuccess);
      
    }
  }

  public int getIndex() {
    return this.index;
  }

  public void setIndex(int i) {
    this.index = i;
  }

  public void highlight() {
    this.jList.setSelectedIndex(index);
  }

  public void setMax(int maxnumber) {
    this.max = maxnumber;
  }

  public void setEXP(int exp) {
    this.exp = exp;
  }

  public void setI(int i) {
    this.i = i;
  }

  public SortValue getValue() {
    return this.values;
  }

  private void setValueCallBack() {
    this.values = new SortValue();
    this.values = currentValue;
    this.values.setNameSort("Radix sort");
    this.values.setTypeAction(this.type);
  }
}
