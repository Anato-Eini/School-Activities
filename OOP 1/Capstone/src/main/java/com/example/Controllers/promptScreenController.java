package com.example.Controllers;

import com.example.Model.gameMaster;
import com.example.Model.sceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

import static com.example.Model.mediaPlayerMain.mediaPlayer;

public class promptScreenController {

    @FXML
    private AnchorPane screenPane;

    @FXML
    void nextPageClick(MouseEvent event) throws IOException {
        gameMaster.start();
        //Initialising path of the media file, replace this with your file path
        String path = "src/main/resources/Pokémon Diamond, Pearl & Platinum - Champion Cynthia Battle Music (HQ).mp3";

        //Instantiating Media class
        Media media = new Media(new File(path).toURI().toString());

        //Instantiating MediaPlayer class
        mediaPlayer = new MediaPlayer(media);

        //by setting this property to true, the audio will be played
        mediaPlayer.setAutoPlay(true);

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();
        new sceneSwitch(screenPane, "view/battleMenu.fxml");
    }

}
