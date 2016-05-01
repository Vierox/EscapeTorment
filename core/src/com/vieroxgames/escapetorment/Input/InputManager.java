package com.vieroxgames.escapetorment.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.Arrays;

/**
 * Created by Ethan on 4/30/2016.
 */
public class InputManager
implements InputProcessor
{
    public boolean[] keys;
    public boolean m_left, m_right, jump;

    public InputManager()
    {
        keys = new boolean[256];
    }

    public void update()
    {
        m_left = keys[Input.Keys.LEFT];
        m_right = keys[Input.Keys.RIGHT];
        jump = keys[Input.Keys.SPACE];
    }

    @Override
    public boolean keyDown(int keycode) {
        keys[keycode] = true;
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        keys[keycode] = false;

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
    public boolean scrolled(int amount) {
        return false;
    }
}
