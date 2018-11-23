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
import com.mochul.testadventure.object.Item;
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

        Place place = new Place(IDs.START_PLACE, "StartPlace", 0, 1);
        place.setDescription("This is the start place, here is {0}");
        Place forest = new Place(IDs.FOREST, "Forest", 0, 2);
        forest.setDescription("You are in a dark forest there is a {1}");

        PlacePosition conToForest = new PlacePosition(IDs.CON_START_PLACE_FOREST, "Forest", 0, 0, place, forest);
        conToForest.setDescription("A street go to a [forest]");
        conToForest.setDetailedDescription("You follow the street to a forest");
        PlacePosition conToStartPlace = new PlacePosition(IDs.CON_FOREST_START_PLACE, "StartPlace", 0, 0, forest, place);
        conToStartPlace.setDetailedDescription("You follow the street to east");

        place.setWest(conToForest);
        forest.setEast(conToStartPlace);

        Place forestHouse = new Place(IDs.FOREST_HOUSE, "ForestHouse", 1, 2);
        forestHouse.setDescription("In the house it's warm.");

        final PlacePosition conToHouse = new PlacePosition(4, "ForestHouse", 0,1,forest, forestHouse);
        conToHouse.setDescription("[ForestHouse] with {0}");
        conToHouse.setDetailedDescription("You go in the foresthouse");
        final PlacePosition conFromHouseToForest = new PlacePosition(5, "Forest", 0, 1, forestHouse, forest);
        conToHouse.passable = false;
        conFromHouseToForest.passable = false;

        Item forestHouseDoor = new Item(IDs.FOREST_HOUSE_DOOR, "Door", 2, "a solid closed wood [door]") {
            @Override
            public boolean act(Player player, Command command, Output output) {
                if(command.action == Action.OPEN) {
                    output.printInfoText("You open the door");
                    conToHouse.passable = true;
                    conFromHouseToForest.passable = true;
                    setDescription("a solid open wood [door]");
                    return true;
                } else if(command.action == Action.CLOSE){
                    output.printInfoText("You close the door");
                    conToHouse.passable = false;
                    conFromHouseToForest.passable = false;
                    setDescription("a solid closed wood [door]");
                    return true;
                }
                return false;
            }
        };
        forestHouseDoor.addAction(Action.OPEN);
        forestHouseDoor.addAction(Action.CLOSE);

        conToHouse.addItem(forestHouseDoor);
        conFromHouseToForest.addItem(forestHouseDoor);

        Position table = new Position(6, "Table", 0, 2, forestHouse);

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
            actionHandler.handle(command);
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
