package Entities;

import java.util.ArrayList;

public class Mage extends Entity {

    public Entity setBaseDmg() {
        setBaseDmg(50);
        return this;
    }

    public Entity setSpeed(){
        setSpeed(1.3F);
        return this;
    }

    @Override
    public void skill1(Entity entity) {
        entity.setHp(entity.getHp() - getBaseDmg());
    }

    @Override
    public void skill2(Entity entity) {
        entity.setHp(entity.getHp() - (getBaseDmg() * 2));
    }

    @Override
    public void skill3(Party p) {
        for(Entity e: p.party){
            if(e.isDead()){
                continue;
            }
            e.setHp((int) (e.getHp() * 1.25));
        }
    }

    @Override
    public void skill4(Entity entity) {
        //cast poison here
        entity.setDebuff(new Poison());
    }
}
