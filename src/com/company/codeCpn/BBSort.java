package com.company.codeCpn;

import javax.swing.*;
import com.company.components.MyJList;
import java.awt.*;

public class BBSort extends Sort {
  private String[] lsCode = {
      "for (i=0; i<N-1; i++)",
      "  for (j=n-1; j>i; j--)",
      "   if (a[i] > a[j])",
      "     Swap(a[j], a[j-1])",
      " " };
  private int indexKeySwap = 3;
  private JLabel label;
  private int i = -1;
  private int j = 0;

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
    this.j = arr.length;
    this.label = new JLabel(arr[0] + " - " + arr[1] +
        " - " + arr[2] + " - " + arr[3] +
        " - " + arr[4] + " - " + arr[5]);

    this.setBackground(Color.WHITE);
    this.jList = new MyJList(lsCode);
    this.add(label, BorderLayout.NORTH);
    this.add(jList, BorderLayout.CENTER);
  }

  @Override
  public void next() {
    if (arr.length > 0 && this.isSuccess == false) {
      this.setIndex(index + 1);
      if (this.index > 3) {
        this.setIndex(1);
      }
      if (this.index == 0) {

      }
      if (this.index == 1) {
        if (j == arr.length) {
          this.setI(i + 1);
          if (this.i == arr.length - 1) {
            this.isSuccess = true;
            this.label.setText(arr[0] + " - " + arr[1] +
                " - " + arr[2] + " - " + arr[3] +
                " - " + arr[4] + " - " + arr[5] + " isS: " + isSuccess);
            return;
          }
        }
      }
      if (this.index == 2) {
        this.setJ(j - 1);
        if (this.j == this.i) {
          this.setIndex(0);
          this.setJ(arr.length);
        }
      }
      if (this.index == this.indexKeySwap) {
        if (arr[i] > arr[j]) {
          this.setIsSwap(true);
          int temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
          this.label.setText(arr[0] + " - " + arr[1] +
              " - " + arr[2] + " - " + arr[3] +
              " - " + arr[4] + " - " + arr[5] + " isS: " + isSuccess);
        }

        else {
          this.setIsSwap(false);
          this.setIndex(index + 1);

        }

      } else
        this.setIsSwap(false);
      this.jList.setSelectedIndex(index);
    }
  }

  public void init() {
    this.setLayout(new BorderLayout());
    this.setBackground(Color.BLACK);
    this.setSize(new Dimension(100, 100));
    this.setVisible(true);
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
