package com.company.model;

public class SelectSortValue extends SortValue {
    

    private int i;
    private int j;
    private int min;

    public SelectSortValue(){
        super();
    }

    public SelectSortValue(int i, int j, int min){
        super();
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
    public void setI(int i){
        this.i = i;
    }
    public void setJ(int j){
        this.j = j;
    }

}
