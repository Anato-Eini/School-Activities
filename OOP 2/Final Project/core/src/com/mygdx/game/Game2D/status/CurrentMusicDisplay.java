package com.mygdx.game.Game2D.status;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Game2D.Manager.ResourceManager;

public class CurrentMusicDisplay {
    private Stage stage;
    private Label musicLabel1;
    private Label musicLabel2;
    private int defaultWidth;
    private int defaultHeight;
    private ScrollPane scrollPane;
    private Table scrollTable = new Table();
    private String currentMusicTitle;

    public CurrentMusicDisplay() {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        defaultWidth = 200;
        defaultHeight = 50;

        NinePatch ninePatch = new NinePatch(ResourceManager.getInstance().atlas.findRegion("UI_Flat_Button_Large_Lock_01a1"));
        NinePatchDrawable backgroundDrawable = new NinePatchDrawable(ninePatch);

        NinePatch musicNotes = new NinePatch(ResourceManager.getInstance().atlas.findRegion("musicnotes"));
        NinePatchDrawable musicNotesDrawable = new NinePatchDrawable(musicNotes);

        Table mainTable = new Table();
        mainTable.pad(10);
        mainTable.left();
        mainTable.setBackground(backgroundDrawable);

        Image musicNotesImage = new Image(musicNotesDrawable);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = ResourceManager.pixel10;
        labelStyle.fontColor = Color.BLACK;

        musicLabel1 = new Label("", labelStyle);
        musicLabel1.setFontScale(1.5f);
        musicLabel2 = new Label("", labelStyle);
        musicLabel2.setFontScale(1.5f);

        scrollTable = new Table();
        scrollTable.add(musicLabel1).align(Align.left);
        scrollTable.add(musicLabel2).align(Align.left);

        scrollPane = new ScrollPane(scrollTable);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setOverscroll(false, false);
        scrollPane.setFadeScrollBars(false);

        mainTable.add(musicNotesImage).padRight(10);
        mainTable.add(scrollPane).width(defaultWidth).height(defaultHeight);

        mainTable.setSize(defaultWidth + 50, defaultHeight);
        stage.addActor(mainTable);

        mainTable.setPosition(Gdx.graphics.getWidth() - mainTable.getWidth() - 10, 10);
    }

    public void startScrolling() {
        Action scrollAction = new Action() {
            @Override
            public boolean act(float delta) {
                float scrollSpeed = 1;

                musicLabel1.moveBy(-scrollSpeed, 0);
                musicLabel2.moveBy(-scrollSpeed, 0);

                if (musicLabel1.getX() + musicLabel1.getWidth() <= 0) {
                    musicLabel1.setX(musicLabel2.getX() + musicLabel2.getWidth() + 10);
                }
                if (musicLabel2.getX() + musicLabel2.getWidth() <= 0) {
                    musicLabel2.setX(musicLabel1.getX() + musicLabel1.getWidth() + 10);
                }

                return false;
            }
        };

        stage.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                scrollAction.act(delta);
                return false;
            }
        });
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }

    public void updateMusicLabel(String musicTitle) {
        currentMusicTitle = musicTitle;
        musicLabel1.setText(currentMusicTitle + "      ");
        musicLabel2.setText(currentMusicTitle + "      ");
        musicLabel1.setX(0);
        musicLabel2.setX(musicLabel1.getWidth() + 10);
        musicLabel1.layout();
        musicLabel2.layout();
        scrollTable.setWidth(musicLabel1.getWidth() + musicLabel2.getWidth() + 10);
    }
}
