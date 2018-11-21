package com.mochul.testadventure.place;

import com.mochul.testadventure.actions.Command;
import com.mochul.testadventure.player.Player;
import com.mochul.testadventure.ui.Output;

public class PlacePosition extends Position {

    private Place otherPlace;

    public PlacePosition(long ID, String name, int countOfActions, int countOfObjects, Place parentPlace, Place otherPlace) {
        super(ID, name, countOfActions, countOfObjects, parentPlace);
        this.otherPlace = otherPlace;
    }

    public Place getPlace(){
        return otherPlace;
    }

    @Override
    public boolean act(Player player, Command command, Output output) {
        output.printPositionText(getDescription());
        return false;
    }
}
