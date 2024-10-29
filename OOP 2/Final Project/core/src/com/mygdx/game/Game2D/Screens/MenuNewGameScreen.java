package com.mygdx.game.Game2D.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Game2D.Entities.Entity;
import com.mygdx.game.Game2D.Entities.player.Player;
import com.mygdx.game.Game2D.Game2D;
import com.mygdx.game.Game2D.Manager.ProfileManager;
import com.mygdx.game.Game2D.Manager.ResourceManager;
import com.mygdx.game.Game2D.Screens.transition.effects.FadeOutTransitionEffect;
import com.mygdx.game.Game2D.Screens.transition.effects.TransitionEffect;
import com.mygdx.game.Game2D.World.World;

import java.util.ArrayList;

import static com.mygdx.game.Game2D.Game2D.profileManager;

public class MenuNewGameScreen extends BaseScreen {


    private Table menuNewGameTable;
    private Stage newStage = new Stage();
    private TextField usernameText;
    private Dialog overwriteDialog;
    private BaseScreen previousScreen;
    private float stateTime;
    Player player;

    public MenuNewGameScreen(Game2D game, BaseScreen previousScreen, ResourceManager resourceManager) {
        super(game);
        this.previousScreen = previousScreen;


        Label profileName = new Label("Enter Profile Name: ", ResourceManager.skin);
        profileName.setFontScale(1.5f);
        usernameText = new TextField("Leo", ResourceManager.skin);
        usernameText.setMaxLength(20);
        usernameText.setWidth(200);
        usernameText.setHeight(10);

        usernameText.getStyle().background.setLeftWidth(10);
        usernameText.getStyle().background.setRightWidth(20);
        usernameText.getStyle().background.setTopHeight(5);
        usernameText.getStyle().background.setBottomHeight(5);

        menuNewGameTable = createTable();
        menuNewGameTable.pad(10);
        menuNewGameTable = createTable();
        menuNewGameTable.setFillParent(true);
        menuNewGameTable.add(profileName).center();
        menuNewGameTable.add(usernameText);

        menuNewGameTable.row();

        createOverwriteDialog();
        menuNewGameTable.add(handlePlayButton()).padRight(10).padTop(10);
        menuNewGameTable.add(handleNewBackButton()).padRight(10);
    }


    private void createOverwriteDialog() {
        overwriteDialog = new Dialog("", ResourceManager.skin);
        overwriteDialog.setKeepWithinStage(true);
        overwriteDialog.setModal(true);
        overwriteDialog.setMovable(false);

        Label label = new Label("Overwrite existing save?", ResourceManager.skin);
        overwriteDialog.getContentTable().pad(10f, 10f, 10f, 10f);
        overwriteDialog.getContentTable().add(label).row();

        Table buttonTable = new Table();

        Actor overwriteButton = createButton("Overwrite");
        overwriteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                profileManager.overwriteProfile(player);
                profileManager.loadProfile(player.username);
                setScreenWithTransition((BaseScreen) game.getScreen(), new GameScreen(game), new ArrayList<>());
                overwriteDialog.hide();
            }
        });
        buttonTable.add(overwriteButton).pad(5);

        Actor cancelButton = createButton("Cancel");
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                overwriteDialog.hide();
            }
        });
        buttonTable.add(cancelButton).pad(5);

        overwriteDialog.getContentTable().add(buttonTable);

        overwriteDialog.pack();

        stage.addActor(overwriteDialog);

        overwriteDialog.setPosition((stage.getWidth() - overwriteDialog.getWidth()) / 2, (stage.getHeight() - overwriteDialog.getHeight()) / 2);
    }


    private Actor handlePlayButton() {
        Actor playButton = createButton("Play");

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y) {
                player = new Player(usernameText.getText(), new Vector2(17.5F, 10), Entity.Direction.DOWN, "ROOM");

                if(profileManager.addProfile(player)){
                    profileManager.loadProfile(player.username);
                    setScreenWithTransition((BaseScreen)game.getScreen(), new GameScreen(game), new ArrayList<>());
                }else{
                    overwriteDialog.show(newStage);
                }
            }
        });

        return playButton;
    }

    private Actor handleNewBackButton() {
        Actor backButton = createButton("Back");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y) {
                setScreenWithTransition((BaseScreen) game.getScreen(), previousScreen, new ArrayList<>());
            }
        });

        return backButton;
    }

    @Override
    public void show() {
        newStage.addActor(menuNewGameTable);
        Gdx.input.setInputProcessor(newStage);
    }

    @Override
    public void render(float delta) {
        stateTime += Gdx.graphics.getDeltaTime();

        if (previousScreen != null) {
            previousScreen.render(stateTime);
        }

        show();
        newStage.act(delta);
        newStage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void hide() {
    }
}