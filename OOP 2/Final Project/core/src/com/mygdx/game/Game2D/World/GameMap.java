package com.mygdx.game.Game2D.World;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Game2D.Entities.Entity;
import com.mygdx.game.Game2D.Entities.NPC.NPC;
import com.mygdx.game.Game2D.Game2D;
import com.mygdx.game.Game2D.Manager.AudioManager;
import com.mygdx.game.Game2D.Manager.NPCManager;
import com.mygdx.game.Game2D.Manager.ResourceManager;
import com.mygdx.game.Game2D.Screens.GameScreen;
import com.mygdx.game.Game2D.Utils.GameQueue;
import com.mygdx.game.Game2D.World.Maps.Minigames.MINIGAME2.TypeRacer;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Random;

import static com.mygdx.game.Game2D.Screens.GameScreen.*;
import static com.mygdx.game.Game2D.World.MapManager.tiledMapRenderer;

public abstract class GameMap {
    public TiledMap tiledMap;
    public String mapName;
    MapLayer collisionLayer;
    MapObjects collisionMapObjects;
    MapObjects mapExitObjects;
    public MapLayer exitLayer;
    public MapLayer interactableLayer;
    public MapObjects interactableObjects;
    public Array<Body> bodies = new Array<>();
    protected int layers;
    TiledMapTileLayer FOREGROUND_LAYER, FOREGROUND_LAYER1;
    public NPCManager npcManager;
    Music mapMusic;

    TmxMapLoader tmxMapLoader;
    public ArrayList<NPC> npcs = new ArrayList<>();
    public GameMap(String mapName) {
        this.mapName = mapName;
        npcManager = new NPCManager(this);
        tmxMapLoader = new TmxMapLoader();
    }

    public GameMap setMap(String path) {
        this.tiledMap = ResourceManager.getInstance().assetManager.get(path);
        layers = tiledMap.getLayers().getCount();
        FOREGROUND_LAYER = (TiledMapTileLayer) tiledMap.getLayers().get("FOREGROUND_LAYER");
        FOREGROUND_LAYER1 = (TiledMapTileLayer) tiledMap.getLayers().get("FOREGROUND_LAYER1");
        return this;
    }

    public GameMap setMapName(String mapName) {
        this.mapName = mapName;
        return this;
    }

    public GameMap setMapMusic(String music) {
        mapMusic = AudioManager.getInstance().getMusic(music);
        return this;
    }

    public void playMusic() {
        if (mapMusic != null) {
            AudioManager.getInstance().playMusic(mapMusic);
        } else{
            AudioManager audioManager = AudioManager.getInstance();
            ArrayList <Music> fillers =  audioManager.fillers;

            if(audioManager.currentMusic == null){
                Random random = new Random();
                int i = random.nextInt(fillers.size());
                audioManager.playMusic(fillers.get(i));
            }
        }
    }

    public void stopMusic() {
        if (mapMusic != null) {
            AudioManager.getInstance().stopMusic();
        }
    }

    public void initializeMap()
    {
        setCollisions();
        setExits();
        setInteractable();
        setNPCS();
        playMusic();
    }


