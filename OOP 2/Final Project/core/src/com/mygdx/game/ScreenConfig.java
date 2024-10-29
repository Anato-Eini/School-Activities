package com.mygdx.game;

public class ScreenConfig {
    public static final int originalTileSize = 32;
    public static final int scale = 1;

    public static final int tileSize = originalTileSize * scale;
    public static final int maxScreenCol = 48;
    public static final int maxScreenRow = 36;
    public static final int screenWidth = tileSize * maxScreenCol;
    public static final int screenHeight = tileSize * maxScreenRow;
    public static final float PPM = 1.0F;
}
