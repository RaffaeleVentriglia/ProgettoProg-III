package com.project.game.model.game;

import com.project.game.model.algorithm.Game15Solver;
import com.project.game.model.board.BoardPrototype;
import com.project.game.model.board.Box;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game {
    private static Game game;
    public static BoardPrototype boardPrototype = new BoardPrototype();
    public Box[][] boxes = new Box[4][4];
    private final List<Integer> num = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0);
    private final ArrayList<Integer> finalList = new ArrayList<>();
    Game15Solver game15Solver = Game15Solver.getInstance();

    /**
     * costruttore privato per applicare Singleton
     */
    private Game() {}

    /**
     * metodo per ritornare l'istanza unica di game
     * @return istanza unica di game2
     */
    public static Game getInstance() {
        if(game == null) {
            game = new Game();
        }
        return game;
    }

    /**
     * metodo che inizializza la board
     * @return board inizializzata
     */
    public BoardPrototype initializeBoard() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                boxes[i][j] = new Box();
                boxes[i][j].setX(i);
                boxes[i][j].setY(j);
            }
        }
        boardPrototype.setBoard(boxes);
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
                    boxes[i][j].setInitialX(i); // imposto la x iniziale
                    boxes[i][j].setInitialY(j); // imposto la y iniziale
                    index++;
                }
            }
            isSolvable = isSolvable(boardPrototype); // controllo se la lista è risolvibile
        }
        return boardPrototype;
    }

    /**
     * Funzione che permette di controllare se il puzzle creato è risolvibile o meno
     * La teoria che determina se un gioco del 15 è risolvibile o meno è basata sul calcolo del numero
     * d'inversioni presenti nella disposizione iniziale delle caselle. Il gioco è risolvibile se il numero
     * d'inversioni è pari. Se il numero d'inversioni è dispari, il gioco non è risolvibile.
     * @param list lista contenente i numeri generati
     * @return vero se risolvibile, falso se non lo è
     */
    private boolean isSolvable(BoardPrototype list) {
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
    public void solve(BoardPrototype list) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print(list.board[i][j].getManhattanDistance() + " ");
            }
            System.out.println();
        }
        System.out.println("Distance: " + boardPrototype.getManhattanDistance());
        game15Solver.aStar(list);
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
                if(boardPrototype.board[i][j].getValue() == finalList.get(count)) {
                    count++;
                } else {
                    break;
                }
            }
        }
        return count == 16;
    }
}
