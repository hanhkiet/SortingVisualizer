package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class FunctionPanel extends JPanel {

    private JLabel inputLabel;
    private JLabel delayLabel;
    private JTextField textField;
    private JButton generateButton;
    private JButton deleteButton;

    private JSlider slider;

    private int _amount;
    private int[] arr;
    private MainFrame parent;

    private static final int HEIGHT = 360, WIDTH = 569;

    private static final Rectangle INPUT_LABEL_BOUND = new Rectangle(20, 40, 150, 20);
    private static final Rectangle TEXTFIELD_BOUND = new Rectangle(160, 40 + 1, 50, 20);

    private static final Rectangle DELAY_LABEL_BOUND = new Rectangle(20, 80, 100, 20);
    private static final Rectangle SLIDER_BOUND = new Rectangle(80, 80, 300, 40);

    private static final Rectangle GENERATE_BUTTON_BOUND = new Rectangle(220, 40 - 2, 100, 25);
    private static final Rectangle DELETE_BUTTON_BOUND = new Rectangle(330, 40 - 2, 100, 25);

    public void setEnabledWhenAnimating(boolean enabled) {
        slider.setEnabled(enabled);
        generateButton.setEnabled(enabled);
        deleteButton.setEnabled(enabled);
    }

    public void setEnabledWhenInterrupt(boolean enabled) {
        generateButton.setEnabled(enabled);
        deleteButton.setEnabled(enabled);
    }

    public void returnNormal() {
        slider.setEnabled(true);
        generateButton.setEnabled(true);
        deleteButton.setEnabled(true);
    }

    private void initialize() {
        inputLabel = new JLabel("Nhập số phần tử mảng");
        inputLabel.setBounds(INPUT_LABEL_BOUND);
        add(inputLabel);

        delayLabel = new JLabel("Độ trễ");
        delayLabel.setBounds(DELAY_LABEL_BOUND);
        add(delayLabel);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(TEXTFIELD_BOUND);
        add(textField);

        generateButton = new JButton("Tạo mảng");
        generateButton.setFocusable(false);
        generateButton.setBounds(GENERATE_BUTTON_BOUND);
        add(generateButton);

        deleteButton = new JButton("Xóa mảng");
        deleteButton.setFocusable(false);
        deleteButton.setBounds(DELETE_BUTTON_BOUND);
        add(deleteButton);

        slider = new JSlider(SwingConstants.HORIZONTAL, 100, 500, 200);
        slider.setBounds(SLIDER_BOUND);
        slider.setMajorTickSpacing(100);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        add(slider);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Số lượng phần tử rỗng!!!", "Cảnh báo",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    _amount = Integer.parseInt(textField.getText());
                    if (_amount <= 10 && _amount > 0) {
                        arr = new int[_amount];
                        initWithoutDuplicated(arr);
                        parent.setArr(arr);
                        parent.changeAlgorithm();
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

        slider.addChangeListener(l -> {
            parent.setSpeed(slider.getValue());
        });
    }

    public int[] getArr() {
        return arr;
    }

    public FunctionPanel(MainFrame frame) {
        super();

        parent = frame;
        initialize();

        setLayout(null);

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
