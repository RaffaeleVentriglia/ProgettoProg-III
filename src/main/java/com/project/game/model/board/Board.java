package com.project.game.model.board;

public class Board {
    private static Board instance;
    public Box[][] board;

    /**
     * costruttore privato per applicare il Singleton
     */
    private Board() {
        board = new Box[3][3];
    }

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
     * metodo per inizializzare la board
     */
    public void setupBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Box();
            }
        }
    }
}
