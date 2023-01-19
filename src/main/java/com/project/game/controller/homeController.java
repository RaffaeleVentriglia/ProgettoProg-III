package com.project.game.controller;

import com.project.game.MainPage;
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
    @FXML
    Label welcomeText;

    @FXML
    protected void startGameClickHandler() throws IOException {
        String temp = usernameField.getText();
        if(temp.isEmpty()) {
            System.out.println("Inserire nome utente");
            errorInsert.setOpacity(1.0);
        } else {
            Parent root = FXMLLoader.load(Objects.requireNonNull(MainPage.class.getResource("view/gamePage.fxml")));
            Stage window = (Stage) startGameButton.getScene().getWindow();
            window.setScene(new Scene(root,500,350));
        }
    }

    @FXML
    protected void generalScoreClickHandler() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainPage.class.getResource("view/generalScorePage.fxml")));
        Stage window = (Stage) generalScoreButton.getScene().getWindow();
        window.setScene(new Scene(root,500,350));
    }
}