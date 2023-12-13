package com.example.Controllers;

import static com.example.Model.gameMaster.*;

import com.example.Model.Entity;
import com.example.Model.GameBehavior;
import com.example.Model.gameMaster;
import com.example.Model.sceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class switchMenuController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button member1Button;

    @FXML
    private Label member1HP;

    @FXML
    private ProgressBar member1HpBar;

    @FXML
    private Label member1Name;

    @FXML
    private ImageView member1PNG;

    @FXML
    private Button member2Button;

    @FXML
    private Label member2HP;

    @FXML
    private ProgressBar member2HpBar;

    @FXML
    private Label member2Name;

    @FXML
    private ImageView member2PNG;

    @FXML
    private Button member3Button;

    @FXML
    private Label member3HP;

    @FXML
    private ProgressBar member3HpBar;

    @FXML
    private Label member3Name;

    @FXML
    private ImageView member3PNG;

    @FXML
    private Button member4Button;

    @FXML
    private Label member4HP;

    @FXML
    private ProgressBar member4HpBar;

    @FXML
    private Label member4Name;

    @FXML
    private ImageView member4PNG;

    @FXML
    private AnchorPane switchMenuPanel;


    public void initialize(){
        member1Name.setText(gb.characters.party.get(0).getName());
        member2Name.setText(gb.characters.party.get(1).getName());
        member3Name.setText(gb.characters.party.get(2).getName());
        member4Name.setText(gb.characters.party.get(3).getName());

        member1HP.setText(gb.characters.party.get(0).getHp() + " | " + gb.characters.party.get(0).getMaxHP());
        member2HP.setText(gb.characters.party.get(1).getHp() + " | " + gb.characters.party.get(1).getMaxHP());
        member3HP.setText(gb.characters.party.get(2).getHp() + " | " + gb.characters.party.get(2).getMaxHP());
        member4HP.setText(gb.characters.party.get(3).getHp() + " | " + gb.characters.party.get(3).getMaxHP());

        member1HpBar.setProgress((double) gb.characters.party.get(0).getHp() / gb.characters.party.get(0).getMaxHP());
        member2HpBar.setProgress((double) gb.characters.party.get(1).getHp() / gb.characters.party.get(1).getMaxHP());
        member3HpBar.setProgress((double) gb.characters.party.get(2).getHp() / gb.characters.party.get(2).getMaxHP());
        member4HpBar.setProgress((double) gb.characters.party.get(3).getHp() / gb.characters.party.get(3).getMaxHP());

        if(currentPlayer.equals(gb.characters.party.get(0)) || gb.characters.party.get(0).isDead()){
            member1Button.setDisable(true);
        } if (currentPlayer.equals(gb.characters.party.get(1)) || gb.characters.party.get(1).isDead()){
            member2Button.setDisable(true);
        } if (currentPlayer.equals(gb.characters.party.get(2)) || gb.characters.party.get(2).isDead()){
            member3Button.setDisable(true);
        } if (currentPlayer.equals(gb.characters.party.get(3)) || gb.characters.party.get(3).isDead()){
            member4Button.setDisable(true);
        }

        if(gb.characters.party.get(0).isDead()){
            member1PNG.setImage(new Image("/member1_Dead.png"));
        } if (gb.characters.party.get(1).isDead()){
            member2PNG.setImage(new Image("/member2_Dead.png"));
        } if (gb.characters.party.get(2).isDead()){
            member3PNG.setImage(new Image("/member3_Dead.png"));
        } if (gb.characters.party.get(3).isDead()){
            member4PNG.setImage(new Image("/member4_Dead.png"));
        }
    }

    @FXML
    void cancelButtonClicked(ActionEvent event) throws IOException {
        new sceneSwitch(switchMenuPanel, "view/battleMenu.fxml");
    }

    @FXML
    void switchTo1(ActionEvent event) throws IOException {
        currentPlayer = gb.characters.party.get(0);
        new sceneSwitch(switchMenuPanel, "view/battleMenu.fxml");
    }

    @FXML
    void switchTo2(ActionEvent event) throws IOException {
        currentPlayer = gb.characters.party.get(1);
        new sceneSwitch(switchMenuPanel, "view/battleMenu.fxml");
    }

    @FXML
    void switchTo3(ActionEvent event) throws IOException {
        currentPlayer = gb.characters.party.get(2);
        new sceneSwitch(switchMenuPanel, "view/battleMenu.fxml");
    }

    @FXML
    void switchTo4(ActionEvent event) throws IOException {
        currentPlayer = gb.characters.party.get(3);
        new sceneSwitch(switchMenuPanel, "view/battleMenu.fxml");
    }

    public void bossAttack(){
        int min = 1; // Minimum value of range
        int max = 4; // Maximum value of range


        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);


        switch(random_int){
            case 1:
                gb.boss.skill1(currentPlayer);
                break;
            case 2:
                gb.boss.skill2(currentPlayer);
                break;
            case 3:
                gb.boss.skill3(gb.characters);
                break;
            case 4:
                gb.boss.skill4(currentPlayer);
                break;
        }

    }

}
