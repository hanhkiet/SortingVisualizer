package com.company.codeCpn;

import com.company.components.MyJList;
import javax.swing.*;
import java.awt.*;

public class SelectSort extends Sort {
    private String[] lsCode = {
        "for(int i = 0; i < n-1 ; i++)  {",
        "   minimum = i ;",
        "   for(int j = i+1; j < n ; j++ )  {",
        "       if(A[ j ] < A[ minimum ])   {",
        "           minimum = j ; ",
        "       }",
        " ",
        "   }",
        "   swap ( A[ minimum ], A[ i ]) ;",
        "}",
    };
    private int indexKeySwap = 8;
    private JLabel label;
    private int i = -1;
    private int j = -1;
    private int min = -1;

    public SelectSort(){
        super();
        init();
        this.label = new JLabel("null");
        this.setBackground(Color.WHITE);
        this.jList = new MyJList(lsCode);
        this.add(label, BorderLayout.NORTH);
        this.add(jList, BorderLayout.CENTER);
    }
    public SelectSort(int[] arr) {
        super(arr);
        init();
        this.label = new JLabel(arr[0] + " - " + arr[1] +
            " - " + arr[2] + " - " + arr[3] +
            " - " + arr[4] + " - " + arr[5]);

        this.setBackground(Color.WHITE);
        this.jList = new MyJList(lsCode);
        this.add(label, BorderLayout.NORTH);
        this.add(jList, BorderLayout.CENTER);
    }

    public void init() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        this.setSize(new Dimension(100, 100));
        this.setVisible(true);
    }

    @Override
    public void next() {
        if (arr.length > 0 && this.isSuccess == false) {
            this.setIndex(index + 1);
            if (this.index > 8) {
                this.setIndex(0);
            }
            if (this.index == 0) {
      
            }
            if (this.index == 1) {
                if (j == -1) {
                    this.setI(i + 1);
                    j = i;
                    min = i;
                    if (this.i == arr.length - 1) {
                        this.isSuccess = true;
                        this.label.setText(arr[0] + " - " + arr[1] +
                            " - " + arr[2] + " - " + arr[3] +
                            " - " + arr[4] + " - " + arr[5] + " isS: " + isSuccess);
                        return;
                    }
                }
            }
            if (this.index == 2) {
                
            }
            if (this.index == 3) {
                if (j == -1) {
                    this.setJ(i + 1);
                }
                else{
                    this.setJ(j+1);
                }
                
                if (this.j == arr.length) {
                    this.setIndex(7);
                    this.setJ(-1);
                }
      
            }
            
            if (this.index == 4) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
                else{
                    this.setIndex(5);
                }  
            }
            if (this.index == 5) {
                
            }
            if (this.index == 6) {
                this.setIndex(2);
            }
           
            if (this.index == 7) {
               
            }
            if (this.index == this.indexKeySwap) {
              
                this.setIsSwap(true);
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
                this.label.setText(arr[0] + " - " + arr[1] +
                    " - " + arr[2] + " - " + arr[3] +
                    " - " + arr[4] + " - " + arr[5] + " isS: " + isSuccess);
             
            } 
            else
              this.setIsSwap(false);
            this.jList.setSelectedIndex(index);
        }
        
    }

    public int getIndex() {
        return this.index;
      }
    
      public void setIndex(int i) {
        this.index = i;
      }
    
      public void highlight() {
        this.jList.setSelectedIndex(index);
      }
    
      public void setI(int i) {
        this.i = i;
      }
    
      public void setJ(int j) {
        this.j = j;
      }
     
    
      public void setMin(int j) {
        this.min = min;
      }
}
