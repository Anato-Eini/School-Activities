package com.example.javafx1.LogInGUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.*;

public class PostController {
    public AnchorPane postContainer;
    public TextArea postContent;
    public Button returnBtn, post;
    public Label status;

    @FXML
    protected void post() throws IOException {
        if(postContent.getText().isBlank())
            status.setText("Blank content");
        else if(postContent.getText().contains("|"))
            status.setText("| are not allowed");
        else{
            BufferedWriter bw = new BufferedWriter(new FileWriter("posts.txt", true));
            bw.write(LogInController.username + "|" + postContent.getText() + '\n');
            bw.close();
            status.setText("Posted Successfully");
            postContent.setText("");
        }
    }
    @FXML
    protected void back(){

    }
}
