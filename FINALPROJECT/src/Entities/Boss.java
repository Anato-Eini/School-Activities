package Entities;
public class Boss extends Entity{
    public Boss(String name, int level, int hp, Skill skill1, Skill skill2, Skill skill3, Skill skill4) {
        super(name, level, hp, skill1, skill2, skill3, skill4);
    }

    @Override
    public void basicAttack(Entity entity) {
        entity.setHp(getHp() - (this.getDamage() - entity.getArmor()));
    }

    public void skill1(Entity entity) {
        skills.getFirst().doSkill(entity);
    }
    public void skill2(Entity entity) {
        skills.get(1).doSkill(entity);
    }
    public void skill3(Entity entity) {
        skills.get(2).doSkill(entity);
    }
    public void skill4(Entity entity){
        skills.get(3).doSkill(entity);
    }
}
