package com.project.game.model.board;

/**
 * classe che rappresenta i singoli quadrati in cui saranno rappresentati i numeri
 */
public class Box implements Manhattan {
    int manhattanDistance;
    int value;
    int x, y;

    public Box() {
        this.value = 0;
        this.x = 0;
        this.y = 0;
        this.manhattanDistance = 0;
    }

    /**
     * metodo get per x
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * metodo get per y
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * metodo set per x
     * @param x coordinata
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * metodo set per y
     * @param y coordinata
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * metodo get per value
     * @return value del box
     */
    public int getValue() {
        return value;
    }

    /**
     * metodo set per value
     * @param value valore del box
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * metodo per settare la distanza di Manhattan
     * @param manhattanDistance distanza di manhattan per ogni bottone
     */
    public void setManhattanDistance(int manhattanDistance) {
        this.manhattanDistance = manhattanDistance;
    }

    /**
     * metodo per restituire la distanza di Manhattan
     * @return manhattanDistance
     */
    @Override
    public int getManhattan(int value, int index) {
        int x = index / 16;
        int y = index % 16;
        int targetX = value / 16;
        int targetY = value % 16;
        manhattanDistance = Math.abs(x - targetX) + Math.abs(y - targetY);
        return this.manhattanDistance;
    }


    public int getManhattanDistance() {
        return manhattanDistance;
    }
}
