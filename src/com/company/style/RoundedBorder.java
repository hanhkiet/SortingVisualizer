package com.company.style;

import javax.swing.border.Border;
import java.awt.*;

public class RoundedBorder implements Border {
    private int r;

    public RoundedBorder(int r) {
        this.r = r;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.r + 1, this.r + 1, this.r + 2, this.r);
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y,
            int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, r, r);
    }
}