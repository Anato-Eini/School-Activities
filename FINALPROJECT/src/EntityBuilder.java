public class EntityBuilder implements Builder{
    int hp;
    String name;
    int damage;

    public EntityBuilder hp(int hp){
        this.hp =hp;
        return this;
    }
    public EntityBuilder name(String name) {
        this.name=name;
        return this;
    }
    public EntityBuilder damage(int damage) {
        this.damage=damage;
        return this;
    }
    public Entity build(){
        return new Entity(this);
    }

}
