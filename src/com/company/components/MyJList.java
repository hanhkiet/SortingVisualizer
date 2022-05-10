package com.company.components;

import javax.swing.*;
import java.awt.*;

public class MyJList extends JList {
  public MyJList(String[] arr) {
    super();
    this.setFont(new Font("Arial", Font.PLAIN, 18));
    this.setListData(arr);
    this.setSelectedIndex(0);
  }
}
