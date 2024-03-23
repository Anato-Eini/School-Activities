package com.example.javafx1.LogInGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class MainPageController {
    public AnchorPane mainPageContainer;
    public Button logOutButton, postButton;
    public ScrollPane forumContainer;
    public VBox posts;
    @FXML
    public void getPosts() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("posts.txt"));
        String line;
        while((line = br.readLine()) != null){
            String[] parts = line.split("\\|");
            Label name = new Label(parts[0]);
            name.getStyleClass().add("names");
            Label postContent = new Label(parts[1]);
            postContent.getStyleClass().add("postContent");
            VBox post = new VBox();
            post.getStyleClass().add("post");
            post.getChildren().add(name);
            post.getChildren().add(postContent);
            posts.getChildren().addFirst(post);
        }
        br.close();
    }
    @FXML
    protected void post() throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("post.fxml")));
        AnchorPane p = (AnchorPane) mainPageContainer.getParent();
        p.getChildren().clear();
        p.getChildren().add(parent);
    }
    @FXML
    protected void logOutClick() throws IOException {
        Parent scene = FXMLLoader.load(
                Objects.requireNonNull(LogInApplication.class.getResource("main-page.fxml")));
        AnchorPane p = (AnchorPane) mainPageContainer.getParent();
        p.getChildren().clear();
        p.getChildren().add(scene);
        LogInController.username = null;
    }
}
