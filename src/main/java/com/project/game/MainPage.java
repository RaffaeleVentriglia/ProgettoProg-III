package com.project.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class MainPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/homePage.fxml")));
        stage.setTitle("Gioco del 15");
        Image image = new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResource("images/icon.png")).toExternalForm());
        stage.getIcons().add(image);
        stage.setScene(new Scene(root,500,350));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}