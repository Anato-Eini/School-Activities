package com.mygdx.game.Game2D.World.Maps.Minigames.MINIGAME2;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public class Word {
    String text;
    Vector2 position;
    private int highlightedCharacters;
    float speed;
    public Word(String text, int highlightedCharacters) {
        this.text = text;
        this.position = new Vector2();
        this.highlightedCharacters = 0;
        speed = 10;
    }

    public void updatePosition(float delta)throws  Exception{
        position.y -=speed * delta;
        if(position.y < 0){
            throw new Exception("Word reached the bottom");
        }
    }

    public void setHighlightedCharacters(int highlightedCharacters) {
        this.highlightedCharacters = highlightedCharacters;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }


    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getHighlightedCharacters() {
        return highlightedCharacters;
    }

    @Override
    public String toString() {
        return text;
    }
}
