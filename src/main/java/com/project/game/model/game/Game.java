package com.project.game.model.game;

import com.project.game.model.algorithm.Game15Solver;
import com.project.game.model.board.Board;
import com.project.game.model.board.Box;

import java.util.*;

/**
 * commento per vari ragionamenti
 *
 * gestire l'intero carico del gioco da questa classe, soprattutto il movimento dei numeri tra le varie caselle,
 * mentre nella gameController spostare semplicemente le caselle graficamente
 *
 * risolvere il gioco prima come arrayList e a ogni passo inviare al
 * gameController la lista in modo tale da spostare gli elementi
 */


public class Game {
    private static Game game;
    private final List<Integer> num = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0);
    private final ArrayList<Integer> finalList = new ArrayList<>();
    public Board initialList = Board.getInstance();
    Box[][] boxes = new Box[4][4];
    Game15Solver game15Solver = Game15Solver.getInstance();

    /**
     * costruttore privato per applicare il Singleton
     */
    private Game() {}

    /**
     * metodo con cui restituiamo l'unica istanza della partita
     * @return game
     */
    public static Game getInstance() {
        if(game == null) {
            game = new Game();
        }
        return game;
    }

    /**
     * metodo che crea la board
     */
    public Board initializeBoard() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                boxes[i][j] = new Box();
            }
        }
        initialList.setBoard(boxes);
        for(boolean isSolvable = false; !isSolvable;) {
            ArrayList<Integer> num = new ArrayList<>(16);
            for(int i = 0; i < 16; i++) {
                num.add(i);
            }
            Collections.shuffle(num);
            int index = 0;
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    boxes[i][j].setValue(num.get(index));
                    boxes[i][j].setX(i);
                    boxes[i][j].setY(j);
                    index++;
                }
            }
            isSolvable = isSolvable(initialList);
        }
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print(initialList.board[i][j].getValue() + " ");
            }
            System.out.println(" ");
        }
        return initialList;
    }

    /**
     * funzione che permette di controllare se il puzzle creato è risolvibile o meno
     * @param list lista contenente i numeri generati
     * @return vero se risolvibile, falso se non lo è
     */
    private boolean isSolvable(Board list) {
        int inversionSum = 0;
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if (list.board[i][j].getValue() == 0) {
                    inversionSum += (i + j);
                }
                for (int x = i; x < 4; x++) {
                    for (int y = j; y < 4; y++) {
                        if (list.board[i][j].getValue() > list.board[x][y].getValue() && list.board[x][y].getValue() != 0) {
                            inversionSum++;
                        }
                    }
                }
            }
        }
        return inversionSum % 2 == 0;
    }

    /**
     * metodo che permette la risoluzione del puzzle
     * @param list ArrayList del gioco
     */
    public void solve(Board list) {
        /*
        int[][] listToArray = new int[4][4];
        for (int i = 0; i < listToArray.length; i++) {
            for (int j = 0; j < listToArray[i].length; j++) {
                listToArray[i][j] = list.get(i * 4 + j);
            }
        }
         */
        //game15Solver.aStar(listToArray);
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                list.board[i][j].setManhattanDistance(list.board[i][j].getManhattan(i,j));
                System.out.println(list.board[i][j].getManhattan(i,j));
            }
        }
        /*
        while(!isFinished()) {
            game15Solver.makeMove();
        }
         */
    }

    /**
     * metodo che permette di controllare se il puzzle è completato
     * @return true se risolto, false se non è risolto
     */
    public boolean isFinished() {
        finalList.addAll(num);
        int count = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(initialList.board[i][j].getValue() == finalList.get(count)) {
                    count++;
                } else {
                    break;
                }
            }
        }
        return count == 16;
    }
}
