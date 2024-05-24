package com.mygdx.game.Game2D.Entities.NPC;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Game2D.Entities.Entity;
import com.mygdx.game.Game2D.Screens.GameScreen;
import com.mygdx.game.Game2D.World.CollisionType;
import com.mygdx.game.ScreenConfig;

import static com.mygdx.game.Game2D.Game2D.batch;
import static com.mygdx.game.Game2D.Screens.GameScreen.gameState;
import static com.mygdx.game.Game2D.Screens.GameScreen.world;

public class NPC extends Entity {
    TextureAtlas textureAtlas;
    Animation<TextureRegion> upAnimation;
    Animation<TextureRegion> downAnimation;
    Animation<TextureRegion> leftAnimation;
    Animation<TextureRegion> rightAnimation;
    Animation<TextureRegion> idleUpAnimation;
    Animation<TextureRegion> idleDownAnimation;
    Animation<TextureRegion> idleLeftAnimation;
    Animation<TextureRegion> idleRightAnimation;
    int length; //Length in seconds in which the entity do a movement
    int movementCounter; //A counter for fixing NPC movement when encountering a collision

    public NPC(int length){
        direction = Direction.DOWN;
        movementCounter = length;
        this.length = length;
        textureAtlas = new TextureAtlas(Gdx.files.internal("atlas/leo.atlas"));

        upAnimation = new Animation<>(0.10f, textureAtlas.findRegions("move_up"));
        downAnimation = new Animation<>(0.10f, textureAtlas.findRegions("move_down"));
        leftAnimation = new Animation<>(0.10f, textureAtlas.findRegions("move_left"));
        rightAnimation = new Animation<>(0.10f, textureAtlas.findRegions("move_right"));

        idleUpAnimation = new Animation<>(0.10f, textureAtlas.findRegions("idle_up"));
        idleDownAnimation = new Animation<>(0.10f, textureAtlas.findRegions("idle_down"));
        idleLeftAnimation = new Animation<>(0.10f, textureAtlas.findRegions("idle_left"));
        idleRightAnimation = new Animation<>(0.10f, textureAtlas.findRegions("idle_right"));

        upAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        downAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        leftAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        rightAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        idleUpAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        idleDownAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        idleLeftAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        idleRightAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        direction = Direction.DOWN;
        frame = idleDownAnimation.getKeyFrame(0);

        sprite = new Sprite(rightAnimation.getKeyFrame(0));
        position = new Vector2((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);

        speed = 50;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(8 * ScreenConfig.tileSize, 8 * ScreenConfig.tileSize);
        boxBody = world.createBody(bodyDef);
        boxBody.setLinearDamping(50f);

        PolygonShape dynamicBox = new PolygonShape();
        dynamicBox.setAsBox(sprite.getWidth() / 3, sprite.getHeight() / 8);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicBox;
        fixtureDef.filter.categoryBits = CollisionType.NPC.getValue();
        fixtureDef.filter.maskBits = CollisionType.WALL.getValue();  // Collides with walls only

        Fixture fixture = boxBody.createFixture(fixtureDef);
        fixture.setUserData(this);

        direction = Direction.UP;
    }

    public void move(){
        if(movementCounter == length * speed)
            newMovement();


        if(direction == Direction.UP){
            setState(State.WALKING);
            boxBody.applyLinearImpulse(new Vector2(0, speed), boxBody.getWorldCenter(), true);
            direction = Direction.UP;
            movementCounter++;

        }else if(direction == Direction.DOWN){
            setState(State.WALKING);
            boxBody.applyLinearImpulse(new Vector2(0, -speed), boxBody.getWorldCenter(), true);
            direction = Direction.DOWN;
            movementCounter++;
        }else if(direction == Direction.LEFT){
            setState(State.WALKING);
            boxBody.applyLinearImpulse(new Vector2(-speed, 0), boxBody.getWorldCenter(), true);
            direction = Direction.LEFT;
        }else if(direction == Direction.RIGHT){
            setState(State.WALKING);
            boxBody.applyLinearImpulse(new Vector2(speed, 0), boxBody.getWorldCenter(), true);
            direction = Direction.RIGHT;
        }else {
            setState(State.IDLE);
        }

        movementCounter++;
        position.set(this.boxBody.getPosition().x / ScreenConfig.originalTileSize, this.boxBody.getPosition().y /
                ScreenConfig.originalTileSize);
    }

    public void newMovement(){
        Direction currentDirection = direction;
        while(currentDirection == direction){
            RandomXS128 randomXS128 = new RandomXS128();
            int randomNumber = randomXS128.nextInt() % 5;
            switch (randomNumber) {
                case 0 -> direction = Direction.DOWN;
                case 1 -> direction = Direction.LEFT;
                case 2 -> direction = Direction.RIGHT;
                case 3 -> direction = Direction.UP;
                case 4 -> direction = Direction.STAY;
            }
        }
        if(direction == Direction.STAY)
            length *= (int) speed;
        movementCounter = 0;
    }


    public void setToStay(int length){
        direction = Direction.STAY;
        movementCounter = 0;
        this.length = length * (int)speed;
    }

    public NPC render(){
        if(gameState == GameScreen.GameState.PAUSED)
            return this;

        move();

/*        if(movement.getCurrentDirection() == Direction.STAY)
            movement.nextDirection();
        else if(((movement.getCurrentDirection() == Direction.LEFT || movement.getCurrentDirection() == Direction.RIGHT)
                && (int)boxBody.getPosition().x % ScreenConfig.tileSize == 0) || ((movement.getCurrentDirection() ==
                Direction.UP || movement.getCurrentDirection() == Direction.DOWN)
                && (int)boxBody.getPosition().y % ScreenConfig.tileSize == 0)){
            movement.nextDirection();
        }*/

        if(movementCounter >= length)
            newMovement();

        float currentX = sprite.getX(), currentY = sprite.getY();
        sprite.setPosition(boxBody.getPosition().x - sprite.getWidth() / 2, boxBody.getPosition().y -
                sprite.getHeight() / 7);

        if(currentX == sprite.getX() && currentY == sprite.getY() && state == State.WALKING)
            newMovement();

        animationStateTime += Gdx.graphics.getDeltaTime();

        if(state == State.WALKING)
            animation(upAnimation, downAnimation, leftAnimation, rightAnimation);
        else
            animation(idleUpAnimation, idleDownAnimation, idleLeftAnimation, idleRightAnimation);

        sprite.setRegion(frame);

        batch.begin();
        sprite.draw(batch);
        batch.end();

        return this;
    }

    private NPC animation(Animation<TextureRegion> upAnimation, Animation<TextureRegion> downAnimation,
                               Animation<TextureRegion> leftAnimation, Animation<TextureRegion> rightAnimation) {
        switch (direction) {
            case UP -> frame = upAnimation.getKeyFrame(animationStateTime, true);
            case DOWN -> frame = downAnimation.getKeyFrame(animationStateTime, true);
            case LEFT -> frame = leftAnimation.getKeyFrame(animationStateTime, true);
            case RIGHT -> frame = rightAnimation.getKeyFrame(animationStateTime, true);
        }
        return this;
    }
}
