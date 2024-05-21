package com.mygdx.game.Game2D.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Game2D.Entities.PlayerMP;
import com.mygdx.game.Game2D.Screens.transition.effects.FadeOutTransitionEffect;
import com.mygdx.game.Game2D.Screens.transition.effects.TransitionEffect;
import com.mygdx.game.Game2D.World.Maps.*;

import java.util.HashMap;
import java.util.Map;

import static com.mygdx.game.Game2D.Game2D.batch;
import static com.mygdx.game.Game2D.Screens.GameScreen.*;

public class MapManager {
    public static OrthogonalTiledMapRenderer tiledMapRenderer;
    private final Map<String, GameMap> maps = new HashMap<>();
    public static Map<String, PlayerMP> otherPlayers = new HashMap<>();
    public GameMap currentMap;

    public MapManager() {
        tiledMapRenderer = new OrthogonalTiledMapRenderer(null);
        maps.put("ROOM", new Room().setMap("Game2D/Maps/HOUSE/HIS_HOUSE.tmx").setMapName("ROOM"));
        maps.put("GLE202", new GLE202().setMap("Game2D/Maps/GLE202 ROOM/GLE202.tmx").setMapName("GLE202"));
        maps.put("NGE_ROOM", new NGE_ROOM().setMap("Game2D/Maps/NGE ROOM/FINAL_NGE_ROOM.tmx").setMapName("NGE_ROOM"));
        maps.put("GLE_CR", new GLE_CR().setMap("Game2D/Maps/COMMON CR/GLE_CR.tmx").setMapName("GLE_CR"));
        maps.put("NGE_CR", new NGE_CR().setMap("Game2D/Maps/COMMON CR/NGE_CR.tmx").setMapName("NGE_CR"));
        maps.put("NGE_HALL", new NGE_HALL_FINAL().setMap("Game2D/Maps/NGE HALLWAY STUDY AREA/NGE_HALL_FINAL.tmx").setMapName("NGE_HALL"));

        maps.put("RTL_ACCOUNTING", new RTL_ACCOUNTING().setMap("Game2D/Maps/ACCOUNTING RTL/RTL_ACCOUNTING.tmx").setMapName("RTL_ACCOUNTING"));

    }

    public void dispatchMap(MapExit mapExit) {
        GameMap map = maps.get(mapExit.nextMap);
        if (map == null) return;

        Gdx.app.postRunnable(() -> {
            if (currentMap != null) {
                currentMap.disposeBodies();
            }

            player.setDirection(mapExit.playerDirection);
            player.setMap(mapExit.nextMap);
            player.setPosition(mapExit.playerPosition.x, mapExit.playerPosition.y);

            tiledMapRenderer.setMap(map.getTiledMap());
            currentMap = map;
            currentMap.setCollisions();
            currentMap.setExits();
        });
    }

    public void update() {
        ScreenUtils.clear(0, 0, 0, 1);

        if (currentMap != null) {
            currentMap.update();
        }
    }

    public GameMap getMap(String map) {
        return maps.get(map);
    }
}