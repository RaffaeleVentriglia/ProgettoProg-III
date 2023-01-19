package com.project.game.controller;
import com.project.game.MainPage;

import com.project.game.model.game.Game;
import com.project.game.model.player.Player;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.project.game.controller.homeController.root2;

public class gameController implements Initializable {
    @FXML
    Button generateButton;
    @FXML
    Button solveButton;
    @FXML
    GridPane mainPane;
    @FXML
    Button backButton;
    Game game = Game.getInstance();
    Player player = Player.getInstance();

    /**
     * gestione del click sul bottone back per tornare alla schermata principale
     * @throws IOException
     */
    @FXML
    protected void backClickHandler() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainPage.class.getResource("view/homePage.fxml")));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root,500,350));
    }

    @FXML
    protected void solveClickHandler() {
        System.out.println("Username: " + player.getUsername());
        System.out.println("Count: " + player.getCount());
    }

    @FXML
    protected void generateClickHandler() {
        ArrayList<Integer> initialList = game.initializeBoard();

        GridPane pane = (GridPane) root2.lookup("#mainPane");
        ObservableList<Node> children = pane.getChildren();

        for (Node child : children) {
            if (child instanceof Label label) {
                System.out.println(label.getText());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
