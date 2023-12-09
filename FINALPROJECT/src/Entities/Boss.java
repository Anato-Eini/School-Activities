package Entities;
public class Boss extends Entity{
    public void specialSkill(Entity entity){
        skills.get(3).doSkill(entity);
    }
}
