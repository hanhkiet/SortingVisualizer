package com.company;

import java.awt.*;

public class MyCanvas extends Canvas {

    private VisualizerProvider provider;

    public MyCanvas(VisualizerProvider provider) {
        super();
        this.provider = provider;
    }

    public void paint(Graphics g) {
        super.paint(g);
        clear(g);

        provider.onDrawArray();
    }

    public void clear(Graphics g) {
        g.setColor(ColorManager.CANVAS_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public interface VisualizerProvider {
        void onDrawArray();
    }
}
