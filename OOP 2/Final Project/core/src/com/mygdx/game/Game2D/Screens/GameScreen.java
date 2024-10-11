package com.mygdx.game.Game2D.Screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Game2D.Entities.player.Player;
import com.mygdx.game.Game2D.Entities.player.PlayerHUD;
import com.mygdx.game.Game2D.Game2D;
import com.mygdx.game.Game2D.Listeners.GameCollisionListener;
import com.mygdx.game.Game2D.Manager.AudioManager;
import com.mygdx.game.Game2D.Manager.InputManager;
import com.mygdx.game.Game2D.Utils.GameQueue;
import com.mygdx.game.Game2D.World.MapExit;
import com.mygdx.game.Game2D.World.MapManager;
import com.mygdx.game.Game2D.status.CurrentMusicDisplay;
import com.mygdx.game.ScreenConfig;

import static com.mygdx.game.Game2D.Game2D.*;

public class GameScreen extends BaseScreen implements ApplicationListener {
    public static Game2D game;
    public static Player player;
    public static OrthographicCamera camera;
    public static World world;
    private final Box2DDebugRenderer debugRenderer;
    public static GameState gameState;
    public static PauseScreen pauseScreen;
    public static InputMultiplexer inputMultiplexer;
    CurrentMusicDisplay currentMusicDisplay;

    public GameScreen(Game2D game) {
        super(game);

        GameScreen.game = game;
        inputMultiplexer = new InputMultiplexer();
        world = new World(new Vector2(0, 0), true);

        world.setContactListener(new GameCollisionListener(game, this));

        camera = new OrthographicCamera();
        viewport = new FitViewport(ScreenConfig.screenWidth, ScreenConfig.screenHeight, camera);
        setupViewport(ScreenConfig.screenWidth, ScreenConfig.screenHeight);

        player = profileManager.getCurrentPlayer();

        mapManager.dispatchMap(new MapExit(player.map, player.position, player.direction));

        OrthographicCamera hudCamera = new OrthographicCamera();
        hudCamera.setToOrtho(false, ScreenConfig.screenWidth, ScreenConfig.screenHeight);
        currentMusicDisplay = AudioManager.getInstance().getCurrentMusicDisplay();

        debugRenderer = new Box2DDebugRenderer();

        //Initialize GameState
        gameState = GameState.RUNNING;

        //PauseScreen
        pauseScreen = new PauseScreen(game);

        //Handle multiple input
        //TODO ADD PLAYERHUD input
        player.playerHUD = new PlayerHUD(player);
        inputManager = new InputManager(this, pauseScreen, player.playerHUD);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (gameState == GameState.PAUSED) {
            pauseScreen.getStage().act(delta);
            pauseScreen.getStage().draw();
            player.playerHUD.render(delta);
            currentMusicDisplay.render(delta);
            return;
        }

        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.zoom = 0.5F;

        //PHYSICS
        world.step(1/120f, 6, 2);

        GameQueue.run();

        batch.setProjectionMatrix(camera.combined);
        mapManager.update();
        player.update();
        player.playerHUD.render(delta);

        camera.position.set((player.getPosition().x * ScreenConfig.originalTileSize) +  (player.getWidth() / 2),
                (player.getPosition().y * ScreenConfig.originalTileSize)  + (player.getHeight() / 2), 0);
        camera.update();

        currentMusicDisplay.render(delta);
//        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        viewport.setWorldHeight(Gdx.graphics.getHeight() * 1.3F);
        viewport.setWorldWidth(Gdx.graphics.getWidth() * 1.3F);
    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    private static void setupViewport(int width, int height) {

        VIEWPORT.viewportWidth = VIEWPORT.virtualWidth = width;
        VIEWPORT.viewportHeight = VIEWPORT.virtualHeight = height;

        VIEWPORT.physicalWidth = Gdx.graphics.getWidth();
        VIEWPORT.physicalHeight = Gdx.graphics.getHeight();

        VIEWPORT.aspectRatio = (VIEWPORT.virtualWidth / VIEWPORT.virtualHeight);

        if (VIEWPORT.physicalWidth / VIEWPORT.physicalHeight >= VIEWPORT.aspectRatio) {
            VIEWPORT.viewportWidth = VIEWPORT.viewportHeight * (VIEWPORT.physicalWidth/VIEWPORT.physicalHeight);
            VIEWPORT.viewportHeight = VIEWPORT.virtualHeight;
        } else {
            VIEWPORT.viewportWidth = VIEWPORT.virtualWidth;
            VIEWPORT.viewportHeight = VIEWPORT.viewportWidth * (VIEWPORT.physicalHeight/VIEWPORT.physicalWidth);
        }
    }

    public static class VIEWPORT {
        private static float viewportWidth;
        private static float viewportHeight;
        private static float virtualWidth;
        private static float virtualHeight;
        private static float physicalWidth;
        private static float physicalHeight;
        private static float aspectRatio;
    }


    public static GameState getGameState() {
        return gameState;
    }

    public GameState setGameState(GameState gameState) {
        this.gameState = gameState;

        return gameState;
    }
    public enum GameState {
        SAVING,
        LOADING,
        RUNNING,
        PAUSED,
    }
}