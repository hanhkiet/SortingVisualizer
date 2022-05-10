package com.company.model;

import com.company.codeCpn.Sort;

public class BubbleSortValue extends SortValue {
  private int i;
  private int j;

  public BubbleSortValue() {
    super();
  }

  public BubbleSortValue(int i, int j) {
    super();
    this.i = i;
    this.j = j;
  }

  @Override
  public String toString() {
    return "{" +
        " typeAction='" + getTypeAction() + "'" +
        ", nameSort='" + getNameSort() + "'" +
        "}" + "{\n" +
        " i='" + getI() + "'" +
        ", j='" + getJ() + "'" +
        "}";
  }

  public int getI() {
    return this.i;
  }

  public void setI(int i) {
    this.i = i;
  }

  public int getJ() {
    return this.j;
  }

  public void setJ(int j) {
    this.j = j;
  }

}