    public void setInteractable() {
        interactableLayer =  this.tiledMap.getLayers().get("INTERACTABLE_LAYER");

        if(interactableLayer != null){
            interactableObjects = interactableLayer.getObjects();
            for( MapObject object : interactableObjects){
                if(object instanceof RectangleMapObject){
                    MapProperties mapProperties = object.getProperties();
                    String type = (String) mapProperties.get("type");

                    Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                    BodyDef collisionBodyDef = new BodyDef();
                    collisionBodyDef.type = BodyDef.BodyType.StaticBody;

                    collisionBodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2), (rectangle.getY() + rectangle.getHeight() / 2));

                    Body collisionBody = world.createBody(collisionBodyDef);

                    FixtureDef exitFixtureDef = new FixtureDef();
                    PolygonShape shape = new PolygonShape();

                    shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
                    exitFixtureDef.shape = shape;
                    exitFixtureDef.filter.categoryBits = CollisionType.EXIT.getValue();
                    exitFixtureDef.filter.maskBits = CollisionType.PLAYER.getValue();


                    if(type.equals("minigame")){
                        String minigame = (String) mapProperties.get("minigame");
                        switch (minigame){
                            case "2":
                                TypeRacer typeRacer = new TypeRacer(game);
                                collisionBody.createFixture(exitFixtureDef).setUserData(typeRacer);
                                break;
                        }
                    }
                }
            }
        }

    }

    public abstract void setNPCS();

    public void setExits() {
        exitLayer = this.tiledMap.getLayers().get("EXIT_LAYER");

        if (exitLayer != null) {
            mapExitObjects = exitLayer.getObjects();
            for (MapObject object : mapExitObjects) {
                if (object instanceof RectangleMapObject) {
                    Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                    BodyDef collisionBodyDef = new BodyDef();
                    collisionBodyDef.type = BodyDef.BodyType.StaticBody;

                    collisionBodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2), (rectangle.getY() + rectangle.getHeight() / 2));

                    Body collisionBody = world.createBody(collisionBodyDef);

                    FixtureDef exitFixtureDef = new FixtureDef();
                    PolygonShape shape = new PolygonShape();

                    shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
                    exitFixtureDef.shape = shape;
                    exitFixtureDef.filter.categoryBits = CollisionType.EXIT.getValue();
                    exitFixtureDef.filter.maskBits = CollisionType.PLAYER.getValue();


                    MapProperties properties = object.getProperties();
                    String type = (String) properties.get("type");
                    switch (type) {
                        case "exit" -> {
                            String nextMap = (String) properties.get("nextMap");
                            Entity.Direction direction = Entity.Direction.fromString((String) properties.get("playerDirection"));

                            float playerX = (float) properties.get("playerX");
                            float playerY = (float) properties.get("playerY");
                            if (playerX == -1) playerX = player.getLastMapPosition().x;
                            if (playerY == -1) playerY = player.getLastMapPosition().y;

                            MapExit exit = new MapExit(nextMap, new Vector2(playerX, playerY), direction);
                            collisionBody.createFixture(exitFixtureDef).setUserData(exit);
                        }
                        case "lastMap" -> {
                            String nextMap = (String) properties.get("nextMap");
                            Entity.Direction direction = Entity.Direction.fromString((String) properties.get("playerDirection"));

                            if (player.getLastMapPosition() != null) {
                                float playerX = player.getLastMapPosition().x;
                                float playerY = player.getLastMapPosition().y;

                                MapExit exit = new MapExit(nextMap, new Vector2(playerX, playerY), direction);
                                collisionBody.createFixture(exitFixtureDef).setUserData(exit);
                            }
                        }
                    }

                    bodies.add(collisionBody);
                    shape.dispose();
                }
            }
        }
    }

    public void setCollisions() {
        collisionLayer = this.tiledMap.getLayers().get("COLLISION_LAYER");

        if (collisionLayer != null) {
            collisionMapObjects = collisionLayer.getObjects();
            for (MapObject object : collisionMapObjects) {
                if (object instanceof PolygonMapObject) {
                    Polygon polygon = ((PolygonMapObject) object).getPolygon();

                    BodyDef collisionBodyDef = new BodyDef();
                    collisionBodyDef.type = BodyDef.BodyType.StaticBody;

                    collisionBodyDef.position.set(polygon.getX(), polygon.getY());

                    Body collisionBody = world.createBody(collisionBodyDef);

                    FixtureDef collisionFixtureDef = new FixtureDef();
                    PolygonShape shape = new PolygonShape();
                    shape.set(polygon.getVertices());
                    collisionFixtureDef.shape = shape;
                    collisionFixtureDef.filter.categoryBits = CollisionType.WALL.getValue();

                    collisionBody.createFixture(collisionFixtureDef).setUserData("COLLISION");
                    bodies.add(collisionBody);
                }

                if (object instanceof RectangleMapObject) {
                    Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                    BodyDef collisionBodyDef = new BodyDef();
                    collisionBodyDef.type = BodyDef.BodyType.StaticBody;

                    collisionBodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2), (rectangle.getY() + rectangle.getHeight() / 2));

                    Body collisionBody = world.createBody(collisionBodyDef);

                    FixtureDef exitFixtureDef = new FixtureDef();
                    PolygonShape shape = new PolygonShape();

                    shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
                    exitFixtureDef.shape = shape;
                    exitFixtureDef.filter.categoryBits = CollisionType.WALL.getValue();

                    collisionBody.createFixture(exitFixtureDef).setUserData("COLLISION");
                    bodies.add(collisionBody);
                    shape.dispose();
                }
            }
        }

    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public void disposeBodies() {
        npcManager.clear();

        for (Body body : bodies)
            if (body != player.boxBody)
                GameQueue.add(() -> world.destroyBody(body));

        this.bodies.clear();
    }

    public void update() {
        ScreenUtils.clear(0, 0, 0, 1);

        tiledMapRenderer.setView(camera);
        for (int i = 0; i < layers; i++) {
            if (tiledMap.getLayers().get(i) == FOREGROUND_LAYER || tiledMap.getLayers().get(i) == FOREGROUND_LAYER1) {
                break;
            }
            tiledMapRenderer.render(new int[]{i});
        }

        synchronized (this) {
            notifyAll();
        }

        for (NPC n : npcManager.getNPCs()) {
            n.render();
        }

        for (NPC n : npcs) {
            n.render();
        }

        player.render();

        for (int i = 0; i < layers; i++) {
            if (tiledMap.getLayers().get(i) == FOREGROUND_LAYER || tiledMap.getLayers().get(i) == FOREGROUND_LAYER1) {
                tiledMapRenderer.render(new int[]{i});
            }
        }

        if (this instanceof Minigame) {
            ((Minigame) this).minigame();
        }
    }
}