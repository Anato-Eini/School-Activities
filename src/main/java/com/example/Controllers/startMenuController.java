package com.example.Controllers;

import com.example.Model.gameMaster;
import com.example.Model.sceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class startMenuController {

    @FXML
    private Button startGameButton;

    @FXML
    private AnchorPane startPane;

    @FXML
    void startGame(ActionEvent event) throws IOException {
        gameMaster.start();
        new sceneSwitch(startPane, "view/battleMenu.fxml");
    }

}
