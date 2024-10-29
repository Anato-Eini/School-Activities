package com.mygdx.game.Game2D.World.Maps;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game2D.Entities.NPC.MainNPC;
import com.mygdx.game.Game2D.Manager.ResourceManager;
import com.mygdx.game.Game2D.Utils.GameQueue;
import com.mygdx.game.Game2D.Utils.RandomGetter;
import com.mygdx.game.Game2D.World.GameMap;

import static com.mygdx.game.Game2D.Screens.GameScreen.camera;
import static com.mygdx.game.Game2D.Screens.GameScreen.player;
import static com.mygdx.game.Game2D.World.MapManager.tiledMapRenderer;

public class RTL_THIRD extends GameMap {
    public RTL_THIRD(String mapName) {
        super(mapName);
    }

    @Override
    public void setNPCS() {
/*        RandomXS128 randomXS128 = new RandomXS128();
        MainNPC[] npcs = new MainNPC[4];

        for(int i = 0; i < npcs.length; i++){
            npcs[i] = new MainNPC(randomXS128.nextInt(50, 300));
            npcs[i].setTextureAtlas(RandomGetter.getRandomTA_NPC());
            npcs[i].setDialogues(RandomGetter.getRandomDialogues());

            MainNPC finalNpc = npcs[i];
            npcManager.addNPC(finalNpc);
            GameQueue.add(() -> bodies.add(finalNpc.boxBody));
        }

        npcs[0].setPosition(new Vector2(87, 18));
        npcs[1].setPosition(new Vector2(31, 18));
        npcs[2].setPosition(new Vector2(87, 50));
        npcs[3].setPosition(new Vector2(87, 32));*/

    }
}
