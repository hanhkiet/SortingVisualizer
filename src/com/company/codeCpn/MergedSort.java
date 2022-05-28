package com.company.codeCpn;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.*;

import com.company.components.MyJList;
import com.company.model.MergeSortValue;
import com.company.model.SortValue;

public class MergedSort extends Sort {
  private String[] lsCode = {
      "0 void mergeSort(int arr[], int l, int r) {",
      "1   if (l < r) {",
      "2     int m = l + (r - l) / 2;",
      // " ",
      "3     mergeSort(arr, l, m);",
      "4     mergeSort(arr, m + 1, r);",
      "5 ",
      "6     merge(arr, l, m, r);",
      "7   }",
      "}",
      "9. void merge(int arr[], int left, int mid, int right) {",
      "10   int n1 = mid - left + 1;",
      "11   int n2 = right - mid;",
      "12   int L[n1], M[n2];",
      "13 ",
      "14   for (int i = 0; i < n1; i++)",
      "15        L[i] = arr[left + i];",
      "16   for (int j = 0; j < n2; j++)",
      "17        M[j] = arr[mid + 1 + j];",
      " ",
      "19   int i, j, k;",
      "20   i = 0; j = 0; k = left;",
      "21   while (i < n1 && j < n2) {",
      "22    if (L[i] <= M[j]) {",
      "23       arr[k] = L[i];",
      "24       i++;",
      "25     } else {",
      "26       arr[k] = M[j];",
      "27       j++;",
      "28     }",
      "29     k++;",
      "   }",
      " ",
      "32   while (i < n1) {",
      "33     arr[k] = L[i];",
      "34     i++;",
      "35     k++;",
      "   }",
      "37   while (j < n2) {",
      "38     arr[k] = M[j];",
      "39     j++;",
      "40     k++;",
      "   }",
      "}", };

  private JTextArea label;
  private ArrayList<MergeSortValue> list;
  private MergeSortValue currentValue;
  private int i, j, k;
  private int n1, n2;
  private int[] LArr, MArr;
  private int left, right, mid;
  private int indexOfStack;
  private String type;

  public MergedSort(int[] arr) {
    super(arr);
    init();
    this.index = 1;
    this.i = -999;
    this.j = -999;
    this.k = -999;
    this.indexOfStack = 0;
    this.type = "none";
    this.list = new ArrayList<MergeSortValue>();
    this.list.add(new MergeSortValue(0, arr.length - 1));
    this.currentValue = list.get(0);
  }

  @Override
  public void next() {
    this.type = "NONE";
    if (arr.length > 0 && this.isSuccess == false) {
      if (index == 1) {
        this.left = currentValue.getLeft();
        this.right = currentValue.getRight();
        this.type = "TARGET_PART";

        this.mid = -999;
      }

      this.setIndex(index + 1);
      System.out.println(index);

      switch (index) {
        case 2:
          if (currentValue.getLeft() >= currentValue.getRight()) {
            if (currentValue.getPreviousIndex() == 0)
              this.setIndex(4);
            else
              this.setIndex(6);
          }
          break;
        case 3:
          mid = left + (right - left) / 2;
          currentValue.setMid(mid);
          this.type = "TARGET_PART";
          break;
        case 4: {
          MergeSortValue temp1 = new MergeSortValue(mid + 1, right);
          MergeSortValue temp2 = new MergeSortValue(left, mid);
          temp1.setPreviousIndex(1);
          temp1.setParentIndex(currentValue.getPreviousIndex());
          temp2.setPreviousIndex(0);
          temp2.setParentIndex(currentValue.getPreviousIndex());

          list.add(temp1);
          list.add(temp2);
          currentValue = list.get(list.size() - 1);
          this.setIndex(1);
          break;
        }
        case 5:
          currentValue = list.get(list.size() - 2);
          this.setIndex(1);
          break;
        case 7: {
          MergeSortValue temp1 = list.get(list.size() - 1);
          // MergeSortValue temp2 = list.get(list.size() - 2);
          if (temp1.getParentIndex() == 0)
            currentValue = list.get(list.size() - 3);
          else
            currentValue = list.get(list.size() - 4);
          // currentValue.setLeft(temp1.getLeft());
          // currentValue.setMid(temp1.getRight());
          // currentValue.setRight(temp2.getRight());
          // currentValue.setParentIndex(temp1.getParentIndex());
          this.setIndex(10);
          this.type = "TARGET_PART";

          break;
        }
        case 11: {
          this.left = currentValue.getLeft();
          this.right = currentValue.getRight();
          this.mid = currentValue.getMid();
          this.n1 = mid - left + 1;

          this.type = "TARGET_PART";
          break;
        }
        case 12: {
          this.n2 = right - mid;
          this.setIndex(14);
          break;
        }
        case 15: {
          this.LArr = new int[n1];
          for (int i = 0; i < n1; i++)
            this.LArr[i] = arr[left + i];
          this.currentValue.setLArr(LArr);
          this.setIndex(16);
          this.type = "LOAD_TEMP_ARRAY";
          break;
        }
        case 17: {
          this.MArr = new int[n2];
          for (int j = 0; j < n2; j++)
            this.MArr[j] = arr[mid + 1 + j];
          this.currentValue.setMArr(MArr);
          this.setIndex(20);
          this.type = "LOAD_TEMP_ARRAY";
          break;
        }
        case 21: {
          this.i = 0;
          this.j = 0;
          this.k = this.left;
          currentValue.setI(0);
          currentValue.setJ(0);
          currentValue.setK(left);
          this.type = "LOAD_DATA";
          break;
        }
        case 22: {
          if (i >= n1 || j >= n2) {
            setIndex(32);
          }
          break;
        }
        case 23: {
          if (LArr[i] > MArr[j]) {
            setIndex(25);
          }
          break;
        }
        case 24: {
          // Thay đổi mảng A
          arr[k] = LArr[i];
          this.type = "MODIFIDE_MAIN_ARRAY";
          break;
        }
        case 25: {
          i++;
          currentValue.setI(i);
          this.type = "LOAD_DATA";
          this.setIndex(28);
          break;
        }
        case 27: {
          // Thay đổi mảng A
          arr[k] = MArr[j];
          this.type = "MODIFIDE_MAIN_ARRAY";
          break;
        }
        case 28: {
          j++;
          currentValue.setJ(j);
          this.type = "LOAD_DATA";
          this.setIndex(29);
          break;
        }
        case 30: {
          k++;
          currentValue.setK(k);
          this.type = "LOAD_DATA";
          this.setIndex(21);
          break;
        }
        case 33: {
          if (i >= n1)
            this.setIndex(37);
          break;
        }
        case 34: {
          // Thay đổi mảng A
          arr[k] = LArr[i];
          this.type = "MODIFIDE_MAIN_ARRAY";

          break;
        }
        case 35: {
          i++;
          currentValue.setI(i);
          this.type = "LOAD_DATA";

          break;
        }
        case 36: {
          k++;
          currentValue.setK(k);
          this.type = "LOAD_DATA";

          this.setIndex(32);
          break;
        }
        case 38: {
          if (j >= n2)
            this.setIndex(41);
          break;
        }
        case 39: {
          // Thay đổi A
          arr[k] = MArr[j];
          this.type = "MODIFIDE_MAIN_ARRAY";

          break;
        }
        case 40: {
          j++;
          currentValue.setJ(j);
          this.type = "LOAD_DATA";

          break;
        }
        case 41: {
          k++;
          currentValue.setK(k);
          this.type = "LOAD_DATA";

          this.setIndex(37);
          break;
        }
        case 42: {
          if (currentValue.getPreviousIndex() == 0) {
            this.setIndex(4);
          } else
            this.setIndex(5);
          list.remove(list.size() - 1);
          list.remove(list.size() - 1);
          this.type = "MERGE_SUCCESS";
          if (list.size() == 1) {
            this.setIndex(0);
            this.isSuccess = true;
            this.type = "SORT_SUCCESS";
          }

          break;
        }
        default:
          break;
      }
      this.print();
      setValueCallBack();
      this.jList.setSelectedIndex(index);
    }
  }

