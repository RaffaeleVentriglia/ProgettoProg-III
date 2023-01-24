package com.project.game.controller;

import com.project.game.MainPage;
import com.project.game.model.db.DataBase;
import com.project.game.model.player.PlayerBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class generalScoreController implements Initializable {
    @FXML
    Button backButton;
    @FXML
    TableView<PlayerBean> score;
    @FXML
    TableColumn<PlayerBean, String> usernameColumn;
    @FXML
    TableColumn<PlayerBean, Integer> countColumn;
    DataBase db = DataBase.getInstance();

    /**
     * controllore del click sul bottone back
     * @throws IOException errori di I/O
     */
    @FXML
    protected void backClickHandler() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainPage.class.getResource("view/homePage.fxml")));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root,500,350));
    }

    /**
     * metodo che attraverso l'uso dell'interfaccia initialize permette di inizializzare le variabili
     * della tabella che permette di visualizzare la classifica dei migliori 5 utilizzando
     * dati presi dal database (username e count)
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        score.setItems(db.getScore());
    }
}