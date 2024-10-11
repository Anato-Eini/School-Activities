import Entities.*;

import java.util.ArrayList;
public class GameBehavior {
    private int totalTime;
    Boss boss;
    Party characters = new Party();
    public GameBehavior() {
        boss = (Boss)new Boss().setName("Serato").setLevel(100).setHp(25000).setSpeed().setBaseDmg().setSkill3cd().setSkill4cd();
        characters.party.add(new Tank().setName("Tank").setHp(10000).setLevel(70).setSpeed().setBaseDmg().setSkill3cd().setSkill4cd());
        characters.party.add(new Fighter().setName("Fighter").setHp(5000).setLevel(70).setSpeed().setBaseDmg().setSkill3cd().setSkill4cd());
        characters.party.add(new Mage().setName("Mage").setHp(3500).setLevel(75).setSpeed().setBaseDmg().setSkill3cd().setSkill4cd());
        characters.party.add(new Support().setName("Support").setHp(5000).setLevel(73).setSpeed().setBaseDmg().setSkill3cd().setSkill4cd());
        this.totalTime = 0;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public GameBehavior setTotalTime(int totalTime) {
        this.totalTime = totalTime;
        return this;
    }

    //hello planets("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto");
}
