package com.jvosantos.games.gameoflife;

public class Size {
    private int width;
    private int height;

    public Size() {
        this(0, 0);
    }

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
