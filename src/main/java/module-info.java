module com.project.game {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.project.game to javafx.fxml;
    exports com.project.game;
    exports com.project.game.controller;
    opens com.project.game.controller to javafx.fxml;
    //exports com.project.game.model;
    //opens com.project.game.model to javafx.fxml;
}