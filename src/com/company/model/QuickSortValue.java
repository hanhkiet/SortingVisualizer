package com.company.model;

public class QuickSortValue extends SortValue {
  private int low;
  private int high;
  private int previousIndex;
  private int i;
  private int j;
  private int pi;
  private int pivot;

  public QuickSortValue() {
    super();
  }

  public QuickSortValue(int low, int high) {
    super();
    this.low = low;
    this.high = high;
  }

  @Override
  public String toString() {
    return "{" +
        " typeAction='" + getTypeAction() + "'" +
        ", nameSort='" + getNameSort() + "'" +
        "}" + "{\n" +
        " low='" + getLow() + "'" +
        ", high='" + getHigh() + "'" +
        ", previousIndex='" + getPreviousIndex() + "'" +
        ", i='" + getI() + "'" +
        ", j='" + getJ() + "'" +
        ", pivot='" + getPivot() + "'" +
        "}";
  }

  public int getPi() {
    return this.pi;
  }

  public void setPi(int pi) {
    this.pi = pi;
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

  public int getPivot() {
    return this.pivot;
  }

  public void setPivot(int pivot) {
    this.pivot = pivot;
  }

  public int getLow() {
    return this.low;
  }

  public void setLow(int low) {
    this.low = low;
  }

  public int getHigh() {
    return this.high;
  }

  public void setHigh(int high) {
    this.high = high;
  }

  public int getPreviousIndex() {
    return this.previousIndex;
  }

  public void setPreviousIndex(int previousIndex) {
    this.previousIndex = previousIndex;
  }
}