  public SortValue getValue() {
    return this.values;
  }

  private void setValueCallBack() {
    this.values = new SortValue();
    this.values = currentValue;
    this.values.setNameSort("Merged sort");
    this.values.setTypeAction(this.type);
  }

  private void init() {
    this.setLayout(new BorderLayout());
    this.setBackground(Color.BLACK);
    // this.setSize(new Dimension(100, 100));
    this.setVisible(true);
//    this.label = new JTextArea(arr[0] + " - " + arr[1] +
//        " - " + arr[2] + " - " + arr[3] +
//        " - " + arr[4] + " - " + arr[5]);
    //this.label.setFont(new Font("Arial", Font.PLAIN, 22));
    //this.label.setBackground(Color.BLACK);
    //this.label.setForeground(Color.white);
    this.setBackground(Color.WHITE);

    JScrollPane scrollPane = new JScrollPane();
    this.jList = new MyJList(lsCode);
    scrollPane.setViewportView(jList);
    this.jList.setLayoutOrientation(JList.VERTICAL);
    // this.add(label, BorderLayout.NORTH);
    this.add(scrollPane, BorderLayout.CENTER);
  }

  public void print() {
    int Ltext = -99;
    int Mtext = -99;
    String ListPrev = "";
    String ListParent = "";
    String ListIndex = "";
    if (LArr != null && MArr != null) {
      Ltext = this.LArr[0];
      Mtext = this.MArr[0];
      // for (int i = 0; i < LArr.length; i++) {
      // Ltext += String.valueOf(LArr[i]);
      // }

      // for (int i = 0; i < MArr.length; i++) {
      // Mtext += String.valueOf(MArr[i]);
      // }
    }
    for (int i = 0; i < list.size(); i++) {
      ListPrev += " (" + String.valueOf(list.get(i).getPreviousIndex()) + ")";
      ListParent += " (" + String.valueOf(list.get(i).getParentIndex()) + ")";
      ListIndex += " (" + String.valueOf(list.get(i).getLeft()) + "-" +
          String.valueOf(list.get(i).getRight()) + ")";
    }
//    this.label.setText(arr[0] + "  " + arr[1] +
//        "  " + arr[2] + "  " + arr[3] +
//        "  " + arr[4] + "  " + arr[5] + "  " + arr[6] +
//        "\n Left = " + currentValue.getLeft() + " mid = "
//        + currentValue.getMid() + " right = "
//        + currentValue.getRight() + "\n i = "
//        + currentValue.getI() + " j = "
//        + currentValue.getJ() + " k = "
//        + currentValue.getK()
//        + " PreviousIndex = "
//        + currentValue.getPreviousIndex()
//        + "\n indexOfStack = "
//        + indexOfStack
//        + "\n n1 = "
//        + this.n1
//        + " - n2 = "
//        + this.n2
//        + "\n ListPrev: " + ListPrev
//        + "\n ListParent: " + ListParent
//        + "\n ListIndex: " + ListIndex);
  }
}
