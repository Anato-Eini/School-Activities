package com.mygdx.game.Game2D.World.Maps;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game2D.Entities.NPC.MainNPC;
import com.mygdx.game.Game2D.Manager.ResourceManager;
import com.mygdx.game.Game2D.Utils.GameQueue;
import com.mygdx.game.Game2D.Utils.RandomGetter;
import com.mygdx.game.Game2D.World.GameMap;

public class NGE_HALL extends GameMap {
    public NGE_HALL(String mapName) {
        super(mapName);
    }

    @Override
    public void setNPCS() {
        RandomXS128 randomXS128 = new RandomXS128();
        for(int i = 0; i < 10; i++){
            MainNPC npc = new MainNPC(Math.abs(randomXS128.nextInt(50, 300)));
            npc.setTextureAtlas(RandomGetter.getRandomTA_NPC());
            npc.setDialogues(RandomGetter.getRandomDialogues());
            npc.setPosition(new Vector2((i % 2 == 0 ? 33 : 35), 32 - i));
            npcManager.addNPC(npc);

            GameQueue.add(() -> bodies.add(npc.boxBody));
        }

        for(int i = 0 ; i < 20; i++){
            MainNPC npc = new MainNPC(Math.abs(randomXS128.nextInt(50, 300)));
            npc.setTextureAtlas(RandomGetter.getRandomTA_NPC());
            npc.setDialogues(RandomGetter.getRandomDialogues());
            npc.setPosition(new Vector2(40 + (i * 2), 27));
            npcManager.addNPC(npc);

            GameQueue.add(() -> bodies.add(npc.boxBody));

            MainNPC mainNPC = new MainNPC(Math.abs(randomXS128.nextInt(100, 500)));
            mainNPC.setTextureAtlas(RandomGetter.getRandomTA_NPC());
            mainNPC.setDialogues(RandomGetter.getRandomDialogues());
            mainNPC.setPosition(new Vector2(Math.abs(randomXS128.nextInt(0, 100)),
                    Math.abs(randomXS128.nextInt(0, 65))));
        }
    }
}