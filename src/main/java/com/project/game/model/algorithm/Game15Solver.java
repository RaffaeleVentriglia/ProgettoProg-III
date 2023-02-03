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
     * metodo che applica l'algoritmo A* con distanza di Manhattan
     * @param board configurazione su cui applicare l'algoritmo
     * @return la board risolta, ma sarebbe meglio ritornare un'arraylist che contiene la strada da seguire
     */
    public BoardPrototype aStar(BoardPrototype board) {
        int maxIterations = 50; // numero massimo d'iterazioni
        int iterations = 0; // contatore d'iterazioni

        PriorityQueue<BoardPrototype> openList = new PriorityQueue<>(new ManhattanComparator());
        Set<BoardPrototype> closeList = new HashSet<>();
        openList.add(board);
        while (!openList.isEmpty() && iterations < maxIterations) {
            BoardPrototype current = openList.poll();
            //openList.clear();
            closeList.clear();
            closeList.add(current);
            System.out.println("Soluzione scelta");
            System.out.println(current.getManhattanDistance());
            printMatrix(current);
            if (current.isSolved()) {
                printMatrix(current);
                return current;
            }
            for (BoardPrototype neighbor : current.neighbors(current)) {
                if (!closeList.contains(neighbor) && !openList.contains(neighbor)) {
                    openList.add(neighbor);
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
