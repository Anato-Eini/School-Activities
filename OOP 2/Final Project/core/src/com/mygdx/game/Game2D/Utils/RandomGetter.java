package com.mygdx.game.Game2D.Utils;

import com.badlogic.gdx.math.RandomXS128;
import com.mygdx.game.Game2D.Dialogues.Dialogues;
import com.mygdx.game.Game2D.Entities.Entity;
import com.mygdx.game.Game2D.Manager.TextureAtlasNPC;

import java.util.ArrayList;

public class RandomGetter {
    public Entity.Direction getRandomDirection() {
        RandomXS128 randomXS128 = new RandomXS128();
        return switch (Math.abs(randomXS128.nextInt() % 4)) {
            case 0 -> Entity.Direction.LEFT;
            case 1 -> Entity.Direction.RIGHT;
            case 2 -> Entity.Direction.UP;
            case 3 -> Entity.Direction.DOWN;
            default -> null;
        };
    }


    public static String getRandomTA_NPC(){
        RandomXS128 randomXS128 = new RandomXS128();
        return switch (Math.abs(randomXS128.nextInt() % TextureAtlasNPC.values().length)) {
            case 0 -> TextureAtlasNPC.NPC1.getPath();
            case 1 -> TextureAtlasNPC.NPC2.getPath();
            case 2 -> TextureAtlasNPC.NPC3.getPath();
            case 3 -> TextureAtlasNPC.NPC4.getPath();
            case 4 -> TextureAtlasNPC.NPC5.getPath();
            case 5 -> TextureAtlasNPC.NPC6.getPath();
            case 6 -> TextureAtlasNPC.NPC7.getPath();
            case 7 -> TextureAtlasNPC.NPC8.getPath();
            case 8 -> TextureAtlasNPC.NPC9.getPath();
            case 9 -> TextureAtlasNPC.NPC10.getPath();
            case 10 -> TextureAtlasNPC.NPC11.getPath();
            case 11 -> TextureAtlasNPC.NPC12.getPath();
            case 12 -> TextureAtlasNPC.NPC13.getPath();
            case 13 -> TextureAtlasNPC.NPC14.getPath();
            case 14 -> TextureAtlasNPC.NPC15.getPath();
            case 15 -> TextureAtlasNPC.NPC16.getPath();
            case 16 -> TextureAtlasNPC.NPC17.getPath();
            case 17 -> TextureAtlasNPC.NPC18.getPath();
            case 18, 19 -> TextureAtlasNPC.NPC20.getPath();
            default -> null;
        };
    }



    public static ArrayList<String> getRandomDialogues(){
        return switch (Math.abs(new RandomXS128().nextInt() % 3)){
            case 0 -> Dialogues.dialogues1;
            case 1 -> Dialogues.dialogues2;
            case 2 -> Dialogues.dialogues3;
            default -> null;
        };
    }
}
