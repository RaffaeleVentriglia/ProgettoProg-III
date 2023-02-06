package com.project.game.model.board;

/**
 * classe che rappresenta i singoli quadrati in cui saranno rappresentati i numeri
 */
public class Box implements Manhattan {
    int manhattanDistance; // dal nodo corrente al nodo finale
    int g_n; // dal nodo iniziale al nodo corrente
    int value; // valore contenuto nella box
    int x, y; // coordinate della box
    int initialX, initialY; // coordinate iniziali che non devono essere modificate

    public Box() {
        this.value = 0;
        this.x = 0;
        this.y = 0;
        this.manhattanDistance = 0;
        this.g_n = 0;
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



    public int getG_n() {
        return this.g_n;
    }

    public void setG_n(int g_n) {
        this.g_n = g_n;
    }

    public int getInitialX() {
        return initialX;
    }

    public int getInitialY() {
        return initialY;
    }

    public void setInitialX(int initialX) {
        this.initialX = initialX;
    }

    public void setInitialY(int initialY) {
        this.initialY = initialY;
    }

    // da modificare con initialX e initialY
    public int getG(int index) {
        int currentX = index / 4;
        int currentY = index % 4;
        return Math.abs(this.initialX - currentX) + Math.abs(this.initialY - currentY);
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
