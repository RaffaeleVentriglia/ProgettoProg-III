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
                boxes[i][j].setX(i);
                boxes[i][j].setY(j);
            }
        }
        initialList.setBoard(boxes);
        for(boolean isSolvable = false; !isSolvable;) {
            ArrayList<Integer> num = new ArrayList<>(16);
            for(int i = 0; i < 16; i++) {
                num.add(i);
            }
            Collections.shuffle(num);
            int index = 0; // indice per iterare tutti gli elementi di num
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    int value = num.get(index); // valore della casella all'interno di num alla posizione index
                    int index2 = i * 4 + j; // indice unidimensionale della casella presa da [i][j]
                    boxes[i][j].setValue(num.get(index)); // setto il valore del box
                    boxes[i][j].setManhattanDistance(boxes[i][j].getManhattan(value,index2)); // chiamo la funzione per settare la distanza di Manhattan per ogni box
                    index++;
                }
            }
            isSolvable = isSolvable(initialList); // controllo se la lista è risolvibile
        }
        return initialList;
    }

    /**
     * Funzione che permette di controllare se il puzzle creato è risolvibile o meno
     * La teoria che determina se un gioco del 15 è risolvibile o meno è basata sul calcolo del numero
     * d'inversioni presenti nella disposizione iniziale delle caselle. Il gioco è risolvibile se il numero
     * d'inversioni è pari. Se il numero d'inversioni è dispari, il gioco non è risolvibile.
     * @param list lista contenente i numeri generati
     * @return vero se risolvibile, falso se non lo è
     */
    private boolean isSolvable(Board list) {
        int[] flat = new int[15];
        int inversionSum = 0;
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if (list.board[i][j].getValue() == 0) {
                    inversionSum += i;
                } else {
                    flat[index++] = list.board[i][j].getValue();
                }
            }
        }
        for (int i = 0; i < 14; i++) {
            for (int j = i + 1; j < 15; j++) {
                if (flat[i] > flat[j]) {
                    inversionSum++;
                }
            }
        }
        return (inversionSum + 4 - (index / 4)) % 2 == 0;
    }

    /**
     * metodo che permette la risoluzione del puzzle
     * @param list ArrayList del gioco
     */
    public void solve(Board list) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print(list.board[i][j].getManhattanDistance() + " ");
            }
            System.out.println();
        }
        System.out.println("Distance: " + initialList.getManhattanDistance());
        game15Solver.aStar();
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
