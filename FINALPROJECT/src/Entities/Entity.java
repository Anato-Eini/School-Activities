package Entities;

import java.util.ArrayList;

public abstract class Entity {
    ArrayList<Skill> skills = new ArrayList<>();
    private String name;
    private int level, hp, armor;
    public Entity(String name, int level, int hp, Skill skill1, Skill skill2, Skill skill3, Skill skill4){
        this.name = name;
        this.level = level;
        this.hp = hp;
        skills.add(skill1);
        skills.add(skill2);
        skills.add(skill3);
        skills.add(skill4);
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


    public Entity setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
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

    public abstract void skillDamage1(Entity entity);
    public abstract void skillDamage2(Entity entity);
    public abstract void skillDamage3(Entity entity);
}
