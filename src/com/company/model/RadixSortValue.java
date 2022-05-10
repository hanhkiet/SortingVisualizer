package com.company.model;

public class RadixSortValue extends SortValue{
    private int[] output;
    private int[] count;
    private int exp;
    private int mainI;
    private int countI;
    private int outputI;
    private int max;
   

    public RadixSortValue(){
        super();
    }


    public RadixSortValue(int exp, int mainI, int countI, int outputI, int max) {
        super();
        this.exp = exp;
        this.mainI = mainI;
        this.countI = countI;
        this.outputI = outputI;
        this.max = max;
        
    }
    @Override
    public String toString() {
        
       
        return "{" +
            " typeAction='" + getTypeAction() + "'" +
            ", nameSort='" + getNameSort() + "'" +
            "}" + "{\n" +
            " max='" + getMax() + "'" +
            " exp='" + getExp() + "'" +
            " outputI='" + getOutputI() + "'" +
            ", countI='" + getCountI() + "'" +
            ", mainI='" + getMainI() + "'" +
            "}" + "{\n" +
            " output='" + getOutput() + "'\n" +
            " count='" + getCount() + "'" +
            
            "}";
    }

    public int getMainI() {
        return this.mainI;
    }

    public void setMainI(int mainI) {
        this.mainI = mainI;
    }

    public int getCountI() {
        return this.countI;
    }

    public void setCountI(int countI) {
        this.countI = countI;
    }

    public int getOutputI() {
        return this.outputI;
    }

    public void setOutputI(int outputI) {
        this.outputI = outputI;
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

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }


}
