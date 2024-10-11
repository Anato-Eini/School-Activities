package com.mygdx.game.Game2D.World.Maps;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game2D.Dialogues.Dialogues;
import com.mygdx.game.Game2D.Entities.Entity;
import com.mygdx.game.Game2D.Entities.NPC.MainNPC;
import com.mygdx.game.Game2D.Entities.NPC.NPCMinigame1;
import com.mygdx.game.Game2D.Utils.GameQueue;
import com.mygdx.game.Game2D.Utils.NPC_NPC_Bridge;
import com.mygdx.game.Game2D.Utils.RandomGetter;
import com.mygdx.game.Game2D.World.GameMap;

import java.util.ArrayList;
import java.util.Arrays;

public class GLE_202 extends GameMap {
    NPC_NPC_Bridge npcNpcBridge;

    public GLE_202(String mapName) {
        super(mapName);
    }

    @Override
    public void setNPCS() {
        MainNPC mainNpc = new MainNPC(100);
        mainNpc.setTextureAtlas(RandomGetter.getRandomTA_NPC());
        mainNpc.setToStay(Entity.Direction.DOWN);
        mainNpc.setPosition(new Vector2(2, 10));
        mainNpc.setDialogues(new ArrayList<>(
                Arrays.asList(
                        "Don't you have something better to do?",
                        "I don't have time for this right now.",
                        "Why are you even here?",
                        "Leave me alone.",
                        "I don't want to talk to you.",
                        "Go bother someone else.",
                        "I have nothing to say to you.",
                        "You’re not worth my time.",
                        "Just go away.",
                        "Stop wasting my time.",
                        "I can't deal with you right now.",
                        "What do you want this time?",
                        "I don’t care about your problems.",
                        "Can’t you take a hint?",
                        "Do you ever get tired of being annoying?",
                        "Your presence is not welcome here.",
                        "Why don't you just disappear?",
                        "I have more important things to do.",
                        "Talking to you is pointless.",
                        "I’m not interested in anything you have to say.",
                        "Why do you always have to make things worse?"
                        )
                )
        );

//        npcManager.addNPC(mainNpc);
//        npcs.add(mainNpc);
        npcManager.addNPC(mainNpc);

        MainNPC npc2 = new MainNPC(100);
        npc2.setTextureAtlas(RandomGetter.getRandomTA_NPC());
        npc2.setToStay(Entity.Direction.UP);
        npc2.setPosition(new Vector2(2, 8));
        npc2.setDialogues(new ArrayList<>(Dialogues.dialogues1));
//        npcs.add(npc2);
        npcManager.addNPC(npc2);

        MainNPC npc3 = new MainNPC(100);
        npc3.setTextureAtlas(RandomGetter.getRandomTA_NPC());
        npc3.setToStay(Entity.Direction.LEFT);
        npc3.setPosition(new Vector2(20, 9));
        npc3.setDialogues(Dialogues.dialogues2);
//        npcs.add(npc3);
        npcManager.addNPC(npc3);


        MainNPC npc4 = new MainNPC(100);
        npc4.setTextureAtlas(RandomGetter.getRandomTA_NPC());
        npc4.setPosition(new Vector2(15, 9));
        npcManager.addNPC(npc4);
//        npcs.add(npc4);

        MainNPC npc5 = new MainNPC(100);
        npc5.setTextureAtlas(RandomGetter.getRandomTA_NPC());
        npc5.setPosition(new Vector2(8, 9));
        npcManager.addNPC(npc5);
//        npcs.add(npc5);
/*        npc1.setDialogues();


        npcNpcBridge = new NPC_NPC_Bridge(npc1, npc2);
        npcNpcBridge.run();*/
    }
}