package com.project.game.model.player;

public class PlayerBean implements java.io.Serializable {
    public String username;
    public String count;

    /**
     * costruttore usato per impostare il valore
     * iniziale di count a ZERO per ogni istanza di player
     */
    public PlayerBean() {
        count = String.valueOf(0);
        username = null;
    }

    /**
     * metodo set di count
     * @param count di passi effettuati
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * metodo set per username
     * @param username del player
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * metodo get del count
     * @return di count
     */
    public String getCount() {
        return count;
    }

    /**
     * metodo get di username
     * @return di username
     */
    public String getUsername() {
        return username;
    }
}
