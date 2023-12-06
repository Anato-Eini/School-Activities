import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class game extends JFrame{
    private JPanel gamePanel;
    private JPanel loadingPanel;
    private JButton startBtn;

    game(){
        setContentPane(gamePanel);
        battleScreen bs = new battleScreen();
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bs.getBattlePanel().setVisible(true);
                gamePanel.setVisible(false);
                setContentPane(bs.getBattlePanel());
            }
        });
    }
}
