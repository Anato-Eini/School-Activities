package com.mygdx.game.Game2D.World.Maps.Minigames.MINIGAME2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game2D.Manager.AudioManager;

import java.time.Duration;

public class GameOver implements Screen {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final long score;
    private final Duration duration;

    public GameOver(long points, Duration duration) {
        this.duration = duration;
        this.score = points;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        AudioManager.getInstance().playSound("game_over");
    }


    @Override
    public void show() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        font.getData().scaleX = 2;
        font.getData().scaleY = 2;
        font.setColor(Color.GREEN);

        batch.begin();

        font.getData().setScale(5);
        // Create a GlyphLayout for each text
        GlyphLayout layoutGameOver = new GlyphLayout(font, "Game Over");
        font.getData().setScale(2);
        GlyphLayout layoutScore = new GlyphLayout(font, "Score: " + score);
        GlyphLayout layoutDuration = new GlyphLayout(font, String.format("Your game took: %s seconds", duration.getSeconds()));

        // Calculate the x and y coordinates for each text
        float xGameOver = (Gdx.graphics.getWidth() - layoutGameOver.width) / 2;
        float yGameOver = Gdx.graphics.getHeight() / 2 + layoutGameOver.height;

        float xScore = (Gdx.graphics.getWidth() - layoutScore.width) / 2;
        float yScore = yGameOver - layoutGameOver.height - 20;

        float xDuration = (Gdx.graphics.getWidth() - layoutDuration.width) / 2;
        float yDuration = yScore - layoutScore.height - 20;

        // Display score and duration
        font.draw(batch, layoutGameOver, xGameOver, yGameOver);
        font.draw(batch, layoutScore, xScore, yScore);
        font.draw(batch, layoutDuration, xDuration, yDuration);

        batch.end();
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
