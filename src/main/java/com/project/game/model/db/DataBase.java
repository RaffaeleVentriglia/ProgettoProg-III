package com.project.game.model.db;

import com.project.game.model.player.Player;
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
    Connection con;

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
            con = DriverManager.getConnection(url);
            System.out.println("Connessione al database eseguita con successo.");
            PreparedStatement preparedStatement = con.prepareStatement("SELECT username, count FROM player ORDER BY count DESC LIMIT 5");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                System.out.println(resultSet.getString(1) + resultSet.getString(2));
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
