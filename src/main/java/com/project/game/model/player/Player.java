package com.project.game.model.player;

public class Player {
    String username;
    int count;
    private static Player player;

    /**
     * costruttore privato per applicare il Singleton
     */
    private Player() {
        count = 0;
    }

    /**
     * metodo per attuare Singleton e ritornare l'unica istanza di player
     * @return player
     */
    public static Player getInstance() {
        if(player == null) {
            player = new Player();
        }
        return player;
    }

    /**
     * metodo set per username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * metodo set per count
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * metodo get per ritornare username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * metodo get per ritornare count
     * @return count
     */
    public int getCount() {
        return count;
    }
}
