package com.project.game.model.player;

import com.project.game.model.db.DataBase;

public class Player {
    DataBase db = DataBase.getInstance();
    private static final PlayerBean player = new PlayerBean();

    /**
     * costruttore del player
     * @param username del giocatore
     * @param count di passi effettuati
     */
    public Player(String username, String count) {
        player.setUsername(username);
        player.setCount(count);
    }

    /**
     * metodo per avere l'istanza unica di player
     * @return istanza unica di player
     */
    public static PlayerBean getInstance() {
        return player;
    }

    public void addVictory() {

    }
}
