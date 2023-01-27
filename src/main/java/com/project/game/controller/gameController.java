package com.project.game.controller;

import com.project.game.MainPage;
import com.project.game.model.board.Board;
import com.project.game.model.board.Box;
import com.project.game.model.game.Game;
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
    ArrayList<Integer> initialList;
    Board initial = Board.getInstance();

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
        /*
        while(!game.isFinished()) {
            game.makeMove();
        }
         */
        game.solve(initial);
    }

    /**
     * controllore del click sul bottone generate
     * ieri sbagliavi, bisogna fare [i][j] e vedere se funziona
     */
    @FXML
    protected void generateClickHandler() {
        initial = game.initializeBoard();
        int index = 0;
        ObservableList<Node> children1 = ((GridPane) root2.lookup("#mainPane")).getChildren();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //Recupero il valore della cella nella posizione i, j
                int value = initial.board[i][j].getValue();
                //recupero il pannello su cui scrivere
                Pane child = (Pane) children1.get(index);
                Label child2 = (Label) child.getChildren().get(0);
                if(value == 0) {
                    child2.setText(" ");
                    child.setBackground(Background.fill(Color.RED));
                    pane16.setBackground(Background.fill(Color.LIGHTGREEN));
                } else {
                    child2.setText(String.valueOf(value));
                }
                index++;
            }
        }
        generateButton.setDisable(true);
    }
}