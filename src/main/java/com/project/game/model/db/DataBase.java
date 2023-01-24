package com.project.game.model.db;

import com.project.game.model.player.Player;
import com.project.game.model.player.PlayerBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class DataBase {
    private static DataBase db;
    ObservableList<PlayerBean> observableList = FXCollections.observableArrayList();
    private final String url = "jdbc:sqlite:identifier.sqlite";
    Connection con;

    private DataBase() {}

    public static DataBase getInstance() {
        if(db == null) {
            db = new DataBase();
        }
        return db;
    }

    /**
     * metodo che permette, tramite la connessione al database, di prendere
     * i primi 5 della classifica generale dei giocatori
     * @return observableList che viene aggiunta alla tabella in generalScore
     */
    public ObservableList<PlayerBean> getScore() {
        String username;
        String count;
        try {
            con = DriverManager.getConnection(url, "", "");
            PreparedStatement preparedStatement = con.prepareStatement("SELECT username, count FROM Player ORDER BY count DESC LIMIT 5");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                username = resultSet.getString(1);
                count = resultSet.getString(2);
                new Player(username, count);
                observableList.add(Player.getInstance());
            }
            return observableList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return observableList;
    }
}
