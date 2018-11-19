package com.mochul.testadventure.actions;

import com.mochul.testadventure.place.Location;
import com.mochul.testadventure.place.PlaceConnection;
import com.mochul.testadventure.place.Place;
import com.mochul.testadventure.place.Position;

public class ActionInterpreter {

    public ActionInterpreter(){

    }

    public Location interpretLeaveAction(Command command, Location currentPlace){
        Location connection;
        Place p = null;
        if(currentPlace instanceof Position){
            p = ((Position)currentPlace).getParentPlace();
        } else if(currentPlace instanceof Place){
            p = ((Place) currentPlace);
        }

        if(p == null){
            return null;
        }

        connection = p.canGoTo(command.subject);
        return connection; //TODO leave action
    }

    public Location interpretGoAction(Command command, Location currentPlace){
        Place p = null;
        Location connection;

        if(currentPlace instanceof Position){
            p = ((Position)currentPlace).getParentPlace();
        } else if(currentPlace instanceof Place){
            p = ((Place) currentPlace);
        }

        if(p == null){
            return null;
        }

        if(command.subject.equalsIgnoreCase("north")){
            connection = p.getNorth().getPlace();
        } else if(command.subject.equalsIgnoreCase("south")){
            connection = p.getSouth().getPlace();
        } else if(command.subject.equalsIgnoreCase("east")){
            connection = p.getEast().getPlace();
        } else if(command.subject.equalsIgnoreCase("west")){
            connection = p.getWest().getPlace();
        } else {
            connection = p.canGoTo(command.subject);
        }
        return connection;
    }

}
