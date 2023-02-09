package com.project.game.controller;

import com.project.game.MainPage;
import com.project.game.model.board.BoardPrototype;
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
import java.util.List;
import java.util.Objects;
import static com.project.game.controller.HomeController.root2;
import static com.project.game.model.game.Game.FifteenPuzzleBoard;

public class GameController {
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

    /**
     * gestione del click sul bottone back per tornare alla schermata principale
     * @throws IOException errori di I/O
     */
    @FXML
    protected void backClickHandler() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainPage.class.getResource("view/HomePage.fxml")));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root,500,350));
    }

    /**
     * controllore del click sul bottone solve
     */
    @FXML
    protected void solveClickHandler() {
        List<BoardPrototype> boardPrototypeList = game.solve(FifteenPuzzleBoard);
        for(BoardPrototype boardPrototype : boardPrototypeList) {
            updateBoard(boardPrototype);
        }
    }

    /**
     * controllore del click sul bottone generate
     */
    @FXML
    protected void generateClickHandler() {
        FifteenPuzzleBoard = game.initializeBoard();
        int index = 0;
        ObservableList<Node> children1 = ((GridPane) root2.lookup("#mainPane")).getChildren();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //Recupero il valore della cella nella posizione i, j
                int value = FifteenPuzzleBoard.board[i][j].getValue();
                //recupero il pannello su cui scrivere
                Pane child = (Pane) children1.get(index);
                Label child2 = (Label) child.getChildren().get(0);
                if(value == 0) {
                    child2.setText(" ");
                    child.setBackground(Background.fill(Color.ANTIQUEWHITE));
                    pane16.setBackground(Background.fill(Color.rgb(253,202,64)));
                } else {
                    child2.setText(String.valueOf(value));
                }
                index++;
            }
        }
        if(FifteenPuzzleBoard.board[3][3].getValue() == 0) {
            pane16.setBackground(Background.fill(Color.ANTIQUEWHITE));
        }
        generateButton.setDisable(true);
        solveButton.setDisable(false);
    }

    private void updateBoard(BoardPrototype boardPrototype) {
        int index = 0;
        ObservableList<Node> children1 = ((GridPane) root2.lookup("#mainPane")).getChildren();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //Recupero il valore della cella nella posizione i, j
                int value = boardPrototype.board[i][j].getValue();
                //recupero il pannello su cui scrivere
                Pane child = (Pane) children1.get(index);
                Label child2 = (Label) child.getChildren().get(0);
                if(value == 0) {
                    child2.setText(" ");
                    child.setBackground(Background.fill(Color.ANTIQUEWHITE));
                } else {
                    child.setBackground(Background.fill(Color.rgb(253,202,64)));
                    child2.setText(String.valueOf(value));
                }
                index++;
            }
        }
    }
}