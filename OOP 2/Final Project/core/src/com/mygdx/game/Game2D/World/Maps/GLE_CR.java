package com.mygdx.game.Game2D.World.Maps;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game2D.Entities.Entity;
import com.mygdx.game.Game2D.Entities.NPC.MainNPC;
import com.mygdx.game.Game2D.Utils.GameQueue;
import com.mygdx.game.Game2D.Utils.RandomGetter;
import com.mygdx.game.Game2D.World.GameMap;

import java.util.ArrayList;
import java.util.Arrays;

public class GLE_CR extends GameMap {
    private final ArrayList<String> dialogues = new ArrayList<>(
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
    );

    public GLE_CR(String mapName) {
        super(mapName);
    }

    @Override
    public void setNPCS() {
        MainNPC mainNpc = new MainNPC(0);
        mainNpc.setTextureAtlas(RandomGetter.getRandomTA_NPC());
        mainNpc.setToStay(Entity.Direction.DOWN);
        mainNpc.setPosition(new Vector2(9, 2));
        mainNpc.setDialogues(dialogues);
        npcManager.addNPC(mainNpc);

        MainNPC npc2 = new MainNPC(0);
        npc2.setTextureAtlas(RandomGetter.getRandomTA_NPC());
        npc2.setToStay(Entity.Direction.DOWN);
        npc2.setPosition(new Vector2(11, 2));
        npc2.setDialogues(dialogues);
        npcManager.addNPC(npc2);

        MainNPC npc3 = new MainNPC(0);
        npc3.setTextureAtlas(RandomGetter.getRandomTA_NPC());
        npc3.setToStay(Entity.Direction.DOWN);
        npc3.setPosition(new Vector2(13, 2));
        npc3.setDialogues(dialogues);
        npcManager.addNPC(npc3);

        GameQueue.add(() -> {
            bodies.add(mainNpc.boxBody);
            bodies.add(npc2.boxBody);
            bodies.add(npc3.boxBody);
        });

    }
}
