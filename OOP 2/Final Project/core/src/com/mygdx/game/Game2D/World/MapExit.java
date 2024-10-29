package com.mygdx.game.Game2D.World;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game2D.Entities.Entity;

public class MapExit {
    public String nextMap;
    public Vector2 playerPosition;
    public Entity.Direction playerDirection;

    public MapExit(String nextMap, Vector2 playerPosition, Entity.Direction playerDirection) {
        this.nextMap = nextMap;
        this.playerPosition = playerPosition;
        this.playerDirection = playerDirection;
    }
}
