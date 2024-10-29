package com.mygdx.game.Game2D.Manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Game2D.World.Maps.*;
import com.mygdx.game.Game2D.World.Maps.Minigames.MINIGAME1;

public class ResourceManager {
    public static ResourceManager instance = null;
    public boolean isFinishedLoading;
    private static InternalFileHandleResolver filePathResolver = new InternalFileHandleResolver();

    public Pixmap cursor;
    public static BitmapFont pixel10;
    public static Skin skin;
    public AssetManager assetManager;
    public Animation<TextureRegion> upAnimation;
    public Animation<TextureRegion> downAnimation;
    public Animation<TextureRegion> leftAnimation;
    public Animation<TextureRegion> rightAnimation;
    public Animation<TextureRegion> idleUpAnimation;
    public Animation<TextureRegion> idleDownAnimation;
    public Animation<TextureRegion> idleLeftAnimation;
    public Animation<TextureRegion> idleRightAnimation;
    public TextureAtlas atlas;
    public TextureAtlas playerAtlas;
    TmxMapLoader tmxMapLoader = new TmxMapLoader();
    private ResourceManager() {
        assetManager = new AssetManager();
        cursor = new Pixmap(Gdx.files.internal("tool/cursor.png"));
    }

    public void loadAssets(){
        System.out.println("LOADING STARTED");
        assetManager.load("atlas/UI/UI.atlas", TextureAtlas.class);
        assetManager.load("atlas/leo.atlas", TextureAtlas.class);
        assetManager.load("tool/cursor.png", Pixmap.class);
        assetManager.load("fonts/pixel.fnt", BitmapFont.class);
        assetManager.load("skin/skin.json", Skin.class);

        assetManager.setLoader(TiledMap.class, tmxMapLoader);
        assetManager.load("Game2D/Maps/HOUSE/HIS_HOUSE.tmx", TiledMap.class);
        assetManager.load("Game2D/Maps/GLE202/GLE202.tmx", TiledMap.class);
        assetManager.load("Game2D/Maps/COMMON_CR/GLE_CR.tmx", TiledMap.class);
        assetManager.load("Game2D/Maps/GLE_HALLWAY/GLE_HALLWAY.tmx", TiledMap.class);
        assetManager.load("Game2D/Maps/NGE_ROOM/NGE_ROOM.tmx", TiledMap.class);
        assetManager.load("Game2D/Maps/COMMON_CR/NGE_CR.tmx", TiledMap.class);
        assetManager.load("Game2D/Maps/NGE_HALL/NGE_HALL.tmx", TiledMap.class);
        assetManager.load("Game2D/Maps/RTL_ACCOUNTING/RTL_ACCOUNTING.tmx", TiledMap.class);
        assetManager.load("Game2D/Maps/RTL_THIRD_FLOOR/RTL_THIRD.tmx", TiledMap.class);
        assetManager.load("Game2D/Maps/RTL_ROOM/RTL_ROOMDAA.tmx", TiledMap.class);
        assetManager.load("Game2D/Maps/RTL_ROOM/RTL_ROOMMATH.tmx", TiledMap.class);
        assetManager.load("Game2D/EXTERIOR/NGE_F/NGE_F.TMX", TiledMap.class);
        assetManager.load("Game2D/EXTERIOR/RTL/RTL.TMX", TiledMap.class);
        assetManager.load("Game2D/EXTERIOR/ALLIEDF/ALLIEDF.TMX", TiledMap.class);
        assetManager.load("Game2D/EXTERIOR/GYMF/GYMF.TMX", TiledMap.class);
        assetManager.load("Game2D/EXTERIOR/ELEMF/ELEMF.TMX", TiledMap.class);
        assetManager.load("Game2D/EXTERIOR/ACADF/ACADF.TMX", TiledMap.class);
        assetManager.load("Game2D/EXTERIOR/GLE_F/GLE_F.TMX", TiledMap.class);
        assetManager.load("Game2D/EXTERIOR/LIB/LIB.TMX", TiledMap.class);
        assetManager.load("Game2D/EXTERIOR/CLINICLINE/CLINICLINE.TMX", TiledMap.class);
        assetManager.load("Game2D/EXTERIOR/SHSF/SHSF.TMX", TiledMap.class);
        assetManager.load("Game2D/Maps/MINIGAME/MINIGAME_1/MINIGAME_1_LVL1.tmx", TiledMap.class);
        assetManager.load("Game2D/Maps/MINIGAME/MINIGAME_1/MINIGAME_1_LVL2.tmx", TiledMap.class);
        assetManager.load("Game2D/Maps/MINIGAME/MINIGAME_1/MINIGAME_1_LVL3.tmx", TiledMap.class);
    }

    public void initializeResource(){
        atlas = assetManager.get("atlas/UI/UI.atlas", TextureAtlas.class);
        playerAtlas = assetManager.get("atlas/leo.atlas", TextureAtlas.class);

        upAnimation = new Animation<>(0.10f, playerAtlas.findRegions("move_up"));
        downAnimation = new Animation<>(0.10f, playerAtlas.findRegions("move_down"));
        leftAnimation = new Animation<>(0.10f, playerAtlas.findRegions("move_left"));
        rightAnimation = new Animation<>(0.10f, playerAtlas.findRegions("move_right"));

        idleUpAnimation = new Animation<>(0.10f, playerAtlas.findRegions("idle_up"));
        idleDownAnimation = new Animation<>(0.10f, playerAtlas.findRegions("idle_down"));
        idleLeftAnimation = new Animation<>(0.10f, playerAtlas.findRegions("idle_left"));
        idleRightAnimation = new Animation<>(0.10f, playerAtlas.findRegions("idle_right"));

        upAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        downAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        leftAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        rightAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        idleUpAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        idleDownAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        idleLeftAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        idleRightAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        pixel10 = assetManager.get("fonts/pixel.fnt", BitmapFont.class);
        skin = assetManager.get("skin/skin.json", Skin.class);
    }

    public ImageButton createImageButton(String buttonName, float scale){
        TextureRegion buttonTextureRegion = ResourceManager.getInstance().atlas.findRegion(buttonName);
        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.imageUp = new TextureRegionDrawable(buttonTextureRegion);

        ImageButton button = new ImageButton(buttonStyle);
        button.setTransform(true);
        button.setScale(scale);

        button.setOrigin(button.getWidth() / 2, button.getHeight() / 2);
        return button;
    }

    public static ResourceManager getInstance(){
        if(instance == null){
            instance = new ResourceManager();
        }
        return instance;
    }

    public void dispose() {
        assetManager.dispose();

        atlas.dispose();
        pixel10.dispose();
    }

    public float getProgress() {
        return assetManager.getProgress();
    }
}
