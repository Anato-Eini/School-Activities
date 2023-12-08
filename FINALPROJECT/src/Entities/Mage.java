package Entities;

import java.util.ArrayList;

public class Mage extends Character{
    @Override
    public void basicAttack(Entity entity) {
        entity.setHp(getHp() - (this.getDamage() - entity.getArmor()));
    }

    @Override
    public void skill1(Entity entity) {
        skills.getFirst().doSkill(entity);
    }

    @Override
    public void skill2(Entity entity) {
        skills.get(1).doSkill(entity);
    }

    @Override
    public void skill3(Entity entity) {
        skills.get(2).doSkill(entity);
    }
    public void specialSkill(ArrayList<Entity> entities){
        for(Entity e: entities)
            e.setHp((int)(getHp() * 0.90));
    }
    public void removeDebuff(Entity entity1){
        entity1.setStatusEffect("Normal");
        entity1.setNumAppliedDebuff(0);
    }
    public void heal(Entity entity1){
        entity1.setHp(entity1.getHp() + 50);
    }
    public void globalHeal(ArrayList<Entity> characters){
        for(Entity e : characters){
            e.setHp(e.getHp() + 50);
        }
    }
}
