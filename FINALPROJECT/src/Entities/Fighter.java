package Entities;

public class Fighter extends Entity {
    public void specialSkill(){
        this.setHp((int)(getHp() * 1.1));
        skills.getFirst().setAppliedTurns(5);
    }
}
