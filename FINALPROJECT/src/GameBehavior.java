import Entities.*;
import Entities.Character;
import java.util.ArrayList;
import java.util.List;
public class GameBehavior {
    int totalTime;
    Boss boss;
    ArrayList<Character> characters = new ArrayList<>();

    public GameBehavior() {
        boss = new Boss("Serato", 100, 100000,
                new Skill("Heaver's Wrath", 50, 5),
                new Skill("Hell's Gate", 75, 8),
                new Skill("Louise Piggy's Bonk", 120, 20),
                new Skill("Syntactically Correct", 200, 60));
        /*characters.add((Character) new Character().setName("James").setHp(500).setArmor(50).setLevel(70).setSkills((ArrayList)List.of(
                new Skill(),
                new Skill(),
        )));*/
    }

    public GameBehavior(int totalTime) {
        this.totalTime = totalTime;
    }
    //hello planets("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto");
    
}
