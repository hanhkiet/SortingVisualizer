package com.company;

import javax.swing.border.TitledBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.company.codeCpn.BBSort;
import com.company.codeCpn.MergedSort;
import com.company.codeCpn.QuickSort;
import com.company.codeCpn.RadixSort;
import com.company.codeCpn.SelectSort;
import com.company.codeCpn.Sort;
import com.company.model.SortValue;

public class CodePanel extends JPanel {

    private String[] lsName = { "Bubble sort",
            "Selection sort", "Merged sort", "Quick sort",
            "Radix sort" };
    private int[] lsElement;
    private String namePicked;
    private int[] arr;
    private Sort codeSort;
    private JButton button;

    public void removeAllCpn() {
        this.removeAll();
        this.revalidate();
        this.repaint();
    }

    public CodePanel(JFrame frame) {
        super();
        System.out.println("x");
        this.setLayout(new BorderLayout());
        button = new JButton("CLick me");
        // this.add(button, BorderLayout.EAST);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();

            }
        });

        TitledBorder border = BorderFactory.createTitledBorder("Code");
        border.setTitleFont(FontManager.titleFont);
        switch ("Radix sort") {
            case "Bubble sort":
                // this.removeAllCpn();
                codeSort = new BBSort(new int[] { 10, 80, 30, 90, 40, 50, 70 });
                this.add(codeSort, BorderLayout.CENTER);
                break;
            case "Quick sort":
                // this.removeAllCpn();
                codeSort = new QuickSort(new int[] { 10, 80, 30, 90, 40, 50, 70 });
                this.add(codeSort, BorderLayout.CENTER);
                break;
            case "Merged sort":
                // this.removeAllCpn();
                codeSort = new MergedSort(new int[] { 10, 80, 30, 90, 40, 50, 70 });
                this.add(codeSort, BorderLayout.CENTER);
                break;
            case "Selection sort":
                // this.removeAllCpn();
                codeSort = new SelectSort(new int[] { 10, 80, 30, 90, 40, 50, 70 });
                this.add(codeSort, BorderLayout.CENTER);
                break;
            case "Radix sort":
                // this.removeAllCpn();
                codeSort = new RadixSort(new int[] { 10, 80, 30, 90, 40, 50, 70 });
                this.add(codeSort, BorderLayout.CENTER);
                break;
            default:
                break;
        }

        setBorder(border);

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
