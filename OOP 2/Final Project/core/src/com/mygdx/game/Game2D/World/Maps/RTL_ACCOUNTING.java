package com.mygdx.game.Game2D.World.Maps;

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

public class RTL_ACCOUNTING extends GameMap {

    public RTL_ACCOUNTING(String mapName) {
        super(mapName);
    }

    @Override
    public void setNPCS() {
//        RandomXS128 randomXS128 = new RandomXS128();
//        for(int i = 0; i < 50; i++){
//            MainNPC mainNPC = new MainNPC(Math.abs(randomXS128.nextInt(100, 500)));
//            mainNPC.setTextureAtlas(RandomGetter.getRandomTA_NPC());
//            mainNPC.setDialogues(RandomGetter.getRandomDialogues());
//            mainNPC.setPosition(new Vector2(Math.abs(randomXS128.nextInt(0, 100)),
//                    Math.abs(randomXS128.nextInt(0, 65))));
//
//            npcManager.addNPC(mainNPC);
//
//            GameQueue.add(() -> bodies.add(mainNPC.boxBody));
//        }

        /*
        RandomXS128 randomXS128 = new RandomXS128();
        for(int i = 0; i < 5; i++){
            MainNPC mainNPC = new MainNPC(Math.abs(randomXS128.nextInt(100, 500)));
            mainNPC.setTextureAtlas(RandomGetter.getRandomTA_NPC());
            mainNPC.setDialogues(RandomGetter.getRandomDialogues());
            mainNPC.setPosition(new Vector2(32 + (i * 3), 12));

            npcManager.addNPC(mainNPC);

            GameQueue.add(() -> bodies.add(mainNPC.boxBody));
        }
         */
    }
}
