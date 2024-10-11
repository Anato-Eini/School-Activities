package com.mygdx.game.Game2D.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.Game2D.Entities.NPC.NPC;
import com.mygdx.game.Game2D.Manager.ResourceManager;
import com.mygdx.game.ScreenConfig;

import java.util.ArrayList;

import static com.mygdx.game.Game2D.Game2D.batch;
import static com.mygdx.game.Game2D.Screens.GameScreen.player;

public class Entity implements InputProcessor {
    public Vector2 position;
    public Sprite sprite;
    public TextureRegion frame;
    public float animationStateTime = 0F;

    public String map;
    public float speed;
    public Direction direction;
    public State state = State.IDLE;

    public Body boxBody;
    public FixtureDef fixtureDef = new FixtureDef();
    protected ArrayList<String> dialogues;
    protected ArrayList<String> dialoguesToNPC;
    public int indexDia;

    public Entity(){
        dialogues = new ArrayList<>();
        dialoguesToNPC = new ArrayList<>();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getSizeDialogues(){
        return dialogues.size();
    }

    public enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT, STAY;

        public static Direction fromString(String direction) {
            if (direction != null) {
                try {
                    return Direction.valueOf(direction.toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid direction: " + direction);
                }
            }
            return null;
        }
    }

    //TODO USE THIS
    public enum State {
        IDLE,
        WALKING
    }

    public void setState(Entity.State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }

    private float timePerCharacter = 0.05f; // Time (in seconds) between each character display
    private float elapsedTime = 0f;
    private int charactersToDisplay = 0;
    private String dialogue;
    public boolean finishedDialogue = true;

    private Direction defaultDirection;
    private boolean defaultDirectionSet = false;
    private boolean previousSetToStay;
    private boolean setPreviousToStay = false;

    public void doDialogue(){
        if(!finishedDialogue) {
            batch.begin();
            BitmapFont font = ResourceManager.pixel10;
            GlyphLayout layout = new GlyphLayout(font, dialogue);
            float textX = sprite.getX() + (sprite.getWidth() - layout.width) / 2;
            float textY = sprite.getY() + sprite.getHeight() + 20;

            float bgWidth = layout.width + 10;
            float bgHeight = layout.height + 10;

            batch.setColor(1, 1, 1, 0.9f);
            Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            pixmap.setColor(1, 1, 1, 1);
            pixmap.drawPixel(0, 0);
            Texture texture = new Texture(pixmap);
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            texture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
            batch.draw(texture, textX - 5, textY - 5, bgWidth, bgHeight);

            font.setColor(0, 0, 0, 1);

            String partialText = dialogue.substring(0, charactersToDisplay);
            font.draw(batch, partialText, textX, textY + 5);

            if (elapsedTime < dialogue.length() * timePerCharacter + 3) {
                elapsedTime += Gdx.graphics.getDeltaTime();
                charactersToDisplay = (int) (elapsedTime / timePerCharacter);
                if(charactersToDisplay > dialogue.length())
                    charactersToDisplay = dialogue.length();

                if(this instanceof NPC) {
                    if(!defaultDirectionSet) {
                        defaultDirection = direction;
                        defaultDirectionSet = true;
                    }
                    if(!setPreviousToStay) {
                        setPreviousToStay = true;
                        previousSetToStay = ((NPC)this).setToStay;
                    }
                    ((NPC)this).setToStay = true;
                    redirection((NPC) this);
                }
            }else {
                finishedDialogue = true;
                if(this instanceof NPC){
                    ((NPC) this).setToStay = previousSetToStay;
                    direction = defaultDirection;

                    setPreviousToStay = defaultDirectionSet = false;

                    synchronized (this){
                        notifyAll();
                    }
                }
            }

            batch.setColor(Color.WHITE);
            font.setColor(Color.WHITE);
            batch.end();
        }
    }

    public void redirection(NPC npc){
        float npcX = npc.getPosition().x, npcY = npc.getPosition().y;
        float playerX = player.getPosition().x, playerY = player.getPosition().y;

        if(playerY > npcY) {
            if(npc.direction == Direction.UP)
                return;
            npc.direction = Direction.UP;
        }else if(playerX < npcX) {
            if(npc.direction == Direction.LEFT)
                return;
            npc.direction = Direction.LEFT;
        }else if(playerX > npcX) {
            if(npc.direction == Direction.RIGHT)
                return;
            npc.direction = Direction.RIGHT;
        }else{
            if(npc.direction == Direction.DOWN)
                return;
            npc.direction = Direction.DOWN;
        }

        npc.setToStay();
    }

    public Entity setSequentialDialogue(){
        if(indexDia == dialogues.size())
            indexDia = 0;

        setDialogue(dialoguesToNPC.get(indexDia));

        return this;
    }

    public Entity setDialogue(){
        if(dialogues.isEmpty())
            return this;
        return setDialogue(dialogues.get(Math.abs(new RandomXS128().nextInt() % dialogues.size())));
    }

    public void setDirection(Entity.Direction direction){
        this.direction = direction;
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }

    public Entity setDialogue(int index){
        return setDialogue(dialogues.get(index));
    }

    public Entity setDialogue(String dialogue){
        this.dialogue = dialogue;
        finishedDialogue = false;
        charactersToDisplay = 0;
        elapsedTime = 0f;

        return this;
    }

    public void setPosition(Vector2 position){
        this.position.set(position);
        this.boxBody.setTransform(new Vector2(position.x * ScreenConfig.originalTileSize, position.y * ScreenConfig.originalTileSize), 0);
    }


    public Vector2 getPosition(){
        return position;
    }

    public void stopDialogue(){
        finishedDialogue = true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return screenX > sprite.getX() && screenX < sprite.getX() + sprite.getWidth() &&
                screenY > sprite.getY() && screenY < sprite.getY() + sprite.getHeight();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}