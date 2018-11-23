package com.mochul.testadventure.actions;

import com.mochul.testadventure.place.Place;
import com.mochul.testadventure.place.PlacePosition;
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

    public boolean executeGoAction(Position nextPosition, Command command){
        if(nextPosition instanceof PlacePosition){
            nextPosition.goToThisLocation(output);

            if(nextPosition.passable){
                Place p = ((PlacePosition) nextPosition).getPlace();
                p.goToThisLocation(output);
                player.setCurrentPosition(p);
                return true;
            } else {
                output.printInfoText("Can't go to " + command.subject);
            }

        } else {
            if(nextPosition.passable) {
                output.printPositionText(nextPosition.getDetailedDescription());
                nextPosition.goToThisLocation(output);
                player.setCurrentPosition(nextPosition);
                return true;
            } else {
                output.printInfoText("Can't go to " + command.subject);
            }
        }
        return false;
    }


}
