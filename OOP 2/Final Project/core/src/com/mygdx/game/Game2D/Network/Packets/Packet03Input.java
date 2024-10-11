package com.mygdx.game.Game2D.Network.Packets;//package org.example.net.Packets;
//
//import org.example.net.GameServer;
//import org.example.net.GameClient;
//
//public class Packet03Input extends Packet{
//    String input;
//    long time;
//
//    public Packet03Input(byte[] data){
//        super(03);
//        String[] dataArray = readData(data).split(",");
//        this.time = Long.parseLong(dataArray[0]);
//        this.input = dataArray[1];
//    }
//
//    public Packet03Input(String input, long time) {
//        super(03);
//        this.input = input;
//        this.time = time;
//    }
//
//    @Override
//    public void writeData(GameClient client) {
//        client.sendData(getData());
//    }
//
//    @Override
//    public void writeData(GameServer server) {
//        server.sendDataToAllClients(getData());
//    }
//
//    @Override
//    public byte[] getData() {
//        return ("03" + this.time + "," + this.input).getBytes();
//    }
//
//    public long getTime() {
//        return time;
//    }
//
//    public String getInput(){
//        return input;
//    }
//}
