package com.example.forum;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class PostController {
    public AnchorPane postContainer;
    public TextArea postContent;
    public Button returnBtn, post;
    public Label status;

    @FXML
    protected void post() throws SQLException {
        if(postContent.getText().isBlank())
            status.setText("Blank content");
        else {
            ForumApplication.connection.createStatement().execute(
                    STR."INSERT INTO tblpost (author_id, body) values (\{LogInController.idSession}, '\{postContent.getText()}')");
            status.setText("Post Successful");
            postContent.clear();
        }
    }
    @FXML
    protected void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-page.fxml"));
        Parent scene = loader.load();
        AnchorPane parent = (AnchorPane) postContainer.getParent();
        parent.getChildren().clear();
        parent.getChildren().add(scene);
        MainPageController mainPageController = loader.getController();
        mainPageController.getPosts();
    }
}
