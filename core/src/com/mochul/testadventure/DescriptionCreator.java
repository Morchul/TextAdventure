package com.mochul.testadventure;

import com.mochul.testadventure.object.Everything;

public class DescriptionCreator {

    public DescriptionCreator() {

    }

    public static String createDescription(String description, Everything[] children){
        String[] parts = description.split("\\{}");
        StringBuilder builder = new StringBuilder(children.length * 2 + 1);
        for(int i = 0; i < parts.length - 1 ; ++i){
            builder.append(parts[i]);
            builder.append(children[i].getDescription());
        }
        if(!description.endsWith("{}")){
            builder.append(parts[parts.length - 1]);
        }

        return builder.toString();
    }
}
