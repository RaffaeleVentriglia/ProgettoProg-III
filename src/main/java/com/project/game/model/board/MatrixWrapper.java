package com.project.game.model.board;

import java.util.Arrays;

public class MatrixWrapper {
    private final int[][] matrix;

    public MatrixWrapper(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MatrixWrapper)) {
            return false;
        }
        return Arrays.deepEquals(matrix, ((MatrixWrapper) obj).matrix);
    }
}

