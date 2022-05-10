package com.company.codeCpn;

import javax.swing.*;
import com.company.components.MyJList;
import com.company.model.RadixSortValue;

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
  private int indexKeySwap = 22;
  private JLabel label;
  private int exp = -1;
  private int max = -1;
  private int i =-1;
  private int n = -1;
  private int[] count = new int[10];
  private int[] output;
  RadixSortValue currentValue;
  
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
    currentValue = new RadixSortValue(output, count, -1, -1);
  }

  public void init() {
    this.setLayout(new BorderLayout());
    this.setBackground(Color.BLACK);
    this.setSize(new Dimension(100, 100));
    this.setVisible(true);
  }

  @Override
  public void next() {
    if (arr.length > 0 && this.isSuccess == false) {
      this.setIndex(index + 1);
      if (this.index > 24) {
        this.setIndex(3);
        currentValue.setTypeAction("none");
      }
      if (this.index == 0 || this.index == 1|| this.index == 2) {
        currentValue.setTypeAction("none");
      }
      if (this.index == 3) {
        max = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];
        currentValue.setTypeAction("CHANCE");
        currentValue.setMax(max);
      }
      if (this.index == 4) {
        if(exp == -1){
          exp = 1;
        }
        else{
          exp = exp * 10;
        }
        currentValue.setTypeAction("CHANCE");
        currentValue.setMax(exp);
        
      }
      if (this.index == 5) {
        currentValue.setTypeAction("NONE");
        if(max/exp > 0){
          this.setIndex(6);
        }
        else{
          this.jList.setSelectedIndex(index);
          this.isSuccess = true;
          return;
        }
      }
      if (this.index == 6 && this.index == 7 && this.index == 8) {
        currentValue.setTypeAction("NONE");
      }
      if (this.index == 9){
        output = new int[n];
        currentValue.setTypeAction("CHANCE");
        currentValue.setCount(count);
      }
      if (this.index == 10){
        count = new int[10];
        currentValue.setTypeAction("CHANCE");
        currentValue.setOutput(output);
      }
      if (this.index == 11){
        Arrays.fill(count, 0);
        currentValue.setTypeAction("CHANCE");
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
        currentValue.setTypeAction("CHANCE");
        currentValue.setI(i);
      }
      if (this.index == 13){
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
      }
      if (this.index == 16){
        count[i] = count[i-1]+ count[i];
        this.setIndex(14);
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
      }
      if (this.index == 19){
        output[count[(arr[i] / exp) % 10] - 1] = arr[i];
        
      }
      if (this.index == 20){
        count[(arr[i] / exp) % 10]--;
        this.setIndex(17);
      }
      
      // if (this.index == 21){
        
      // }
      
      if (this.index == this.indexKeySwap) {
        this.setI(i+ 1);
        if(i >= n){
          this.setIndex(24);
          this.setI(-1);
        }
        else{
          this.isSwap = true;
          arr[i] = output[i];
          this.label.setText(arr[0] + " - " + arr[1] +
          " - " + arr[2] + " - " + arr[3] +
          " - " + arr[4] + " - " + arr[5] + " isS: " + isSuccess);
        }
      } else
        this.setIsSwap(false);
      if (this.index == 23) {
        this.setIndex(21);
      }
      this.jList.setSelectedIndex(index);
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
}
