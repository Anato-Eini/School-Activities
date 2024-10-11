package com.mygdx.game.Game2D.Entities.NPC;

public class MainNPC extends NPC {

    public MainNPC(int length) {
        super(length);
        setTextureAtlas("atlas/leo.atlas");
    }

    @Override
    public void update() {
        move();
    }
}
