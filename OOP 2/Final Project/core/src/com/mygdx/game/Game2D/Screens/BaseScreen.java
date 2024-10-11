package com.mygdx.game.Game2D.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Game2D.Game2D;
import com.mygdx.game.Game2D.Manager.ResourceManager;
import com.mygdx.game.Game2D.Manager.AudioManager;
import com.mygdx.game.Game2D.Screens.transition.effects.TransitionEffect;
import com.mygdx.game.Game2D.status.CurrentMusicDisplay;

import java.util.ArrayList;
import java.util.List;


public class BaseScreen implements Screen{
    protected final Game2D game;
    protected OrthographicCamera gameCam;
    protected OrthographicCamera battleCam;
    protected Viewport viewport;
    protected Stage stage;
    CurrentMusicDisplay currentMusicDisplay;

    public BaseScreen(Game2D game) {
        this.game = game;
        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())); // Initialize the stage
        if(ResourceManager.getInstance().isFinishedLoading){
            currentMusicDisplay = AudioManager.getInstance().getCurrentMusicDisplay();
        }
    }

    public void setScreenWithTransition(BaseScreen current, BaseScreen next, List<TransitionEffect> transitionEffect) {
        ArrayList<TransitionEffect> effects = new ArrayList<>(transitionEffect);

        Screen transitionScreen = new TransitionScreen(game, current, next, effects);
        game.setScreen(transitionScreen);
    }

    public TextButton createButton(String buttonName) {
        BitmapFont pixel10 = ResourceManager.pixel10;

        TextureRegionDrawable imageUp = new TextureRegionDrawable(ResourceManager.getInstance().atlas.findRegion("button"));
        TextureRegionDrawable imageDown = new TextureRegionDrawable(ResourceManager.getInstance().atlas.findRegion("button"));
        imageDown.setMinSize(100, 50);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle(imageUp, imageDown, null, pixel10);
        TextButton button = new TextButton(buttonName, buttonStyle);

        button.setTransform(true);
        button.setOrigin(Align.center);

        button.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!AudioManager.buttonSound) {
                    button.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
                    AudioManager.getInstance().playSound("hover");
                }
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                button.addAction(Actions.scaleTo(1f, 1f, 0.1f)); // Smooth scaling animation
                AudioManager.buttonSound = false;
            }
        });

        button.getLabel().setColor(new Color(79 / 255f, 79 / 255f, 117 / 255f, 1));

        return button;
    }


    public void createImageButton(ImageButton button, Table table) {
        button.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!AudioManager.buttonSound) {
                    AudioManager.getInstance().playSound("hover");
                }
                button.setScale(button.getScaleX() + .1F, button.getScaleY() + .1F);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                AudioManager.buttonSound = false;
                button.setScale(button.getScaleX() - .1F, button.getScaleY() - .1F);
            }
        });

        table.add(button).pad(10);
    }


    public ImageButton createImageButton(String buttonName, Table table) {
        TextureRegion buttonUp = ResourceManager.getInstance().atlas.findRegion(buttonName);

        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.imageUp = new TextureRegionDrawable(buttonUp);

        ImageButton button = new ImageButton(buttonStyle);
        button.setTransform(true);

        button.setOrigin(button.getWidth() / 2, button.getHeight() / 2);

        button.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // Hover sound
                if (!AudioManager.buttonSound) {
                    AudioManager.getInstance().playSound("hover");
                }
                button.setScale(1.1F);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                AudioManager.buttonSound = false;
                button.setScale(1.0F);
            }
        });

        table.add(button).pad(10);

        return button;
    }

    public Table createTable() {
        Table table = new Table();
        table.setBounds(0,0, (float) Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight());
//        table.setDebug(true);
        return table;
    }

    public Table createTable(float width, float height)
    {
        Table table = new Table();
        table.setBounds(0,0, width, height);
        return table;
    }

    @Override
    public void show() {
        // Nothing
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        stage.act(delta);
        stage.draw();
        if(currentMusicDisplay != null){
            currentMusicDisplay.render(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        // Nothing
    }

    @Override
    public void pause() {
        // Nothing
    }

    @Override
    public void resume() {
        // Nothing
    }

    @Override
    public void hide() {
        // Nothing
    }

    @Override
    public void dispose() {
//        stage.dispose();
    }
    public Stage getStage() {
        return stage;
    }
}
