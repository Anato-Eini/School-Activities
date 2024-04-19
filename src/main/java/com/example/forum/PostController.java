package com.example.forum;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.PreparedStatement;
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
            PreparedStatement preparedStatement = ForumApplication.connection.prepareStatement(
                    "INSERT INTO tblpost (author_id, body) values (?, ?)"
            );
            preparedStatement.setInt(1, LogInController.idSession);
            preparedStatement.setString(2, postContent.getText());
            if(preparedStatement.executeUpdate() > 0)
                status.setText("Post Successful");
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
