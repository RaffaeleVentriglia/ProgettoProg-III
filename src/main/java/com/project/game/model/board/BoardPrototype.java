package com.project.game.model.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardPrototype implements Prototype, Cloneable {
    public Box[][] board;
    public int manhattanDistance = 0;
    public int g_n = 0;
    public final List<Integer> finalList = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0);

    /**
     * override del metodo clone per clonare la board
     * @return clonazione della board
     */
    @Override
    public Object clone() {
        try {
            BoardPrototype cloned = (BoardPrototype) super.clone();
            cloned.board = new Box[board.length][board[0].length];
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    cloned.board[i][j] = new BoxBuilder()
                            .setX(board[i][j].getX())
                            .setY(board[i][j].getY())
                            .setValue(board[i][j].getValue())
                            .setG_n(board[i][j].getG_n())
                            .setInitialX(board[i][j].getInitialX())
                            .setInitialY(board[i][j].getInitialY())
                            .setManhattanDistance(board[i][j].getManhattanDistance())
                            .build();
                }
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * override del metodo equals per confrontare due board
     * @param o board da confrontare
     * @return true se uguale, false se diversa
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof BoardPrototype that) {
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    if (board[i][j].getValue() != that.board[i][j].getValue())
                        return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * override del metodo hashCode che permette di calcolare
     * un codice di Hash per il confronto tra due board
     * @return risultato dell'hashCode
     */
    @Override
    public int hashCode() {
        int result = Arrays.hashCode(board[0]);
        for (int i = 1; i < board.length; i++) {
            result = 31 * result + Arrays.hashCode(board[i]);
        }
        return result;
    }

    /**
     * metodo set per board
     * @param board composta da Box[][]
     */
    public void setBoard(Box[][] board) {
        this.board = board;
    }

    public void setManhattanDistance(int manhattanDistance) {
        this.manhattanDistance = manhattanDistance;
    }

    /**
     * metodo che ritorna la distanza di Manhattan dell'intera board
     * @return distanza di Manhattan
     */
    public int getManhattanDistance() {
        manhattanDistance = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                manhattanDistance += board[i][j].getManhattanDistance();
            }
        }
        return manhattanDistance;
    }

    /**
     * metodo set per g(n)
     * @param g_n della board
     */
    public void setG_n(int g_n) {
        this.g_n = g_n;
    }

    /**
     * metodo get per g(n)
     * @return g(n)
     */
    public int getG_n() {
        return this.g_n;
    }

    /**
     * metodo che permette di scambiare gli elementi a index1 e index2
     * @param index1 indice unidimensionale del primo elemento
     * @param index2 indice unidimensionale del secondo elemento
     * @return copia della board con elementi scambiati
     */
    public BoardPrototype swap(int index1, int index2) {
        BoardPrototype copy = (BoardPrototype) this.clone();
        // x e y del primo elemento
        int row1 = index1 / 4;
        int col1 = index1 % 4;
        // x e y del secondo elemento
        int row2 = index2 / 4;
        int col2 = index2 % 4;

        // swap dei valori presenti nei box
        copy.board[row1][col1].setValue(copy.board[row2][col2].getValue());
        copy.board[row2][col2].setValue(0);

        int initialX1 = copy.board[row1][col1].getInitialX();
        int initialY1 = copy.board[row1][col1].getInitialY();
        int initialX2 = copy.board[row2][col2].getInitialX();
        int initialY2 = copy.board[row2][col2].getInitialY();

        // swap dei valori della initialX e initialY delle due box
        copy.board[row1][col1].setInitialX(initialX2);
        copy.board[row2][col2].setInitialX(initialX1);
        copy.board[row1][col1].setInitialY(initialY2);
        copy.board[row2][col2].setInitialY(initialY1);

        // setto le distanze di Manhattan dopo averle calcolate
        copy.board[row1][col1].setManhattanDistance(copy.board[row1][col1].getManhattan(index2, copy.board[row1][col1].getValue()));
        copy.board[row2][col2].setManhattanDistance(copy.board[row2][col2].getManhattan(index1, copy.board[row2][col2].getValue()));

        // swap dei valori di g(n)
        copy.board[row1][col1].setG_n(copy.board[row2][col2].getG_n() + 1);
        copy.board[row2][col2].setG_n(copy.board[row1][col1].getG_n() + 1);

        // update degli observer
        copy.board[row1][col1].update();
        copy.board[row2][col2].update();

        return copy;
    }

    /**
     * funzione che permette di calcolare i possibili movimenti in ogni board
     * @param board di cui calcolare i vicini
     * @return arraylist con i possibili vicini
     */
    public List<BoardPrototype> neighbors(BoardPrototype board) {
        ArrayList<BoardPrototype> neighbors = new ArrayList<>();
        int blankIndex = board.getBlankIndex();
        int row = blankIndex / 4;
        int col = blankIndex % 4;

        // verifica se il blank può spostarsi in alto
        if (row > 0) {
            BoardPrototype cloned = (BoardPrototype) board.clone();
            cloned = cloned.swap(blankIndex, blankIndex - 4);
            cloned.setManhattanDistance(cloned.getManhattanDistance());
            neighbors.add(cloned);
        }
        // verifica se il blank può spostarsi a sinistra
        if (col > 0) {
            BoardPrototype cloned = (BoardPrototype) board.clone();
            cloned = cloned.swap(blankIndex, blankIndex - 1);
            cloned.setManhattanDistance(cloned.getManhattanDistance());
            neighbors.add(cloned);
        }
        // verifica se il blank può spostarsi in basso
        if (row < 3) {
            BoardPrototype cloned = (BoardPrototype) board.clone();
            cloned = cloned.swap(blankIndex, blankIndex + 4);
            cloned.setManhattanDistance(cloned.getManhattanDistance());
            neighbors.add(cloned);
        }
        // verifica se il blank può spostarsi a destra
        if (col < 3) {
            BoardPrototype cloned = (BoardPrototype) board.clone();
            cloned = cloned.swap(blankIndex, blankIndex + 1);
            cloned.setManhattanDistance(cloned.getManhattanDistance());
            neighbors.add(cloned);
        }
        return neighbors;
    }

    /**
     * metodo che ritorna l'indice unidimensionale del blank
     * @return indice unidimensionale
     */
    public int getBlankIndex() {
        int index = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(this.board[i][j].getValue() == 0) {
                    index = i * 4 + j;
                }
            }
        }
        return index;
    }

    /**
     * metodo che controlla se il gioco è risolto
     * @return true se risolto, false se non è risolto
     */
    public boolean isSolved() {
        int count = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[i][j].getValue() == finalList.get(count)) {
                    count++;
                    if (count == 16) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
