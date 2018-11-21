package com.mochul.testadventure.actions;

import com.mochul.testadventure.place.Location;
import com.mochul.testadventure.place.Place;
import com.mochul.testadventure.place.PlacePosition;
import com.mochul.testadventure.place.Position;
import com.mochul.testadventure.player.Player;
import com.mochul.testadventure.ui.Output;

public class ActionInterpreter {

    private Output output;
    private Player player;

    public ActionInterpreter(Output output, Player player) {
        this.output= output;
        this.player = player;
    }

    public Position goAction(Command command){
        Location currentLocation = player.getCurrentPosition();
        Place p = null;

        if(currentLocation instanceof Place){
            p = (Place)currentLocation;
        } else if(currentLocation instanceof Position){
            p = ((Position)currentLocation).getParentPlace();
        }

        if(p == null) return null;

        Position pos;
        if(command.subject.equalsIgnoreCase("north")) pos = p.getNorth();
        else if(command.subject.equalsIgnoreCase("south")) pos = p.getSouth();
        else if(command.subject.equalsIgnoreCase("west")) pos = p.getWest();
        else if(command.subject.equalsIgnoreCase("east")) pos = p.getEast();
        else pos = p.canGoTo(command.subject);

        if(pos == null) return null;
        return pos;
    }
}
