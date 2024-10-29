package com.mygdx.game.Game2D.Manager;

public enum TextureAtlasNPC {
    NPC1("atlas/NPC/NPC1/NPC1.atlas"),
    NPC2("atlas/NPC/NPC2/npc2.atlas"),
    NPC3("atlas/NPC/NPC3/npc3.atlas"),
    NPC4("atlas/NPC/NPC4/npc4.atlas"),
    NPC5("atlas/NPC/NPC5/npc5.atlas"),
    NPC6("atlas/NPC/NPC6/npc6.atlas"),
    NPC7("atlas/NPC/NPC7/npc7.atlas"),
    NPC8("atlas/NPC/NPC8/npc8.atlas"),
    NPC9("atlas/NPC/NPC9/npc9.atlas"),
    NPC10("atlas/NPC/NPC10/npc10.atlas"),
    NPC11("atlas/NPC/NPC11/npc11.atlas"),
    NPC12("atlas/NPC/NPC12/npc12.atlas"),
    NPC13("atlas/NPC/NPC13/npc13.atlas"),
    NPC14("atlas/NPC/NPC14/npc14.atlas"),
    NPC15("atlas/NPC/NPC15/npc15.atlas"),
    NPC16("atlas/NPC/NPC16/npc16.atlas"),
    NPC17("atlas/NPC/NPC17/npc17.atlas"),
    NPC18("atlas/NPC/NPC18/npc18.atlas"),
    NPC19("atlas/NPC/NPC19/npc19.atlas"),
    NPC20("atlas/NPC/NPC20/npc20.atlas");

    private final String path;

    TextureAtlasNPC(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String toString(){
        return path;
    }
}
