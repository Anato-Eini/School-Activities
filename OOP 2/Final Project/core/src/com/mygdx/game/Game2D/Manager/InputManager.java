package com.mygdx.game.Game2D.Manager;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Game2D.Entities.player.PlayerHUD;
import com.mygdx.game.Game2D.Screens.GameScreen;
import com.mygdx.game.Game2D.Screens.PauseScreen;

public class InputManager implements InputProcessor {
    public static GameScreen gameScreen;
    public static PauseScreen pauseScreen;
    public static PlayerHUD playerHUD;

    //Stages
    public static Stage gameStage;
    public static Stage pauseStage;
    public static Stage hudStage;

    public static InputMultiplexer inputMultiplexer;

    public InputManager() {
        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public InputManager(GameScreen gameScreen, PauseScreen pauseScreen, PlayerHUD playerHUD) {
        if (inputMultiplexer == null) {
            inputMultiplexer = new InputMultiplexer();
        }


        this.gameScreen = gameScreen;
        this.pauseScreen = pauseScreen;
        this.playerHUD = playerHUD;

        gameStage = gameScreen.getStage();
        pauseStage = pauseScreen.getStage();
        hudStage = playerHUD.getStage();

        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(gameStage);



        Gdx.input.setInputProcessor(inputMultiplexer);
    }


    public void renderPauseScreen() {


    }

    public void switchtoPauseScreen() {
        if (GameScreen.getGameState() == GameScreen.GameState.RUNNING) {
            pauseScreen.show();
            gameScreen.setGameState(GameScreen.GameState.PAUSED);
            setInputProcessorForPause();
        } else if (GameScreen.getGameState() == GameScreen.GameState.PAUSED) {
            gameScreen.setGameState(GameScreen.GameState.RUNNING);
            setInputProcessorForGame();
        }


    }


    private void  setInputProcessorForGame()
    {
        gameScreen.setGameState(GameScreen.GameState.RUNNING);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }
    private void setInputProcessorForPause() {
        InputMultiplexer inputMultiplexer1 = new InputMultiplexer();
        inputMultiplexer1.addProcessor(this);
        inputMultiplexer1.addProcessor(pauseStage);
        Gdx.input.setInputProcessor(inputMultiplexer1);
    }

    public void toggleInventory() {
        //TODO toggleInventory
    }

    @Override
    public boolean keyDown(int keycode) {
        if (Input.Keys.ESCAPE == keycode) {
            switchtoPauseScreen();
            return true;
        }
        if (Input.Keys.I == keycode) {
            toggleInventory();
            return true;
        }


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
