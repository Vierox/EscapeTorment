package com.vieroxgames.escapetorment.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.vieroxgames.escapetorment.EscapeTorment;
import com.vieroxgames.escapetorment.Utils.Handler;
import com.vieroxgames.escapetorment.World.InitWorld;

/**
 * Created by Ethan on 4/25/2016.
 */
public class PlayScreen
    implements Screen
{
    private Handler handler;
    private InitWorld initWorld;
    private EscapeTorment game;

    public int value;

    public PlayScreen(Handler handler)
    {
        this.handler = handler;
        initWorld = new InitWorld(handler);
        value = 1;
    }

    @Override
    public void dispose()
    {
        initWorld.dispose();
    }

    @Override
    public void show()
    {}

    public void update(float delta)
    {
        handler.getInputManager().update();
        initWorld.update(delta);
    }

    @Override
    public void render(float delta)
    {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        initWorld.render();
    }

    @Override
    public void resize(int width, int height)
    { initWorld.resize(width, height); }

    @Override
    public void pause()
    {}

    @Override
    public void resume()
    {}

    @Override
    public void hide()
    {}

    //GETTERS & SETTERS
    public InitWorld getInitWorld()
    {
        return initWorld;
    }
}
