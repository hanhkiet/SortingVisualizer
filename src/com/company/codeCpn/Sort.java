package com.company.codeCpn;

import javax.swing.*;
import java.awt.*;
import com.company.components.MyJList;

public abstract class Sort extends JPanel {
  public abstract void next();

  protected int[] arr;
  protected int index;
  protected MyJList jList;
  protected boolean isSwap;
  protected boolean isSuccess = false;
  protected int[] lsElement;

  public Sort() {
    this.isSuccess = false;
  }

  public Sort(int[] arr) {
    this.arr = arr;
    this.isSuccess = false;
  }

  public int[] getLsElement() {
    return this.lsElement;
  }

  public void setlsElement(int[] arr) {
    this.lsElement = arr;
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
