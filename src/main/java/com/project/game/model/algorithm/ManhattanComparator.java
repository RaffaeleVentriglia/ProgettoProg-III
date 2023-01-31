package com.project.game.model.algorithm;

import com.project.game.model.board.Board;
import java.util.Comparator;

public class ManhattanComparator implements Comparator<Board> {
    @Override
    public int compare(Board board1, Board board2) {
        int manhattan1 = board1.getManhattanDistance();
        int manhattan2 = board2.getManhattanDistance();
        // Integer.compare ritorna:
        //          -1 se manhattan1 è minore di manhattan2
        //           1 se manhattan1 è maggiore di manhattan2
        //           0 se manhattan1 è uguale a manhattan2
        return Integer.compare(manhattan1, manhattan2);
    }
}
