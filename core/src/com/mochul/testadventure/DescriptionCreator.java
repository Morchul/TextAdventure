package com.mochul.testadventure;

import com.mochul.testadventure.place.Everything;

public class DescriptionCreator {

    public DescriptionCreator() {

    }

    public static String createDescription(String description, Everything[] children){

        StringBuilder builder = new StringBuilder(children.length * 2 + 1);

        int b = 0, e;
        for(int i = 0; i < description.length(); ++i){
            if(description.charAt(i) == '{'){
                e = i;
                builder.append(description.substring(b, e));
                builder.append(children[description.charAt(i + 1) - 48].getDescription());
                b = i + 3;
            }
        }
        if(b < description.length()){
            builder.append(description.substring(b));
        }

        return builder.toString();
    }
}
