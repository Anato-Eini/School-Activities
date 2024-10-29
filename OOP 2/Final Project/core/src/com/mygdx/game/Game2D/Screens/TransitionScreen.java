package com.mygdx.game.Game2D.Screens;

import com.mygdx.game.Game2D.Game2D;
import com.mygdx.game.Game2D.Screens.transition.effects.TransitionEffect;

import java.util.ArrayList;

public class TransitionScreen extends BaseScreen {
    private Game2D game;
    private BaseScreen current;
    private BaseScreen next;

    int currentTransitionEffect;
    ArrayList<TransitionEffect> transitionEffects;

    TransitionScreen(Game2D game, BaseScreen current, BaseScreen next, ArrayList<TransitionEffect> transitionEffects) {
        super(game);
        this.current = current;
        this.next = next;
        this.transitionEffects = transitionEffects;
        this.currentTransitionEffect = 0;
        this.game = game;
    }

    @Override
    public void render(float delta) {
//        if (next.getClass() != OptionScreen.class) {
//            Arrays.stream(AudioObserver.AudioTypeEvent.values())
//                    .filter(e -> e.equals(current.getMusicTheme()))
//                    .findFirst()
//                    .filter(a -> !a.equals(next.getMusicTheme()))
//                    .ifPresent(a -> notify(AudioObserver.AudioCommand.MUSIC_STOP, a));
//        }

        if (currentTransitionEffect >= transitionEffects.size()) {
            current.dispose();
            game.setScreen(next);
            return;
        }

        transitionEffects.get(currentTransitionEffect).update(delta);
        transitionEffects.get(currentTransitionEffect).render(current, next);

        if (transitionEffects.get(currentTransitionEffect).isFinished()) {
            currentTransitionEffect++;
        }

    }

    @Override
    public void show() {
        // Nothing
    }

    @Override
    public void resize(int width, int height) {
        // Nothing
    }

    @Override
    public void pause() {
        // Nothing
    }

    @Override
    public void resume() {
        // Nothing
    }

    @Override
    public void hide() {
        // Nothing
    }

    @Override
    public void dispose() {
        current.dispose();
        next.dispose();
    }
}
