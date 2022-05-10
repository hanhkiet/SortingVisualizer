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

  private JTextArea label;
  private int i = 0;
  private int j = -1;
  private int pivot;
  private int low = 0;
  private int high = 0;
  private int pi = -999;
  QuickSortValue currentValue;
  private Stack<QuickSortValue> stack;

  public void print() {
    this.label.setText(arr[0] + "  " + arr[1] +
        "  " + arr[2] + "  " + arr[3] +
        "  " + arr[4] + "  " + arr[5] + "  " + arr[6] +
        "|| _i: " + this.i +
        "_j:" + this.j +
        "_low: " + this.low +
        "_high:" + this.high +
        "_ pi:" + this.pi +
        "_ pivot:" + this.pivot);
  }

  public QuickSort(int[] arr) {
    super(arr);
    init();
    this.label = new JTextArea(arr[0] + " - " + arr[1] +
        " - " + arr[2] + " - " + arr[3] +
        " - " + arr[4] + " - " + arr[5]);
    this.setBackground(Color.WHITE);
    JScrollPane scrollPane = new JScrollPane();

    this.jList = new MyJList(lsCode);
    scrollPane.setViewportView(jList);
    jList.setLayoutOrientation(JList.VERTICAL);
    this.label.setSize(new Dimension(100, 100));
    this.add(label, BorderLayout.NORTH);
    this.add(scrollPane, BorderLayout.CENTER);
  }

  private void init() {
    this.setLayout(new BorderLayout());
    this.setBackground(Color.BLACK);
    this.setSize(new Dimension(100, 100));
    this.setVisible(true);

    this.stack = new Stack<>();
    this.stack.push(new QuickSortValue(0, this.arr.length - 1));
    this.index = 0;
  }

  @Override
  public void next() {
    if (arr.length > 0 && this.isSuccess == false) {
      if (index == 0) {
        currentValue = stack.pop();
        this.low = currentValue.getLow();
        this.high = currentValue.getHigh();
        this.i = -999;
        this.j = -999;
      }
      this.setIndex(index + 1);
      switch (index) {
        case 2:
          if (low > high) {
            if (stack.empty()) {
              this.isSuccess = true;
              this.setIndex(0);
            } else {
              this.setIndex(currentValue.getPreviousIndex());
            }
          }
          break;
        case 4: {
          if (pi == -999)
            this.setIndex(8);

          break;
        }
        case 5: {
          stack.push(new QuickSortValue(pi + 1, high));
          break;
        }
        case 6: {
          stack.push(new QuickSortValue(low, pi - 1));
          this.setIndex(0);
          this.pi = -999;
          break;
        }
        case 10: {
          this.pivot = arr[high];
          break;
        }
        case 11: {
          if (this.i == -999 && this.j == -999) {
            this.i = this.low - 1;
            this.j = this.low - 1;
          } else {

          }
          break;
        }
        case 12: {
          this.j = this.j + 1;
          if (this.j > this.high - 1) {
            this.setIndex(19);
          }
          break;
        }
        case 14: {
          if (!(arr[j] < pivot)) {
            this.setIndex(11);
          }
          break;
        }
        case 16: {
          this.i = this.i + 1;

          break;
        }
        case 17: {
          this.setIsSwap(true);
          int temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
          this.setIndex(11);
          break;
        }
        case 20: {
          this.setIsSwap(true);
          int temp = arr[i + 1];
          arr[i + 1] = arr[high];
          arr[high] = temp;
          this.i = this.i + 1;
          break;
        }
        case 21: {
          this.setIndex(4);
          this.pi = this.i;
          break;
        }
        default:
          break;
      }
      print();
      this.jList.setSelectedIndex(index);
    }
  }

}
