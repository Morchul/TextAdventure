package com.mochul.testadventure.place;

import com.mochul.testadventure.actions.Action;
import com.mochul.testadventure.actions.CanDoAction;
import com.mochul.testadventure.actions.Command;
import com.mochul.testadventure.object.Item;
import com.mochul.testadventure.player.Player;
import com.mochul.testadventure.ui.Output;

public class Place implements Location {

    private long ID;
    private String name;
    private String description;

    private Item[] items;
    private int itemIndex = 0;

    private Position[] positions;
    private int positionIndex = 0;

    private PlaceConnection[] placesToGo;
    private int placesToGoIndex = 0;

    private Child[] children;
    private int childIndex = 0;

    private PlaceConnection north;
    private PlaceConnection south;
    private PlaceConnection west;
    private PlaceConnection east;

    public Place(long ID, String name, int countOfItems, int countOfPlacesToGo, int countOfPositions) {
        this.ID = ID;
        this.items = new Item[countOfItems];
        this.placesToGo = new PlaceConnection[countOfPlacesToGo];
        this.positions = new Position[countOfPositions];
        this.children = new Child[countOfItems + countOfPositions];
        this.name = name;
        this.description = "";
    }

    public void goToThisPlace(Player player, Command command, Output output){
        //output.printPlaceText("GO_TO_THIS_PLACE_" +  name);
    }

    @Override
    public boolean canDoAction(Action action) {
        for(Position position : positions){
            if(position.canDoAction(action)) return true;
        }
        for(Item item: items){
            if(item.hasAction(action)) return true;
        }
        return false;
    }

    public Location canGoTo(String locationName){
        for(PlaceConnection con : placesToGo){
            if(con.getPlace().getName().equalsIgnoreCase(locationName)){
                if(con.passable)
                    return con.getPlace();
                else
                    return null;
            }
        }

        for(Position pos: positions){
            if(pos.getName().equalsIgnoreCase(locationName)){
                return pos;
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
    public Child[] getChildren() {
        return children;
    }

    private void addChild(Child child) {
        children[childIndex++] = child;
    }

    public void addPosition(Position position){
        positions[positionIndex++] = position;
        addChild(position);
    }

    public void addPlaceToGo(PlaceConnection connection){
        placesToGo[placesToGoIndex++] = connection;
    }

    public PlaceConnection getPlaceToGo(int i){
        return placesToGo[i];
    }

    public PlaceConnection getNorth() {
        return north;
    }

    public void setNorth(PlaceConnection north) {
        this.north = north;
    }

    public PlaceConnection getSouth() {
        return south;
    }

    public void setSouth(PlaceConnection south) {
        this.south = south;
    }

    public PlaceConnection getWest() {
        return west;
    }

    public void setWest(PlaceConnection west) {
        this.west = west;
    }

    public PlaceConnection getEast() {
        return east;
    }

    public void setEast(PlaceConnection east) {
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
