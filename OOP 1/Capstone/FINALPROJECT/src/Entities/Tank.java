package Entities;

import java.util.ArrayList;

public class Tank extends Entity {

    public Entity setBaseDmg() {
        setBaseDmg(100);
        return this;
    }

    public Entity setSpeed(){
        setSpeed(1.0F);
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
            e.setBuff(new DamageBuff(getBaseDmg()));
        }
    }

    @Override
    public void skill4(Entity entity) {
        //cast stun
        entity.setDebuff(new Stun());
    }
}
