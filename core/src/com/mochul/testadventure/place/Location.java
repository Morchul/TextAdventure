package com.mochul.testadventure.place;

import com.mochul.testadventure.actions.Action;
import com.mochul.testadventure.actions.CanDoAction;
import com.mochul.testadventure.object.Item;

public interface Location extends Everything {

    CanDoAction getActionObject(String name);
    boolean canDoAction(Action action);

    Item[] getItems();
    void addItem(Item item);

    Child[] getChildren();
}
