package com.company.codeCpn;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import com.company.components.MyJList;
import com.company.model.SortValue;

public abstract class Sort extends JPanel {
  public abstract void next();

  protected MyJList jList;

  protected int[] arr;
  protected int index;
  protected boolean isSwap = false;
  protected boolean isSuccess = false;
  protected int[] lsValues; // Danh sách i,j, mid
  protected SortValue values;

  public Sort() {
    this.isSuccess = false;
  }

  public Sort(int[] arr) {
    this.arr = Arrays.copyOf(arr, arr.length);
    this.isSuccess = false;
    this.isSwap = false;
  }

  public MyJList getJList() {
    return this.jList;
  }

  public void setJList(MyJList jList) {
    this.jList = jList;
  }

  public int[] getArr() {
    return this.arr;
  }

  public void setArr(int[] arr) {
    this.arr = arr;
  }

  public boolean isIsSwap() {
    return this.isSwap;
  }

  public boolean isIsSuccess() {
    return this.isSuccess;
  }

  public SortValue getValues() {
    return this.values;
  }

  public void setValues(SortValue values) {
    this.values = values;
  }

  public int[] getLsValues() {
    return this.lsValues;
  }

  public void setLsValues(int[] arr) {
    this.lsValues = arr;
  }

  public int getIndex() {
    return this.index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public boolean getIsSwap() {
    return this.isSwap;
  }

  public void setIsSwap(boolean isSwap) {
    this.isSwap = isSwap;
  }

  public boolean getIsSuccess() {
    return this.isSuccess;
  }

  public void setIsSuccess(boolean value) {
    this.isSuccess = value;
  }
}
