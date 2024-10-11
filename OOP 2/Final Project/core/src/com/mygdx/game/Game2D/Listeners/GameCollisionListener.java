package com.mygdx.game.Game2D.Listeners;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Game2D.Entities.NPC.MainNPC;
import com.mygdx.game.Game2D.Entities.NPC.NPCMinigame1;
import com.mygdx.game.Game2D.Entities.player.Player;
import com.mygdx.game.Game2D.Game2D;
import com.mygdx.game.Game2D.World.MapExit;
import com.mygdx.game.Game2D.World.Maps.Minigames.MINIGAME1;
import com.mygdx.game.Game2D.World.Maps.Minigames.MINIGAME2.TypeRacer;
import com.mygdx.game.ScreenConfig;

import static com.mygdx.game.Game2D.Game2D.mapManager;
import static com.mygdx.game.Game2D.Screens.GameScreen.*;

public class GameCollisionListener implements ContactListener {
    Game2D game;
    Screen screen;

    public GameCollisionListener(Game2D game, Screen screen) {
        this.game = game;
        this.screen = screen;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Object objectA = fixtureA.getUserData();
        Object objectB = fixtureB.getUserData();
        if (fixtureA.getUserData() == null || fixtureB.getUserData() == null) return;

        if (objectA instanceof Player) {
            if (objectB instanceof MapExit mapExit) {
                float playerX = player.boxBody.getPosition().x / ScreenConfig.originalTileSize;
                float playerY = player.boxBody.getPosition().y / ScreenConfig.originalTileSize;
                switch (player.getDirection()) {
                    case UP -> playerY -= .5F;
                    case DOWN -> playerY += .5F;
                    case LEFT -> playerX += .5F;
                    case RIGHT -> playerX -= .5F;
                }
                player.setLastMapPosition(new Vector2(playerX, playerY));
                mapManager.dispatchMap(mapExit);
            }

            if(objectB instanceof TypeRacer typeRacer){
                typeRacer.create();
                game.setScreen(typeRacer);
            }
        }

        if (objectA instanceof MainNPC) {
            if (objectB == "COLLISION") {
                ((MainNPC) objectA).newMovement();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}