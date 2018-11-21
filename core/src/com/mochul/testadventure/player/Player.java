package com.mochul.testadventure.player;

import com.mochul.testadventure.place.Location;

public class Player {

    private Location currentPosition;

    public Player(){

    }

    public Location getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Location currentPosition) {
        this.currentPosition = currentPosition;
    }

}
