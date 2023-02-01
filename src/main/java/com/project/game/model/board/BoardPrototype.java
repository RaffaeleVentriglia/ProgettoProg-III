package com.project.game.model.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardPrototype implements Prototype, Cloneable {
    public Box[][] board;
    public int manhattanDistance = 0;
    public final List<Integer> num = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0);
    public final ArrayList<Integer> finalList = new ArrayList<>();

    @Override
    public Object clone() {
        try {
            BoardPrototype cloned = (BoardPrototype) super.clone();
            cloned.board = new Box[board.length][board[0].length];
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    cloned.board[i][j] = board[i][j];
                }
            }
            cloned.manhattanDistance = manhattanDistance;
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
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

    public BoardPrototype swap(int index1, int index2) {
        BoardPrototype copy = (BoardPrototype) this.clone();
        // x e y del primo elemento
        int row1 = index1 / 4;
        int col1 = index1 % 4;
        // x e y del secondo elemento
        int row2 = index2 / 4;
        int col2 = index2 % 4;

        int tmp = copy.board[row1][col1].getValue();
        copy.board[row1][col1].setValue(copy.board[row2][col2].getValue());
        copy.board[row2][col2].setValue(tmp);
        return copy;
    }

    /**
     * attraverso questo metodo generiamo una nuova board muovendo la cella vuota
     * @return board con la cella vuota spostata
     */
    public List<BoardPrototype> neighbors() {
        BoardPrototype newBoard;
        ArrayList<BoardPrototype> neighbors = new ArrayList<>();
        int blankIndex = this.getBlankIndex();
        int row = blankIndex / 4;
        int col = blankIndex % 4;

        // verifica se il blank può spostarsi in alto
        if (row > 0) {
            newBoard = this.swap(blankIndex, blankIndex - 4);
            neighbors.add(newBoard);
        }
        // verifica se il blank può spostarsi a sinistra
        if (col > 0) {
            newBoard = this.swap(blankIndex, blankIndex - 1);
            neighbors.add(newBoard);
        }
        // verifica se il blank può spostarsi in basso
        if (row < 3) {
            newBoard = this.swap(blankIndex, blankIndex + 4);
            neighbors.add(newBoard);
        }
        // verifica se il blank può spostarsi a destra
        if (col < 3) {
            newBoard = this.swap(blankIndex, blankIndex + 1);
            neighbors.add(newBoard);
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
        finalList.addAll(num);
        int count = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[i][j].getValue() == finalList.get(count)) {
                    count++;
                } else {
                    break;
                }
            }
        }
        return count == 16;
    }
}
