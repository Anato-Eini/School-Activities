package com.mygdx.game.Game2D.Entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Game2D.Entities.Entity;
import com.mygdx.game.Game2D.Game2D;
import com.mygdx.game.Game2D.Inventory.Inventory;
import com.mygdx.game.Game2D.Manager.ResourceManager;
import com.mygdx.game.Game2D.Network.Packets.Packet00Login;
import com.mygdx.game.Game2D.Network.Packets.Packet02Move;
import com.mygdx.game.Game2D.Screens.GameScreen;
import com.mygdx.game.Game2D.World.Collision;

import static com.mygdx.game.Game2D.Game2D.*;
import static com.mygdx.game.Game2D.Screens.GameScreen.*;

public class Player extends Entity {
    public String username;
    public boolean isCollisionSet;
    public int hp;
    public int charisma;
    public int intelligence;
    public int agility;
    public Inventory inventory;
    public boolean isMoving = false;
    TextureRegion frame;
    float stateTime = 0F;
    public Body boxBody;

    private static final short PLAYER_CATEGORY = 0x0001;
    private static final short WALL_CATEGORY = 0x0002;
    private static final short ENEMY_CATEGORY = 0x0004;
    private static final short OTHER_CATEGORY = 0x0008;

    public Player(String username, Vector2 position, Entity.Direction direction){
        this.x = position.x;
        this.y = position.y;
        this.username = username;
        this.direction = direction;
        speed = 150F;
    }

    public void login(){
        Packet00Login packet = new Packet00Login(username, boxBody.getPosition().x, boxBody.getPosition().y, direction, this.map);
        packet.writeData(GameScreen.game.getGameClient());
    }

    public void setCollision(float x, float y) {
        ResourceManager resourceManager = ResourceManager.getInstance();

        frame = resourceManager.idleDownAnimation.getKeyFrame(0);
        sprite = new Sprite(resourceManager.rightAnimation.getKeyFrame(0));

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        boxBody = world.createBody(bodyDef);
        boxBody.setLinearDamping(50f);

        PolygonShape dynamicBox = new PolygonShape();
        dynamicBox.setAsBox(sprite.getWidth() / 3, sprite.getHeight() / 8);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicBox;

        fixtureDef.filter.categoryBits = Collision.PLAYER.getValue();
        fixtureDef.filter.maskBits = Collision.WALL.getValue();

        Fixture fixture = boxBody.createFixture(fixtureDef);
        fixture.setUserData(this);

        boxBody.setTransform(x, y, 0);

        isCollisionSet = true;
    }


    public void update(){
        isMoving = Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) ||
            Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.W);
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            direction = Direction.LEFT;
            boxBody.applyLinearImpulse(new Vector2(-speed, 0), boxBody.getWorldCenter(), true);
            if(isMultiplayer){
                Packet02Move packet = new Packet02Move(username, getX(), getY(), direction, map);
                packet.writeData(GameScreen.game.getGameClient());
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            direction = Direction.RIGHT;
            boxBody.applyLinearImpulse(new Vector2(speed, 0), boxBody.getWorldCenter(), true);

            if(isMultiplayer){
                Packet02Move packet = new Packet02Move(username, getX(), getY(), direction, map);
                packet.writeData(GameScreen.game.getGameClient());
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            direction = Direction.UP;
            boxBody.applyLinearImpulse(new Vector2(0, speed), boxBody.getWorldCenter(), true);

            if(isMultiplayer){
                Packet02Move packet = new Packet02Move(username, getX(), getY(), direction, map);
                packet.writeData(GameScreen.game.getGameClient());
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            direction = Direction.DOWN;
            boxBody.applyLinearImpulse(new Vector2(0 , -speed), boxBody.getWorldCenter(), true);

            if(isMultiplayer){
                Packet02Move packet = new Packet02Move(username, getX(), getY(), direction, map);
                packet.writeData(GameScreen.game.getGameClient());
            }
        }
    }

    public void render(){
        stateTime += Gdx.graphics.getDeltaTime();
        if(isMoving){
            switch (direction) {
                case UP -> frame = resourceManager.upAnimation.getKeyFrame(stateTime, true);
                case DOWN -> frame = resourceManager.downAnimation.getKeyFrame(stateTime, true);
                case LEFT -> frame = resourceManager.leftAnimation.getKeyFrame(stateTime, true);
                case RIGHT -> frame = resourceManager.rightAnimation.getKeyFrame(stateTime, true);
            }
        }else{
            switch (direction) {
                case UP -> frame = resourceManager.idleUpAnimation.getKeyFrame(stateTime, true);
                case DOWN -> frame = resourceManager.idleDownAnimation.getKeyFrame(stateTime, true);
                case LEFT -> frame = resourceManager.idleLeftAnimation.getKeyFrame(stateTime, true);
                case RIGHT -> frame = resourceManager.idleRightAnimation.getKeyFrame(stateTime, true);
            }
        }
        sprite.setPosition(boxBody.getPosition().x - sprite.getWidth() / 2, boxBody.getPosition().y - sprite.getHeight() / 7);
        sprite.setRegion(frame);
        sprite.draw(batch);
        isMoving = false;
    }

    public void setPosition(Vector2 position){
        this.boxBody.setTransform(position, 0);
    }

    public Player setDirection(Direction direction){
        this.direction = direction;
        return this;
    }

    public Player setUsername(String username) {
        this.username = username;
        return this;
    }

    public Player setIsMoving(boolean isMoving){
        this.isMoving = isMoving;
        return this;
    }


    public float getX(){
//        return boxBody.getPosition().x - sprite.getWidth() / 2;
        if(boxBody == null)return 0;
        return  boxBody.getPosition().x;
    }

    public float getY(){
//        return boxBody.getPosition().y - sprite.getHeight() / 7;
        if(boxBody == null)return 0;
        return  boxBody.getPosition().y;
    }

    public float getWidth(){
        return sprite.getWidth();
    }

    public float getHeight(){
        return sprite.getHeight();
    }
    public String getUsername(){
        return username;
    }
}