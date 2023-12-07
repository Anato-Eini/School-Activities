package Entities;


public class Skill {
    private String name;
    private int damage, realCooldown, appliedSec;
    private final int cooldown;

    public Skill(String name, int damage, int realCooldown){
        this.name = name;
        this.damage = damage;
        this.realCooldown = realCooldown;
        this.cooldown = 0;
    }
    private boolean isAvailable(){
        return realCooldown == 0;
    }

    public void doSkill(Entity entity1){
        if(isAvailable()){
            realCooldown = cooldown;
            entity1.setHp(entity1.getHp() - (damage - entity1.getArmor()));
        }
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getRealCooldown() {
        return realCooldown;
    }

    public int getAppliedSec() {
        return appliedSec;
    }

    public int getCooldown() {
        return cooldown;
    }

    public Skill setName(String name) {
        this.name = name;
        return this;
    }

    public Skill setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public Skill setRealCooldown(int realCooldown) {
        this.realCooldown = realCooldown;
        return this;
    }

    public Skill setAppliedSec(int appliedSec) {
        this.appliedSec = appliedSec;
        return this;
    }
}
