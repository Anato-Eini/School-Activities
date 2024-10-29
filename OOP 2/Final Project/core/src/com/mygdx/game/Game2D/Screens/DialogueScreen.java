package com.mygdx.game.Game2D.Screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.Game2D.Game2D;
import com.mygdx.game.Game2D.Manager.ResourceManager;

/*TODO Implement transition screen saying "School is finally starting. Im a Wildcat now" at first day.
* Function create dialogue will be repeatedly called when creating new dialogue screen
*/
public class DialogueScreen extends BaseScreen{
    Stage newStage;

    public DialogueScreen(Game2D game, BaseScreen previousScreen, ResourceManager resourceManager) {
        super(game);

    }

    public void createDialogue(String dialogue){
        newStage = new Stage();
    }
}
