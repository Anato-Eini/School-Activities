package Entities;

import java.util.ArrayList;

public class Party {

    public ArrayList<Entity> party = new ArrayList<>();

    public boolean isWipedOut() {
        int count = 0;
        for(Entity e:party){
            if(!e.isDead()){
                count++;
            }
        }
        if(count == 0){
            return true;
        } else{
            return false;
        }
    }


    //add sa arraylist ang mga characters sa gamebehavior.java


}
