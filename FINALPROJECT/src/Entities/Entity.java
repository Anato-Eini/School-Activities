package Entities;
import java.util.ArrayList;

public abstract class Entity {
    private String name;
    private int level, hp, baseDmg;
    private int skill1cd = 0,skill2cd = 0,skill3cd = 0,skill4cd = 0;

    private Debuff debuff = null;
    private Buff buff = null;

    private float speed;


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

    public int getSkill1cd() {
        return skill4cd;
    }

    public Entity setSkill1cd(int skill1cd) {
        this.skill1cd = skill1cd;
        return this;
    }

    public int getSkill2cd() {
        return skill2cd;
    }

    public Entity setSkill2cd(int skill2cd) {
        this.skill2cd = skill2cd;
        return this;
    }

    public int getSkill3cd() {
        return skill4cd;
    }

    public Entity setSkill3cd(int skill3cd) {
        this.skill3cd = skill3cd;
        return this;
    }

    public int getSkill4cd() {
        return skill4cd;
    }

    public Entity setSkill4cd(int skill4cd) {
        this.skill4cd = skill4cd;
        return this;
    }

    public Debuff getDebuff() {
        return debuff;
    }

    public Entity setDebuff(Debuff debuff) {
        this.debuff = debuff;
        return this;
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
}
