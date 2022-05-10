package com.company.model;

public class SortValue {

    protected String typeAction;
    protected String nameSort;

    public SortValue() {
        super();
    }

    public String getTypeAction() {
        return this.typeAction;
    }

    public void setTypeAction(String typeAction) {
        this.typeAction = typeAction;
    }

    public String getNameSort() {
        return this.nameSort;
    }

    public void setNameSort(String nameSort) {
        this.nameSort = nameSort;
    }

    @Override
    public String toString() {
        return "{" +
                " typeAction='" + getTypeAction() + "'" +
                ", nameSort='" + getNameSort() + "'" +
                "}";
    }

}
