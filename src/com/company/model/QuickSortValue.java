package com.company.model;

public class QuickSortValue {
  private int low;
  private int high;

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
        " low='" + getLow() + "'" +
        ", high='" + getHigh() + "'" +
        "}";
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

}
