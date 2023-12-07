package Entities;

import java.util.ArrayList;

public class Support extends Character{
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
    public void specialSkill(ArrayList<Character> characters){
        for(Character c: characters)
            c.setHp((int)(getHp() * 1.15));
    }
}
