package com.company;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.company.codeCpn.BBSort;
import com.company.codeCpn.MergedSort;
import com.company.codeCpn.QuickSort;
import com.company.codeCpn.RadixSort;
import com.company.codeCpn.SelectSort;
import com.company.codeCpn.Sort;
import com.company.model.SortValue;

public class CodePanel extends JPanel {

    private int[] lsElement;
    private Sort codeSort;

    private TitledBorder border;

    private MainFrame parent;

    public void removeAllCpn() {
        this.removeAll();
        this.revalidate();
        this.repaint();
    }

    public CodePanel(MainFrame frame) {
        super();
        parent = frame;
        this.setLayout(new BorderLayout());

        border = BorderFactory.createTitledBorder("Code");
        border.setTitleFont(FontManager.titleFont);

        setBorder(border);
    }

    public void changeAlgorithm(AlgorithmsEnum algorithm) {
        System.out.println("change");
        int[] arr = parent.getArr();
        switch (algorithm) {
            case BUBBLE_SORT:
                this.removeAllCpn();
                codeSort = new BBSort(arr);
                this.add(codeSort, BorderLayout.CENTER);
                break;
            case QUICK_SORT:
                this.removeAllCpn();
                codeSort = new QuickSort(arr);
                this.add(codeSort, BorderLayout.CENTER);
                break;
            case MERGE_SORT:
                this.removeAllCpn();
                codeSort = new MergedSort(arr);
                this.add(codeSort, BorderLayout.CENTER);
                break;
            case SELECTION_SORT:
                this.removeAllCpn();
                codeSort = new SelectSort(arr);
                this.add(codeSort, BorderLayout.CENTER);
                break;
            case RADIX_SORT:
                this.removeAllCpn();
                codeSort = new RadixSort(arr);
                this.add(codeSort, BorderLayout.CENTER);
                break;
            default:
                System.out.println("default");
                break;
        }
    }

    public SortValue next() {
        codeSort.next();
        SortValue temp = new SortValue();
        temp = codeSort.getValues();
        System.out.println(temp);
        return temp;
    }

    public boolean getIsSwap() {
        return this.codeSort.getIsSwap();
    }

    public int[] getLsElement() {
        return this.lsElement;
    }
}
