package com.project.game.model.board;

import com.project.game.model.algorithm.Game15Solver;

import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Board {
    private static Board instance;
    public int[][] board;
    int blankX;
    int blankY;
    public int depth;
    HashMap<MatrixWrapper, Board> cameFrom;
    private static final Logger LOGGER = Logger.getLogger(Game15Solver.class.getName());
    static {
        try {
            FileHandler fileHandler = new FileHandler("game15.log");
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public Board(int[][] board, int blankX, int blankY, int depth) {
        this.board = board;
        this.blankX = blankX;
        this.blankY = blankY;
        this.depth = depth;
    }

    /**
     * calcola la distanza di manhattan per la board
     * @return distanza di manhattan
     */
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    int targetX = (board[i][j] - 1) / 4;
                    int targetY = (board[i][j] - 1) % 4;
                    manhattan += Math.abs(i - targetX) + Math.abs(j - targetY);
                }
            }
        }
        return manhattan;
    }

    /**
     * attraverso questo metodo generiamo una nuova board muovendo la cella vuota
     * @return board con la cella vuota spostata
     */
    public List<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int x = blankX + dx[i];
            int y = blankY + dy[i];
            if (x >= 0 && x < 4 && y >= 0 && y < 4) {
                int[][] newBoard = new int[4][4];
                for(int m = 0; m < 4; m++)
                    newBoard[m] = board[m].clone();
                newBoard[blankX][blankY] = newBoard[x][y];
                newBoard[x][y] = 0;
                neighbors.add(new Board(newBoard, x, y, depth + 1));
            }
        }
        return neighbors;
    }

    /**
     * attraverso questo metodo teniamo traccia di tutti i passaggi
     * effettuati per risolvere il puzzle
     * @return configurazione corrente della board
     */
    public List<int[][]> path() {
        List<int[][]> path = new ArrayList<>();
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
}
