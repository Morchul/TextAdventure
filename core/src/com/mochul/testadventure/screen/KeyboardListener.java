package com.mochul.testadventure.screen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class KeyboardListener implements InputProcessor {

    private boolean executeCommand;
    private final GameStage game;

    public KeyboardListener(GameStage game) {
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(executeCommand && keycode == Input.Keys.ENTER){

            game.executeCommand();

            executeCommand = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        executeCommand = true;
        return false;
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
