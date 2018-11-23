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

    public void executeLeaveAction(PlacePosition pp, Command command){
        if(pp.passable){
            pp.goToThisLocation(output);
            Place p = pp.getPlace();
            p.goToThisLocation(output);
            player.setCurrentLocation(p);
        } else {
            output.printInfoText("Can't leave " + command.subject);
        }
    }

    public boolean executeGoAction(Position nextPosition, Command command){
        if(nextPosition instanceof PlacePosition){

            if(nextPosition.passable){
                nextPosition.goToThisLocation(output);
                Place p = ((PlacePosition) nextPosition).getPlace();
                p.goToThisLocation(output);
                player.setCurrentLocation(p);
                return true;
            } else {
                output.printInfoText("Can't go to " + command.subject);
            }

        } else {
            if(nextPosition.passable) {
                nextPosition.goToThisLocation(output);
                player.setCurrentLocation(nextPosition);
                return true;
            } else {
                output.printInfoText("Can't go to " + command.subject);
            }
        }
        return false;
    }


}
