package com.vieroxgames.escapetorment;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vieroxgames.escapetorment.Input.InputManager;
import com.vieroxgames.escapetorment.Screens.PlayScreen;
import com.vieroxgames.escapetorment.Utils.Handler;

public class EscapeTorment
extends Game
{
	private InputManager inputManager;
	private Handler handler;
	public PlayScreen playScreen;

	public SpriteBatch batch;

	@Override
	public void create () {
		inputManager = new InputManager();

		handler = new Handler(this);

		playScreen = new PlayScreen(handler);

		batch = new SpriteBatch();
		setScreen(playScreen);
		Gdx.input.setInputProcessor(inputManager);
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}

	@Override
	public void render () {	super.render(); }

	// GETTERS & SETTERS
	public InputManager getInputManager()
	{
		return inputManager;
	}

	public PlayScreen getPlayScreen()
	{
		return playScreen;
	}

}
