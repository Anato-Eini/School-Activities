package com.mygdx.game.Game2D.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Game2D.Entities.PlayerMP;
import com.mygdx.game.Game2D.World.Maps.*;
import com.mygdx.game.Game2D.World.Maps.Minigames.MINIGAME1;

import java.util.HashMap;
import java.util.Map;

import static com.mygdx.game.Game2D.Screens.GameScreen.*;

public class MapManager {
    public static OrthogonalTiledMapRenderer tiledMapRenderer;
    private final Map<String, GameMap> maps = new HashMap<>();
    public static Map<String, PlayerMP> otherPlayers = new HashMap<>();
    public GameMap currentMap;

    public MapManager() {
        tiledMapRenderer = new OrthogonalTiledMapRenderer(null);
        maps.put("ROOM", new ROOM("ROOM").setMap("Game2D/Maps/HOUSE/HIS_HOUSE.tmx"));

//        maps.put("GLE_202", new GLE_202("GLE_202").setMap("Game2D/Maps/GLE202/GLE202.tmx"));
        maps.put("GLE_CR", new GLE_CR("GLE_CR").setMap("Game2D/Maps/COMMON_CR/GLE_CR.tmx"));
        maps.put("GLE_HALLWAY", new GLE_HALLWAY("GLE_HALLWAY").setMap("Game2D/Maps/GLE_HALLWAY/GLE_HALLWAY.tmx"));

        maps.put("NGE_ROOM", new NGE_ROOM("NGE_ROOM").setMap("Game2D/Maps/NGE_ROOM/NGE_ROOM.tmx"));
        maps.put("NGE_CR", new NGE_CR("NGE_CR").setMap("Game2D/Maps/COMMON_CR/NGE_CR.tmx"));
        maps.put("NGE_HALL", new NGE_HALL("NGE_HALL").setMap("Game2D/Maps/NGE_HALL/NGE_HALL.tmx"));

        maps.put("RTL_ACCOUNTING", new RTL_ACCOUNTING("RTL_ACCOUTING").setMap("Game2D/Maps/RTL_ACCOUNTING/RTL_ACCOUNTING.tmx"));
        maps.put("RTL_THIRD", new RTL_THIRD("RTL_THIRD").setMap("Game2D/Maps/RTL_THIRD_FLOOR/RTL_THIRD.tmx"));
        maps.put("RTL_ROOMDAA", new RTL_THIRD("RTL_ROOMDAA").setMap("Game2D/Maps/RTL_ROOM/RTL_ROOMDAA.tmx"));
        maps.put("RTL_ROOMMATH", new RTL_THIRD("RTL_ROOMMATH").setMap("Game2D/Maps/RTL_ROOM/RTL_ROOMMATH.tmx"));

        maps.put("MINIGAME_1_LVL1", new MINIGAME1("MINIGAME_1_LVL1", 1).setMap("Game2D/Maps/MINIGAME/MINIGAME_1/MINIGAME_1_LVL1.tmx"));
        maps.put("MINIGAME_1_LVL2", new MINIGAME1("MINIGAME_1_LVL2", 2).setMap("Game2D/Maps/MINIGAME/MINIGAME_1/MINIGAME_1_LVL2.tmx"));
        maps.put("MINIGAME_1_LVL3", new MINIGAME1("MINIGAME_1_LVL3", 3).setMap("Game2D/Maps/MINIGAME/MINIGAME_1/MINIGAME_1_LVL3.tmx"));
    }

    public void dispatchMap(MapExit mapExit) {
        GameMap map = maps.get(mapExit.nextMap);

        if(map != null){
            Gdx.app.postRunnable(() -> {
                if (currentMap != null) {
                    currentMap.disposeBodies();
                }

                player.setDirection(mapExit.playerDirection);
                player.setMap(mapExit.nextMap);
                player.setPosition(new Vector2(mapExit.playerPosition.x, mapExit.playerPosition.y));

                tiledMapRenderer.setMap(map.getTiledMap());
                currentMap = map;
                currentMap.setCollisions();
                currentMap.setExits();
                currentMap.setNPCS();
            });
        }
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