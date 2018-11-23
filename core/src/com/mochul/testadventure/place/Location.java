package com.mochul.testadventure.place;

import com.mochul.testadventure.actions.Action;
import com.mochul.testadventure.actions.CanDoAction;
import com.mochul.testadventure.object.Item;
import com.mochul.testadventure.ui.Output;

public interface Location extends Everything {

    CanDoAction getActionObject(String name);
    boolean canDoAction(Action action);

    Item[] getItems();
    void addItem(Item item);

    Child[] getChildren();

    void goToThisLocation(Output output);
}
