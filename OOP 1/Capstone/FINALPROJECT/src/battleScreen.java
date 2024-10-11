import Entities.Entity;
import Entities.Party;


import javax.swing.*;


import java.awt.*;
import java.util.Objects;

public class battleScreen extends JFrame {
    private JPanel battlePanel;
    private JLabel HeroEmblemIcon;
    private JLabel switchIcon;
    private JTextPane dialogBox;
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
    private JPanel HeroSelectionPanel;
    private JPanel switchIconPanel;
    private JPanel EmblemPanel;

    //SAKTO NI? NAGHIMO KOG INSTANCE PARTY??
    //NA AKO GE GAMIT SA SKILL 3
    //MU GANA NA ANG CONSOLE E MINIMIZE LANG APP
    private Party party = new Party();
    private Entity currentPlayer;

    public JPanel getBattlePanel() {
        return battlePanel;
    }
    public battleScreen() {
        GameBehavior gb = new GameBehavior();

        currentPlayer = gb.characters.party.getFirst();
        bossName.setText(gb.boss.getName());
        playerName.setText(currentPlayer.getName());
        bossIcon.setText("NAME: " + gb.boss.getName() + "\nHP: " + gb.boss.getHp() + "\nLVL: " + gb.boss.getLevel());
        playerIcon.setText("NAME: " + currentPlayer.getName() + "\nHP: " + currentPlayer.getHp() + "\nLVL: " + currentPlayer.getLevel());
        setContentPane(battlePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        skillPanel.setVisible(false);
        switchIconPanel.setVisible(true);
        HeroSelectionPanel.setVisible(true);

        SKILL1Button.addActionListener(e -> {
            dialogBox.setText(currentPlayer.getName() + " has use basic light attack!!!");
            currentPlayer.skill1(gb.boss);
            bossIcon.setText("NAME: " + gb.boss.getName() + "\nHP: " + gb.boss.getHp() + "\nLVL: " + gb.boss.getLevel());
            playerIcon.setText("NAME: " + currentPlayer.getName() + "\nHP: " + currentPlayer.getHp() + "\nLVL: " + currentPlayer.getLevel());
            skillPanel.setVisible(false);
        });

        SKILL2Button.addActionListener(e -> {
            dialogBox.setText(currentPlayer.getName() + " has use basic heavy attack!!!");
            currentPlayer.skill2(gb.boss);
            bossIcon.setText("NAME: " + gb.boss.getName() + "\nHP: " + gb.boss.getHp() + "\nLVL: " + gb.boss.getLevel());
            playerIcon.setText("NAME: " + currentPlayer.getName() + "\nHP: " + currentPlayer.getHp() + "\nLVL: " + currentPlayer.getLevel());
            skillPanel.setVisible(false);
        });

        SKILL3Button.addActionListener(e -> {
            dialogBox.setText(currentPlayer.getName() + " has use a buff!!!");
            currentPlayer.skill3(party);
            bossIcon.setText("NAME: " + gb.boss.getName() + "\nHP: " + gb.boss.getHp() + "\nLVL: " + gb.boss.getLevel());
            playerIcon.setText("NAME: " + currentPlayer.getName() + "\nHP: " + currentPlayer.getHp() + "\nLVL: " + currentPlayer.getLevel());
            skillPanel.setVisible(false);
        });
        SKILL4Button.addActionListener(e -> {
            dialogBox.setText(currentPlayer.getName() + " has use a debuff!!!");
            currentPlayer.skill4(gb.boss);
            bossIcon.setText("NAME: " + gb.boss.getName() + "\nHP: " + gb.boss.getHp() + "\nLVL: " + gb.boss.getLevel());
            playerIcon.setText("NAME: " + currentPlayer.getName() + "\nHP: " + currentPlayer.getHp() + "\nLVL: " + currentPlayer.getLevel());
            skillPanel.setVisible(false);
        });
//switch icon
        ImageIcon sw = new ImageIcon("C:\\Java_Practice\\New folder\\CAPSTONE\\icons\\switch.png");
        Image a = sw.getImage();
        Image imageA = a.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        a.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        sw = new ImageIcon(imageA);
        switchIconPanel.setSize(50,50);
        switchIcon.setIcon(sw);

        ImageIcon finalSw = sw;
        player1Button.addActionListener(e -> {
            if (!Objects.equals(currentPlayer, gb.characters.party.getFirst())) {

                dialogBox.setText(currentPlayer.getName() + " Has switch to " + gb.characters.party.getFirst().getName());
                playerIcon.setText("NAME: " + gb.characters.party.getFirst().getName() + "\nHP: " + currentPlayer.getHp() + "\nLVL: " + currentPlayer.getLevel());
                currentPlayer = gb.characters.party.getFirst();
                playerName.setText(currentPlayer.getName());
            } else {
                dialogBox.setText("Invalid choice! You cannot switch with yourself");
            }

            ImageIcon tk = new ImageIcon("C:\\Java_Practice\\New folder\\CAPSTONE\\icons\\tank.png");
            Image d = tk.getImage();
            Image imageD = d.getScaledInstance(finalSw.getIconWidth(), finalSw.getIconHeight(), Image.SCALE_SMOOTH);
            d.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            tk = new ImageIcon(imageD);
            HeroEmblemIcon.setIcon(tk);
            skillPanel.setVisible(true);
        });
        player2Button.addActionListener(e -> {
            if (!Objects.equals(currentPlayer, gb.characters.party.get(1))) {
                dialogBox.setText(currentPlayer.getName() + " Has switch to " + gb.characters.party.get(1).getName());
                playerIcon.setText("NAME: " + gb.characters.party.get(1) + "\nHP: " + currentPlayer.getHp() + "\nLVL: " + currentPlayer.getLevel());
                currentPlayer = gb.characters.party.get(1);
                playerName.setText(currentPlayer.getName());
            } else {
                dialogBox.setText("Invalid choice! You cannot switch with yourself");
            }
            ImageIcon ft = new ImageIcon("C:\\Java_Practice\\New folder\\CAPSTONE\\icons\\fighter.png");
            Image b = ft.getImage();
            Image imageB = b.getScaledInstance(finalSw.getIconWidth(), finalSw.getIconHeight(), Image.SCALE_SMOOTH);
            b.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ft = new ImageIcon(imageB);
            HeroEmblemIcon.setIcon(ft);
            skillPanel.setVisible(true);
        });
        player3Button.addActionListener(e -> {
            if (!Objects.equals(currentPlayer, gb.characters.party.get(2))) {
                dialogBox.setText(currentPlayer.getName() + " Has switch to " + gb.characters.party.get(2).getName());
                playerIcon.setText("NAME: " + gb.characters.party.get(2) + "\nHP: " + currentPlayer.getHp() + "\nLVL: " + currentPlayer.getLevel());
                currentPlayer = gb.characters.party.get(2);
                playerName.setText(currentPlayer.getName());
            } else {
                dialogBox.setText("Invalid choice! You cannot switch with yourself");
            }
            ImageIcon mg = new ImageIcon("C:\\Java_Practice\\New folder\\CAPSTONE\\icons\\mage.png");
            Image c = mg.getImage();
            Image imageC = c.getScaledInstance(finalSw.getIconWidth(), finalSw.getIconHeight(), Image.SCALE_SMOOTH);
            c.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            mg = new ImageIcon(imageC);
            HeroEmblemIcon.setIcon(mg);
            skillPanel.setVisible(true);
        });
        player4Button.addActionListener(ev -> {
            if (!Objects.equals(currentPlayer, gb.characters.party.get(3))) {
                dialogBox.setText(currentPlayer.getName() + " Has switch to " + gb.characters.party.get(3).getName());
                playerIcon.setText("NAME: " + gb.characters.party.get(3) + "\nHP: " + currentPlayer.getHp() + "\nLVL: " + currentPlayer.getLevel());
                currentPlayer = gb.characters.party.get(3);
                playerName.setText(currentPlayer.getName());
            } else {
                dialogBox.setText("Invalid choice! You cannot switch with yourself");
            }
            ImageIcon sp = new ImageIcon("C:\\Java_Practice\\New folder\\CAPSTONE\\icons\\support.png");
            Image e = sp.getImage();
            Image imageE = e.getScaledInstance(finalSw.getIconWidth(), finalSw.getIconHeight(), Image.SCALE_SMOOTH);
            e.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            sp = new ImageIcon(imageE);

            HeroEmblemIcon.setIcon(sp);
            skillPanel.setVisible(true);
        });

        ImageIcon p1 = new ImageIcon("C:\\Java_Practice\\New folder\\CAPSTONE\\icons\\tankIcon.png");
        Image ip1 = p1.getImage();
        Image imagep1 = ip1.getScaledInstance(40,40, Image.SCALE_SMOOTH);
        ip1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        p1 = new ImageIcon(imagep1);

        ImageIcon p2 = new ImageIcon("C:\\Java_Practice\\New folder\\CAPSTONE\\icons\\fighterIcon.png");
        Image ip2 = p2.getImage();
        Image imagep2 = ip2.getScaledInstance(40,40, Image.SCALE_SMOOTH);
        ip2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        p2 = new ImageIcon(imagep2);

        ImageIcon p3 = new ImageIcon("C:\\Java_Practice\\New folder\\CAPSTONE\\icons\\mageIcon.png");
        Image ip3 = p3.getImage();
        Image imagep3 = ip3.getScaledInstance(40,40, Image.SCALE_SMOOTH);
        ip3.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        p3 = new ImageIcon(imagep3);

        ImageIcon p4 = new ImageIcon("C:\\Java_Practice\\New folder\\CAPSTONE\\icons\\healerIcon.png");
        Image ip4 = p4.getImage();
        Image imagep4 = ip4.getScaledInstance(40,40, Image.SCALE_SMOOTH);
        ip4.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        p4 = new ImageIcon(imagep4);

        player1Button.setIcon(p1);
        player2Button.setIcon(p2);
        player3Button.setIcon(p3);
        player4Button.setIcon(p4);
    }
}

