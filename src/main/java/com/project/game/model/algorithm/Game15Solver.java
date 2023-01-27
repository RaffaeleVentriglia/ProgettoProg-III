package com.project.game.model.algorithm;

import com.project.game.model.board.Board;
import com.project.game.model.board.Box;

import java.util.*;

public class Game15Solver {
    private static Game15Solver game15Solver;

    private Game15Solver() {}

    public static Game15Solver getInstance() {
        if(game15Solver == null) {
            game15Solver = new Game15Solver();
        }
        return game15Solver;
    }

    /*
    public static List<int[][]> aStar(Box[][] start) {
        PriorityQueue<Board> queue = new PriorityQueue<>(Comparator.comparingInt(a -> (a.depth + a.manhattan())));
        int blankX = 0;
        int blankY = 0;
        for (int i = 0; i < start.length; i++) {
            for (int j = 0; j < start[i].length; j++) {
                if (start[i][j].getValue() == 0) {
                    blankX = i;
                    blankY = j;
                    break;
                }
            }
        }
        queue.offer(new Board(start, blankX, blankY, 0));
        while (!queue.isEmpty()) {
            Board current = queue.poll();
            if (current.manhattan() == 0) {
                return current.path();
            }
            for (Board neighbor : current.neighbors()) {
                printMatrix(neighbor.board);
                queue.offer(neighbor);
            }
        }
        return null;
    }
     */

    public static void printMatrix(Box[][] matrix) {
        for (Box[] ints : matrix) {
            for (Box anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
