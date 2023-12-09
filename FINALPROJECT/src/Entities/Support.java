package Entities;

import java.util.ArrayList;
public class Support extends Entity {
    public void specialSkill(ArrayList<Entity> allies){
        for(Entity e: allies)
            e.setHp((int)(getHp() * 1.15));
    }
}
