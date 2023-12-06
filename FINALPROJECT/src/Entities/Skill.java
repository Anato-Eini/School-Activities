package Entities;


public class Skill {
    private String name;
    private int damage, cooldown, realCooldown;

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
        realCooldown = cooldown;
        entity1.setHp(entity1.getHp() - (damage - entity1.getArmor()));
    }
}
