package com.mygdx.game.Game2D.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Game2D.Entities.player.Player;
import com.mygdx.game.Game2D.Game2D;
import com.mygdx.game.Game2D.Manager.ResourceManager;
import com.mygdx.game.Game2D.Screens.transition.effects.FadeOutTransitionEffect;
import com.mygdx.game.Game2D.Screens.transition.effects.TransitionEffect;

import java.util.ArrayList;

import static com.mygdx.game.Game2D.Game2D.profileManager;

public class MenuLoadGameScreen extends BaseScreen {
    private Table loadTable;
    private Table topTable;
    private Table bottomTable;
    private Stage loadStage = new Stage();
    private BaseScreen previousScreen;
    private List<Label> listItems = new List<>(ResourceManager.skin);
    private float stateTime;
    private Dialog cloudAuthDialog;
    private Array<Table> profileRows = new Array<>();
    private final Label resultText = new Label("", ResourceManager.skin);
    private Table selectedRow;
    private Table controlsTable = new Table();
    private Dialog confirmDeleteDialog = new Dialog("Confirm Delete", ResourceManager.skin);

    public MenuLoadGameScreen(Game2D game, BaseScreen previousScreen, ResourceManager resourceManager) {
        super(game);
        this.previousScreen = previousScreen;
        resourceManager.setMenuLoadGameScreen(true);

        loadTable = createTable();

        TextureRegionDrawable background = new TextureRegionDrawable(ResourceManager.getInstance().UI.findRegion("load_profiles_bg"));
        topTable = createTable();
        topTable.setBackground(background);
//        topTable.center();
//        topTable.setFillParent(true);


        bottomTable = createTable();
        bottomTable.setWidth(Gdx.graphics.getWidth());
        bottomTable.setHeight(Gdx.graphics.getHeight() / 2f);
        bottomTable.center();

        Table table = new Table();
        table.add(resultText).pad(10).center();
        bottomTable.add(table).center().row();
        bottomTable.add(controlsTable);

        createProfileList();
        handleLoadButton();
        handleCloudUploadButton();
        handleCloudDownloadButton();
        handleLoadBackButton();
        createCloudAuthDialog();
    }

    private void createProfileList() {
        ArrayList<Player> profiles = profileManager.getProfiles();
        for (Player p : profiles) {
            addProfileRow(p.username);
        }

        refreshList();
    }

