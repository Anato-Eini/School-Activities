import Entities.*;

import java.util.ArrayList;
public class GameBehavior {
    int totalTime;
    Boss boss;
    ArrayList<Entity> characters = new ArrayList<>();
    public GameBehavior(int totalTime) {
        boss = new Boss("Serato", 100, 100000,
                new Skill("psst ayaw lagig tanaw sa papel", 50, 2),
                new Skill("I hate this :<", 75, 3),
                new Skill("Cool and Normal", 120, 5),
                new Skill("Syntactically Correct", 200, 10).setDamage(30));
        characters.add(new Fighter().setName("Fighter").setHp(500).setArmor(270).setLevel(70).setDamage(120).setSkills(
                new Skill("Aleutenian's Rhapsody", 175, 4),
                new Skill("Arma Qualia", 500, 3),
                new Skill("Demonia", 875, 4),
                new Skill("Blood Borne", 1000, 8)
        ));
        characters.add(new Tank().setName("Tank").setHp(1000).setArmor(500).setLevel(70).setDamage(90).setSkills(
                new Skill("Vision Shift", 315, 4),
                new Skill("Temporal Void", 509, 3),
                new Skill("Emperial's Defense", 619, 2),
                new Skill("Divine Providence", 0, 10)
        ));
        characters.add(new Mage().setName("Mage").setHp(350).setArmor(230).setLevel(75).setDamage(170).setSkills(
                new Skill("Electric Magic", 200, 3),
                new Skill("Fire Magic", 1000, 6),
                new Skill("Earth Magic", 900, 2),
                new Skill("Heaven's Strike", 0, 10)
        ));
        characters.add(new Support().setName("Support").setHp(500).setArmor(250).setLevel(73).setDamage(100).setSkills(
                new Skill("Synthesia", 260, 5),
                new Skill("Chronostasis", 245, 7),
                new Skill("Angelic Grace", 0, 2),
                new Skill("Divine Grace", 0, 10)
        ));
        this.totalTime = totalTime;
    }
    protected void updateTurn(Entity entity){
        if(entity.isInDebuff()){
            entity.setNumAppliedDebuff(entity.getNumAppliedDebuff() - 1);
            if (entity.getNumAppliedDebuff()== 0)
                entity.setInDebuff(false);
        }
        for(Skill s: entity.getSkills())
            if(s.getRealCooldown() > 0)
                s.setRealCooldown(s.getRealCooldown() - 1);
    }
    //hello planets("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto");
}
