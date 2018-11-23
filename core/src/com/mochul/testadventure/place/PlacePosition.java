package com.mochul.testadventure.place;

public class PlacePosition extends Position {

    private Place otherPlace;

    public PlacePosition(long ID, String name, int countOfActions, int countOfObjects, Place parentPlace, Place otherPlace) {
        super(ID, name, countOfActions, countOfObjects, parentPlace);
        this.otherPlace = otherPlace;
    }

    public Place getPlace(){
        return otherPlace;
    }
}
