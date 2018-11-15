package com.mochul.testadventure.place;

public class LocationConnection {

    private final Location otherPlace;
    public boolean passable;

    public LocationConnection(Location otherPlace) {
        this(otherPlace, true);
    }
    public LocationConnection(Location otherPlace, boolean passable){
        this.otherPlace = otherPlace;
        this.passable = passable;
    }

    public Location getPlace() {
        return otherPlace;
    }

    public static void connectPlaces(Place place1, Place place2, boolean passable){
        place1.addPlaceToGo(new LocationConnection(place2, passable));
        place2.addPlaceToGo(new LocationConnection(place1, passable));
    }
}
