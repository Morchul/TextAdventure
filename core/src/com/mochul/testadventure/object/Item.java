package com.mochul.testadventure.object;

import com.mochul.testadventure.actions.Action;
import com.mochul.testadventure.actions.CanDoAction;
import com.mochul.testadventure.place.Location;

public abstract class Item implements CanDoAction, Everything {

    private long ID;
    private String name;
    private String description;

    private Location parentLocation;

    private Action[] actions;
    private int actionIndex = 0;

    public Item(long ID, String name, int countOfActions, Location parentLocation, String defaultDescription){
        this.ID = ID;
        this.name = name;
        this.actions = new Action[countOfActions];
        setParentPlace(parentLocation);
        setDescription(defaultDescription);
    }

    public String getName(){
        return name;
    }

    public long getID(){
        return ID;
    }

    @Override
    public boolean canDoAction(Action action) {
        for(Action a: actions){
            if(a == action) return true;
        }
        return false;
    }

    @Override
    public boolean hasAction(Action action) {
        for(Action a: actions){
            if(a == action) return true;
        }
        return false;
    }

    @Override
    public Action[] getActions(){
        return actions;
    }

    @Override
    public void addAction(Action action) {
        actions[actionIndex++] = action;
    }

    public Location getParentPlace(){
        return parentLocation;
    }

    private void setParentPlace(Location parentLocation){
        this.parentLocation = parentLocation;
        this.parentLocation.addItem(this);
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
    public String getDetailedDescription() {
        return description;
    }

    @Override
    public void setDetailedDescription(String detailedDescription) {
        this.description = detailedDescription;
    }
}
