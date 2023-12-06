import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class battleScreen extends JFrame {
    private JPanel battlePanel;
    private JButton SkillOptionBtn;
    private JButton SWITCHButton;
    private JTextArea dialogBox;
    private JLabel bossIcon;
    private JLabel playerIcon;
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


    public JPanel getBattlePanel() {
        return battlePanel;
    }
    public JPanel getBattleScreen() {
        return battleScreen;
    }

    public battleScreen(){
        setContentPane(battlePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        skillPanel.setVisible(false);
        SwitchPanel.setVisible(false);

//Switch button

        SkillOptionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(toggleBtn){
                    skillPanel.setVisible(true);
                    toggleBtn = false;
                }else{
                    skillPanel.setVisible(false);
                    toggleBtn = true;
                }

            }
        });

        SKILL1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogBox.setText("playername has use skillname.");
                skillPanel.setVisible(false);

            }
        });
        SKILL2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogBox.setText("playername has use skillname.");
                skillPanel.setVisible(false);
            }
        });
        SKILL3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogBox.setText("playername has use skillname.");
                skillPanel.setVisible(false);
            }
        });
        SKILL4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogBox.setText("playername has use skillname.");
                skillPanel.setVisible(false);
            }
        });

//Switch button
        SWITCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                SwitchPanel.setVisible(true);
                if(toggleBtn){
                    SwitchPanel.setVisible(true);
                    toggleBtn = false;
                }else{
                    SwitchPanel.setVisible(false);
                    toggleBtn = true;
                }

            }
        });
        player1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogBox.setText("currentPlayerName" + " Has switch to " + "Player1Name...");
                SwitchPanel.setVisible(false);

            }
        });player2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogBox.setText("currentPlayerName" + " Has switch to " + "Player2Name...");
                SwitchPanel.setVisible(false);

            }
        });
        player3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogBox.setText("currentPlayerName" + " Has switch to " + "Player3Name...");
                SwitchPanel.setVisible(false);
            }
        });
        player4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogBox.setText("currentPlayerName" + " Has switch to " + "Player4Name...");
                SwitchPanel.setVisible(false);

            }
        });

    }


}

