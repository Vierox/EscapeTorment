package com.vieroxgames.escapetorment.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vieroxgames.escapetorment.EscapeTorment;
import com.vieroxgames.escapetorment.References;
import com.vieroxgames.escapetorment.World.InitWorld;

/**
 * Created by Ethan on 4/25/2016.
 */
public class PlayScreen
    implements Screen
{
    private EscapeTorment game;
    private InitWorld world;


    public PlayScreen(EscapeTorment game)
    {
        this.game = game;

        world = new InitWorld(game);


    }

    @Override
    public void dispose()
    {
        world.dispose();
    }

    @Override
    public void show()
    {}

    public void update(float delta)
    {
        world.update(delta);
    }

    @Override
    public void render(float delta)
    {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.render();
    }

    @Override
    public void resize(int width, int height)
    { world.resize(width, height); }

    @Override
    public void pause()
    {}

    @Override
    public void resume()
    {}

    @Override
    public void hide()
    {}
}
