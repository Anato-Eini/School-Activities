package com.mygdx.game.Game2D.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Game2D.Game2D;
import com.mygdx.game.Game2D.Manager.ResourceManager;
import com.mygdx.game.Game2D.Network.GameClient;
import com.mygdx.game.Game2D.Screens.transition.effects.FadeOutTransitionEffect;
import com.mygdx.game.Game2D.Screens.transition.effects.TransitionEffect;
import com.mygdx.game.Game2D.World.World;

import java.util.ArrayList;

public class MenuNewMultiplayerScreen extends BaseScreen {

    private Table newTable;
    private Table topTable;
    private Table bottomTable;
    private Stage newStage = new Stage();
    private TextField IPAddressText;
    private TextField usernameText;
    private BaseScreen previousScreen;
    private float stateTime;

    public MenuNewMultiplayerScreen(Game2D game, BaseScreen previousScreen, ResourceManager resourceManager) {
        super(game);
        this.previousScreen = previousScreen;


        Label IPAdressLabel = new Label("Enter IP Address: ", ResourceManager.skin);
        IPAddressText = new TextField("127.0.0.1", ResourceManager.skin);
        IPAddressText.setMaxLength(20);


        Label usernameLabel = new Label("Enter username: ", ResourceManager.skin);
        usernameText = new TextField("", ResourceManager.skin);
        usernameText.setMaxLength(20);

        newTable = createTable();

        topTable = createTable();
        topTable.setFillParent(true);
        topTable.add(IPAdressLabel).center();
        topTable.add(IPAddressText).center();
        topTable.row();

        topTable.setFillParent(true);
        topTable.add(usernameLabel).center();
        topTable.add(usernameText).center();

        bottomTable = createTable();
        bottomTable.setWidth(Gdx.graphics.getWidth());
        bottomTable.setHeight(Gdx.graphics.getHeight()/2f);
        bottomTable.center();

        handlePlayButton();
        handleNewBackButton();
    }

    private void handlePlayButton() {

        Actor playButton = createButton("Play");

        bottomTable.add(playButton).padRight(50);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y) {
//
//                String ipAddress = IPAddressText.getText();
//                String username = usernameText.getText();
//
//                if(ipAddress.trim().isEmpty() || username.trim().isEmpty()){
//                    System.out.println("INVALID INPUTS");
//                    return;
//                }
//
//                previousScreen.dispose();
//
//                game.setGameClient(new GameClient(ipAddress));
//                game.getGameClient().start();
//                World.isMultiplayer = true;
//
//                ArrayList<TransitionEffect> effects = new ArrayList<>();
//                effects.add(new FadeOutTransitionEffect(1F));
//
//                setScreenWithTransition(
//                    (BaseScreen) game.getScreen(),
//                    new GameScreen(game),
//                    effects
//                );
            }
        });
    }

    private void handleNewBackButton() {

        Actor backButton = createButton("Back");
        bottomTable.add(backButton);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y) {
                setScreenWithTransition((BaseScreen) game.getScreen(), previousScreen, new ArrayList<>());
            }
        });
    }

    @Override
    public void show() {
        newStage.addActor(newTable);
        newStage.addActor(topTable);
        newStage.addActor(bottomTable);
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
        newTable.remove();
    }

    @Override
    public void hide() {
    }
}
