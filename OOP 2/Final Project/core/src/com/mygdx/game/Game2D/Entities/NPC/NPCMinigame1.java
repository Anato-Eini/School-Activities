package com.mygdx.game.Game2D.Entities.NPC;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game2D.World.CollisionType;

import java.util.Random;

public class NPCMinigame1 extends NPC {
    Random random = new Random();
    int level;
    public NPCMinigame1(int length, int level) {
        super(length);
        this.level = level;
        randomizeNPC();
        setDirection(Direction.DOWN);
        fixtureDef.filter.maskBits = (short) (CollisionType.WALL.getValue() | CollisionType.PLAYER.getValue() | CollisionType.NPC.getValue());
    }

    public void randomizeNPC(){
        float random1 = random.nextFloat(11 - 7 + 1) + 7.5F;
        float random2 = random.nextFloat(17 - 13 + 1) + 12.5F;

        if(random.nextInt(2) == 1){
            setPosition(new Vector2(random1, 33));
        }else{
            setPosition(new Vector2(random2, 33));
        }

        float speedLowerBound = 0;
        float speedUpperBound = 0;

        if(level == 1){
            speedLowerBound = 140;
            speedUpperBound = 180;
        }
        if(level == 2){
            speedLowerBound = 180;
            speedUpperBound = 220;
        }

        if(level == 3){
            speedLowerBound = 120;
            speedUpperBound = 260;
        }

        setSpeed(random.nextFloat(speedUpperBound - speedLowerBound + 1) + speedLowerBound);
    }

    public void move(){
        boxBody.applyLinearImpulse(new Vector2(0, -speed / 2), boxBody.getWorldCenter(), true);

        if(getPosition().y <= 0.5){
            randomizeNPC();
        }
    }

    @Override
    public void update() {
        move();
//        float speedLowerBound = 0;
//        float speedUpperBound = 0;
//
//        if(level == 1){
//            speedLowerBound = 140;
//            speedUpperBound = 180;
//        }
//
//        if(level == 2){
//            speedLowerBound = 180;
//            speedUpperBound = 220;
//        }
//
//        if(level == 3){
//            speedLowerBound = 120;
//            speedUpperBound = 260;
//        }
//
//        setSpeed(random.nextFloat(speedUpperBound - speedLowerBound + 1) + speedLowerBound);
    }
}
