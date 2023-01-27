package com.project.game.model.game;

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
    public ArrayList<Integer> initialList = new ArrayList<>(16);
    public ArrayList<Box> initial = new ArrayList<>(16);
    public Board initialLIST = Board.getInstance();

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
    public ArrayList<Integer> initializeBoard() {
        for(boolean isSolvable = false; !isSolvable;) {
            // creo la lista ordinata
            initialList = new ArrayList<>(16);
            for(int i = 0; i < 16; i++) {
                // inserisco l'elemento i alla posizione i
                initialList.add(i,i);
            }
            // mischio la lista
            Collections.shuffle(initialList);
            // controllo se la lista è risolvibile o no
            isSolvable = isSolvable(initialList);
        }
        System.out.println(initialList);
        return initialList;
    }

    public Board initialize() {
        for(boolean isSolvable = false; !isSolvable;) {
            ArrayList<Integer> num = new ArrayList<>(16);
            for(int i = 0; i < 16; i++) {
                num.add(i);
            }
            Collections.shuffle(num);
            int index = 0;
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    initialLIST.board[i][j].setValue(num.get(index));
                    index++;
                }
            }
            isSolvable = isSolvable2(initialLIST);
        }
        System.out.println(initialLIST);
        return initialLIST;
    }

    /**
     * funzione che permette di controllare se il puzzle creato è risolvibile o meno
     * @param list lista contenente i numeri generati
     * @return vero se risolvibile, falso se non lo è
     */
    private boolean isSolvable(ArrayList<Integer> list) {
        if (list.size() != 16) {
            System.err.println("La funzione isSolvable funziona unicamente con una lista avente valori da 0 a 16");
        }
        // se inversionSum è pari allora è risolvibile
        int inversionSum = 0;
        for (int i = 0; i < list.size(); i++) {
            // per il bottone vuoto aggiungi il numero della riga alla somma
            if (list.get(i) == 0) {
                inversionSum += ((i / 4) + 1);
                continue;
            }
            int count = 0;
            for (int j = i + 1; j < list.size(); j++) {
                // se il quadrato è vuoto non viene contato
                if (list.get(j) == 0) {
                    continue;
                }
                // se trovo un elemento più grande dell'elemento preso in considerazione allora incremento count
                else if (list.get(i) > list.get(j)) {
                    count++;
                }
            }
            inversionSum += count;
        }
        // se inversionSum è pari ritorna true, altrimenti false
        return inversionSum % 2 == 0;
    }

    private boolean isSolvable2(Board list) {
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
    public void solve(ArrayList<Integer> list) {
        int[][] listToArray = new int[4][4];
        for (int i = 0; i < listToArray.length; i++) {
            for (int j = 0; j < listToArray[i].length; j++) {
                listToArray[i][j] = list.get(i * 4 + j);
            }
        }
        //Game15Solver.aStar(listToArray);
    }

    /**
     * metodo che permette di controllare se il puzzle è completato
     * @return true se risolto, false se non è risolto
     */
    public boolean isFinished() {
        finalList.addAll(num);
        return initialList.equals(finalList);
    }
}
