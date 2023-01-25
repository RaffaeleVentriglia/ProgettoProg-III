package com.project.game.model.player;

public class PlayerBean implements java.io.Serializable {
    public String username;
    public String count;

    /**
     * costruttore senza parametri
     */
    public PlayerBean() {}

    /**
     * costruttore con parametri
     * @param username del player
     * @param count di passi effettuati
     */
    public PlayerBean(String username, String count) {
        this.username = username;
        this.count = count;
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
