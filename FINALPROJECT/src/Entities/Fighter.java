package Entities;
import java.util.ArrayList;
public class Fighter extends Entity {
    public void specialSkill(){
        this.setHp((int)(getHp() * 1.1));
        skills.get(0).setAppliedTurns(5);
    }
}
