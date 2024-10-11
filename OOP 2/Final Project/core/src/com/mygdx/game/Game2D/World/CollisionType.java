package com.mygdx.game.Game2D.World;

public enum CollisionType {
    PLAYER((short) 0x0001),
    NPC((short) 0x0002),
    WALL((short) 0x0004),
    EXIT((short) 0x0008);
    private final short value;

    CollisionType(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}
