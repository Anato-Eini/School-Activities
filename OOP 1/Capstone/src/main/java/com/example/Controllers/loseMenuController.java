package com.example.Controllers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

import static com.example.Model.mediaPlayerMain.mediaPlayer;

public class loseMenuController {

    public void initialize(){
        //Initialising path of the media file, replace this with your file path
        String path = "src/main/resources/Maalaala Mo Kaya Theme - Carol Banawa.mp3";

        //Instantiating Media class
        Media media = new Media(new File(path).toURI().toString());

        mediaPlayer.stop();

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
    }
}
