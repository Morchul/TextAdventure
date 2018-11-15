package com.mochul.testadventure.place;

import com.mochul.testadventure.actions.Action;
import com.mochul.testadventure.actions.CanDoAction;
import com.mochul.testadventure.actions.Command;
import com.mochul.testadventure.object.Everything;
import com.mochul.testadventure.object.Item;
import com.mochul.testadventure.player.Player;
import com.mochul.testadventure.ui.Output;

public class Place implements Location {

    private long ID;
    private String name;
    private String description;

    private Place parentLocation;

    private Item[] items;
    private int itemIndex = 0;

    private Position[] positions;
    private int positionIndex = 0;

    private LocationConnection[] placesToGo;
    private int placesToGoIndex = 0;

    private Everything[] children;
    private int childIndex = 0;

    private LocationConnection north;
    private LocationConnection south;
    private LocationConnection west;
    private LocationConnection east;

    public Place(long ID, String name, int countOfItems, int countOfPlacesToGo, int countOfPositions, int countOfChildren) {
        this.ID = ID;
        this.items = new Item[countOfItems];
        this.placesToGo = new LocationConnection[countOfPlacesToGo];
        this.positions = new Position[countOfPositions];
        this.children = new Everything[countOfChildren];
        this.name = name;
        this.description = "";
    }

    public void goToThisPlace(Player player, Command command, Output output){
        output.printPlaceText("GO_TO_THIS_PLACE_" +  name);
    }

    @Override
    public boolean canDoAction(Action action) {
        for(Position position : positions){
            if(position.canDoAction(action)) return true;
        }
        for(Item item: items){
            if(item.canDoAction(action)) return true;
        }
        return false;
    }

    public LocationConnection canGoTo(String locationName){
        for(LocationConnection con : placesToGo){
            if(con.getPlace().getName().equalsIgnoreCase(locationName)){
                return con;
            }
        }

        for(Position pos: positions){
            if(pos.getName().equalsIgnoreCase(locationName)){
                return new LocationConnection(pos, true);
            }
        }
        return null;
    }

    @Override
    public CanDoAction getActionObject(String name) {
        CanDoAction item;
        for(Position position : positions){
            if(position.getName().equalsIgnoreCase(name)) return position;
            if((item = position.getActionObject(name)) != null) return item;
        }
        for(Item i : items){
            if(i.getName().equalsIgnoreCase(name)) return i;
        }
        return null;
    }

    @Override
    public long getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Item[] getItems() {
        return items;
    }

    @Override
    public void addItem(Item item) {
        items[itemIndex++] = item;
        addChild(item);
    }

    @Override
    public Everything[] getChildren() {
        return children;
    }

    public void addChild(Everything child) {
        children[childIndex++] = child;
    }

    public void addPosition(Position position){
        positions[positionIndex++] = position;
    }

    public void addPlaceToGo(LocationConnection connection){
        placesToGo[placesToGoIndex++] = connection;
    }

    public LocationConnection getPlaceToGo(int i){
        return placesToGo[i];
    }

    @Override
    public Place getParentLocation() {
        return parentLocation;
    }

    @Override
    public void setParentLocation(Place parentLocation) {
        this.parentLocation = parentLocation;
        this.parentLocation.addChild(this);
    }

    public LocationConnection getNorth() {
        return north;
    }

    public void setNorth(LocationConnection north) {
        this.north = north;
    }

    public LocationConnection getSouth() {
        return south;
    }

    public void setSouth(LocationConnection south) {
        this.south = south;
    }

    public LocationConnection getWest() {
        return west;
    }

    public void setWest(LocationConnection west) {
        this.west = west;
    }

    public LocationConnection getEast() {
        return east;
    }

    public void setEast(LocationConnection east) {
        this.east = east;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

}
