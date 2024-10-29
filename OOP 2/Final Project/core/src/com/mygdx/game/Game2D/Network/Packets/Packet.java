package com.mygdx.game.Game2D.Network.Packets;

import com.mygdx.game.Game2D.Network.GameClient;
import com.mygdx.game.Game2D.Network.GameServer;

public abstract class Packet {

    public static enum PacketTypes{
        INVALID(-1),
        LOGIN(00),
        DISCONNECT(01),
        MOVE(02),
        INPUT(03);

        private int packetId;
        private PacketTypes(int packetId){
            this.packetId = packetId;
        }

        public int getId(){
            return packetId;
        }
    }

    public byte PacketId;

    public Packet(int packetId){
        this.PacketId = (byte) packetId;
    }

    public abstract void writeData(GameClient client);

    public abstract void writeData(GameServer server);

    public abstract byte[] getData();

    public String readData(byte[] data){
        String message = new String(data).trim();
        return message.substring(2);
    }

    public static PacketTypes lookupPacket(String packetId){
        try{
            return lookupPackets(Integer.parseInt(packetId));
        }catch (NumberFormatException e){
            return PacketTypes.INVALID;
        }
    }

    public static PacketTypes lookupPackets(int id){
        for(PacketTypes p : PacketTypes.values()){
            if(p.getId() == id){
                return p;
            }
        }
        return PacketTypes.INVALID;
    }
}
