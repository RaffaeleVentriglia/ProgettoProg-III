package com.project.game.model.algorithm;

import com.project.game.model.board.BoardPrototype;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Game15Solver {
    private static Game15Solver game15Solver;

    /**
     * costruttore privato per applicare il Singleton
     */
    private Game15Solver() {}

    /**
     * metodo per ritornare l'istanza unica del risolutore
     * @return istanza unica del risolutore
     */
    public static Game15Solver getInstance() {
        if(game15Solver == null) {
            game15Solver = new Game15Solver();
        }
        return game15Solver;
    }

    /**
     * metodo che applico l'algoritmo A* sulla board
     * @param board sulla quale applicare l'algoritmo
     * @return board corretta, oppure una delle soluzioni da seguire per arrivare alla soluzione
     */
    public BoardPrototype aStar(BoardPrototype board) {
        int maxIterations = 100; // numero massimo d'iterazioni
        int iterations = 0; // contatore d'iterazioni

        PriorityQueue<BoardPrototype> priorityQueue = new PriorityQueue<>(new ManhattanComparator());
        Set<BoardPrototype> visited = new HashSet<>();
        priorityQueue.add(board);

        while (!priorityQueue.isEmpty() && iterations < maxIterations) {
            BoardPrototype current = priorityQueue.poll();
            priorityQueue.clear();
            System.out.println(current.getManhattanDistance());
            //printMatrix(current);
            if (current.isSolved()) {
                printMatrix(current);
                return current;
            }
            visited.add(current);
            for (BoardPrototype neighbor : current.neighbors()) {
                if (!visited.contains(neighbor)) {
                    priorityQueue.add(neighbor);
                }
            }
            iterations++;
        }
        return board;
    }

    /**
     * funzione che permette di stampare la matrice
     * @param matrix da stampare
     */
    public static void printMatrix(BoardPrototype matrix) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print(matrix.board[i][j].getValue() + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
