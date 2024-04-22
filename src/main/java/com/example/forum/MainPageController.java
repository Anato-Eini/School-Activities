package com.example.forum;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

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
                    replyContainer.getChildren().addAll(replyUsername, replyContent);
                    //TODO implement delete own replies
                    //TODO implement edit own replies

                    replies.getChildren().add(replyContainer);
                }

                HBox newReply = new HBox();
                TextField theReply = new TextField();
                newReply.getChildren().addAll(theReply, createSubmitButton(
                        theReply, LogInController.idSession, postRecords.getInt("id"), replies));
                replies.getChildren().add(newReply);

                post.getStyleClass().add("post");
                post.getChildren().addAll(header, postContent, replies);
                if(LogInController.idSession == postRecords.getInt("author_id"))
                    header.getChildren().addAll(
                            name,
                            createEditPostBtn(postRecords.getInt("id"), postContent, post),
                            createDeletePostBtn(postRecords.getInt("id"), post));
                header.getChildren().forEach(node ->
                        HBox.setMargin(node, new Insets(0, 10, 0, 0)));
                posts.getChildren().addFirst(post);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private Button createEditPostBtn(int post_id, Text postContent, VBox post){
        Button editPostBtn = new Button(), cancelEditPostBtn = new Button(), submitEditPostBtn = new Button();
        editPostBtn.setText("Edit Post");
        cancelEditPostBtn.setText("Cancel");
        submitEditPostBtn.setText("Submit");

        editPostBtn.setOnMouseClicked(_ -> {
            AtomicReference<String> bodyPost = new AtomicReference<>(postContent.getText());
            HBox header = (HBox)post.getChildren().getFirst();
            TextArea editBody = new TextArea(bodyPost.get());
            Button editButton = (Button) header.getChildren().get(1);
            header.getChildren().remove(editButton);
            post.getChildren().remove(1);
            VBox editPostContainer = new VBox();
            HBox optionButtons = new HBox();
            editBody.setPrefWidth(Region.USE_COMPUTED_SIZE);
            editBody.setWrapText(true);
            editBody.setPrefHeight(50);
            optionButtons.getChildren().addAll(cancelEditPostBtn, submitEditPostBtn);
            editPostContainer.getChildren().addAll(editBody, optionButtons);
            post.getChildren().add(1, editPostContainer);

            cancelEditPostBtn.setOnMouseClicked(_ -> {
                editBody.setText(bodyPost.get());
                post.getChildren().remove(editPostContainer);
                post.getChildren().add(1, postContent);
                header.getChildren().add(1, editButton);
            });

            submitEditPostBtn.setOnMouseClicked(_ -> {
                if(editBody.getText().isEmpty())
                    status.setText("Can't leave as blank");
                else if(!editBody.getText().equals(bodyPost.get())){
                    try{
                        ForumApplication.connection.createStatement().execute(
                                STR."UPDATE tblpost SET body='\{editBody.getText()}' WHERE id=\{post_id}"
                        );
                        postContent.setText((editBody.getText()));
                        post.getChildren().add(1, postContent);
                        header.getChildren().add(1, editButton);
                        post.getChildren().remove(editPostContainer);
                        bodyPost.set(editBody.getText());
                        status.setText("Edit successfully");
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }else
                    status.setText("Same Content");
            });
        });
        return editPostBtn;
    }

    private Button createDeletePostBtn(int post_id, VBox post) {
        Button deletePostBtn = new Button();
        deletePostBtn.setText("Delete Post");
        deletePostBtn.setOnMouseClicked(_ -> {
            try {
                Statement statement = ForumApplication.connection.createStatement();
                statement.addBatch(STR."DELETE FROM tblpost WHERE id = \{post_id}");
                statement.addBatch(STR."DELETE FROM tblreply WHERE post_id = \{post_id}");
                statement.executeBatch();
                posts.getChildren().remove(post);
                status.setText("Delete post successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return deletePostBtn;
    }

    private Button createSubmitButton(TextField theReply, int author_id, int post_id, VBox replies) {
        Button submitReply = new Button();
        submitReply.setText("Submit Reply");
        submitReply.setOnMouseClicked(_ -> {
            if(theReply.getText().isEmpty())
                status.setText("Empty Reply");
            else{
                try {
                    ResultSet results = ForumApplication.connection.createStatement().executeQuery(
                            STR."SELECT name FROM tbluseracct WHERE id = \{author_id}");
                    results.next();
                    String authorUsername = results.getString("name");
                    VBox replyContainer = new VBox();
                    replyContainer.getStyleClass().add("replyContainers");
                    Label replyUsername = new Label(authorUsername);
                    replyUsername.getStyleClass().add("replyUsernames");
                    Text replyContent = new Text(theReply.getText());
                    replyContent.getStyleClass().add("replyContents");
                    replyContainer.getChildren().addAll(replyUsername, replyContent);
                    replies.getChildren().add(replies.getChildren().size() - 1, replyContainer);
                    reply(post_id, author_id, theReply.getText());
                    theReply.setText("");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
