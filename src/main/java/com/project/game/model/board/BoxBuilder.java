package com.project.game.model.board;

public class BoxBuilder {
    private int x;
    private int y;
    private int value;
    private int initialX;
    private int initialY;
    private int g_n;
    private int manhattanDistance;

    public BoxBuilder setX(int x) {
        this.x = x;
        return this;
    }

    public BoxBuilder setY(int y) {
        this.y = y;
        return this;
    }

    public BoxBuilder setValue(int value) {
        this.value = value;
        return this;
    }

    public BoxBuilder setInitialX(int initialX) {
        this.initialX = initialX;
        return this;
    }

    public BoxBuilder setInitialY(int initialY) {
        this.initialY = initialY;
        return this;
    }

    public BoxBuilder setG_n(int g_n) {
        this.g_n = g_n;
        return this;
    }

    public BoxBuilder setManhattanDistance(int manhattanDistance) {
        this.manhattanDistance = manhattanDistance;
        return this;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    public int getInitialX() {
        return initialX;
    }

    public int getInitialY() {
        return initialY;
    }

    public int getG_n() {
        return g_n;
    }

    public int getManhattanDistance() {
        return manhattanDistance;
    }

    public Box build() {
        Box box = new Box();
        box.setX(x);
        box.setY(y);
        box.setValue(value);
        box.setInitialX(initialX);
        box.setInitialY(initialY);
        box.setG_n(g_n);
        box.setManhattanDistance(manhattanDistance);
        return box;
    }
}