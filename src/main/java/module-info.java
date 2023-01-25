module com.project.game {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.project.game to javafx.fxml;
    // aggiunto per vedere se funziona
    opens com.project.game.model.player;
    exports com.project.game;
    exports com.project.game.controller;
    opens com.project.game.controller to javafx.fxml;
    //exports com.project.game.model; TOLTO PERCHÈ DAVA ERRORI
    //opens com.project.game.model to javafx.fxml; // TOLTO PERCHÈ DAVA ERRORI
}