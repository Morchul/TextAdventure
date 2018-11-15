package com.mochul.testadventure;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mochul.testadventure.screen.GameStage;

public class TextAdventure extends Game {

	@Override
	public void create () {

		setScreen(new GameStage());

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		super.render();
	}
}
