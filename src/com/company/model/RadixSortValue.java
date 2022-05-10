package com.company.model;

public class RadixSortValue extends SortValue{
    private int[] output;
    private int[] count;
    private int exp;
    private int i;
    private int max;
    private int newArrValue;

    public RadixSortValue(){
        super();
    }

    public RadixSortValue(int[] output, int[] count, int exp, int i) {
        super();
        this.output = output;
        this.count = count;
        this.exp = exp;
        this.i = i;
    }

    public int getI() {
        return this.i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int[] getOutput() {
        return this.output;
    }

    public void setOutput(int[] output) {
        this.output = output;
    }

    public int[] getCount() {
        return this.count;
    }

    public void setCount(int[] count) {
        this.count = count;
    }

    public int getExp() {
        return this.exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
    public int getNewArrValue() {
        return this.newArrValue;
    }

    public void setNewArrValue(int newArrValue) {
        this.newArrValue = newArrValue;
    }


    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }


}
