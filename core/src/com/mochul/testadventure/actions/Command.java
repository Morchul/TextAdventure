package com.mochul.testadventure.actions;

public class Command {

    public final String[] words;
    public final boolean validCommand; // is not empty
    public final Action action;
    public final String subject;
    public final boolean hasAdditionalSubject;
    public final String additionalSubject;

    public Command(String input) {
        if(input == null || input.equals("")){
            validCommand = false;
            action = Action.UNKNOWN;
            words = null;
            subject = "";
            additionalSubject = "";
            hasAdditionalSubject = false;
            return ;
        }

        words = input.split(" ");
        action = Action.getAction(words[0]);
        validCommand = true;

        if(input.contains("with")){
            int wi = 0;
            for(int i = 0; i < words.length; ++i){
                if(words[i].equalsIgnoreCase("with")){ wi = i; break; } }

            hasAdditionalSubject = true;
            subject = words[wi - action.getSubjectPosFromEnd()];
            additionalSubject = words[words.length - 1];
        } else {
            hasAdditionalSubject = false;
            additionalSubject = "";
            subject = words[words.length - action.getSubjectPosFromEnd()];
        }
    }
}
