package Entities;
public class Skill {
    private String name, debuff = null;
    private int damage, realCooldown, appliedTurns;
    private final int cooldown;

    public Skill(String name, int damage, int realCooldown){
        this.name = name;
        this.damage = damage;
        this.realCooldown = realCooldown;
        this.cooldown = 0;
    }

    public Skill(String name, int damage, int realCooldown, String debuff){
        this.name = name;
        this.damage = damage;
        this.realCooldown = realCooldown;
        this.cooldown = 0;
        this.debuff = debuff;
    }
    private boolean isAvailable(){
        return realCooldown == 0;
    }

    public void doSkill(Entity entity1){
        if(isAvailable()){
            realCooldown = cooldown;
            entity1.setHp(entity1.getHp() - (damage - entity1.getArmor()));
        }
        if(this.debuff != null){
            switch(this.debuff){
                case "Poison":
                    entity1.setStatusEffect("Poisoned");
                    entity1.setNumAppliedDebuff(5);
                    break;
                case "Stun":
                    entity1.setStatusEffect("Stunned");
                    entity1.setNumAppliedDebuff(1);
                    break;
                default:
                    break;
            }
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

    public int getAppliedTurns() {
        return appliedTurns;
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

    public Skill setAppliedTurns(int appliedTurns) {
        this.appliedTurns = appliedTurns;
        return this;
    }

    public Skill setDebuff(String debuff){
        this.debuff = debuff;
        return this;
    }

    @Override
    public String toString() {
        return name;
    }
}
