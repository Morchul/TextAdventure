package com.mochul.testadventure.place;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mochul.testadventure.object.Item;

public class Map {

    private Place[] places;
    private Position[] positions;
    private Item[] items;
    private PlaceConnection[] placeConnections;

    private Map(){
        places = new Place[];
        positions = new Position[];
        items = new Item[];
        placeConnections = new PlaceConnection[];
    }

    public static Map createMap(String fileName){
        Map map = new Map();
        FileHandle file = Gdx.files.internal(fileName);
        String content = file.readString();
        return map;
    }


    public Place searchPlace(long id){
        for(Place p : places){
            if(p.getID() == id){
                return p;
            }
        }
        return null;
    }
    public Item searchItem(long id){
        for(Item i : items){
            if(i.getID() == id){
                return i;
            }
        }
        return null;
    }
    public PlaceConnection searchPlaceConnection(long id){
        for(PlaceConnection con : placeConnections){
            if(con.getID() == id){
                return con;
            }
        }
        return null;
    }
    public Position searchPosition(long id){
        for(Position p : positions){
            if(p.getID() == id){
                return p;
            }
        }
        return null;
    }
}
