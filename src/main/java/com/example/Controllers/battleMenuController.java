package com.example.Controllers;

import com.example.Model.Entity;
import com.example.Model.GameBehavior;
import com.example.Model.gameMaster;
import com.example.Model.sceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Scanner;

import static com.example.Model.gameMaster.*;

public class battleMenuController {


    @FXML
    private Button attack1Button;

    @FXML
    private Button attack2Button;

    @FXML
    private Button attack3Button;

    @FXML
    private Button attack4Button;

    @FXML
    private AnchorPane battleMenuPanel;

    @FXML
    private Label bossHP;

    @FXML
    private ProgressBar bossHPBar;

    @FXML
    private TextArea consoleTextArea;

    @FXML
    private Label currentMemberHP;

    @FXML
    private ProgressBar currentMemberHPBar;

    @FXML
    private Label currentMemberName;

    @FXML
    private Button switchButton;

    @FXML
    private Label turnLabel;

    public GameOverWrapper gameOver = new GameOverWrapper();


    public void initialize() throws IOException {
        gameMaster.newTurn();
        turnLabel.setText("Turn: " + gb.getTotalTime());
        currentMemberName.setText(currentPlayer.getName());
        consoleTextArea.setText("Serato awaits your next move...");

        bossHP.setText(gb.boss.getHp() + " | 25000");
        bossHPBar.setProgress((double) gb.boss.getHp() / 25000);
        currentMemberHP.setText(currentPlayer.getHp() + " | " + currentPlayer.getMaxHP());
        currentMemberHPBar.setProgress((double) currentPlayer.getHp() / currentPlayer.getMaxHP());

        System.out.println((double) currentPlayer.getHp() / currentPlayer.getMaxHP());

        if(currentPlayer.isDead()){
            displayConsole("You're still dead!");
            new sceneSwitch(battleMenuPanel, "view/switchMenu.fxml");
        }

        gameOver.setState(false);
    }

    void displayConsole(String log){
        consoleTextArea.setVisible(true);
        consoleTextArea.setText(log);
    }

    @FXML
    void doAttack1(ActionEvent event) throws IOException {
        checkSkill3Cd(currentPlayer);
        checkSkill4Cd(currentPlayer);
        handlePlayerBuffs(currentPlayer);
        //
        handleBossDebuff(gb,currentPlayer,gameOver);
        if(gb.boss.getDebuff() != null){
            if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                currentPlayer.skill1(gb.boss);
                if(gb.boss.isDead()){
                    System.out.println("You win");
                    gameOver.setState(true);

                    new sceneSwitch(battleMenuPanel, "view/winMenu.fxml");
                }

                System.out.println(currentPlayer);
                System.out.println(gb.boss);

                gameMaster.newTurn();

                turnLabel.setText("Turn: " + gb.getTotalTime());
                displayConsole("Mr.Serato was stunned and you managed to attack him...");
                bossHP.setText(gb.boss.getHp() + " | 25000");
                bossHPBar.setProgress((double) gb.boss.getHp() /25000);
                currentMemberHP.setText(currentPlayer.getHp() + " | " + currentPlayer.getMaxHP());
                currentMemberHPBar.setProgress((double) currentPlayer.getHp() / currentPlayer.getMaxHP());
                return;
            }
        }
        //spacer
        currentPlayer = handlePlayerDebuffs(gb,currentPlayer,gameOver);
        if(currentPlayer.getDebuff() != null){
            if(currentPlayer.getDebuff().getDebuffName().equals("Stun")){
                bossAttack(gb,currentPlayer);
                if(currentPlayer.isDead()){
                    currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
                }



                System.out.println(currentPlayer);
                System.out.println(gb.boss);

                gameMaster.newTurn();

                turnLabel.setText("Turn: " + gb.getTotalTime());
                displayConsole("You were stunned and Mr.Serato managed to attack you...");
                bossHP.setText(gb.boss.getHp() + " | 25000");
                bossHPBar.setProgress((double) gb.boss.getHp() /25000);
                currentMemberHP.setText(currentPlayer.getHp() + " | " + currentPlayer.getMaxHP());
                currentMemberHPBar.setProgress((double) currentPlayer.getHp() / currentPlayer.getMaxHP());
                return;
            }
        }
        //spacer
        //spacer
        if(currentPlayer.getSpeed() > gb.boss.getSpeed()){
            currentPlayer.skill1(gb.boss);
            if(gb.boss.isDead()){
                System.out.println("You win");
                gameOver.setState(true);

                new sceneSwitch(battleMenuPanel, "view/winMenu.fxml");
            }
            bossAttack(gb,currentPlayer);
            if(currentPlayer.isDead()){
                currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
            }
        } else{
            bossAttack(gb,currentPlayer);
            if(currentPlayer.isDead()){
                currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
            }
            currentPlayer.skill1(gb.boss);
            if(gb.boss.isDead()){
                System.out.println("You win");
                gameOver.setState(true);
                new sceneSwitch(battleMenuPanel, "view/winMenu.fxml");
            }
        }
        System.out.println(currentPlayer);
        System.out.println(gb.boss);

