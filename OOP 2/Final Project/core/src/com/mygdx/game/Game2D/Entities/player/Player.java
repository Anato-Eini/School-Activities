package com.mygdx.game.Game2D.Entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Game2D.Entities.Entity;
import com.mygdx.game.Game2D.Entities.NPC.NPC;
import com.mygdx.game.Game2D.Inventory.Inventory;
import com.mygdx.game.Game2D.Manager.ResourceManager;
import com.mygdx.game.Game2D.World.CollisionType;
import com.mygdx.game.ScreenConfig;

import java.util.List;

import static com.mygdx.game.Game2D.Game2D.*;
import static com.mygdx.game.Game2D.Game2D.mapManager;
import static com.mygdx.game.Game2D.Screens.GameScreen.*;

public class Player extends Entity implements Json.Serializable {
    public String username;
    public boolean isCollisionSet;
    private Vector2 lastMapPosition;
    public float interactionDistance = 40; //Maximum distanceW when interacting with other entities
    public PlayerHUD playerHUD;

    public Player(){
        //IMPORTANT: No argument constructor needed for deserialization
        setDefaults();
    }

    public Player(String username, Vector2 position, Entity.Direction direction, String map){
        this.position = position;
        this.username = username;
        this.direction = direction;
        this.map = map;
        setDefaults();
    }

    public void setDefaults(){
        this.speed = 150F;
        dialogues.addAll(List.of(new String[]{
                "Cool and Normal",
                "OOP lezgow"
        }));
    }

    public void setCollision() {
        ResourceManager resourceManager = ResourceManager.getInstance();

        frame = resourceManager.idleDownAnimation.getKeyFrame(0);
        sprite = new Sprite(resourceManager.rightAnimation.getKeyFrame(0));
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        boxBody = world.createBody(bodyDef);
        boxBody.setLinearDamping(50f);
        boxBody.setTransform(0, 0, 0);

        PolygonShape dynamicBox = new PolygonShape();
        dynamicBox.setAsBox(sprite.getWidth() / 3, sprite.getHeight() / 8);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicBox;

        fixtureDef.filter.categoryBits = CollisionType.PLAYER.getValue();
        fixtureDef.filter.maskBits = (short) (CollisionType.WALL.getValue() | CollisionType.EXIT.getValue() | CollisionType.NPC.getValue());

        Fixture fixture = boxBody.createFixture(fixtureDef);
        fixture.setUserData(this);
        isCollisionSet = true;
    }


    public void update(){
        if ((Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.W))) {
            setState(State.WALKING);
        } else {
            setState(State.IDLE);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            direction = Direction.LEFT;
            boxBody.applyLinearImpulse(new Vector2(-speed, 0), boxBody.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            direction = Direction.RIGHT;
            boxBody.applyLinearImpulse(new Vector2(speed, 0), boxBody.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            direction = Direction.UP;
            boxBody.applyLinearImpulse(new Vector2(0, speed), boxBody.getWorldCenter(), true);

        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            direction = Direction.DOWN;
            boxBody.applyLinearImpulse(new Vector2(0 , -speed), boxBody.getWorldCenter(), true);
        }
        position.set(this.boxBody.getPosition().x / ScreenConfig.originalTileSize, this.boxBody.getPosition().y / ScreenConfig.originalTileSize);
    }

    private boolean withinInteractionRange(Sprite player, Sprite entity){
        float entitySpriteX = entity.getX(), entitySpriteY = entity.getY();
        float playerSpriteX = player.getX(), playerSpriteY = player.getY();

        Rectangle playerRectangle;
        Rectangle entityRectangle = new Rectangle(entitySpriteX, entitySpriteY - entity.getHeight(),
                entity.getWidth(), entity.getHeight());

        if(direction == Direction.LEFT)
             playerRectangle = new Rectangle(playerSpriteX - interactionDistance,
                     playerSpriteY - player.getHeight(), player.getWidth(), player.getHeight());
        else if(direction == Direction.RIGHT)
            playerRectangle = new Rectangle(playerSpriteX, playerSpriteY - player.getHeight()
                    , player.getWidth() + interactionDistance, player.getHeight());
        else if(direction == Direction.UP)
            playerRectangle = new Rectangle(playerSpriteX, playerSpriteY - player.getHeight(), player.getWidth()
                    , player.getHeight() + interactionDistance);
        else
            playerRectangle = new Rectangle(playerSpriteX, playerSpriteY - player.getHeight() - interactionDistance
                    , player.getWidth(), player.getHeight());

        return playerRectangle.overlaps(entityRectangle);
    }

    public void render(){
        animationStateTime += Gdx.graphics.getDeltaTime();
        if(state == State.WALKING){
            switch (direction) {
                case UP -> frame = resourceManager.upAnimation.getKeyFrame(animationStateTime, true);
                case DOWN -> frame = resourceManager.downAnimation.getKeyFrame(animationStateTime, true);
                case LEFT -> frame = resourceManager.leftAnimation.getKeyFrame(animationStateTime, true);
                case RIGHT -> frame = resourceManager.rightAnimation.getKeyFrame(animationStateTime, true);
            }
        }else{
            switch (direction) {
                case UP -> frame = resourceManager.idleUpAnimation.getKeyFrame(animationStateTime, true);
                case DOWN -> frame = resourceManager.idleDownAnimation.getKeyFrame(animationStateTime, true);
                case LEFT -> frame = resourceManager.idleLeftAnimation.getKeyFrame(animationStateTime, true);
                case RIGHT -> frame = resourceManager.idleRightAnimation.getKeyFrame(animationStateTime, true);
            }
        }

        sprite.setPosition(boxBody.getPosition().x - sprite.getWidth() / 2,
                boxBody.getPosition().y - sprite.getHeight() / 7);

        sprite.setRegion(frame);
        batch.begin();
        sprite.draw(batch);
        batch.end();
        setState(State.IDLE);

        if(Gdx.input.isKeyPressed(Input.Keys.F))
            for(Entity e: mapManager.currentMap.npcManager.getNPCs())
                if(withinInteractionRange(sprite, e.sprite) && e.finishedDialogue)
                    e.setDialogue();

        for(NPC e: mapManager.currentMap.npcManager.getNPCs())
            if(!e.finishedDialogue)
                e.doDialogue();

        doDialogue();
    }


    public void setPosition(Vector2 position){
        if(!isCollisionSet)
            setCollision();
        this.boxBody.setTransform(new Vector2(position.x * ScreenConfig.originalTileSize, position.y * ScreenConfig.originalTileSize), 0);
    }

    public Player setUsername(String username) {
        this.username = username;
        return this;
    }

    public void setMap(String map){
        this.map = map;
        this.playerHUD.setMapLabelText(map);
    }

    public void setLastMapPosition(Vector2 position){
        this.lastMapPosition = position;
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
    public Vector2 getLastMapPosition(){
        return lastMapPosition;
    }
    public Direction getDirection(){
        return this.direction;
    }

    public Vector2 getPosition(){
        return position;
    }

    public String getMap(){
        return this.map;
    }

    @Override
    public void write(Json json) {
        json.writeValue("username", username);
        json.writeValue("position", getPosition());
        json.writeValue("direction", getDirection());
        json.writeValue("map", getMap());
        json.writeValue("lastMapPosition", getLastMapPosition());
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        username = json.readValue("username", String.class, jsonData);
        position = json.readValue("position", Vector2.class, jsonData);
        direction = json.readValue("direction", Entity.Direction.class, jsonData);
        map = json.readValue("map", String.class, jsonData);
        lastMapPosition = json.readValue("lastMapPosition", Vector2.class, jsonData);
    }
}