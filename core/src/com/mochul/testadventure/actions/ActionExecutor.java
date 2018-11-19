package com.mochul.testadventure.actions;

import com.mochul.testadventure.DescriptionCreator;
import com.mochul.testadventure.place.Location;
import com.mochul.testadventure.place.PlaceConnection;
import com.mochul.testadventure.place.Place;
import com.mochul.testadventure.place.Position;
import com.mochul.testadventure.player.Player;
import com.mochul.testadventure.ui.Output;

public class ActionExecutor {

    private Output output;
    private Player player;

    public ActionExecutor(Output output, Player player) {
        this.output = output;
        this.player = player;
    }

    public void goAction(boolean successful, Location con, Command command){
        if(successful){
            if(con instanceof Place){
                output.printPlaceText("Go to " + command.subject);
                output.printPlaceText(DescriptionCreator.createDescription(con.getDescription(), con.getChildren()));
                ((Place)con).goToThisPlace(player, command, output);
            } else if(con instanceof Position){
                output.printPositionText("Go to " + command.subject);
                output.printPositionText(DescriptionCreator.createDescription(((Position) con).getDetailedDescription(), con.getChildren()));
                ((Position)con).act(player, command, output);
            }
            player.setCurrentPosition(con);

        } else {
            output.printInfoText("Can't go to " + command.subject);
        }
    }

    public void leafAction(){

    }
}
