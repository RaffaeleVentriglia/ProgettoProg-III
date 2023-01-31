package com.project.game.model.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private static Board instance;
    public Box[][] board;
    public int manhattanDistance = 0;
    private final List<Integer> num = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0);
    private final ArrayList<Integer> finalList = new ArrayList<>();

    /**
     * costruttore privato per applicare il Singleton
     */
    private Board() {}

    /**
     * metodo per attuare Singleton e ritornare l'unica istanza della board
     * @return instance
     */
    public static Board getInstance() {
        if(instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public Board copyBoard() {
        Board copy = new Board();
        copy.setBoard(this.getBoard());
        copy.setManhattanDistance(this.getManhattanDistance());
        return copy;
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

    /**
     * metodo che permette di scambiare gli elementi agli indici unidimensionali i1 e i2
     * @param i1 indice unidimensionale del primo elemento
     * @param i2 indice unidimensionale del secondo elemento
     * @return board clonata con elementi spostati
     */
    public Board swap(int i1, int i2) {
        Board copy = this.copyBoard();
        // x e y del primo elemento
        int row1 = i1 / 4;
        int col1 = i1 % 4;
        // x e y del secondo elemento
        int row2 = i2 / 4;
        int col2 = i2 % 4;

        int tmp = copy.board[row1][col1].getValue();
        copy.board[row1][col1].setValue(copy.board[row2][col2].getValue());
        copy.board[row2][col2].setValue(tmp);
        return copy;
    }

    /**
     * attraverso questo metodo generiamo una nuova board muovendo la cella vuota
     * @return board con la cella vuota spostata
     */
    public List<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<>();
        int blankIndex = this.getBlankIndex();
        int row = blankIndex / 4;
        int col = blankIndex % 4;

        // verifica se il blank può spostarsi in alto
        if (row > 0) {
            Board newBoard = this.swap(blankIndex, blankIndex - 4);
            neighbors.add(newBoard);
        }
        // verifica se il blank può spostarsi a sinistra
        if (col > 0) {
            Board newBoard = this.swap(blankIndex, blankIndex - 1);
            neighbors.add(newBoard);
        }
        // verifica se il blank può spostarsi in basso
        if (row < 3) {
            Board newBoard = this.swap(blankIndex, blankIndex + 4);
            neighbors.add(newBoard);
        }
        // verifica se il blank può spostarsi a destra
        if (col < 3) {
            Board newBoard = this.swap(blankIndex, blankIndex + 1);
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
     * attraverso questo metodo teniamo traccia di tutti i passaggi
     * effettuati per risolvere il puzzle
     * @return configurazione corrente della board
     */

    /*
    public List<Box[][]> path() {
        List<Box[][]> path = new ArrayList<>();
        Board current = this;
        while (current.depth != 0) {
            path.add(0, current.board);
            if(cameFrom.containsKey(current.hashCode()))
                current = cameFrom.get(current.hashCode());
            else
                throw new IllegalStateException("cameFrom map doesn't contain current state");
        }
        return path;
    }
     */
}
