package com.mygdx.game.Game2D.Manager;

import com.mygdx.game.Game2D.Algorithms.Algorithm;
import com.mygdx.game.Game2D.Entities.NPC.NPC;
import com.mygdx.game.Game2D.Threads.NPCRunnable;
import com.mygdx.game.Game2D.World.GameMap;

import java.util.ArrayList;

/**
    This class will be for managing npc movements with threads.
    Implemented with Heap
 */
public class NPCManager {
    private final NPCRunnable[] activeNPCRunnable;
    public NPCManager(GameMap gameMap) {
        activeNPCRunnable = new NPCRunnable[Runtime.getRuntime().availableProcessors()];

        for(int i = 0; i < activeNPCRunnable.length; i++)
            activeNPCRunnable[i] = new NPCRunnable(gameMap);
    }

    public NPCManager addNPC(NPC npc) {
        activeNPCRunnable[0].addNPC(npc);

        if(activeNPCRunnable[0].getState() == Thread.State.NEW)
            activeNPCRunnable[0].start();

        int currentIndex = 0;
        while (true){
            int minimum = currentIndex, left = minimum * 2 + 1, right = minimum * 2 + 2;

            if(left < activeNPCRunnable.length &&
                    activeNPCRunnable[left].getSize() < activeNPCRunnable[minimum].getSize())
                minimum = left;
            if(right < activeNPCRunnable.length &&
                    activeNPCRunnable[right].getSize() < activeNPCRunnable[minimum].getSize())
                minimum = right;

            if(minimum != currentIndex){
                Algorithm.swap(activeNPCRunnable, minimum, currentIndex);
                currentIndex = minimum;
            }else
                break;
        }

        return this;
    }

    /**
     * Must be called whenever this manager is not used anymore to properly stop all the threads
     */
    public void clear(){
        for(NPCRunnable npcRunnable : activeNPCRunnable)
            npcRunnable.stopThread();
    }

    public ArrayList<NPC> getNPCs(){
        ArrayList<NPC> npcs = new ArrayList<>();
        for (NPCRunnable npcRunnable : activeNPCRunnable)
            npcs.addAll(npcRunnable.getNpc());

        return npcs;
    }
}
