package com.mochul.testadventure.place;

import com.mochul.testadventure.actions.Action;
import com.mochul.testadventure.actions.CanDoAction;
import com.mochul.testadventure.object.Item;

public abstract class Position implements PositionInterface {

    private long ID;
    private String name;
    private String description;
    private String detailedDescription;

    private Place parentLocation;

    private Action[] actions;
    private int actionIndex = 0;

    public boolean passable;

    private Item[] items;
    private int objectIndex = 0;

    public Position(long ID, String name, int countOfActions, int countOfObjects, Place parentPlace) {
        this.ID = ID;
        this.name = name;
        this.actions = new Action[countOfActions];
        this.items = new Item[countOfObjects];
        this.parentLocation = parentPlace;
        this.parentLocation.addPosition(this);
        description = "";
        detailedDescription = "";
        passable = true;
    }

    @Override
    public boolean canDoAction(Action action) {
        if(hasAction(action)) return true;
        for(Item item : items){
            if(item.hasAction(action)) return true;
        }
        return false;
    }

    @Override
    public boolean hasAction(Action action) {
        for(Action a : actions){
            if(a == action) return true;
        }
        return false;
    }

    @Override
    public CanDoAction getActionObject(String name) {
        if(name.equalsIgnoreCase(this.name)) return this;
        for(Item item : items){
            if(item.getName().equalsIgnoreCase(name)) return item;
        }
        return null;
    }

    @Override
    public Action[] getActions() {
        return actions;
    }

    @Override
    public void addAction(Action action) {
        actions[actionIndex++] = action;
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
        items[objectIndex++] = item;
    }

    @Override
    public Child[] getChildren() {
        return items;
    }

    public Place getParentPlace() {
        return parentLocation;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }


    public String getDetailedDescription() {
        return detailedDescription;
    }


    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }
}
