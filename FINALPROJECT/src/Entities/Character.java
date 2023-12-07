package Entities;

public abstract class Character extends Entity{
    public Character() {
        super("", 0, 0, null, null, null, null);
    }
    public abstract void skill1(Entity entity);


    public abstract void skill2(Entity entity);


    public abstract void skill3(Entity entity);
}
