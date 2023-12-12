import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class game extends JFrame {
    //comment this out
    private JPanel gamePanel;
    private JLabel startBtn;
    game() {

        setContentPane(gamePanel);

        ImageIcon icon = new ImageIcon("C:\\Java_Practice\\New folder\\CAPSTONE\\icons\\start.png");
        Image i = icon.getImage();
        Image image = i.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        i.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        MouseListener ms = getMouseListener();
        gamePanel.setBackground(null);
        startBtn.setIcon(icon);
        startBtn.addMouseListener(ms);
    }
    private MouseListener getMouseListener() {
        battleScreen bs = new battleScreen();
        MouseListener ms = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               bs.getBattlePanel().setVisible(true);
                gamePanel.setVisible(false);
                //loadingPanel
                setContentPane(bs.getBattlePanel());
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        return ms;
    }
    //
}
