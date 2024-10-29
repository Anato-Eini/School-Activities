package com.mygdx.game.Game2D.Entities;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game2D.Entities.player.Player;
import java.net.InetAddress;

public class PlayerMP extends Player {
    public InetAddress ipAddress;
    public int port;

    public PlayerMP(String username, Vector2 position, Entity.Direction direction, String map, InetAddress ipAddress, int port) {
        super(username, position, direction, map);
//        this.map = map;
//        this.ipAddress = ipAddress;
//        this.port = port;
    }
}