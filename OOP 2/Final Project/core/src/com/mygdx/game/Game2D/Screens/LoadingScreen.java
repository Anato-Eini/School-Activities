package com.mygdx.game.Game2D.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Game2D.Game2D;
import com.mygdx.game.Game2D.Manager.ResourceManager;
import com.mygdx.game.Game2D.World.MapManager;

import java.util.ArrayList;

import static com.mygdx.game.Game2D.Game2D.resourceManager;
import static com.mygdx.game.Game2D.Game2D.shapeRenderer;

public class LoadingScreen extends BaseScreen {
    private static final float PROGRESS_BAR_WIDTH = 500;
    private static final float PROGRESS_BAR_HEIGHT = 50f;
    private ResourceManager resourceManager;
    private TextureAtlas catAtlas;
    private Sprite catSprite;
    private SpriteBatch spriteBatch;
    private Animation<TextureRegion> catAnimation;
    private float catAnimationStateTime = 0;
    private BitmapFont font;

    public LoadingScreen(Game2D game) {
        super(game);
        this.resourceManager = ResourceManager.getInstance();
        this.spriteBatch = new SpriteBatch();
        this.font = new BitmapFont();
        font.getData().setScale(1.1f);
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(Game2D.resourceManager.cursor, 0, 0));
    }

    @Override
    public void show() {
        super.show();
        resourceManager.loadAssets();
        catAtlas = new TextureAtlas("atlas/Cat/cat.atlas");
        catAnimation = new Animation<>(0.10f, catAtlas.findRegions("cat_walk"));
        catSprite = new Sprite(catAnimation.getKeyFrame(0));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        resourceManager.assetManager.update();

        spriteBatch.begin();
        catAnimationStateTime += Gdx.graphics.getDeltaTime();
        catSprite.setPosition(Gdx.graphics.getWidth() / 2f - catSprite.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - catSprite.getHeight() / 2f);
        catSprite.setRegion(catAnimation.getKeyFrame(catAnimationStateTime, true));
        catSprite.draw(spriteBatch);
        int loadingProgress = (int) (resourceManager.getProgress() * 100);

        String loadingText = "Loading... " + loadingProgress + "%";
        GlyphLayout layout = new GlyphLayout(font, loadingText);
        float textWidth = layout.width;

        font.draw(spriteBatch, loadingText,
                (Gdx.graphics.getWidth() / 2f) - textWidth / 2f,
                Gdx.graphics.getHeight() / 2f - catSprite.getHeight() / 2f - 40);

        spriteBatch.end();

        if(loadingProgress == 100){
            resourceManager.isFinishedLoading = true;
            resourceManager.initializeResource();
            Game2D.mapManager = new MapManager();
            setScreenWithTransition(this, new MenuScreen(game), new ArrayList<>());
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        spriteBatch.dispose();
        catAtlas.dispose();
        font.dispose();
    }
}
