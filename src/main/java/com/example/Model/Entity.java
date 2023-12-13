package com.example.Model;

public abstract class Entity {
    private String DebuffType;
    private String name;
    private int level, hp, baseDmg, maxHP;
    private int skill3cd = 0,skill4cd = 0;
    private final int skill3RealCd = 3, skill4RealCd = 3;

    private Debuff debuff = null;
    private Buff buff = null;

    private float speed;

    public Entity setDebuffType(String debuffType) {
        DebuffType = debuffType;
        return this;
    }

    public Entity(){}
    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    public int getHp() {
        return hp;
    }


    public Entity setName(String name) {
        this.name = name;
        return this;
    }
    public Entity setLevel(int level) {
        this.level = level;
        return this;
    }
    public Entity setHp(int hp) {
        this.hp = hp;
        return this;
    }
    public abstract void skill1(Entity entity); //basic light attack
    public abstract void skill2(Entity entity); //basic heavy attack
    public abstract void skill3(Party p); //buffer
    public abstract void skill4(Entity entity); //enemy debuffer

    public abstract Entity setSpeed();
    public abstract Entity setBaseDmg();
    public boolean isDead(){
        return hp <= 0;
    }
    @Override
    public String toString() {
        return name + " " + hp;
    }


    public int getSkill3cd() {
        return skill3cd;
    }

    public Entity setSkill3cd(int skill3cd) {
        this.skill3cd = skill3cd;
        return this;
    }
    public Entity setSkill3cd() {
        this.skill3cd = 0;
        return this;
    }

    public int getSkill4cd() {
        return skill4cd;
    }

    public Entity setSkill4cd(int skill4cd) {
        this.skill4cd = skill4cd;
        return this;
    }
    public Entity setSkill4cd() {
        this.skill4cd = 0;
        return this;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public Entity setMaxHP(int maxHP) {
        this.maxHP = maxHP;
        return this;
    }

    public String getDebuffType() {
        return DebuffType;
    }

    public Entity setDebuff(Debuff debuff) {
        this.debuff = debuff;
        return this;
    }

    public Debuff getDebuff() {
        return debuff;
    }

    public Buff getBuff() {
        return buff;
    }

    public Buff setBuff(Buff buff) {
        this.buff = buff;
        return buff;
    }

    public int getBaseDmg() {
        return baseDmg;
    }

    public Entity setBaseDmg(int baseDmg) {
        this.baseDmg = baseDmg;
        return this;
    }

    public float getSpeed() {
        return speed;
    }

    public Entity setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public int getSkill3RealCd() {
        return skill3RealCd;
    }

    public int getSkill4RealCd() {
        return skill4RealCd;
    }



}
