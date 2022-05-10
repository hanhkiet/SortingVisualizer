package com.company.codeCpn;

import javax.swing.*;
import com.company.components.MyJList;
import com.company.model.BubbleSortValue;
import com.company.model.SortValue;

import java.awt.*;

public class BBSort extends Sort {
  private String[] lsCode = {
      "for (i = 0; i < n - 1; i++)",
      "   for (j = 0; j < n - i - 1; j++)",
      "       if (arr[j] > arr[j + 1])",
      "           swap(arr[j], arr[j + 1]);",
      " " };
  private int indexKeySwap = 4;
  private JLabel label;
  private int i = -1;
  private int j = -1;
  private String type;
  private BubbleSortValue currentValue;

  public BBSort() {
    super();
    init();
    this.label = new JLabel("null");
    this.setBackground(Color.WHITE);
    this.jList = new MyJList(lsCode);
    this.add(label, BorderLayout.NORTH);
    this.add(jList, BorderLayout.CENTER);
  }

  public BBSort(int[] arr) {
    super(arr);
    init();
    this.label = new JLabel(arr[0] + " - " + arr[1] +
        " - " + arr[2] + " - " + arr[3] +
        " - " + arr[4] + " - " + arr[5]);

    this.setBackground(Color.WHITE);
    this.jList = new MyJList(lsCode);
    // this.add(label, BorderLayout.NORTH);
    this.add(jList, BorderLayout.CENTER);
    this.currentValue = new BubbleSortValue(-999, -999);
  }

  public void init() {
    this.setLayout(new BorderLayout());
    this.setBackground(Color.BLACK);
    this.setSize(new Dimension(100, 100));
    this.setVisible(true);
  }

  @Override
  public void next() {
    this.type = "NONE";
    if (arr.length > 0 && this.isSuccess == false) {
      this.setIndex(index + 1);
      if (this.index > 4) {
        this.setIndex(1);
      }
      if (this.index == 0) {

      }
      if (this.index == 1) {
        if (j == -1) {
          this.setI(i + 1);
          this.type = "LOAD_DATA";
          if (this.i == arr.length - 1) {
            this.isSuccess = true;
            this.type = "SORT_SUCCESS";
            return;
          }
        }
      }
      if (this.index == 2) {
        this.setJ(j + 1);
        if (this.j == arr.length - this.i - 1) {
          this.setIndex(0);
          this.setJ(-1);
        }
        this.type = "LOAD_DATA";
      }
      if (this.index == 4) {
        if (arr[j] > arr[j + 1]) {
          this.setIsSwap(true);
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
          this.type = "SWAP";
        } else {
          this.setIsSwap(false);
          this.setIndex(1);
        }

      } else
        this.setIsSwap(false);
      this.setValueCallBack();
      // this.print();
      this.jList.setSelectedIndex(index);
    }
  }

  public SortValue getValue() {
    return this.values;
  }

  private void setValueCallBack() {
    this.values = new SortValue();

    this.currentValue.setI(this.i);
    this.currentValue.setJ(this.j);
    this.values = currentValue;
    this.values.setNameSort("Bubble sort");
    this.values.setTypeAction(this.type);
  }

  public void print() {
    this.label.setText(arr[0] + " - " + arr[1] +
        " - " + arr[2] + " - " + arr[3] +
        " - " + arr[4] + " - " + arr[5] + " isS: " + isSuccess);
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

  public void setI(int i) {
    this.i = i;
  }

  public void setJ(int j) {
    this.j = j;
  }
}
