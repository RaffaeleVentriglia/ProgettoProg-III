package com.project.game.model.algorithm;

import com.project.game.model.board.Board;

import java.util.PriorityQueue;

public class Game15Solver {
    private static Game15Solver game15Solver;

    private Game15Solver() {}

    public static Game15Solver getInstance() {
        if(game15Solver == null) {
            game15Solver = new Game15Solver();
        }
        return game15Solver;
    }

    public static void aStar(int[][] start) {
        PriorityQueue<Board> queue = new PriorityQueue<>((a, b) -> (a.depth + a.manhattan()) - (b.depth + b.manhattan()));
        int blankX = 0;
        int blankY = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (start[i][j] == 0) {
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
                current.path();
                return;
            }
            for (Board neighbor : current.neighbors()) {
                printMatrix(neighbor.board);
                queue.offer(neighbor);
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
