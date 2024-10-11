package com.mygdx.game.Game2D.World.Maps;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game2D.Dialogues.Dialogues;
import com.mygdx.game.Game2D.Entities.Entity;
import com.mygdx.game.Game2D.Entities.NPC.MainNPC;
import com.mygdx.game.Game2D.Manager.ResourceManager;
import com.mygdx.game.Game2D.Utils.GameQueue;
import com.mygdx.game.Game2D.Utils.RandomGetter;
import com.mygdx.game.Game2D.World.GameMap;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mygdx.game.Game2D.Screens.GameScreen.*;

public class ROOM extends GameMap {
    public ROOM(String map){
        super(map);
    }

    public void setNPCS(){
        MainNPC gymNPC = new MainNPC(100);
        gymNPC.setTextureAtlas(RandomGetter.getRandomTA_NPC());
        gymNPC.setDialogues(Dialogues.dialogues3);
        gymNPC.setToStay(Entity.Direction.RIGHT);
        gymNPC.setPosition(new Vector2(29, 9));

        MainNPC anotherNPC = new MainNPC(100);
        anotherNPC.setTextureAtlas(RandomGetter.getRandomTA_NPC());
        anotherNPC.setPosition(new Vector2(13, 9));
        anotherNPC.setDialogues(new ArrayList<>(Arrays.asList(
                "Sooo Warm...",
                "Hopefully it will be rainy day soon",
                "Zed dakog PERSONALITY",
                "Goodluck on your studies"
        )));
        anotherNPC.setToStay(Entity.Direction.UP);

        npcManager.addNPC(gymNPC).addNPC(anotherNPC);

        GameQueue.add(() -> {
            bodies.add(gymNPC.boxBody);
            bodies.add(anotherNPC.boxBody);
        });

        player.setDialogue();
    }
}