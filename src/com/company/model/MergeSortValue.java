package com.company.model;

public class MergeSortValue extends SortValue {
  private int left;
  private int mid;
  private int right;
  private int[] LArr;
  private int[] MArr;
  private int i;
  private int j;
  private int k;
  private int previousIndex;
  private int parentIndex;

  public MergeSortValue() {
    this.i = -999;
    this.j = -999;
    this.k = -999;
    this.previousIndex = -999;
    this.parentIndex = 0;
  }

  public MergeSortValue(int left, int right) {
    super();
    this.left = left;
    this.right = right;
  }

  public MergeSortValue(int left, int mid, int right) {
    super();
    this.left = left;
    this.mid = mid;
    this.right = right;
  }

  public int getParentIndex() {
    return this.parentIndex;
  }

  public void setParentIndex(int parentIndex) {
    this.parentIndex = parentIndex;
  }

  public int getPreviousIndex() {
    return this.previousIndex;
  }

  public void setPreviousIndex(int previousIndex) {
    this.previousIndex = previousIndex;
  }

  public int getLeft() {
    return this.left;
  }

  public void setLeft(int left) {
    this.left = left;
  }

  public int getMid() {
    return this.mid;
  }

  public void setMid(int mid) {
    this.mid = mid;
  }

  public int getRight() {
    return this.right;
  }

  public void setRight(int right) {
    this.right = right;
  }

  public int[] getLArr() {
    return this.LArr;
  }

  public void setLArr(int[] LArr) {
    this.LArr = LArr;
  }

  public int[] getMArr() {
    return this.MArr;
  }

  public void setMArr(int[] MArr) {
    this.MArr = MArr;
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

  public int getK() {
    return this.k;
  }

  public void setK(int k) {
    this.k = k;
  }

  @Override
  public String toString() {
    return "{" +
        " typeAction='" + getTypeAction() + "'" +
        ", nameSort='" + getNameSort() + "'" +
        "}" + "{\n" +
        " left='" + getLeft() + "'" +
        ", mid='" + getMid() + "'" +
        ", right='" + getRight() + "'" +
        ", LArr='" + getLArr() + "'" +
        ", MArr='" + getMArr() + "'" +
        ", i='" + getI() + "'" +
        ", j='" + getJ() + "'" +
        ", k='" + getK() + "'" +
        ", previousIndex='" + getPreviousIndex() + "'" +
        ", parentIndex='" + getParentIndex() + "'" +
        "}";
  }

}
