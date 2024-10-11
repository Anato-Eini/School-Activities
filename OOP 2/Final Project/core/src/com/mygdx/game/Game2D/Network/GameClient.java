package com.mygdx.game.Game2D.Network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game2D.Entities.Entity;
import com.mygdx.game.Game2D.Entities.PlayerMP;
import com.mygdx.game.Game2D.Network.Packets.Packet;
import com.mygdx.game.Game2D.Network.Packets.Packet00Login;
import com.mygdx.game.Game2D.Network.Packets.Packet01Disconnect;
import com.mygdx.game.Game2D.Network.Packets.Packet02Move;
import com.mygdx.game.Game2D.Screens.GameScreen;
import com.mygdx.game.Game2D.World.GameMap;
import com.mygdx.game.Game2D.World.MapManager;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Objects;

import static com.mygdx.game.Game2D.World.MapManager.otherPlayers;

public class GameClient extends Thread {
    private InetAddress ipAddress;
    private DatagramSocket socket;

    public GameClient(String ipAddress) {
        try {
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
                this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parsePacket(byte[] data, InetAddress address, int port) {
        String message = new String(data).trim();
        Packet.PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
        Packet packet;
        System.out.println(message);
        switch (type){
            default:
            case INVALID:
                break;
            case LOGIN:
                packet = new Packet00Login(data);
                System.out.println(((Packet00Login)packet).getUsername());
                if(Objects.equals(GameScreen.player.getUsername(), ((Packet00Login) packet).getUsername()))
                    return;

                System.out.println("["+ Arrays.toString(address.getAddress()) + ": " + port + "]" +
                        ((Packet00Login) packet).getUsername() + " has joined the game");

                if (!otherPlayers.containsKey(((Packet00Login) packet).getUsername())) {
                    PlayerMP player = new PlayerMP(((Packet00Login) packet).getUsername(), new Vector2(((Packet00Login) packet).getX(), ((Packet00Login) packet).getY()), ((Packet00Login) packet).getDirection(), ((Packet00Login) packet).getMap(), address, port);
                    otherPlayers.put(((Packet00Login) packet).getUsername(), player);
//                    player.setCollision(player.getX(), player.getY());
                }
                break;
            case DISCONNECT:
                packet = new Packet01Disconnect(data);
//                System.out.println("[" + address.getHostAddress() + ":" + port + "] "
//                        + ((Packet01Disconnect) packet).getUsername() + " has left the world...");
//                GameScreen.otherPlayers.remove(((Packet01Disconnect) packet).getUsername());
                break;
            case MOVE:
//                packet = new Packet02Move(data);
//
//                if(Objects.equals(((Packet02Move) packet).getUsername(), GameScreen.player.getUsername()))
//                    return;
//
//                String username = ((Packet02Move) packet).getUsername();
//
//                PlayerMP player = otherPlayers.get(username);
//                player.setDirection(((Packet02Move) packet).getDirection());
//
//                switch (((Packet02Move) packet).getDirection()){
//                    case LEFT -> {
//                        player.boxBody.applyLinearImpulse(new Vector2(-player.speed, 0), player.boxBody.getWorldCenter(), true);
//                    }
//                    case RIGHT -> {
//                        player.boxBody.applyLinearImpulse(new Vector2(player.speed, 0), player.boxBody.getWorldCenter(), true);
//                    }
//                    case UP -> {
//                        player.boxBody.applyLinearImpulse(new Vector2(0, player.speed), player.boxBody.getWorldCenter(), true);
//                    }
//                    case DOWN -> {
//                        player.boxBody.applyLinearImpulse(new Vector2(0, -player.speed), player.boxBody.getWorldCenter(), true);
//                    }
//                }
//                player.setIsMoving(true);
//                player.setPosition(new Vector2(((Packet02Move) packet).getX(), ((Packet02Move) packet).getY()));
                break;
        }
    }

    public void sendData(byte[] data){
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 2787);
        try {
            socket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}