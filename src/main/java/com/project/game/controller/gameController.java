package com.project.game.controller;

import com.project.game.MainPage;
import com.project.game.model.game.Game;
import com.project.game.model.player.Player;
import com.project.game.model.player.PlayerBean;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import static com.project.game.controller.homeController.root2;

public class gameController {
    @FXML
    Pane pane1;
    @FXML
    Pane pane2;
    @FXML
    Pane pane3;
    @FXML
    Pane pane4;
    @FXML
    Pane pane5;
    @FXML
    Pane pane6;
    @FXML
    Pane pane7;
    @FXML
    Pane pane8;
    @FXML
    Pane pane9;
    @FXML
    Pane pane10;
    @FXML
    Pane pane11;
    @FXML
    Pane pane12;
    @FXML
    Pane pane13;
    @FXML
    Pane pane14;
    @FXML
    Pane pane15;
    @FXML
    Pane pane16;
    @FXML
    Button generateButton;
    @FXML
    Button solveButton;
    @FXML
    GridPane mainPane;
    @FXML
    Button backButton;
    Game game = Game.getInstance();
    PlayerBean player = Player.getInstance();
    ArrayList<Integer>  initialList;

    /**
     * gestione del click sul bottone back per tornare alla schermata principale
     * @throws IOException errori di I/O
     */
    @FXML
    protected void backClickHandler() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainPage.class.getResource("view/homePage.fxml")));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root,500,350));
    }

    /**
     * controllore del click sul bottone solve
     */
    @FXML
    protected void solveClickHandler() {
        System.out.println("Username: " + player.getUsername());
        System.out.println("Count: " + player.getCount());
        /*
        while(!game.isFinished()) {
            game.makeMove();
        }
         */
    }

    /**
     * controllore del click sul bottone generate
     */
    @FXML
    protected void generateClickHandler() {
        initialList = game.initializeBoard();
        int index = 0;
        ObservableList<Node> children1 = ((GridPane) root2.lookup("#mainPane")).getChildren();
        // itero tutto il mainPanel per impostare il testo delle label con gli elementi creati nella initialList
        for (Node child : children1) {
            if (child instanceof Pane) {
                ObservableList<Node> children2 = ((Pane) child).getChildren();
                for(Node child2 : children2) {
                    if(child2 instanceof Label) {
                        if(initialList.get(index).equals(0)) {
                            ((Label) child2).setText(" ");
                        } else {
                            ((Label) child2).setText(initialList.get(index).toString());
                        }
                        index++;
                    }
                }
            }
        }
        // itero ancora tutto il mainPanel per trovare il pannello vuoto e impostarne lo sfondo a rosso
        for (Node child : children1) {
            if (child instanceof Pane) {
                ObservableList<Node> children2 = ((Pane) child).getChildren();
                for(Node child2 : children2) {
                    if(child2 instanceof Label) {
                        if(Objects.equals(((Label) child2).getText(), " ")) {
                            ((Pane) child).setBackground(Background.fill(Color.RED));
                            pane16.setBackground(Background.fill(Color.LIGHTGREEN));
                        }
                    }
                }
            }
        }
        generateButton.setDisable(true);
    }
}