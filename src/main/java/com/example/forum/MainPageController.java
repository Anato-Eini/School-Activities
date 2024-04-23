package com.example.forum;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
                Label replyLabel = new Label("Replies");
                replyLabel.getStyleClass().add("replyLabel");
                VBox replies = new VBox(replyLabel);
                replies.getStyleClass().add("replies");

                ResultSet replySets = statement1.executeQuery(
                        STR."SELECT * FROM tblreply WHERE post_id = \{postRecords.getInt("id")}");
                while(replySets.next()) {
                    replies.getChildren().add(createReplyContainer(replySets.getInt("author_id"),
                            replySets.getString("body"), replySets.getInt("id"), replies));
                }

                TextField theReply = new TextField();
                replies.getChildren().add(new HBox(theReply, createSubmitReplyBtn(
                        theReply, LogInController.idSession, postRecords.getInt("id"), replies)));

                VBox post = new VBox(header, postContent, replies);
                post.getStyleClass().add("post");
                header.getChildren().add(name);

                if(LogInController.idSession == postRecords.getInt("author_id"))
                    header.getChildren().addAll(
                            addEditContentElements(postRecords.getInt("id"), postContent, post, header
                                    , new Button("Edit Post"), true),
                            createDeletePostBtn(postRecords.getInt("id"), post));
                header.getChildren().forEach(node ->
                        HBox.setMargin(node, new Insets(0, 10, 0, 0)));
                posts.getChildren().addFirst(post);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private Button createDeleteReplyBtn(int reply_id, VBox replyContainer, VBox replies) {
        Button deleteReplyBtn = new Button("Delete Reply");
        deleteReplyBtn.setOnMouseClicked(_ -> {
            try {
                ForumApplication.connection.createStatement()
                        .execute(STR."DELETE FROM tblreply WHERE id = \{reply_id}");
                replies.getChildren().remove(replyContainer);
                status.setText("Delete reply successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return deleteReplyBtn;
    }
    private Button addEditContentElements(int id, Text textContent, VBox container, HBox replyHeader,
                                        Button editContentBtn, boolean isPost) {
        Button cancelEditContentBtn = new Button("Cancel"), submitEditContentBtn = new Button("Submit");
        AtomicReference<String> content = new AtomicReference<>(textContent.getText());

        editContentBtn.setOnMouseClicked(_ -> {
            replyHeader.getChildren().remove(editContentBtn);

            TextArea editBody = new TextArea(content.get());
            editBody.setPrefHeight(50);
            editBody.setWrapText(true);

            VBox editReplyContainer = new VBox(editBody, new HBox(cancelEditContentBtn, submitEditContentBtn));
            container.getChildren().set(1, editReplyContainer);

            cancelEditContentBtn.setOnMouseClicked(_ -> {
                editBody.setText(content.get());
                container.getChildren().set(1, textContent);
                replyHeader.getChildren().add(1, editContentBtn);
            });

            submitEditContentBtn.setOnMouseClicked(_ -> {
                if(editBody.getText().isEmpty()){
                    status.setText("Can't leave as blank");
                }else if(!editBody.getText().equals(content.get())){
                    try{
                        if(isPost)
                            ForumApplication.connection.createStatement().execute(
                                    STR."UPDATE tblpost SET body='\{editBody.getText()}' WHERE id=\{id}"
                            );
                        else
                            ForumApplication.connection.createStatement().execute(
                                    STR."UPDATE tblreply SET body='\{editBody.getText()}' WHERE id=\{id}"
                            );
                        textContent.setText(editBody.getText());
                        replyHeader.getChildren().add(1, editContentBtn);
                        container.getChildren().set(1, textContent);
                        content.set(editBody.getText());
                        status.setText("Edit successfully");
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }else
                    status.setText("Same Content");
            });
        });
        return editContentBtn;
    }
    private Button createDeletePostBtn(int post_id, VBox post) {
        Button deletePostBtn = new Button("Delete Post");
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
    private Button createSubmitReplyBtn(TextField theReply, int author_id, int post_id, VBox replies) {
        Button submitReply = new Button("Submit Reply");
        submitReply.setOnMouseClicked(_ -> {
            if(theReply.getText().isEmpty())
                status.setText("Empty Reply");
            else{
                try (Statement statement = ForumApplication.connection.createStatement()){
                    statement.execute(STR."INSERT INTO tblreply (author_id, post_id, body) VALUES (\{author_id}, \{post_id}, '\{theReply.getText()}')",
                            Statement.RETURN_GENERATED_KEYS);
                    ResultSet rs = statement.getGeneratedKeys();
                    rs.next();
                    replies.getChildren().add(replies.getChildren().size() - 1,
                            createReplyContainer(author_id
                                    , theReply.getText(), rs.getInt(1), replies));
                    theReply.clear();
                    status.setText("Submit successfully");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        return submitReply;
    }
    private VBox createReplyContainer(int author_id, String body, int replyId, VBox replies) {
        VBox replyContainer = new VBox();
        replyContainer.getStyleClass().add("replyContainers");
        HBox headerReply = new HBox();
        Label replyUsername = new Label(getUser(author_id));
        headerReply.getChildren().add(replyUsername);

        replyUsername.getStyleClass().add("replyUsernames");
        Text replyContent = new Text(body);
        replyContent.getStyleClass().add("replyContents");
        replyContainer.getChildren().addAll(headerReply, replyContent);
        if(author_id == LogInController.idSession)
            headerReply.getChildren().addAll(
                    addEditContentElements(replyId, replyContent, replyContainer, headerReply
                            , new Button("Edit Reply"), false),
                    createDeleteReplyBtn(replyId, replyContainer, replies)
            );

        headerReply.getChildren().forEach(node -> HBox.setMargin(node, new Insets(0, 10, 0, 0)));
        return replyContainer;
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
    protected void refreshPosts(){
        posts.getChildren().clear();
        getPosts();
        status.setText("Refreshed");
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
}
