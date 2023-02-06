package com.project.game.model.algorithm;

import com.project.game.model.board.BoardPrototype;

import java.util.*;

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
     * metodo che applica l'algoritmo A* con distanza di Manhattan
     * @param board configurazione su cui applicare l'algoritmo
     */
    /*
    public void aStar(BoardPrototype board) {
        int maxIterations = 150; // numero massimo d'iterazioni
        int iterations = 0; // contatore d'iterazioni
        BoardPrototype current;

        PriorityQueue<BoardPrototype> openList = new PriorityQueue<>(new ManhattanComparator());
        Map<BoardPrototype, Integer> closeList = new HashMap<>();
        openList.add(board);
        while (!openList.isEmpty() && iterations < maxIterations) {
            current = openList.poll();
            closeList.put(current, iterations);
            //closeList.clear();
            System.out.println("Soluzione scelta: " + current.getManhattanDistance());
            printMatrix(current);
            if (current.isSolved()) {
                printMatrix(current);
                return;
            } else {
                for (BoardPrototype neighbor : current.neighbors(current)) {
                    if (!closeList.containsKey(neighbor) && !openList.contains(neighbor)) {
                        openList.add(neighbor);
                        closeList.put(neighbor, iterations + 1);
                    }
                }
            }
            iterations++;
        }
    }

     */

    public BoardPrototype aStar(BoardPrototype board) {
        int maxIterations = 100; // numero massimo d'iterazioni
        int iterations = 0; // contatore d'iterazioni
        BoardPrototype current;

        PriorityQueue<BoardPrototype> openList = new PriorityQueue<>(new ManhattanComparator());
        Set<BoardPrototype> closeList = new HashSet<>();
        openList.add(board);
        while (!openList.isEmpty() && iterations < maxIterations) {
            current = openList.poll();
            closeList.add(current);
            printMatrix(current);
            System.out.println("G(N): " + current.getG_n());
            if (current.isSolved()) {
                System.out.println("Soluzione trovata in xxxx iterazioni: ");
                printMatrix(current);
                return current;
            } else {
                for (BoardPrototype neighbor : current.neighbors(current)) {
                    if (!closeList.contains(neighbor) && !openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
            iterations++;
        }
        System.out.println("Soluzione non trovata");
        return null;
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
