package com.project.game.model.algorithm;

import com.project.game.model.board.BoardPrototype;
import java.util.Comparator;

public class ManhattanComparator implements Comparator<BoardPrototype> {
    @Override
    public int compare(BoardPrototype board1, BoardPrototype board2) {
        int f1 = board1.getManhattanDistance() + board1.getG_n();
        int f2 = board2.getManhattanDistance() + board2.getG_n();
        // Integer.compare ritorna:
        //          -1 se manhattan1 è minore di manhattan2
        //           1 se manhattan1 è maggiore di manhattan2
        //           0 se manhattan1 è uguale a manhattan2
        return Integer.compare(f1, f2);
    }
}
