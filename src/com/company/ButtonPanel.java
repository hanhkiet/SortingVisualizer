package com.company;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonPanel extends JPanel {
    public static final int BUTTON_WIDTH = 200, BUTTON_HEIGHT = 80;

    private JButton[] buttons;
    private int numbers = 6;

    private SortButtonListener listener;

    public ButtonPanel(SortButtonListener listener) {
        super();
        this.listener = listener;

        buttons = new JButton[numbers];
        for (int i = 0; i < numbers; i++) {
            buttons[i] = new JButton();
        }

        initButton(buttons[0], "Create button", 0);
        initButton(buttons[1], "Bubble sort", 1);
        initButton(buttons[2], "Quick sort", 2);
        initButton(buttons[3], "Merge sort", 3);


        setLayout(null);
        for (int i = 0; i < numbers; i++) {
            buttons[i].setBounds(20, 20 + (BUTTON_HEIGHT + 5) * i, BUTTON_WIDTH, BUTTON_HEIGHT);
            add(buttons[i]);
        }
    }

    private void initButton(JButton button, String text, int id) {
        button.setText(text);
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                listener.sortButtonClicked(id);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public interface SortButtonListener {
        void sortButtonClicked(int id);
    }
}