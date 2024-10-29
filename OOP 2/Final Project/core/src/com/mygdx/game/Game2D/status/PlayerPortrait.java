package com.mygdx.game.Game2D.status;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayerPortrait extends Actor {
    private Animation<TextureRegion> animation;
    private float stateTime = 0;

    public PlayerPortrait() {
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("atlas/portrait.atlas"));
        animation = new Animation<>(0.10f, textureAtlas.findRegions("frame"));
        animation.setPlayMode(Animation.PlayMode.LOOP);
        setSize(animation.getKeyFrame(0).getRegionWidth(), animation.getKeyFrame(0).getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(animation.getKeyFrame(stateTime), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
