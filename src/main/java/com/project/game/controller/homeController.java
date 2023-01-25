package com.project.game.controller;

import com.project.game.MainPage;
import com.project.game.model.player.Player;
import com.project.game.model.player.PlayerBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class homeController {
    @FXML
    Label errorInsert;
    @FXML
    Button generalScoreButton;
    @FXML
    Button startGameButton;
    @FXML
    TextField usernameField;
    PlayerBean player = Player.getInstance();
    public static Parent root2;

    /**
     * controllore del click sul bottone start game
     */
    @FXML
    protected void startGameClickHandler() {
        String temp = usernameField.getText();
        if(temp.isEmpty()) {
            errorInsert.setOpacity(1.0);
        } else {
            try {
                root2 = FXMLLoader.load(Objects.requireNonNull(MainPage.class.getResource("view/gamePage.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            player.setUsername(temp);
            Stage window = (Stage) startGameButton.getScene().getWindow();
            window.setScene(new Scene(root2,500,350));
        }
    }

    /**
     * controllore del click sul pulsante general score
     * @throws IOException errori di I/O
     */
    @FXML
    protected void generalScoreClickHandler() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainPage.class.getResource("view/generalScorePage.fxml")));
        Stage window = (Stage) generalScoreButton.getScene().getWindow();
        window.setScene(new Scene(root,500,350));
    }
}