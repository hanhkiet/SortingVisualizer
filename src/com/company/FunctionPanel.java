package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class FunctionPanel extends JPanel {

    private JLabel label;
    private JTextField textField;
    private JButton generateButton;
    private JButton deleteButton;
    private int _amount;
    private int[] arr;
    private MainFrame parent;

    private void initialize() {
        label = new JLabel("Số phần tử mảng");
        textField = new JTextField();
        textField.setColumns(10);

        generateButton = new JButton("Tạo mảng");
        deleteButton = new JButton("Xóa mảng");

        add(label);
        add(textField);
        add(generateButton);
        add(deleteButton);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Số lượng phần tử rỗng!!!", "Cảnh báo",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    _amount = Integer.parseInt(textField.getText());
                    if (_amount <= 15 && _amount > 0) {
                        arr = new int[_amount];
                        initWithoutDuplicated(arr);
                        parent.setArr(arr);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Số lượng phần tử không hợp lệ (số phần tử phải lớn hơn 0 và tối đa là 10)!!!",
                                "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    }

                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arr = null;
                parent.setArr(arr);

            }
        });

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
    }

    public int[] getArr() {
        return arr;
    }

    public FunctionPanel(MainFrame frame) {
        super();

        parent = frame;
        initialize();

        TitledBorder border = BorderFactory.createTitledBorder("Tùy chỉnh");
        border.setTitleFont(FontManager.titleFont);

        setBorder(border);
    }

    public void initWithoutDuplicated(int[] arr) {
        String test = "";
        Random rand = new Random();

        for (int i = 0; i < _amount; i++) {
            int temp = rand.nextInt(100);
            boolean flat = true;
            while (flat) {
                for (int j = 0; j < i; j++) {
                    if (arr[j] == temp) {
                        temp = rand.nextInt(100);
                        break;
                    }
                }
                flat = false;
            }
            arr[i] = temp;
            test += Integer.toString(arr[i]) + " ";
        }

    }

    public void initWithDuplicated(int[] arr) {
        String test = "";
        Random rand = new Random();
        for (int i = 0; i < _amount; i++) {
            arr[i] = rand.nextInt(100);
        }
    }
}
