import Entities.*;
import Entities.Character;
import java.util.ArrayList;
public class GameBehavior {
    int totalTime;
    Boss boss;
    ArrayList<Character> characters = new ArrayList<>();

    public GameBehavior(int totalTime) {
        boss = new Boss("Serato", 100, 100000,
                new Skill("psst", 50, 2),
                new Skill("I hate this :<", 75, 3),
                new Skill("Cool and Normal", 120, 5),
                new Skill("Syntactically Correct", 200, 10).setDamage(30));
        characters.add((Character) new Fighter().setName("Fighter").setHp(500).setArmor(270).setLevel(70).setDamage(120).setSkills(
                new Skill("Aleutenian's Rhapsody", 619, 8),
                new Skill("Arma Qualia", 500, 1),
                new Skill("Demonia", 315, 4),
                new Skill("Blood Borne", 509, 4)
        ));
        characters.add((Character) new Tank().setName("Tank").setHp(1000).setArmor(500).setLevel(70).setDamage(90).setSkills(
                new Skill("Vision Shift", 179, 4),
                new Skill("Temporal Void", 875, 3),
                new Skill("Emperial's Defense", 1000, 2),
                new Skill("Divine Providence", 0, 10)
        ));
        characters.add((Character) new Mage().setName("Mage").setHp(350).setArmor(230).setLevel(75).setDamage(170).setSkills(
                new Skill("Electric Magic", 200, 3),
                new Skill("Fire Magic", 1000, 6),
                new Skill("Earth Magic", 900, 2),
                new Skill("Heaven's Strike", 0, 10)
        ));
        characters.add((Character) new Support().setName("Support").setHp(500).setArmor(250).setLevel(73).setDamage(100).setSkills(
                new Skill("Angelic Grace", 260, 5),
                new Skill("???", 245, 7),
                new Skill("Smol Hil", 0, 2),
                new Skill("Big Hil", 0, 10)
        ));
        this.totalTime = totalTime;
    }
    //hello planets("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto");
    
}