        gameMaster.newTurn();

        turnLabel.setText("Turn: " + gb.getTotalTime());
        displayConsole("Serato awaits your next move...");
        bossHP.setText(gb.boss.getHp() + " | 25000");
        bossHPBar.setProgress((double) gb.boss.getHp() /25000);
        currentMemberHP.setText(currentPlayer.getHp() + " | " + currentPlayer.getMaxHP());
        currentMemberHPBar.setProgress((double) currentPlayer.getHp() / currentPlayer.getMaxHP());
    }

    @FXML
    void doAttack2(ActionEvent event) throws IOException {
        checkSkill3Cd(currentPlayer);
        checkSkill4Cd(currentPlayer);
        handlePlayerBuffs(currentPlayer);
        //
        handleBossDebuff(gb,currentPlayer,gameOver);
        if(gb.boss.getDebuff() != null){
            if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                currentPlayer.skill2(gb.boss);
                if(gb.boss.isDead()){
                    System.out.println("You win");
                    gameOver.setState(true);
                    new sceneSwitch(battleMenuPanel, "view/winMenu.fxml");
                }

                System.out.println(currentPlayer);
                System.out.println(gb.boss);

                gameMaster.newTurn();

                turnLabel.setText("Turn: " + gb.getTotalTime());
                displayConsole("Mr.Serato was stunned and you managed to attack him...");
                bossHP.setText(gb.boss.getHp() + " | 25000");
                bossHPBar.setProgress((double) gb.boss.getHp() /25000);
                currentMemberHP.setText(currentPlayer.getHp() + " | " + currentPlayer.getMaxHP());
                currentMemberHPBar.setProgress((double) currentPlayer.getHp() / currentPlayer.getMaxHP());
                return;
            }
        }
        //spacer
        currentPlayer = handlePlayerDebuffs(gb,currentPlayer,gameOver);
        if(currentPlayer.getDebuff() != null){
            if(currentPlayer.getDebuff().getDebuffName().equals("Stun")){
                bossAttack(gb,currentPlayer);
                if(currentPlayer.isDead()){
                    currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
                }


                System.out.println(currentPlayer);
                System.out.println(gb.boss);

                gameMaster.newTurn();

                turnLabel.setText("Turn: " + gb.getTotalTime());
                displayConsole("You were stunned and Mr.Serato managed to attack you...");
                bossHP.setText(gb.boss.getHp() + " | 25000");
                bossHPBar.setProgress((double) gb.boss.getHp() /25000);
                currentMemberHP.setText(currentPlayer.getHp() + " | " + currentPlayer.getMaxHP());
                currentMemberHPBar.setProgress((double) currentPlayer.getHp() / currentPlayer.getMaxHP());
                return;
            }
        }
        //spacer
        //spacer
        if(currentPlayer.getSpeed() > gb.boss.getSpeed()){
            currentPlayer.skill2(gb.boss);
            if(gb.boss.isDead()){
                System.out.println("You win");
                gameOver.setState(true);
                new sceneSwitch(battleMenuPanel, "view/winMenu.fxml");
            }
            bossAttack(gb,currentPlayer);
            if(currentPlayer.isDead()){
                currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
            }
        } else{
            bossAttack(gb,currentPlayer);
            if(currentPlayer.isDead()){
                currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
            }
            currentPlayer.skill2(gb.boss);
            if(gb.boss.isDead()){
                System.out.println("You win");
                gameOver.setState(true);
                new sceneSwitch(battleMenuPanel, "view/winMenu.fxml");
            }
        }
        System.out.println(currentPlayer);
        System.out.println(gb.boss);

        gameMaster.newTurn();

        turnLabel.setText("Turn: " + gb.getTotalTime());
        displayConsole("Serato awaits your next move...");
        bossHP.setText(gb.boss.getHp() + " | 25000");
        bossHPBar.setProgress((double) gb.boss.getHp() /25000);
        currentMemberHP.setText(currentPlayer.getHp() + " | " + currentPlayer.getMaxHP());
        currentMemberHPBar.setProgress((double) currentPlayer.getHp() / currentPlayer.getMaxHP());
    }

    @FXML
    void doAttack3(ActionEvent event) throws IOException {
        if(currentPlayer.getSkill3cd() > 0){
            displayConsole("Cooldown skill 3: " + currentPlayer.getSkill3cd());
            return;
        }

        checkSkill3Cd(currentPlayer);
        checkSkill4Cd(currentPlayer);
        handlePlayerBuffs(currentPlayer);
        //
        handleBossDebuff(gb,currentPlayer,gameOver);
        if(gb.boss.getDebuff() != null){
            if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                currentPlayer.skill3(gb.characters);
                if(gb.boss.isDead()){
                    System.out.println("You win");
                    gameOver.setState(true);
                    new sceneSwitch(battleMenuPanel, "view/winMenu.fxml");
                }

                System.out.println(currentPlayer);
                System.out.println(gb.boss);

                gameMaster.newTurn();

                turnLabel.setText("Turn: " + gb.getTotalTime());
                displayConsole("Mr.Serato was stunned and you managed to attack him...");
                bossHP.setText(gb.boss.getHp() + " | 25000");
                bossHPBar.setProgress((double) gb.boss.getHp() /25000);
                currentMemberHP.setText(currentPlayer.getHp() + " | " + currentPlayer.getMaxHP());
                currentMemberHPBar.setProgress((double) currentPlayer.getHp() / currentPlayer.getMaxHP());
                return;
            }
        }
        //spacer
        currentPlayer = handlePlayerDebuffs(gb,currentPlayer,gameOver);
        if(currentPlayer.getDebuff() != null){
            if(currentPlayer.getDebuff().getDebuffName().equals("Stun")){
                bossAttack(gb,currentPlayer);
                if(currentPlayer.isDead()){
                    currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
                }


                System.out.println(currentPlayer);
                System.out.println(gb.boss);

                gameMaster.newTurn();

                turnLabel.setText("Turn: " + gb.getTotalTime());
                displayConsole("You were stunned and Mr.Serato managed to attack you...");
                bossHP.setText(gb.boss.getHp() + " | 25000");
                bossHPBar.setProgress((double) gb.boss.getHp() /25000);
                currentMemberHP.setText(currentPlayer.getHp() + " | " + currentPlayer.getMaxHP());
                currentMemberHPBar.setProgress((double) currentPlayer.getHp() / currentPlayer.getMaxHP());
                return;
            }
        }
        //spacer
        //spacer
        if(currentPlayer.getSpeed() > gb.boss.getSpeed()){
            currentPlayer.skill3(gb.characters);
            if(gb.boss.isDead()){
                System.out.println("You win");
                gameOver.setState(true);
                new sceneSwitch(battleMenuPanel, "view/winMenu.fxml");
            }
            bossAttack(gb,currentPlayer);
            if(currentPlayer.isDead()){
                currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
            }
        } else{
            bossAttack(gb,currentPlayer);
            if(currentPlayer.isDead()){
                currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
            }
            currentPlayer.skill3(gb.characters);
            if(gb.boss.isDead()){
                System.out.println("You win");
                gameOver.setState(true);
                new sceneSwitch(battleMenuPanel, "view/winMenu.fxml");
            }
        }

        currentPlayer.setSkill3cd(currentPlayer.getSkill3RealCd());
        System.out.println(currentPlayer);
        System.out.println(gb.boss);

        gameMaster.newTurn();

        turnLabel.setText("Turn: " + gb.getTotalTime());
        displayConsole("Serato awaits your next move...");
        bossHP.setText(gb.boss.getHp() + " | 25000");
        bossHPBar.setProgress((double) gb.boss.getHp() /25000);
        currentMemberHP.setText(currentPlayer.getHp() + " | " + currentPlayer.getMaxHP());
        currentMemberHPBar.setProgress((double) currentPlayer.getHp() / currentPlayer.getMaxHP());
    }

    @FXML
    void doAttack4(ActionEvent event) throws IOException {
        if(currentPlayer.getSkill4cd() > 0){
            displayConsole("Cooldown skill 4: " + currentPlayer.getSkill4cd());
            return;
        }

        checkSkill3Cd(currentPlayer);
        handlePlayerBuffs(currentPlayer);
        handleBossDebuff(gb,currentPlayer,gameOver);

        if(gb.boss.getDebuff() != null){
            if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                currentPlayer.skill3(gb.characters);
                if(gb.boss.isDead()){
                    System.out.println("You win");
                    gameOver.setState(true);
                    new sceneSwitch(battleMenuPanel, "view/winMenu.fxml");
                }

                System.out.println(currentPlayer);
                System.out.println(gb.boss);

                gameMaster.newTurn();

                turnLabel.setText("Turn: " + gb.getTotalTime());
                displayConsole("Mr.Serato was stunned and you managed to attack him...");
                bossHP.setText(gb.boss.getHp() + " | 25000");
                bossHPBar.setProgress((double) gb.boss.getHp() /25000);
                currentMemberHP.setText(currentPlayer.getHp() + " | " + currentPlayer.getMaxHP());
                currentMemberHPBar.setProgress((double) currentPlayer.getHp() / currentPlayer.getMaxHP());
                return;
            }
        }
        //spacer
        currentPlayer = handlePlayerDebuffs(gb,currentPlayer,gameOver);
        if(currentPlayer.getDebuff() != null){
            if(currentPlayer.getDebuff().getDebuffName().equals("Stun")){
                bossAttack(gb,currentPlayer);
                if(currentPlayer.isDead()){
                    currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
                }


                System.out.println(currentPlayer);
                System.out.println(gb.boss);

                gameMaster.newTurn();

                turnLabel.setText("Turn: " + gb.getTotalTime());
                displayConsole("You were stunned and Mr.Serato managed to attack you...");
                bossHP.setText(gb.boss.getHp() + " | 25000");
                bossHPBar.setProgress((double) gb.boss.getHp() /25000);
                currentMemberHP.setText(currentPlayer.getHp() + " | " + currentPlayer.getMaxHP());
                currentMemberHPBar.setProgress((double) currentPlayer.getHp() / currentPlayer.getMaxHP());
                return;
            }
        }

        System.out.println("Successfully using skill");
        if(currentPlayer.getSpeed() > gb.boss.getSpeed()){
            currentPlayer.skill4(gb.boss);
            if(gb.boss.isDead()){
                System.out.println("You win");
                gameOver.setState(true);
                new sceneSwitch(battleMenuPanel, "view/winMenu.fxml");
            }
            bossAttack(gb,currentPlayer);
            if(currentPlayer.isDead()){
                currentPlayer =  handlePlayerDeath(gb,currentPlayer,gameOver);
            }
        } else{
            bossAttack(gb,currentPlayer);
            if(currentPlayer.isDead()){
                currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
            }
            currentPlayer.skill4(gb.boss);
            if(gb.boss.isDead()){
                System.out.println("You win");
                gameOver.setState(true);
                new sceneSwitch(battleMenuPanel, "view/winMenu.fxml");

            }
        }
        currentPlayer.setSkill4cd(currentPlayer.getSkill4RealCd());
        System.out.println(currentPlayer);
        System.out.println(gb.boss);

        gameMaster.newTurn();

        turnLabel.setText("Turn: " + gb.getTotalTime());
        displayConsole("Serato awaits your next move...");
        bossHP.setText(gb.boss.getHp() + " | 25000");
        bossHPBar.setProgress((double) gb.boss.getHp() /25000);
        currentMemberHP.setText(currentPlayer.getHp() + " | " + currentPlayer.getMaxHP());
        currentMemberHPBar.setProgress((double) currentPlayer.getHp() / currentPlayer.getMaxHP());
    }

    @FXML
    void onMouseClick(MouseEvent event) {
        consoleTextArea.setVisible(false);
    }

    @FXML
    void goToSwitchMenu(ActionEvent event) throws IOException {
        new sceneSwitch(battleMenuPanel, "view/switchMenu.fxml");
    }

    public  void bossAttack(GameBehavior gb, Entity currentPlayer){
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
    public  void checkSkill3Cd(Entity curr){
        if(curr.getSkill3cd() > 0){
            curr.setSkill3cd(curr.getSkill3cd() - 1);
            System.out.println("Cooldown skill 3: " + curr.getSkill3cd());
        }
    }
    public  void checkSkill4Cd(Entity curr){
        if(curr.getSkill4cd() > 0){
            curr.setSkill4cd(curr.getSkill4cd() - 1);
            System.out.println("Cooldown skill 4: " + curr.getSkill4cd());
        }
    }
    public void handlePlayerBuffs(Entity curr){
        if(curr.getBuff() != null){
            if(curr.getBuff().getBuffName().equals("Damage Buff")){
                if(curr.getBuff().getBuffTurnsApplied() == 0){
                    int before = curr.getBaseDmg();
                    curr.getBuff().setOriginal(before);
                    System.out.println("Before dmg buff: " + curr.getBaseDmg());
                    curr.setBaseDmg(curr.getBuff().getBuffed());
                    System.out.println("After dmg buff: " + curr.getBaseDmg());
                }
                curr.getBuff().setBuffTurnsApplied(curr.getBuff().getBuffTurnsApplied() + 1);
                System.out.println("How many turns? " + curr.getBuff().getBuffTurnsApplied());
                if(curr.getBuff().getBuffTurnsApplied() >= curr.getBuff().getBuffDuration()){
                    curr.setBaseDmg(curr.getBuff().getOriginal());
                    System.out.println("Buff is over, base dmg is " + curr.getBaseDmg());
                    curr.setBuff(null);
                }
            }
        }
    }


    public void switchCharacters(GameBehavior gameBehavior, Entity curr) throws IOException {
        new sceneSwitch(battleMenuPanel, "view/switchMenu.fxml");
    }
    public Entity handlePlayerDebuffs(GameBehavior gb, Entity currentPlayer, GameOverWrapper gameOver) throws IOException {
        if(currentPlayer.getDebuff() != null){
            if(currentPlayer.getDebuff().getDebuffName().equals("Stun")){
                System.out.println("You are stunned");
                bossAttack(gb,currentPlayer);
                currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                    currentPlayer.setDebuff(null);
                }
                if(currentPlayer.isDead()){
                    handlePlayerDeath(gb,currentPlayer,gameOver);
                }
                System.out.println(currentPlayer);
                System.out.println(gb.boss);
            } else{
                System.out.println("You are currently poisoned");
                currentPlayer.setHp(currentPlayer.getHp() - 50);
                currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                    currentPlayer.setDebuff(null);
                }
                if(currentPlayer.isDead()){
                    currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
                }
            }
        }
        return currentPlayer;
    }
    public Entity handlePlayerDeath(GameBehavior gb, Entity currentPlayer, GameOverWrapper gameOver) throws IOException {
        System.out.println("You are dead");
        if(gb.characters.isWipedOut()){
            System.out.println("You lose");
            gameOver.setState(true);
            new sceneSwitch(battleMenuPanel, "view/loseMenu.fxml");
        } else{
            switchCharacters(gb,currentPlayer);
        }
        return currentPlayer;
    }

    public void handleBossDebuff(GameBehavior gb, Entity currentPlayer, GameOverWrapper gameOver) throws IOException {
        if(gb.boss.getDebuff() != null){
            if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                System.out.println("Boss stunned");
                currentPlayer.skill1(gb.boss);
                gb.boss.getDebuff().setTurnsApplied(gb.boss.getDebuff().getTurnsApplied() + 1);
                if(gb.boss.getDebuff().getTurnsApplied() >= gb.boss.getDebuff().getDuration()){
                    gb.boss.setDebuff(null);
                }
                if(gb.boss.isDead()){
                    System.out.println("You win");
                    gameOver.setState(true);
                    new sceneSwitch(battleMenuPanel, "view/winMenu.fxml");
                }
                System.out.println(currentPlayer);
                System.out.println(gb.boss);
            } else{
                System.out.println("Boss poisoned");
                gb.boss.setHp(gb.boss.getHp() - 50);
                gb.boss.getDebuff().setTurnsApplied(gb.boss.getDebuff().getTurnsApplied() + 1);
                if(gb.boss.getDebuff().getTurnsApplied() >= gb.boss.getDebuff().getDuration()){
                    gb.boss.setDebuff(null);
                }
            }
        }
    }
    public class GameOverWrapper{
        public boolean state;
        public void setState(boolean state){
            this.state = state;
        }
    }

}
