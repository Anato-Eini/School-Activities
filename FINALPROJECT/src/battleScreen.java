import Entities.Entity;


import javax.swing.*;



import java.util.Objects;

public class battleScreen extends JFrame {
    private JPanel battlePanel;
    private JButton SkillOptionBtn;
    private JButton SWITCHButton;
    private JTextArea dialogBox;
    private JTextPane bossIcon;
    private JTextPane playerIcon;
    private JLabel bossName;
    private JLabel playerName;
    private JPanel battleScreen;
    private JButton SKILL1Button;
    private JButton SKILL2Button;
    private JButton SKILL3Button;
    private JButton SKILL4Button;
    private JPanel skillPanel;
    private JButton player1Button;
    private JButton player2Button;
    private JButton player3Button;
    private JButton player4Button;
    private JPanel SwitchPanel;


    private boolean toggleBtn = true;

    private Entity currentPlayer;

    public JPanel getBattlePanel() {
        return battlePanel;
    }
    public battleScreen(){
        GameBehavior gb = new GameBehavior(50);
        currentPlayer = gb.characters.getFirst();
        bossName.setText(gb.boss.getName());
        playerName.setText(currentPlayer.getName());
        bossIcon.setText("NAME: "+gb.boss.getName() + "\nHP: " + gb.boss.getHp() + "\nLVL: " + gb.boss.getLevel());
        playerIcon.setText("NAME: "  + gb.characters.get(0)+ "\nHP: " +currentPlayer.getHp()+ "\nLVL: " + currentPlayer.getLevel());
        setContentPane(battlePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        skillPanel.setVisible(false);
        SwitchPanel.setVisible(false);

//Switch button

        SkillOptionBtn.addActionListener(e -> {
            if(toggleBtn){
                skillPanel.setVisible(true);
                toggleBtn = false;
            }else{
                skillPanel.setVisible(false);
                toggleBtn = true;
            }
        });

        SKILL1Button.addActionListener(e -> {
            dialogBox.setText(currentPlayer.getName() + " has use the skill " + currentPlayer.getSkills().get(0).getName());
            currentPlayer.getSkills().get(0).doSkill(gb.boss);
            gb.boss.setHp(gb.boss.getHp() - currentPlayer.getSkills().get(0).getDamage());
            bossIcon.setText("NAME: "+gb.boss.getName() + "\nHP: " + gb.boss.getHp() + "\nLVL: " + gb.boss.getLevel());
            playerIcon.setText("NAME: "  + currentPlayer.getName()+ "\nHP: " +currentPlayer.getHp()+ "\nLVL: " + currentPlayer.getLevel());
            skillPanel.setVisible(false);

        });
        SKILL2Button.addActionListener(e -> {
            dialogBox.setText(currentPlayer.getName() + " has use skill " + currentPlayer.getSkills().get(1).getName());
            currentPlayer.getSkills().get(1).doSkill(gb.boss);
            gb.boss.setHp(gb.boss.getHp() - currentPlayer.getSkills().get(1).getDamage());
            bossIcon.setText("NAME: "+gb.boss.getName() + "\nHP: " + gb.boss.getHp() + "\nLVL: " + gb.boss.getLevel());
            playerIcon.setText("NAME: "  + currentPlayer.getName()+ "\nHP: " +currentPlayer.getHp()+ "\nLVL: " + currentPlayer.getLevel());
            skillPanel.setVisible(false);
        });
        SKILL3Button.addActionListener(e -> {
            dialogBox.setText(currentPlayer.getName() + " has use skill " + currentPlayer.getSkills().get(2).getName());
            currentPlayer.getSkills().get(2).doSkill(gb.boss);
            gb.boss.setHp(gb.boss.getHp() - currentPlayer.getSkills().get(2).getDamage());
            bossIcon.setText("NAME: "+gb.boss.getName() + "\nHP: " + gb.boss.getHp() + "\nLVL: " + gb.boss.getLevel());
            playerIcon.setText("NAME: "  + currentPlayer.getName()+ "\nHP: " +currentPlayer.getHp()+ "\nLVL: " + currentPlayer.getLevel());
            skillPanel.setVisible(false);
        });
        SKILL4Button.addActionListener(e -> {
            dialogBox.setText(currentPlayer.getName() + " has use skill " + currentPlayer.getSkills().get(3).getName());
            //implement special skill
            bossIcon.setText("NAME: "+gb.boss.getName() + "\nHP: " + gb.boss.getHp() + "\nLVL: " + gb.boss.getLevel());

            skillPanel.setVisible(false);
        });

//Switch button
        SWITCHButton.addActionListener(e -> {
            SwitchPanel.setVisible(true);
            if(toggleBtn){
                SwitchPanel.setVisible(true);
                toggleBtn = false;
            }else{
                SwitchPanel.setVisible(false);
                toggleBtn = true;
            }
        });
        player1Button.addActionListener(e -> {
            if(!Objects.equals(currentPlayer, gb.characters.getFirst())){

                dialogBox.setText(currentPlayer.getName() + " Has switch to " + gb.characters.getFirst().getName());
                playerIcon.setText("NAME: "  + gb.characters.getFirst().getName()+ "\nHP: " +currentPlayer.getHp()+ "\nLVL: " + currentPlayer.getLevel());
                currentPlayer = gb.characters.getFirst();
                playerName.setText(currentPlayer.getName());
            }else{
                dialogBox.setText("Invalid choice! You cannot switch with yourself");
            }

            SwitchPanel.setVisible(false);
        });player2Button.addActionListener(e -> {
            if(!Objects.equals(currentPlayer, gb.characters.get(1))){
                dialogBox.setText(currentPlayer.getName() + " Has switch to " + gb.characters.get(1).getName());
                playerIcon.setText("NAME: "  + gb.characters.get(1)+ "\nHP: " +currentPlayer.getHp()+ "\nLVL: " + currentPlayer.getLevel());
                currentPlayer = gb.characters.get(1);
                playerName.setText(currentPlayer.getName());
            }else{
                dialogBox.setText("Invalid choice! You cannot switch with yourself");
            }
            SwitchPanel.setVisible(false);
        });
        player3Button.addActionListener(e -> {
            if(!Objects.equals(currentPlayer, gb.characters.get(2))){
                dialogBox.setText(currentPlayer.getName() + " Has switch to " + gb.characters.get(2).getName());
                playerIcon.setText("NAME: "  + gb.characters.get(2)+ "\nHP: " +currentPlayer.getHp()+ "\nLVL: " + currentPlayer.getLevel());
                currentPlayer = gb.characters.get(2);
                playerName.setText(currentPlayer.getName());
            }else{
                dialogBox.setText("Invalid choice! You cannot switch with yourself");
            }
            SwitchPanel.setVisible(false);
        });
        player4Button.addActionListener(e -> {
            if(!Objects.equals(currentPlayer, gb.characters.get(3))){
                dialogBox.setText(currentPlayer.getName() + " Has switch to " + gb.characters.get(3).getName());
                playerIcon.setText("NAME: "  + gb.characters.get(3)+ "\nHP: " +currentPlayer.getHp()+ "\nLVL: " + currentPlayer.getLevel());
                currentPlayer = gb.characters.get(3);
                playerName.setText(currentPlayer.getName());
            }else{
                dialogBox.setText("Invalid choice! You cannot switch with yourself");
            }
            SwitchPanel.setVisible(false);
        });
    }
}

