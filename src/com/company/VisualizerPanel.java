package com.company;

import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class VisualizerPanel extends JPanel {

    private JButton playButton;
    private JButton stopButton;

    private Icon playIcon;
    private Icon stopIcon;

    private void getIcon() {
        playIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("./images/play-solid.png"))
                        .getImage().getScaledInstance(10, 12,
                                Image.SCALE_AREA_AVERAGING));

        stopIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("./images/stop-solid.png")).getImage()
                .getScaledInstance(10, 12, Image.SCALE_SMOOTH));
    }

    private void initialize() {
        getIcon();
        playButton = new JButton(playIcon);
        stopButton = new JButton(stopIcon);

        add(playButton);
        add(stopButton);
    }

    public VisualizerPanel(JFrame frame) {
        super();

        initialize();

        TitledBorder border = BorderFactory.createTitledBorder("Minh họa thuật toán");
        border.setTitleFont(FontManager.titleFont);

        setBorder(border);
    }
}
