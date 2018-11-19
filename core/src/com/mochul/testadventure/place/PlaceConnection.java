package com.mochul.testadventure.place;

public class PlaceConnection implements Everything {

    private final Place otherPlace;
    public boolean passable;
    private final long ID;
    private String description;
    private String name;

    public PlaceConnection(long ID, Place otherPlace) {
        this(ID, "", otherPlace, true);
    }
    private PlaceConnection(long ID, String name, Place otherPlace, boolean passable){
        this.ID = ID;
        this.otherPlace = otherPlace;
        this.passable = passable;
        this.name = name;
    }

    public Place getPlace() {
        return otherPlace;
    }

    public static void connectPlaces(long ID, String name, Place place1, Place place2, boolean passable){
        place1.addPlaceToGo(new PlaceConnection(ID, name, place2, passable));
        place2.addPlaceToGo(new PlaceConnection(ID, name, place1, passable));
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getID() {
        return ID;
    }
}
