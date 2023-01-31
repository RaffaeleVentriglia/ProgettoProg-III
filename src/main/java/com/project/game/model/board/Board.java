package com.project.game.model.board;

public class Board {
    private static Board instance;
    public Box[][] board;
    public int manhattanDistance = 0;

    /**
     * costruttore privato per applicare il Singleton
     */
    private Board() {}

    /**
     * metodo per attuare Singleton e ritornare l'unica istanza della board
     * @return instance
     */
    public static Board getInstance() {
        if(instance == null) {
            instance = new Board();
        }
        return instance;
    }

    /**
     * metodo get per board
     * @return board
     */
    public Box[][] getBoard() {
        return board;
    }

    /**
     * metodo set per board
     * @param board composta da Box[][]
     */
    public void setBoard(Box[][] board) {
        this.board = board;
    }

    public void swap(int row1, int col1, int row2, int col2) {
        Box temp = board[row1][col1];
        board[row1][col1] = board[row2][col2];
        board[row2][col2] = temp;
    }

    public int getManhattanDistance() {
        manhattanDistance = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                manhattanDistance += board[i][j].getManhattanDistance();
            }
        }
        return manhattanDistance;
    }

    /**
     * attraverso questo metodo generiamo una nuova board muovendo la cella vuota
     * @return board con la cella vuota spostata
     */

    /*
    public List<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int x = blankX + dx[i];
            int y = blankY + dy[i];
            if (x >= 0 && x < 4 && y >= 0 && y < 4) {
                int[][] newBoard = new int[4][4];
                for(int m = 0; m < 4; m++)
                    newBoard[m] = board[m].clone();
                newBoard[blankX][blankY] = newBoard[x][y];
                newBoard[x][y] = 0;
                neighbors.add(new Board(newBoard, x, y, depth + 1));
            }
        }
        return neighbors;
    }
     */

    /**
     * attraverso questo metodo teniamo traccia di tutti i passaggi
     * effettuati per risolvere il puzzle
     * @return configurazione corrente della board
     */

    /*
    public List<Box[][]> path() {
        List<Box[][]> path = new ArrayList<>();
        Board current = this;
        while (current.depth != 0) {
            path.add(0, current.board);
            if(cameFrom.containsKey(current.hashCode()))
                current = cameFrom.get(current.hashCode());
            else
                throw new IllegalStateException("cameFrom map doesn't contain current state");
        }
        return path;
    }
     */
}
