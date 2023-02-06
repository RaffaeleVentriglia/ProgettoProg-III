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
                    cloned.board[i][j] = new Box();
                    cloned.board[i][j].setX(board[i][j].getX());
                    cloned.board[i][j].setY(board[i][j].getY());
                    cloned.board[i][j].setValue(board[i][j].getValue());
                    cloned.board[i][j].setG_n(board[i][j].getG_n());
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardPrototype that = (BoardPrototype) o;
        if (manhattanDistance != that.manhattanDistance) return false;
        if (!Arrays.deepEquals(board, that.board)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(board[0]);
        for (int i = 1; i < board.length; i++) {
            result = 31 * result + Arrays.hashCode(board[i]);
        }
        return result;
    }

    /**
     * metodo get per board
     * @return board
     */
    public Box[][] getBoard() {
        return board;
    }

    /**
     * metodo set per board
     * @param board composta da Box[][]
     */
    public void setBoard(Box[][] board) {
        this.board = board;
    }

    /**
     * metodo set per manhattanDistance
     * @param manhattanDistance distanza di Manhattan
     */
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



    public void setG_n(int g_n) {
        this.g_n = g_n;
    }

    public int getG_n() {
        g_n = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                g_n += board[i][j].getG_n();
            }
        }
        return g_n;
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

        copy.board[row1][col1].setValue(copy.board[row2][col2].getValue());
        copy.board[row2][col2].setValue(0);
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
            for(int i = 0; i < 4; ++i) {
                for(int j = 0; j < 4; ++j) {
                    int index = i * 4 + j;
                    cloned.board[i][j].setManhattanDistance(cloned.board[i][j].getManhattan(cloned.board[i][j].getValue(), index));
                    cloned.board[i][j].setG_n(cloned.board[i][j].getG(blankIndex - 4));
                }
            }
            neighbors.add(cloned);
        }
        // verifica se il blank può spostarsi a sinistra
        if (col > 0) {
            BoardPrototype cloned = (BoardPrototype) board.clone();
            cloned = cloned.swap(blankIndex, blankIndex - 1);
            for(int i = 0; i < 4; ++i) {
                for(int j = 0; j < 4; ++j) {
                    int index = i * 4 + j;
                    cloned.board[i][j].setManhattanDistance(cloned.board[i][j].getManhattan(cloned.board[i][j].getValue(), index));
                    cloned.board[i][j].setG_n(cloned.board[i][j].getG(blankIndex - 1));
                }
            }
            neighbors.add(cloned);
        }
        // verifica se il blank può spostarsi in basso
        if (row < 3) {
            BoardPrototype cloned = (BoardPrototype) board.clone();
            cloned = cloned.swap(blankIndex, blankIndex + 4);
            for(int i = 0; i < 4; ++i) {
                for(int j = 0; j < 4; ++j) {
                    int index = i * 4 + j;
                    cloned.board[i][j].setManhattanDistance(cloned.board[i][j].getManhattan(cloned.board[i][j].getValue(), index));
                    cloned.board[i][j].setG_n(cloned.board[i][j].getG(blankIndex + 4));
                }
            }
            neighbors.add(cloned);
        }
        // verifica se il blank può spostarsi a destra
        if (col < 3) {
            BoardPrototype cloned = (BoardPrototype) board.clone();
            cloned = cloned.swap(blankIndex, blankIndex + 1);
            for(int i = 0; i < 4; ++i) {
                for(int j = 0; j < 4; ++j) {
                    int index = i * 4 + j;
                    cloned.board[i][j].setManhattanDistance(cloned.board[i][j].getManhattan(cloned.board[i][j].getValue(), index));
                    cloned.board[i][j].setG_n(cloned.board[i][j].getG(blankIndex + 1));
                }
            }
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
                    break;
                }
            }
        }
        return false;
    }

}
