package com.project.game.model.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * commento per vari ragionamenti
 *
 * gestire l'intero carico del gioco da questa classe, soprattutto il movimento dei numeri tra le varie caselle,
 * mentre nella gameController spostare semplicemente le caselle graficamente
 */


public class Game {
    private static Game game;
    private final List<Integer> num = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0);
    private final ArrayList<Integer> finalList = new ArrayList<>();
    public ArrayList<Integer> initialList = new ArrayList<>(16);

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
        return initialList;
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

    public boolean isFinished() {
        finalList.addAll(num);
        return initialList.equals(finalList);
    }

    public void makeMove() {

    }
}
