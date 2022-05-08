package com.company;

import javax.swing.border.TitledBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.company.codeCpn.BBSort;
import com.company.codeCpn.QuickSort;
import com.company.codeCpn.Sort;

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
        this.setLayout(new BorderLayout());
        button = new JButton("CLick me");
        this.add(button, BorderLayout.EAST);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });
        TitledBorder border = BorderFactory.createTitledBorder("Code");
        border.setTitleFont(FontManager.titleFont);
        switch ("Quick sort") {
            case "Bubble sort":
                // this.removeAllCpn();
                codeSort = new BBSort(new int[] { 5, 4, 62, 1, 78, 4 });
                this.add(codeSort, BorderLayout.CENTER);
                break;
            case "Quick sort":
                // this.removeAllCpn();
                codeSort = new QuickSort(new int[] { 5, 4, 62, 1, 78, 4 });
                this.add(codeSort, BorderLayout.CENTER);
                break;
            default:
                break;
        }

        setBorder(border);

    }

    public void next() {
        codeSort.next();
    }

    public boolean getIsSwap() {
        return this.codeSort.getIsSwap();
    }

    public int[] getLsElement() {
        return this.lsElement;
    }

}
