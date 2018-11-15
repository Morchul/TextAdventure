package com.mochul.testadventure;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {

    private AssetManager assetManager;

    public Skin defaultSkin;

    public Assets() {
        assetManager = new AssetManager();

        load();
    }

    private void load(){
        assetManager.load("skin/default/uiskin.json", Skin.class);
        assetManager.finishLoading();
        defaultSkin = assetManager.get("skin/default/uiskin.json", Skin.class);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
