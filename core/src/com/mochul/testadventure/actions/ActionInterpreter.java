package com.mochul.testadventure.actions;

import com.mochul.testadventure.place.Location;
import com.mochul.testadventure.place.LocationConnection;
import com.mochul.testadventure.place.Place;
import com.mochul.testadventure.place.Position;

public class ActionInterpreter {

    public ActionInterpreter(){

    }

    public LocationConnection interpretLeaveAction(Command command, Location currentPlace){
        LocationConnection connection;
        Place p = null;
        if(currentPlace instanceof Position){
            p = currentPlace.getParentLocation();
        } else if(currentPlace instanceof Place){
            p = ((Place) currentPlace);
        }

        if(p == null){
            return null;
        }

        connection = p.canGoTo(command.subject);
        return connection; //TODO leave action
    }

    public LocationConnection interpretGoAction(Command command, Location currentPlace){
        Place p = null;
        LocationConnection connection;

        if(currentPlace instanceof Position){
            p = currentPlace.getParentLocation();
        } else if(currentPlace instanceof Place){
            p = ((Place) currentPlace);
        }

        if(p == null){
            return null;
        }

        if(command.subject.equalsIgnoreCase("north")){
            connection = p.getNorth();
        } else if(command.subject.equalsIgnoreCase("south")){
            connection = p.getSouth();
        } else if(command.subject.equalsIgnoreCase("east")){
            connection = p.getEast();
        } else if(command.subject.equalsIgnoreCase("west")){
            connection = p.getWest();
        } else {
            connection = p.canGoTo(command.subject);
        }
        return connection;
    }

}
