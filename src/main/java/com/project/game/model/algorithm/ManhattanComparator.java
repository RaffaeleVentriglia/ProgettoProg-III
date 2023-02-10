package com.project.game.model.algorithm;

import com.project.game.model.board.BoardPrototype;
import java.util.Comparator;

public class ManhattanComparator implements Comparator<BoardPrototype> {
    @Override
    public int compare(BoardPrototype board1, BoardPrototype board2) {
        int f1 = board1.getManhattanDistance() + board1.getG_n();
        int f2 = board2.getManhattanDistance() + board2.getG_n();
        // Integer.compare ritorna:
        //          -1 se f1 è minore di f2
        //           1 se f1 è maggiore di f2
        //           0 se f1 è uguale a f2
        return Integer.compare(f1, f2);
    }
}