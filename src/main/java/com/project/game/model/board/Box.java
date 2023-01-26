package com.project.game.model.board;


/**
 * classe che rappresenta i singoli quadrati in cui saranno rappresentati i numeri
 */
public class Box {
    int manhattanDistance;
    int value;
    int x, y;

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
    public int getManhattanDistance() {
        return manhattanDistance;
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
}
