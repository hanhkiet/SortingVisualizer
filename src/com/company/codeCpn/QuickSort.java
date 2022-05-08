package com.company.codeCpn;

import java.awt.*;
import java.util.Stack;

import javax.swing.*;

import com.company.components.MyJList;
import com.company.model.QuickSortValue;

public class QuickSort extends Sort {
  private String[] lsCode = {
      "void quickSort(int arr[], int low, int high) {",
      "   if (low < high)",
      "   {",
      "       int pi = partition(arr, low, high);",
      "       quickSort(arr, low, pi - 1);",
      "       quickSort(arr, pi + 1, high);",
      "   }",
      "}",
      "int partition (int arr[], int low, int high) {",
      "   int pivot = arr[high];",
      "   int i = (low - 1);",
      "   for (int j = low; j <= high - 1; j++)",
      "   {",
      "       if (arr[j] < pivot) ",
      "       {",
      "           i++;",
      "           swap(&arr[i], &arr[j]);",
      "       }",
      "    }",
      "   swap(&arr[i + 1], &arr[high]);",
      "   return (i + 1);",
      "}", };

  private JLabel label;
  private int i = 0;
  private int j = -1;
  private int low = 0;
  private int high = 0;
  private Stack<QuickSortValue> stack;

  public void print() {
    this.label.setText(arr[0] + " - " + arr[1] +
        " - " + arr[2] + " - " + arr[3] +
        " - " + arr[4] + " - " + arr[5] + " isS: ");
  }

  public QuickSort(int[] arr) {
    super();
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
  }

  private void init() {
    this.setLayout(new BorderLayout());
    this.setBackground(Color.BLACK);
    this.setSize(new Dimension(100, 100));
    this.setVisible(true);

    this.low = 0;
    this.high = this.arr.length - 1;
    this.index = 0;
  }

  @Override
  public void next() {
    if (arr.length > 0 && this.isSuccess == false) {
      this.setIndex(index + 1);
      if (index == 3) {
        if (low < high) {

        }
      }
    }
  }

}
