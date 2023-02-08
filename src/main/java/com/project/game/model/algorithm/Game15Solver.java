package com.project.game.model.algorithm;

import com.project.game.model.board.BoardPrototype;

import java.util.*;

public class Game15Solver {
    private static Game15Solver game15Solver;
    ManhattanComparator manhattanComparator = new ManhattanComparator();

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
    public BoardPrototype aStar(BoardPrototype board) {
        PriorityQueue<BoardPrototype> openList = new PriorityQueue<>(manhattanComparator);
        List<BoardPrototype> closeList = new ArrayList<>();
        openList.add(board);
        while (!openList.isEmpty()) {
            BoardPrototype current = openList.poll();
            closeList.add(current);
            //openList.clear();
            System.out.println("G(N): " + current.getG_n());
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    System.out.print(current.board[i][j].getG_n() + " ");
                }
                System.out.println();
            }
            System.out.println();
            if (current.isSolved()) {
                System.out.println("Soluzione trovata in xxxx iterazioni: ");
                printMatrix(current);
                return current;
            } else {
                for (BoardPrototype neighbor : current.neighbors(current)) {
                    System.out.println("Possibile soluzione");
                    System.out.println("G: " + neighbor.getG_n() + " Manhattan: " + neighbor.getManhattanDistance());
                    printMatrix(neighbor);
                    if (!closeList.contains(neighbor)) {
                        if(!openList.contains(neighbor)) {
                            neighbor.setG_n(current.getG_n() + 1);
                            openList.add(neighbor);
                        } else {
                            var tmpList = (Arrays.asList(openList.toArray()));
                            int ind = tmpList.indexOf(neighbor);
                            BoardPrototype prova = (BoardPrototype) tmpList.get(ind);
                            if(neighbor.getG_n() + 1 < prova.getG_n()) {
                                openList.remove(neighbor);
                                neighbor.setG_n(current.getG_n() + 1);
                                openList.add(neighbor);
                            }
                        }
                    }
                }
            }
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
    }
}