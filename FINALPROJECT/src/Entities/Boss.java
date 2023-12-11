package Entities;

import java.util.ArrayList;

public class Boss extends Entity{

    public Entity setBaseDmg() {
        setBaseDmg(200);
        return this;
    }

    public Entity setSpeed(){
        setSpeed(1.7F);
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
    public void skill3(Party p) { //lets just say AoE na attack
        for(Entity e: p.party){
            e.setHp(e.getHp() - (getBaseDmg()/2));
        }
    }

    @Override
    public void skill4(Entity entity) {
        //cast stun
        entity.setDebuff(new Stun());
    }
}
