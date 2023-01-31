package com.project.game.model.algorithm;

import com.project.game.model.board.Board;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Game15Solver {
    private static Game15Solver game15Solver;
    public Board board = Board.getInstance();

    private Game15Solver() {}

    public static Game15Solver getInstance() {
        if(game15Solver == null) {
            game15Solver = new Game15Solver();
        }
        return game15Solver;
    }

    public Board aStar() {
        int maxIterations = 100; // numero massimo d'iterazioni
        int iterations = 0; // contatore d'iterazioni

        PriorityQueue<Board> priorityQueue = new PriorityQueue<>(new ManhattanComparator());
        Set<Board> visited = new HashSet<>();
        priorityQueue.add(board);

        while (!priorityQueue.isEmpty() && iterations < maxIterations) {
            Board current = priorityQueue.poll();
            printMatrix(current);
            if (current.isSolved()) {
                printMatrix(current);
                return current;
            }
            visited.add(current);
            for (Board neighbor : current.neighbors()) {
                if (!visited.contains(neighbor)) {
                    //printMatrix(neighbor);
                    priorityQueue.add(neighbor);
                }
            }
            iterations++;
        }
        return null;
    }


    /**
     * funzione che permette di stampare la matrice
     * @param matrix da stampare
     */
    public static void printMatrix(Board matrix) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print(matrix.board[i][j].getValue() + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
