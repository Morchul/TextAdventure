package com.mochul.testadventure.place;

import com.mochul.testadventure.actions.Action;
import com.mochul.testadventure.actions.CanDoAction;
import com.mochul.testadventure.object.Everything;
import com.mochul.testadventure.object.Item;

public interface Location extends Everything {

    boolean canDoAction(Action action);
    CanDoAction getActionObject(String name);

    long getID();
    String getName();

    Item[] getItems();
    void addItem(Item item);

    Everything[] getChildren();

    Place getParentLocation();
    void setParentLocation(Place parentLocation);
}
