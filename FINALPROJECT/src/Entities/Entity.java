package Entities;

import java.util.ArrayList;

public abstract class Entity {
    ArrayList<Skill> skills = new ArrayList<>();
    private String name;
    private int level, hp, armor, damage, numAppliedDebuff;
    String statusEffect;
    public Entity(){}
    public Entity(String name, int level, int hp, Skill skill1, Skill skill2, Skill skill3, Skill skill4){
        this.name = name;
        this.level = level;
        this.hp = hp;
        skills.add(skill1);
        skills.add(skill2);
        skills.add(skill3);
        skills.add(skill4);
        statusEffect = "Normal";
    }

    public Entity setStatusEffect(String statusEffect) {
        this.statusEffect = statusEffect;
        return this;
    }

    public String getStatusEffect(){
        return statusEffect;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }
    public int getArmor(){
        return armor;
    }


    public Entity setSkills(Skill skills, Skill skills1, Skill skills2, Skill skills3) {
        this.skills.add(skills);
        this.skills.add(skills1);
        this.skills.add(skills2);
        this.skills.add(skills3);
        return this;
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

    public Entity setArmor(int armor) {
        this.armor = armor;
        return this;
    }

    public Entity setNumAppliedDebuff(int numAppliedDebuff) {
        this.numAppliedDebuff = numAppliedDebuff;
        return this;
    }
    public int getDamage(){
        return damage;
    }
    public Entity setDamage(int damage) {
        this.damage = damage;
        return this;
    }
    public int getNumAppliedDebuff() {return numAppliedDebuff;}
    public abstract void basicAttack(Entity entity);
    public abstract void skill1(Entity entity);
    public abstract void skill2(Entity entity);
    public abstract void skill3(Entity entity);

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return name;
    }
}
