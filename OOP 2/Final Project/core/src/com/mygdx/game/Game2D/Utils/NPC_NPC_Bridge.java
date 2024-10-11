package com.mygdx.game.Game2D.Utils;

import com.mygdx.game.Game2D.Entities.NPC.NPC;

public class NPC_NPC_Bridge {
    NPC npc1, npc2;
    Thread npc1Thread, npc2Thread;
    boolean run;


    public NPC_NPC_Bridge(NPC npc1, NPC npc2) {
        this.npc1 = npc1;
        this.npc2 = npc2;

        run = true;

        npc1Thread = new Thread(() -> {
            while (run && npc1.indexDia < npc1.getSizeDialogues()) {
                npc1.doDialogue();
                npc1.indexDia++;
                try{
                    npc2.wait();
                }catch (InterruptedException ignored){}
            }
        });

        npc2Thread = new Thread(() -> {
            while (run && npc2.indexDia < npc2.getSizeDialogues()) {
                npc2.doDialogue();
                npc2.indexDia++;
                try{
                    npc1.wait();
                }catch (InterruptedException ignored) {}
            }
        });
    }

    public void run(){
        npc1Thread.start();
        npc2Thread.start();
    }

    public void stop(){
        run = false;
    }

}
