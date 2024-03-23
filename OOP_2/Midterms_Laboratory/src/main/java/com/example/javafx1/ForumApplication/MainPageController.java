package com.example.javafx1.ForumApplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
            name.setWrapText(true);
            Text postContent = new Text(parts[1]);
            postContent.getStyleClass().add("postContent");
            postContent.setWrappingWidth(445);
            VBox post = new VBox(), replies = new VBox();
            replies.getStyleClass().add("replies");
            Label replyLabel = new Label("Replies");
            replyLabel.getStyleClass().add("replyLabel");
            replies.getChildren().add(replyLabel);


            for(int i = 2; i < parts.length; i += 2){
                VBox replyContainer = new VBox();
                if(i != parts.length - 2)
                    VBox.setMargin(replyContainer, new Insets(0, 0, 10, 0));
                replyContainer.getStyleClass().add("replyContainers");
                Label replyUsername = new Label(parts[i]);
                replyUsername.getStyleClass().add("replyUsernames");
                Text replyContent = new Text(parts[i + 1]);
                replyContent.getStyleClass().add("replyContents");
                replyContainer.getChildren().add(replyUsername);
                replyContainer.getChildren().add(replyContent);
                replies.getChildren().add(replyContainer);
            }

            HBox newReply = new HBox();
            TextField theReply = new TextField();
            Button submitReply = new Button();
            submitReply.setText("Submit Reply");
            newReply.getChildren().add(theReply);
            newReply.getChildren().add(submitReply);
            replies.getChildren().add(newReply);



            post.getStyleClass().add("post");
            post.getChildren().add(name);
            post.getChildren().add(postContent);
            post.getChildren().add(replies);
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
                Objects.requireNonNull(ForumApplication.class.getResource("login-view.fxml")));
        AnchorPane p = (AnchorPane) mainPageContainer.getParent();
        p.getChildren().clear();
        p.getChildren().add(scene);
        LogInController.username = null;
    }
}
