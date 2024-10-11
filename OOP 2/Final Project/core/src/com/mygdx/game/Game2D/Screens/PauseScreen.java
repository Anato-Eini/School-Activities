package com.mygdx.game.Game2D.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Game2D.Game2D;
import com.mygdx.game.Game2D.Manager.AudioManager;
import com.mygdx.game.Game2D.Manager.ResourceManager;
import com.mygdx.game.Game2D.Screens.transition.effects.FadeOutTransitionEffect;
import com.mygdx.game.Game2D.Screens.transition.effects.TransitionEffect;

import java.util.ArrayList;

import static com.mygdx.game.Game2D.Game2D.profileManager;
import static com.mygdx.game.Game2D.Manager.ResourceManager.pixel10;
import static com.mygdx.game.Game2D.Screens.GameScreen.player;

public class PauseScreen extends BaseScreen {
    private Stage stage;
    private Dialog settingsDialog = new Dialog("", ResourceManager.skin);

    public PauseScreen (Game2D game) {
        super(game);
        this.stage = new Stage(new ScreenViewport());

        handleSettings();

        Button resumeButton = createButton("Resume");
        Button settingsButton = createButton("Settings");
        Button saveButton = createButton("Save");
        Button exitButton = createButton("Exit");

        Table table = new Table();
        table.setFillParent(true);
        Table pause = new Table();

        TextureRegionDrawable background = new TextureRegionDrawable(ResourceManager.getInstance().atlas.findRegion("pause_bg"));
        pause.setBackground(background);
        pause.center();
        pause.pad(20f, 20f, 20f, 20f);

        Label pauseLabel = new Label("Game Paused", ResourceManager.skin);
        pauseLabel.setColor(Color.BLACK);
        pauseLabel.setFontScale(2F);

        pause.add(pauseLabel).pad(20);
        pause.row();
        pause.add(resumeButton).padBottom(20);
        pause.row();
        pause.add(settingsButton).padBottom(20);
        pause.row();
        pause.add(saveButton).padBottom(20);
        pause.row();
        pause.add(exitButton).padBottom(20);
        table.add(pause);

        stage.addActor(table);
        settingsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                settingsDialog.show(stage);
            }
        });

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide();
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ArrayList<TransitionEffect> effects = new ArrayList<>();
                effects.add(new FadeOutTransitionEffect(0.5F));

                AudioManager.getInstance().playMusic("Celeste Original Soundtrack - 02 - First Steps");
                setScreenWithTransition(
                        (BaseScreen)game.getScreen(),
                        new MenuScreen(game),
                        effects
                );

            }
        });
        saveButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.postRunnable(() -> {
                    profileManager.saveProfile(player);
                });
            }
        });

    }

    private void handleSettings(){
        settingsDialog.pad(10f, 10f, 10f, 10f);
        Label settingsLabel = new Label("Settings", ResourceManager.skin);
        settingsLabel.setColor(Color.BLACK);
        settingsLabel.setFontScale(2F);
        settingsDialog.getContentTable().add(settingsLabel).pad(20);
        settingsDialog.getContentTable().row();

        Table musicControl = new Table();

        Label musicLabel = new Label("Music", ResourceManager.skin);
        musicLabel.setColor(Color.BLACK);
        Slider musicSlider = new Slider(0F, 100F, 1F, false, ResourceManager.skin);
        musicSlider.setValue(AudioManager.getInstance().musicVolume);
        musicControl.add(musicLabel).pad(5f);
        musicControl.add(musicSlider).pad(5f);
        musicControl.padLeft(10f);
        musicControl.padRight(10f);
        settingsDialog.getContentTable().add(musicControl).row();
        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float volume = musicSlider.getValue() / 100.0f;
                AudioManager.getInstance().musicVolume = volume;
                if (AudioManager.getInstance().currentMusic != null) {
                    AudioManager.getInstance().currentMusic.setVolume(volume);
                }
            }
        });

        Table soundControl = new Table();
        Label soundLabel = new Label("Sound", ResourceManager.skin);
        soundLabel.setColor(Color.BLACK);
        Slider soundSlider = new Slider(0F, 100F, 1F, false, ResourceManager.skin);
        soundSlider.setValue(AudioManager.getInstance().soundVolume);
        soundControl.add(soundLabel).pad(5f);
        soundControl.add(soundSlider).pad(5f);
        soundControl.padLeft(10f);
        soundControl.padRight(10f);
        settingsDialog.getContentTable().add(soundControl).row();
        soundSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AudioManager.getInstance().soundVolume = soundSlider.getValue() / 100.0f;
            }
        });

        Actor backButton = createButton("Back");
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsDialog.hide();
            }
        });

        settingsDialog.getContentTable().add(backButton).center();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        stage.act();
        stage.draw();
    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {
    }

    public void hide() {
        Game2D.inputManager.switchtoPauseScreen();
    }

    public Stage getStage() {
        return stage;
    }
}
