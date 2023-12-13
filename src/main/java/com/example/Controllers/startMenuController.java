package com.example.Controllers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import com.example.Model.gameMaster;
import com.example.Model.sceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;

public class startMenuController {

    @FXML
    private Button startGameButton;

    @FXML
    private AnchorPane startPane;


    @FXML
    void startGame(ActionEvent event) throws IOException {
        new sceneSwitch(startPane, "view/promptScreen.fxml");
    }

}
