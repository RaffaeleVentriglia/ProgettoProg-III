package com.project.game.model.db;

import com.project.game.model.player.PlayerBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class DataBase {
    // istanza unica del database
    private static DataBase db;
    // observableList per memorizzare i player
    ObservableList<PlayerBean> observableList = FXCollections.observableArrayList();
    // indirizzo url del database
    private final String url = "jdbc:sqlite:identifier.sqlite";
    // variabile utilizzata per la connessione
    Connection connection;

    /**
     * costruttore privato per Singleton
     */
    private DataBase() {}

    /**
     * metodo con cui restituiamo l'unica istanza del database
     * @return istanza unica del database
     */
    public static DataBase getInstance() {
        if(db == null) {
            db = new DataBase();
        }
        return db;
    }

    /**
     * metodo che permette, tramite la connessione al database, di prendere
     * i primi CINQUE della classifica generale dei giocatori
     * @return observableList che viene aggiunta alla tabella in generalScore
     */
    public ObservableList<PlayerBean> getScore() {
        String username;
        String count;
        try {
            observableList.clear();
            connection = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT username, count FROM player ORDER BY count LIMIT 5");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                username = resultSet.getString(1);
                count = resultSet.getString(2);
                PlayerBean playerBean = new PlayerBean(username, count);
                observableList.add(playerBean);
            }
            return observableList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return observableList;
    }

    /**
     * metodo che permette di aggiungere la vittoria di una partita
     * @param username dell'utente
     * @param count dei passi effettuati per raggiungere la soluzione
     */
    public void addVictory(String username, int count) {
        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO player (username, count) VALUES (?,?)");
            preparedStatement.setString(1,username);
            preparedStatement.setInt(2,count);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
