package com.example.forum;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class MainPageController {
    public AnchorPane mainPageContainer;
    public Button logOutButton, postButton;
    public ScrollPane forumContainer;
    public VBox posts;
    public Label status;

    @FXML
    public void getPosts() {
        try(Statement statement = ForumApplication.connection.createStatement();
            Statement statement1 = ForumApplication.connection.createStatement()) {
            ResultSet postRecords = statement.executeQuery("SELECT * FROM tblpost");
            while (postRecords.next()) {
                HBox header = new HBox();
                Label name = new Label(getUser(postRecords.getInt("author_id")));
                header.getChildren().add(name);
                if(LogInController.idSession == postRecords.getInt("author_id")){

                    header.getChildren().add(createDeletePostBtn(postRecords.getInt("id")));
                }
                name.getStyleClass().add("names");
                name.setWrapText(true);
                Text postContent = new Text(postRecords.getString("body"));
                postContent.getStyleClass().add("postContent");
                postContent.setWrappingWidth(445);
                VBox post = new VBox(), replies = new VBox();
                replies.getStyleClass().add("replies");
                Label replyLabel = new Label("Replies");
                replyLabel.getStyleClass().add("replyLabel");
                replies.getChildren().add(replyLabel);

                ResultSet replySets = statement1.executeQuery(
                        STR."SELECT * FROM tblreply WHERE post_id = \{postRecords.getInt("id")}");
                while(replySets.next()) {
                    VBox replyContainer = new VBox();
                    replyContainer.getStyleClass().add("replyContainers");
                    Label replyUsername = new Label(getUser(replySets.getInt("author_id")));
                    replyUsername.getStyleClass().add("replyUsernames");
                    Text replyContent = new Text(replySets.getString("body"));
                    replyContent.getStyleClass().add("replyContents");
                    replyContainer.getChildren().add(replyUsername);
                    replyContainer.getChildren().add(replyContent);

                    replies.getChildren().add(replyContainer);
                }

                HBox newReply = new HBox();
                TextField theReply = new TextField();
                Button submitReply = createSubmitButton(
                        theReply, LogInController.idSession, postRecords.getInt("id"));
                newReply.getChildren().add(theReply);
                newReply.getChildren().add(submitReply);
                replies.getChildren().add(newReply);
                //TODO implement delete own replies
                //TODO implement edit own replies

                post.getStyleClass().add("post");
                post.getChildren().add(header);
                post.getChildren().add(postContent);
                post.getChildren().add(replies);
                posts.getChildren().addFirst(post);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private Button createEditPostBtn(int post_id){
        Button editPostBtn = new Button();
        editPostBtn.setText("Edit Post");
        editPostBtn.setOnMouseClicked(_ -> {
            //TODO implement edit own post
        });
        return editPostBtn;
    }

    private Button createDeletePostBtn(int post_id) {
        Button deletePostBtn = new Button();
        deletePostBtn.setText("Delete Post");
        deletePostBtn.setOnMouseClicked(_ -> {
            try {
                Statement statement = ForumApplication.connection.createStatement();
                statement.addBatch(STR."DELETE FROM tblpost WHERE id = \{post_id}");
                statement.addBatch(STR."DELETE FROM tblreply WHERE post_id = \{post_id}");
                statement.executeBatch();
                posts.getChildren().clear();
                getPosts();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return deletePostBtn;
    }

    private Button createSubmitButton(TextField theReply, int author_id, int post_id) {
        Button submitReply = new Button();
        submitReply.setText("Submit Reply");
        submitReply.setOnMouseClicked(_ -> {
            if(theReply.getText().isEmpty())
                status.setText("Empty Reply");
            else{
                reply(post_id, author_id, theReply.getText());
                posts.getChildren().clear();
                getPosts();
            }
        });
        return submitReply;
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
        LogInController.idSession = -1;
    }
    @FXML
    protected void toUpdateAccount() throws IOException {
        Parent scene = FXMLLoader.load(
                Objects.requireNonNull(ForumApplication.class.getResource("updateAcct.fxml")));
        AnchorPane p = (AnchorPane) mainPageContainer.getParent();
        p.getChildren().clear();
        p.getChildren().add(scene);
    }

    private String getUser(int id){
        try(Statement statement = ForumApplication.connection.createStatement()){
            ResultSet resultSet  = statement.executeQuery(STR."SELECT name FROM tbluseracct WHERE id = \{id}");
            resultSet.next();
            return resultSet.getString(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private void reply(int post_id, int author_id, String body){
        try{
            ForumApplication.connection.createStatement().execute(
                    STR."INSERT INTO tblreply (author_id, post_id, body) VALUES (\{author_id}, \{post_id}, '\{body}')");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
