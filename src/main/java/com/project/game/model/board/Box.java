package com.project.game.model.board;


/**
 * classe che rappresenta i singoli quadrati in cui saranno rappresentati i numeri
 */
public class Box {
    int manhattanDistance;

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
}
