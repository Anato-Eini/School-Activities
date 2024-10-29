package com.mygdx.game.Game2D.World.Maps;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game2D.Dialogues.Dialogues;
import com.mygdx.game.Game2D.Entities.NPC.MainNPC;
import com.mygdx.game.Game2D.Manager.ResourceManager;
import com.mygdx.game.Game2D.Utils.GameQueue;
import com.mygdx.game.Game2D.Utils.RandomGetter;
import com.mygdx.game.Game2D.World.GameMap;

public class NGE_ROOM extends GameMap {

    public NGE_ROOM(String mapName) {
        super(mapName);
    }

    @Override
    public void setNPCS() {
        MainNPC mainNpc = new MainNPC(100);
        mainNpc.setTextureAtlas(RandomGetter.getRandomTA_NPC());
        mainNpc.setDialogues(Dialogues.dialogues2);
        mainNpc.setPosition(new Vector2(7, 3));
        npcManager.addNPC(mainNpc);

/*        NPC1 npc2 = new NPC1(200);
        npc2.setTextureAtlas(ResourceManager.getRandomTA_NPC());
        npc2.setDialogues(Dialogues.dialogues2);
        npc2.setPosition(new Vector2(12, 15));
        npcManager.addNPC(npc2);*/

        GameQueue.add(() -> {
            bodies.add(mainNpc.boxBody);
//            bodies.add(npc2.boxBody);
        });
    }
}
