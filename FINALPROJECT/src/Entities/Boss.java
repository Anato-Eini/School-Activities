package Entities;

public class Boss extends Entity{
    public Boss(String name, int level, int hp, Skill skill1, Skill skill2, Skill skill3, Skill skill4) {
        super(name, level, hp, skill1, skill2, skill3, skill4);
    }

    @Override
    public void skillDamage1(Entity entity) {
        skills.get(0).doSkill(entity);
    }

    @Override
    public void skillDamage2(Entity entity) {
        skills.get(1).doSkill(entity);
    }

    @Override
    public void skillDamage3(Entity entity) {
        skills.get(2).doSkill(entity);
    }
    public void skillDamage4(Entity entity){
        skills.get(3).doSkill(entity);
    }
}
