package com.project.game.model.algorithm;

import com.project.game.model.board.BoardPrototype;

import java.util.*;

public class Game15Solver extends BoardComparator {
    private static Game15Solver game15Solver;
    BoardComparator boardComparator = new BoardComparator();

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
    public List<BoardPrototype> AStar(BoardPrototype board) {
        PriorityQueue<BoardPrototype> openList = new PriorityQueue<>(boardComparator);
        List<BoardPrototype> closeList = new ArrayList<>();
        openList.add(board);
        while (!openList.isEmpty()) {
            BoardPrototype current = openList.poll();
            closeList.add(current);
            openList.clear();
            if (current.isSolved()) {
                System.out.println("Soluzione trovata");
            } else {
                for (BoardPrototype neighbor : current.neighbors(current)) {
                    if (!closeList.contains(neighbor)) {
                        if(!openList.contains(neighbor)) {
                            neighbor.setG_n(current.getG_n() + 1);
                            openList.add(neighbor);
                        } else {
                            var tmpList = (Arrays.asList(openList.toArray()));
                            int ind = tmpList.indexOf(neighbor);
                            BoardPrototype boardPrototype = (BoardPrototype) tmpList.get(ind);
                            if(neighbor.getG_n() + 1 < boardPrototype.getG_n()) {
                                openList.remove(neighbor);
                                neighbor.setG_n(current.getG_n() + 1);
                                openList.add(neighbor);
                            }
                        }
                    }
                }
            }
        }
        return closeList;
    }
}