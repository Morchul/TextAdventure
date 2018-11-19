package com.mochul.testadventure.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mochul.testadventure.Assets;
import com.mochul.testadventure.actions.Action;
import com.mochul.testadventure.actions.ActionHandler;
import com.mochul.testadventure.actions.Command;
import com.mochul.testadventure.place.*;
import com.mochul.testadventure.player.Player;
import com.mochul.testadventure.ui.ConsoleOutput;
import com.mochul.testadventure.ui.Output;

public class GameStage implements Screen, GameScreen {

    private Output output;

    private Assets assets;

    //Graphical
    private FitViewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage stage;
    private TextField textField;

    //Command handler
    private ActionHandler actionHandler;

    //Player
    private Player player;

    public GameStage() {
        assets = new Assets();

        camera = new OrthographicCamera(400,400);
        viewport = new FitViewport(400,400, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        output = new ConsoleOutput();
        player = new Player();

        actionHandler = new ActionHandler(output, player);

        Place place = new Place(IDs.START_PLACE, "StartPlace", 0, 1, 0);
        final Place forest = new Place(IDs.FOREST, "Forest", 0, 2, 1);
        PlaceConnection.connectPlaces(place, forest, true);
        place.setWest(place.getPlaceToGo(0));
        forest.setEast(forest.getPlaceToGo(0));

        forest.setDescription("You are in a dark forest. {}");

        final Place forestHouse = new Place(IDs.FOREST_HOUSE, "ForestHouse", 0, 1, 0);
        PlaceConnection.connectPlaces(forestHouse, forest, false);

        Position position = new Position(IDs.BEFORE_FOREST_HOUSE, "ForestHouseDoor", 2, 0, forest) {
            @Override
            public boolean act(Player player, Command command, Output output) {
                if(command.action == Action.OPEN) {
                    output.printInfoText("You open the door");
                    setDescription("There is a [ForestHouse] with a opened [Door]");
                    forest.getPlaceToGo(1).passable = true;
                    forestHouse.getPlaceToGo(0).passable = true;
                    return true;
                } else if(command.action == Action.CLOSE) {
                    output.printInfoText("You close the door");
                    setDescription("There is a [ForestHouse] with a closed [Door]");
                    forest.getPlaceToGo(1).passable = false;
                    forestHouse.getPlaceToGo(0).passable = false;
                    return true;
                }
                return false;
            }
        };
        position.setDescription("There is a [ForestHouse] with a closed [Door]");
        position.setDetailedDescription("This is a very solid Door");
        position.addAction(Action.OPEN);
        position.addAction(Action.CLOSE);

        setStartLocationOfPlayer(place);


    }

    public void setStartLocationOfPlayer(Location startPosition){
        player.setCurrentPosition(startPosition);

    }

    @Override
    public void show() {

        Table table = new Table(assets.defaultSkin);
        table.setFillParent(true);
        table.setDebug(true);

        textField = new TextField("", assets.defaultSkin);
        textField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                if(c == '\r' || c == '\n'){
                    executeCommand();
                    textField.setText("");
                }
            }
        });

        table.add(textField).bottom().growX();

        stage.addActor(table);
    }

    public void executeCommand(){
        Command command = new Command(textField.getText());
        if(command.validCommand) {
            actionHandler.act(command);
        }
    }

    @Override
    public void render(float delta) {

        stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        assets.dispose();
    }

    @Override
    public Output getInformOutput() {
        return output;
    }

    @Override
    public Output getMainOutput() {
        return output;
    }

}
