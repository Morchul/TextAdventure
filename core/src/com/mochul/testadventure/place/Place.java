package com.mochul.testadventure.place;

import com.mochul.testadventure.DescriptionCreator;
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

    private Child[] children;
    private int childIndex = 0;

    private PlacePosition north;
    private PlacePosition south;
    private PlacePosition west;
    private PlacePosition east;

    private PlacePosition leavePosition;

    public Place(long ID, String name, int countOfItems, int countOfPositions) {
        this.ID = ID;
        this.items = new Item[countOfItems];
        this.positions = new Position[countOfPositions];
        this.children = new Child[countOfItems + countOfPositions];
        this.name = name;
        this.description = "";
    }

    @Override
    public void goToThisLocation(Output output){
        output.printPlaceText(DescriptionCreator.createDescription(description, children));
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

    public Position canLeave(String name){
        if(name.equalsIgnoreCase(this.name)){
            if(leavePosition != null)
                return leavePosition;
        }
        return null;
    }

    public void setLeavePosition(PlacePosition pp){
        this.leavePosition = pp;
    }

    public Position canGoTo(String locationName){
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

    public PlacePosition getNorth() {
        return north;
    }

    public void setNorth(PlacePosition north) {
        this.north = north;
    }

    public PlacePosition getSouth() {
        return south;
    }

    public void setSouth(PlacePosition south) {
        this.south = south;
    }

    public PlacePosition getWest() {
        return west;
    }

    public void setWest(PlacePosition west) {
        this.west = west;
    }

    public PlacePosition getEast() {
        return east;
    }

    public void setEast(PlacePosition east) {
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
