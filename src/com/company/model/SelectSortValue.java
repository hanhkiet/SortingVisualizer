package com.company.model;

public class SelectSortValue {
    private int i;
    private int j;
    private int min;

    public SelectSortValue(){
    }

    public SelectSortValue(int i, int j, int min){
        this.i = i;
        this.j = j;
        this.min = min;
    }

    public int getMin() {
        return this.min;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public int getI(){
        return i;
    }
    public int getJ(){
        return j;
    }
    public int setI(){
        return i;
    }
    public int setJ(){
        return j;
    }

}
