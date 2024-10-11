package com.mygdx.game.Game2D.World.Maps.Minigames;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game2D.Entities.NPC.NPC;
import com.mygdx.game.Game2D.Entities.NPC.NPCMinigame1;
import com.mygdx.game.Game2D.Utils.GameQueue;
import com.mygdx.game.Game2D.Utils.RandomGetter;
import com.mygdx.game.Game2D.World.GameMap;
import com.mygdx.game.Game2D.World.Minigame;

import java.util.Random;

public class MINIGAME1 extends GameMap implements Minigame {

    public static int health = 10;
    public int level;

    public MINIGAME1(String mapName, int level) {
        super(mapName);
        this.level = level;
    }
    float elapsedTime;
    @Override
    public void setNPCS() {
//        new Thread(() -> {
//            for(int i = 0; i < 10; i++) {
//                NPCMinigame1 npcMinigame1 = new NPCMinigame1(1000, level);
//                Gdx.app.postRunnable(() -> npcMinigame1.setTextureAtlas(RandomGetter.getRandomTA_NPC()));
//
//
//
//                try {
//                    Thread.sleep(499);
//                }catch (InterruptedException ignored){}
//
//                npcManager.addNPC(npcMinigame1);
//                GameQueue.add(() -> bodies.add(npcMinigame1.boxBody));
//            }
//        }).start();

        for(int i = 0; i < 10; i++){
            Random random = new Random();
            float speed = random.nextFloat(400 - 140) + 140;
            NPCMinigame1 npcMinigame1 = new NPCMinigame1(1000, level);
            npcMinigame1.setTextureAtlas(RandomGetter.getRandomTA_NPC());
            npcMinigame1.setSpeed(speed);
            bodies.add(npcMinigame1.boxBody);
            npcs.add(npcMinigame1);
        }
    }

    @Override
    public void onGameOver() {

    }

    @Override
    public void minigame() {
//        npcManager.getNPCs().forEach(NPC::update);
        for (NPC npc : npcs
             ) {
            npc.update();
        }
        if(health <= 0){
            onGameOver();
        }
    }
}