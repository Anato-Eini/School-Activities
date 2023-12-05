import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class game extends JFrame{
    private JPanel gamePanel;
    private JPanel loadingPanel;
    private JPanel battlePanel;
    private JButton startBtn;
    private JTextArea dialogBox;
    private JButton skill1Button;
    private JButton skill2Button;
    private JButton player1Button;
    private JButton player2Button;
    private JButton skill3Button;
    private JButton player3Button;
    private JButton player4Button;
    private JButton skill4Button;
    private JProgressBar progressBar1;
    private JProgressBar progressBar2;
    private JProgressBar progressBar3;
    private JProgressBar progressBar4;
    private JProgressBar progressBar5;
    private JTextPane textPane1;
    private JTextPane textPane2;
    private JPanel p1Panel;

    game(){
        setContentPane(gamePanel);
        battlePanel.setVisible(false);

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(battlePanel);
                battlePanel.setVisible(true);
                textPane1.setText("Player");
                textPane2.setText("Boss");
                dialogBox.setText("Let the Final Battle Start!");
            }
        });
    }
    public static void main(String[] args) {
        game app = new game();
        app.setTitle("UGANG LEGENDS");

        app.setSize(1000,500);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setVisible(true);

    }
}