    private void addProfileRow(String username) {
        Table row = new Table();
        row.setName(username);

        Label usernameLabel = new Label(username, ResourceManager.skin);
        row.add(usernameLabel).left().expandX().fillX().pad(10);

        TextButton deleteButton = new TextButton("Delete", ResourceManager.skin);
        row.add(deleteButton).right().pad(10);

        deleteButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                confirmDeleteDialog.show(loadStage);
            }
        });


        Actor yesButton = createButton("Yes");
        yesButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y) {
                Gdx.app.postRunnable(new Runnable() {
                    final boolean result = profileManager.deleteProfile(username);
                    @Override
                    public void run() {
                        if (result) {
                            profileRows.removeValue(row, true);
                            refreshList();
                            resultText.setText("Successfully deleted character");
                        } else {
                            resultText.setText("Failed to delete character");
                        }
                        confirmDeleteDialog.hide();
                    }
                });
            }
        });

        Actor noButton = createButton("No");
        noButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y) {
                confirmDeleteDialog.hide();
            }
        });

        confirmDeleteDialog.getContentTable().add(yesButton);
        confirmDeleteDialog.getContentTable().add(noButton);

        row.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedRow != null) {
                    selectedRow.setBackground(ResourceManager.skin.getDrawable("default-round"));
                }
                selectedRow = row;
                selectedRow.setBackground(ResourceManager.skin.getDrawable("selection"));
            }
        });

        row.setBackground(ResourceManager.skin.getDrawable("default-round"));
        profileRows.add(row);
    }

    void refreshList() {
        topTable.clear();
        for (Actor row : profileRows) {
            topTable.add(row).row();
        }
    }

    private void handleLoadButton() {
        Actor loadButton = createButton("Play");
        controlsTable.add(loadButton);

        loadButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedRow == null) {
                    resultText.setText("No profile selected");
                    return;
                }

                previousScreen.dispose();
                String username = selectedRow.getName();
                profileManager.loadProfile(username);

                setScreenWithTransition((BaseScreen) game.getScreen(), new GameScreen(game), new ArrayList<>());
            }
        });
    }

    private void createCloudAuthDialog() {
        cloudAuthDialog = new Dialog("Login/Register", ResourceManager.skin);
        cloudAuthDialog.setKeepWithinStage(true);
        cloudAuthDialog.setModal(true);
        cloudAuthDialog.setMovable(false);
        cloudAuthDialog.row();

        Label usernameLabel = new Label("Username:", ResourceManager.skin);
        TextField usernameField = new TextField("", ResourceManager.skin);

        Label passwordLabel = new Label("Password:", ResourceManager.skin);
        TextField passwordField = new TextField("", ResourceManager.skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        cloudAuthDialog.getContentTable().add(usernameLabel).pad(5);
        cloudAuthDialog.getContentTable().row();
        cloudAuthDialog.getContentTable().add(usernameField).width(200).pad(5);
        cloudAuthDialog.getContentTable().row();
        cloudAuthDialog.getContentTable().add(passwordLabel).pad(5);
        cloudAuthDialog.getContentTable().row();
        cloudAuthDialog.getContentTable().add(passwordField).width(200).pad(5);
        cloudAuthDialog.getContentTable().row();

        Label resultLabel = new Label("", ResourceManager.skin);
        cloudAuthDialog.getContentTable().add(resultLabel).colspan(2).pad(5);
        cloudAuthDialog.getContentTable().row();

        Table buttonTable = new Table();

        Actor loginButton = createButton("Login");
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                String result = profileManager.cloudLogin(username, password);
                resultLabel.setText(result);
            }
        });
        buttonTable.add(loginButton).pad(5);

        Actor registerButton = createButton("Register");
        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                String result = profileManager.cloudRegister(username, password);
                resultLabel.setText(result);
            }
        });
        buttonTable.add(registerButton).pad(5);

        Actor cancelButton = createButton("Cancel");
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cloudAuthDialog.hide();
            }
        });
        buttonTable.add(cancelButton).pad(5);

        cloudAuthDialog.getContentTable().add(buttonTable).colspan(2).pad(10);
        cloudAuthDialog.row();
    }

    private void handleCloudUploadButton() {
        ImageButton imageButton = ResourceManager.getInstance().createImageButton("upload_button", 2);
        createImageButton(imageButton, controlsTable);

        imageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!profileManager.getPreferences().contains("cloud_id")) {
                    cloudAuthDialog.show(loadStage);
                } else {
                    boolean status = profileManager.cloudProfilesUpload();

                    Dialog dialog = new Dialog("Upload Status", ResourceManager.skin);
                    if (status) {
                        dialog.text("Upload Successful");
                    } else {
                        dialog.text("Upload Failed");
                    }
                    dialog.button("OK", true);
                    dialog.show(loadStage);
                }
            }
        });
    }

    private void handleCloudDownloadButton() {
        ImageButton imageButton = ResourceManager.getInstance().createImageButton("download_button", 2);
        createImageButton(imageButton, controlsTable);

        imageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!profileManager.getPreferences().contains("cloud_id")) {
                    cloudAuthDialog.show(loadStage);
                } else {
                    boolean status = profileManager.cloudProfilesDownload();
                    Dialog dialog = new Dialog("Download Status", ResourceManager.skin);
                    if (status) {
                        dialog.text("Profiles downloaded");

                        ArrayList<Player> profiles = profileManager.getProfiles();

                        profileRows.clear();
                        for (Player p : profiles) {
                            addProfileRow(p.username);
                        }

                        refreshList();

                    } else {
                        dialog.text("Download failed");
                    }
                    dialog.button("OK", true);
                    dialog.show(loadStage);
                }
            }
        });
    }

    private void handleLoadBackButton() {
        Actor backButton = createButton("Back");
        controlsTable.add(backButton);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y) {
                ArrayList<TransitionEffect> effects = new ArrayList<>();
                effects.add(new FadeOutTransitionEffect(.3F));
                setScreenWithTransition((BaseScreen) game.getScreen(), previousScreen, effects);
            }
        });
    }

    @Override
    public void show() {
        loadStage.addActor(loadTable);
        loadStage.addActor(topTable);
        loadStage.addActor(bottomTable);
        Gdx.input.setInputProcessor(loadStage);
    }

    @Override
    public void render(float delta) {
        stateTime += Gdx.graphics.getDeltaTime();

        if (previousScreen != null) {
            previousScreen.render(stateTime);
        }

        loadStage.act(delta);
        loadStage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        loadTable.remove();
        topTable.remove();
        bottomTable.remove();
    }

    @Override
    public void hide() {
        resourceManager.setMenuLoadGameScreen(false);
    }
}
