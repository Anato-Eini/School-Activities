package com.mygdx.game.Game2D.Threads;

import com.mygdx.game.Game2D.Entities.NPC.NPC;
import com.mygdx.game.Game2D.World.GameMap;

import java.util.ArrayList;

public class NPCRunnable extends Thread{
    private final ArrayList<NPC> npc;
    private boolean run;
    private final GameMap gameMap;

    public NPCRunnable(GameMap gameMap){
        npc = new ArrayList<>();
        run = true;
        this.gameMap = gameMap;
    }

    public void addNPC(NPC npc){
        synchronized (this.npc){
            this.npc.add(npc);
        }
    }

    @Override
    public void run() {
        while(run) {
            synchronized (this.npc){
                for (NPC npc : npc)
                    npc.update();
            }
            synchronized (gameMap){
                try {
                    gameMap.wait();
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    public void stopThread(){
        run = false;
    }

    public ArrayList<NPC> getNpc() {
        return npc;
    }

    public int getSize(){
        return npc.size();
    }
}