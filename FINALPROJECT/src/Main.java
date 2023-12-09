import Entities.Character;
import Entities.Entity;
import Entities.Fighter;
import Entities.Skill;

import javax.swing.*;
import java.util.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        game app = new game();
        app.setTitle("UGANG LEGENDS");
        app.setSize(1000,500);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setVisible(true);
        System.out.println("I love intellij");

//        Director director = new Director();
//        EntityBuilder entityBuilder = new EntityBuilder();
//
//        director.buildPlayer1(entityBuilder);
//        Entity p1 = entityBuilder.build();
//
//        director.buildBoss(entityBuilder);
//        Entity boss = entityBuilder.build();
//
//
//        System.out.println(p1.toString() + '\n');
//        System.out.println(boss.toString() + '\n');



    }
}