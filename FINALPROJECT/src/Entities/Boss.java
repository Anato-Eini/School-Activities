package Entities;
public class Boss extends Entity{
    public Boss(String name, int level, int hp, Skill skill1, Skill skill2, Skill skill3, Skill skill4) {
        super(name, level, hp, skill1, skill2, skill3, skill4);
    }
    public void specialSkill(Entity entity){
        skills.get(3).doSkill(entity);
    }
}
