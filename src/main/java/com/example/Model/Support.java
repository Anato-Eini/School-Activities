package com.example.Model;

import java.util.ArrayList;



public class Support extends Entity {

    static String DebuffType = "Poison";
    public Entity setBaseDmg() {
        setBaseDmg(50);
        return this;
    }

    public Entity setSpeed(){
        setSpeed(1.6F);
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
        //buff damage
        for(Entity e: p.party){
            if(e.isDead()){
                continue;
            }
            e.setBuff(new DamageBuff(getBaseDmg()));
        }
    }

    @Override
    public void skill4(Entity entity) {
        //cast poison
        entity.setDebuff(new Poison());

    }
}
