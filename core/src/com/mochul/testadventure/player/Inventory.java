package com.mochul.testadventure.player;

import com.mochul.testadventure.object.Item;

public class Inventory {

    private final Item[] inventory;
    private int emptySpace;
    private int currentPos;

    public Inventory(int inventorySize) {
        inventory = new Item[inventorySize];
        emptySpace = inventorySize;
        currentPos = 0;
    }

    public Item[] getInventory() {
        return inventory;
    }

    public boolean add(Item item){
        if (emptySpace == 0){
            return false;
        } else {
            inventory[currentPos++] = item;
            return true;
        }
    }
}
