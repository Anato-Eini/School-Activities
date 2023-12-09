package Entities;

public class Tank extends Entity {
    public void specialSkill(){
        setArmor((int)(getArmor() * 1.2));
        skills.get(3).setAppliedTurns(5);
    }
}
