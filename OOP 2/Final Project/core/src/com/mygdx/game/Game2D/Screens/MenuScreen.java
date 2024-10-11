package com.mygdx.game.Game2D.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Game2D.Game2D;
import com.mygdx.game.Game2D.Manager.AudioManager;
import com.mygdx.game.Game2D.Screens.transition.effects.FadeOutTransitionEffect;
import com.mygdx.game.Game2D.Screens.transition.effects.TransitionEffect;

import java.util.ArrayList;

import static com.mygdx.game.Game2D.Game2D.batch;


public class MenuScreen extends BaseScreen {

    private Table menuTable;
    private Stage menuStage = new Stage();
    private Animation<TextureRegion> flowAnimation;
    private float stateTime;

    public MenuScreen(Game2D game) {
        super(game);

        menuTable = createTable();
        menuTable.setFillParent(false);
        menuTable.bottom().padBottom(20);

        handlePlayButton();
        handleLoadButton();
        handleExitButton();

        AudioManager.getInstance().playMusic("Celeste Original Soundtrack - 02 - First Steps");
    }

    private void handlePlayButton() {
        Actor newButton =  createImageButton("new_button",  menuTable);
        newButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y) {
                ArrayList<TransitionEffect> effects = new ArrayList<>();
                effects.add(new FadeOutTransitionEffect(0.5F));

                setScreenWithTransition(
                        (BaseScreen)game.getScreen(),
                        new MenuNewGameScreen(game, (BaseScreen)game.getScreen(), Game2D.resourceManager),
                        effects
                );
            }
        });
    }

    private void handleExitButton() {
        Actor exitButton =  createImageButton("exit_button",  menuTable);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y) {
                Gdx.app.exit();
            }
        });
    }


    private void handleOptionButton() {
//        createButton("Options", 0, menuTable.getHeight()/10, menuTable);

//        Actor optionButton = menuTable.getCells().get(2).getActor();
//        optionButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent even, float x, float y) {
//                setScreenWithTransition(
//                        (BaseScreen) game.getScreen(),
//                        new OptionScreen(game, (BaseScreen) game.getScreen(), resourceManager),
//                        new ArrayList<>()
//                );
//            }
//        });
    }


    private void handleLoadButton() {
        Actor loadButton = createImageButton("load_button",   menuTable);
        loadButton.setSize(64, 48);
        loadButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y) {
                setScreenWithTransition((BaseScreen) game.getScreen(), new MenuLoadGameScreen(game, (BaseScreen) game.getScreen(), Game2D.resourceManager), new ArrayList<>());
            }
        });
    }

    @Override
    public void show() {
        menuStage.addActor(menuTable);
        Gdx.input.setInputProcessor(menuStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        stateTime += Gdx.graphics.getDeltaTime();

        batch.begin();
        //TODO ADD BACKGROUND HERE
        batch.end();

        menuStage.act(delta);
        menuStage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        menuTable.remove();
    }
}