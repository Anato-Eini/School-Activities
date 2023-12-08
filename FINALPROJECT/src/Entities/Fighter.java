package Entities;

public class Fighter extends Character{
    @Override
    public void basicAttack(Entity entity) {
        entity.setHp(getHp() - (this.getDamage() - entity.getArmor()));
    }

    @Override
    public void skill1(Entity entity) {
        skills.get(3).doSkill(entity);
    }

    @Override
    public void skill2(Entity entity) {
        skills.get(1).doSkill(entity);
    }

    @Override
    public void skill3(Entity entity) {
        skills.get(2).doSkill(entity);
    }
    public void specialSkill(){
        this.setHp((int)(getHp() * 1.1));
        skills.getFirst().setAppliedTurns(5);
    }
}
