package com.vieroxgames.escapetorment.Utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vieroxgames.escapetorment.EscapeTorment;
import com.vieroxgames.escapetorment.Input.InputManager;
import com.vieroxgames.escapetorment.Screens.PlayScreen;

/**
 * Created by Ethan on 4/30/2016.
 */
public class Handler
{
    private EscapeTorment game;

    public Handler(EscapeTorment game)
    {
        this.game = game;
    }

    // GETTERS & SETTERS
    public SpriteBatch getBatch()
    {
        return game.batch;
    }

    public InputManager getInputManager()
    {
       return game.getInputManager();
    }

    public PlayScreen getPlayScreen()
    {
        return game.playScreen;
    }
}
