package com.mygdx.game.Game2D.Entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Game2D.Manager.ResourceManager;
import com.mygdx.game.Game2D.status.PlayerPortrait;

public class PlayerHUD {

    private Stage stage;
    private PlayerPortrait playerPortrait;
    private Label nameLabel;
    private Label mapLabel;
    private int defaultWidth;
    private int defaultHeight;


    public PlayerHUD(Player player) {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        playerPortrait = new PlayerPortrait();
        defaultWidth = 350;
        defaultHeight = 100;


        Table table = new Table();
        table.pad(10);

        NinePatch PLAYER_HUD_BG = new NinePatch(ResourceManager.getInstance().atlas.findRegion("player_hud_bg"));
        NinePatchDrawable PLAYER_HUD_BG_DRAWABLE = new NinePatchDrawable(PLAYER_HUD_BG);

        PLAYER_HUD_BG_DRAWABLE.setPadding(10f, 10f, 10f, 10f);
        PLAYER_HUD_BG_DRAWABLE.setMinWidth(table.getMinWidth());
        PLAYER_HUD_BG_DRAWABLE.setMinHeight(table.getMinHeight());
        table.setBackground(PLAYER_HUD_BG_DRAWABLE);

        table.left();

        Table playerTable = new Table();
        playerTable.add(playerPortrait).size(50).align(Align.left).padLeft(20);
        table.add(playerTable).align(Align.left);

        Table table2 = new Table();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = ResourceManager.pixel10;
        labelStyle.fontColor = Color.BLACK;

        nameLabel = new Label(player.username, labelStyle);
        nameLabel.setFontScale(2.5f);
        table2.add(nameLabel).align(Align.left).size(10).padBottom(10);
        table2.row();
        mapLabel = new Label(player.map, labelStyle);
        mapLabel.setFontScale(2.5f);
        table2.add(mapLabel).align(Align.left).size(10);

        table2.padLeft(15);
        table.add(table2).align(Align.left);

        table.setSize(defaultWidth, defaultHeight);
        stage.addActor(table);

        table.setPosition(10, Gdx.graphics.getHeight() - table.getHeight() - 10);
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    public void setNameLabelText(String text) {
        nameLabel.setText(text);
    }
    public void setMapLabelText(String text) {
        mapLabel.setText(text);
    }

    public void dispose() {
        stage.dispose();
    }

    public  Stage  getStage(){
        return stage;
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

}
