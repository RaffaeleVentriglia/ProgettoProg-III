package com.project.game.model.board;

public class Box extends Observable implements Manhattan, Observer {
    int manhattanDistance; // dal nodo corrente al nodo finale
    int g_n = 0; // dal nodo iniziale al nodo corrente
    int value; // valore contenuto nella box
    int x, y; // coordinate della box
    int initialX, initialY; // coordinate iniziali che non devono essere modificate

    public Box() {}

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
        notifyObservers();
    }

    /**
     * metodo set per y
     * @param y coordinata
     */
    public void setY(int y) {
        this.y = y;
        notifyObservers();
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
        notifyObservers();
    }

    /**
     * metodo get per la funzione G
     * @return g
     */
    public int getG_n() {
        return this.g_n;
    }

    /**
     * metodo set per la funzione G
     * @param g_n della box
     */
    public void setG_n(int g_n) {
        this.g_n = g_n;
        notifyObservers();
    }

    /**
     * metodo get per initialX
     * @return coordinata X iniziale
     */
    public int getInitialX() {
        return initialX;
    }

    /**
     * metodo get per initialY
     * @return coordinata Y iniziale
     */
    public int getInitialY() {
        return initialY;
    }

    /**
     * metodo set per initialX
     * @param initialX coordinata X
     */
    public void setInitialX(int initialX) {
        this.initialX = initialX;
        notifyObservers();
    }

    /**
     * metodo set per initialY
     * @param initialY coordinata T
     */
    public void setInitialY(int initialY) {
        this.initialY = initialY;
        notifyObservers();
    }

    /**
     * metodo per settare la distanza di Manhattan
     * @param manhattanDistance distanza di manhattan per ogni bottone
     */
    public void setManhattanDistance(int manhattanDistance) {
        this.manhattanDistance = manhattanDistance;
        notifyObservers();
    }

    /**
     * metodo per restituire la distanza di Manhattan
     * @return manhattanDistance
     */
    @Override
    public int getManhattan(int value, int index) {
        int goalX = (value - 1) / 4;
        int goalY = (value - 1) % 4;
        int currentX = index / 4;
        int currentY = index % 4;
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

    /**
     * override del metodo update per implementare
     * il desgin pattern Observer
     */
    @Override
    public void update() {
        this.setValue(this.value);
        this.setG_n(this.g_n);
        this.setX(this.x);
        this.setY(this.y);
    }
}