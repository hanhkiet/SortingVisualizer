package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.nio.Buffer;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Visualizer {
    private static final int PADDING = 20;
    private static final int MAX_BAR_HEIGHT = 350, MIN_BAR_HEIGHT = 30;
    private Integer[] array;
    private int capacity, speed;
    private Bar[] bars;
    private boolean hasArray;

    private SortedListener listener;

    private Color originalColor, swappingColor, comparingColor;

    private BufferStrategy bs;
    private Graphics g;

    public Visualizer(int capacity, int fps, SortedListener listener) {
        this.capacity = capacity;
        this.listener = listener;
        this.speed = (int) (1000.0 / fps);

        originalColor = ColorManager.BAR_WHITE;
        comparingColor = Color.YELLOW;
        swappingColor = ColorManager.BAR_RED;

        bs = listener.getBufferStrategy();

        hasArray = false;
    }

    public void drawArray() {
        if(!hasArray) {
            return;
        }

        g = bs.getDrawGraphics();

        for(int i = 0; i < bars.length; i++) {
            bars[i].draw(g);
        }

        bs.show();
        g.dispose();
    }

    public void bubbleSort() {
        if(!hasArray) {
            return;
        }

        g = bs.getDrawGraphics();

        int count = 0;

        for(int i = array.length - 1; i >= 0; i--) {
            count = 0;
            for(int j = 0; j < i; j++) {
                if(array[j] > array[j + 1]) {
                    swap(j, j + 1);
                    count++;
                }
            }

            bars[i].draw(g);
            bs.show();

            if(count == 0) {
                break;
            }
        }

        g.dispose();
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;

        bars[i].clear(g);
        bars[j].clear(g);

        bars[j].setValue(bars[i].getValue());
        bars[i].setValue(temp);

        colorPair(i, j, swappingColor);
    }

    private void colorPair(int i, int j, Color color) {
        Color color1 = bars[i].getColor(), color2 = bars[j].getColor();

        bars[i].setColor(color);
        bars[i].draw(g);

        bars[j].setColor(color);
        bars[j].draw(g);

        bs.show();

        try {
            TimeUnit.MILLISECONDS.sleep(speed);
        } catch (Exception e) {};

        bars[i].setColor(color1);
        bars[i].draw(g);

        bars[j].setColor(color2);
        bars[j].draw(g);

        bs.show();
    }

    public void createRandomArray(int canvasWidth, int canvasHeight) {
        array = new Integer[capacity];
        bars = new Bar[capacity];
        hasArray = true;

        double x = PADDING;
        int y = canvasHeight - PADDING;

        double width = (double) (canvasWidth - PADDING * 2) / capacity;

        g = bs.getDrawGraphics();
        g.setColor(ColorManager.CANVAS_BACKGROUND);
        g.fillRect(0, 0, canvasWidth, canvasHeight);

        Random rand = new Random();
        int value;
        Bar bar;
        for(int i = 0; i < array.length; i++) {
            value = rand.nextInt(MAX_BAR_HEIGHT) + MIN_BAR_HEIGHT;
            array[i] = value;

            bar = new Bar((int) x, y, (int) width, value, originalColor);
            bar.draw(g);
            bars[i] = bar;

            x += width;
        }

        bs.show();
        g.dispose();
    }

   public interface SortedListener {
        BufferStrategy getBufferStrategy();
   }
}
