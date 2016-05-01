package com.vieroxgames.escapetorment.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Ethan on 5/1/2016.
 */
public class GameCamera
{
    private OrthographicCamera camera;

    public GameCamera()
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }

    public void update()
    {

    }

    public void render()
    {

    }

    // GETTERS & SETTERS
    public OrthographicCamera getCamera()
    {
        return camera;
    }
}
