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
import com.mochul.testadventure.DescriptionCreator;
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

        Place place = new Place(IDs.START_PLACE, "StartPlace", 0, 2, 0,0);
        Place place2 = new Place(IDs.SECOND_PLACE, "SecondPlace", 1, 2, 0, 1);
        final Place forest = new Place(IDs.FOREST, "Forest", 0, 3, 1, 2);
        LocationConnection.connectPlaces(place, place2, true);
        LocationConnection.connectPlaces(place, forest, true);
        LocationConnection.connectPlaces(place2, forest, true);
        place.setSouth(place.getPlaceToGo(0));
        place.setWest(place.getPlaceToGo(1));
        place2.setNorth(place2.getPlaceToGo(0));
        forest.setSouth(forest.getPlaceToGo(1));
        forest.setEast(forest.getPlaceToGo(0));

        final Place forestHouse = new Place(IDs.FOREST_HOUSE, "ForestHouse", 0, 1, 0, 0);
        LocationConnection.connectPlaces(forestHouse, forest, false);
        forestHouse.setParentLocation(forest);

        Position position = new Position(IDs.BEFORE_FOREST_HOUSE, "Door", 1, 0, forest, "a solid closed [Door] ") {
            @Override
            public boolean act(Player player, Command command, Output output) {
                if(command.action == Action.OPEN) {
                    output.printInfoText("You open the door");
                    setDescription("a solid open [Door] ");
                    forest.getPlaceToGo(2).passable = true;
                    forestHouse.getPlaceToGo(0).passable = true;
                    return true;
                } else if(command.action == Action.CLOSE) {
                    output.printInfoText("You close the door");
                    setDescription("a solid closed [Door] ");
                    forest.getPlaceToGo(2).passable = false;
                    forestHouse.getPlaceToGo(0).passable = false;
                    return true;
                }
                return false;
            }
        };
        position.addAction(Action.OPEN);
        forest.addPosition(position);

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
