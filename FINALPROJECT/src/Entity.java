public  class Entity {
    private final int hp;
    private final String name;
    private final int damage;

    Entity(EntityBuilder builder){
        this.hp = builder.hp;
        this.damage = builder.damage;
        this.name = builder.name;
    }

    @Override
    public String toString() {
        return "Name: " + name + '\n' +
                "HP: " + hp + '\n' +
                "Damage: " + damage;
    }
}
