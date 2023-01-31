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
        int goalX = (value - 1) % 4;
        int goalY = (value - 1) / 4;
        int currentX = index % 4;
        int currentY = index / 4;
        if (value == 0) {
            goalX = 3;
            goalY = 3;
        }
        return Math.abs(goalX - currentX) + Math.abs(goalY - currentY);
    }

    /**
     * metodo che ritorna la manhattanDistance
     * @return manhattan distance
     */
    public int getManhattanDistance() {
        return manhattanDistance;
    }
}
