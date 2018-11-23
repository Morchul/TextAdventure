package com.mochul.testadventure.player;

import com.mochul.testadventure.place.Location;
import com.mochul.testadventure.place.Place;
import com.mochul.testadventure.place.Position;

public class Player {

    private Location currentLocation;

    public Player(){

    }

    public Location getCurrentLocation() {
        return currentLocation;
    }
    public Place getCurrentPlace(){
        Place p = null;
        if(currentLocation instanceof Place){
            p = (Place)currentLocation;
        } else if(currentLocation instanceof Position){
            p = ((Position)currentLocation).getParentPlace();
        }
        return p;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

}
