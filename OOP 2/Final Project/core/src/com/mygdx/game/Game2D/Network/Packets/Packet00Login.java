package com.mygdx.game.Game2D.Network.Packets;

import com.mygdx.game.Game2D.Entities.Entity;
import com.mygdx.game.Game2D.Network.GameClient;
import com.mygdx.game.Game2D.Network.GameServer;
import com.mygdx.game.Game2D.World.GameMap;

public class Packet00Login extends Packet {
    private String username;
    private float x, y;
    private Entity.Direction direction;
    private String map;

    public Packet00Login(byte[] data){
        super(00);
        String[] dataArray = readData(data).split(",");
        this.username = dataArray[0];
        this.x = Float.parseFloat(dataArray[1]);
        this.y = Float.parseFloat(dataArray[2]);
        this.direction = Entity.Direction.valueOf(dataArray[3]);
        this.map = dataArray[4];
    }

    public Packet00Login(String username, float x, float y, Entity.Direction direction, String map){
        super(00);
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
//        server.sendDataToAllClients(getData());
    }

    @Override
    public byte[] getData() {
        return ("00" + this.username + "," + getX() + "," + getY() + "," + getDirection() + "," + getMap()).getBytes();
    }

    public String getUsername(){
        return username;
    }

    public float getX(){
        return x;
    }

    public Entity.Direction getDirection() {
        return direction;
    }

    public String getMap() {
        return map;
    }

    public float getY(){
        return y;
    }
}