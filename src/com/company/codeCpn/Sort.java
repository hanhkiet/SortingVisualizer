package com.company.codeCpn;

import javax.swing.*;
import java.awt.*;
import com.company.components.MyJList;

public abstract class Sort extends JPanel {
  public abstract void next();

  protected MyJList jList;

  protected int[] arr;
  protected int index;
  protected boolean isSwap = false;
  protected boolean isSuccess = false;
  protected int[] lsValues; // Danh sách i,j, mid

  public Sort() {
    this.isSuccess = false;
  }

  public Sort(int[] arr) {
    this.arr = arr;
    this.isSuccess = false;
    this.isSwap = false;
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
