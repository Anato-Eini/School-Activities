package com.mygdx.game.Game2D.Network.Packets;

import com.mygdx.game.Game2D.Entities.Entity;
import com.mygdx.game.Game2D.Network.GameClient;
import com.mygdx.game.Game2D.Network.GameServer;

public class Packet02Move extends Packet {
    private String username;
    private float x, y;
    private Entity.Direction direction;
    private String map;

    public Packet02Move(byte[] data) {
        super(02);
        String[] dataArray = readData(data).split(",");
        this.username = dataArray[0];
        this.x = Float.parseFloat(dataArray[1]);
        this.y = Float.parseFloat(dataArray[2]);
        this.direction = Entity.Direction.valueOf(dataArray[3]);
        this.map = dataArray[4];
    }

    public Packet02Move(String username, float x, float y, Entity.Direction direction, String map) {
        super(02);
        this.username = username;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.map = map;
    }

    @Override
    public void writeData(GameClient client) {
        client.sendData(getData());
    }

    @Override
    public void writeData(GameServer server) {
        server.sendDataToAllClients(getData());
    }

    @Override
    public byte[] getData() {
        return ("02" + this.username + "," + this.x + "," + this.y + "," + this.direction + "," + this.map).getBytes();
    }

    public Entity.Direction getDirection() {
        return direction;
    }

    public String getMap() {
        return map;
    }

    public String getUsername() {
        return username;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }
}