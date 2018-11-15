package com.mochul.testadventure.actions;

import com.mochul.testadventure.DescriptionCreator;
import com.mochul.testadventure.place.LocationConnection;
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

    public void goAction(boolean successful, LocationConnection con, Command command){
        if(successful){
            if(con.getPlace() instanceof Place){
                output.printPlaceText("Go to " + command.subject);
                output.printPlaceText(DescriptionCreator.createDescription(con.getPlace().getDescription(), con.getPlace().getChildren()));
                ((Place)con.getPlace()).goToThisPlace(player, command, output);
            } else if(con.getPlace() instanceof Position){
                output.printPositionText("Go to " + command.subject);
                output.printPositionText(DescriptionCreator.createDescription(con.getPlace().getDescription(), con.getPlace().getChildren()));
                ((Position)con.getPlace()).act(player, command, output);
            }
            player.setCurrentPosition(con.getPlace());

        } else {
            output.printInfoText("Can't go to " + command.subject);
        }
    }

    public void leafAction(){

    }
}
