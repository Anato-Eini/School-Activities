package Entities;

import java.util.ArrayList;

public class Mage extends Entity {
    public void specialSkill(ArrayList<Entity> entities){
        for(Entity e: entities)
            e.setHp((int)(getHp() * 0.90));
    }
}
