package Entities;

import java.util.ArrayList;

public abstract class Entity {
    ArrayList<Skill> skills = new ArrayList<>();
    private String name;
    private int level, hp, armor, damage, numAppliedDebuff;
    String statusEffect;
    protected boolean isInDebuff = false;
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

    public String getStatusEffect(){
        return statusEffect;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getDamage(){
        return damage;
    }
    public int getHp() {
        return hp;
    }

    public int getArmor(){
        return armor;
    }

    public int getNumAppliedDebuff() {return numAppliedDebuff;}

    public boolean isInDebuff() {
        return isInDebuff;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public Entity setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
        return this;
    }

    public Entity setInDebuff(boolean inDebuff) {
        isInDebuff = inDebuff;
        return this;
    }

    public Entity setStatusEffect(String statusEffect) {
        this.statusEffect = statusEffect;
        return this;
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
    public Entity setDamage(int damage) {
        this.damage = damage;
        return this;
    }
    public void basicAttack(Entity entity){
       entity.setHp(getHp() - (this.getDamage() - entity.getArmor()));
    }
    public void skill1(Entity entity){
        skills.get(0).doSkill(entity);
    }
    public void skill2(Entity entity){
        skills.get(1).doSkill(entity);
    }
    public void skill3(Entity entity){
        skills.get(2).doSkill(entity);
    }
    public void skill4(Entity entity){
        skills.get(3).doSkill(entity);
    }
    @Override
    public String toString() {
        return name;
    }
}
