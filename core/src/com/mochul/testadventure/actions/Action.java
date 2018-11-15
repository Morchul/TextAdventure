package com.mochul.testadventure.actions;

public enum Action {
    INVESTIGATE(1, "untersuche", "untersuchen"),
    TAKE(1, "nimm", "nehme", "nehmen"),
    GO(1, "gehe", "gehen", "geh", "go"),
    USE(1, "benutze", "benutzen"),
    LEAVE(1, "verlass", "verlassen", "verlasse"),
    OPEN(1, "oeffne", "open"),
    CLOSE(1, "schliesse", "close"),
    READ(1, "lies", "lese"),
    UNKNOWN(1);

    private String[] actions;
    private int subjectPos;

    Action(int subjectPos, String... actions) {
        this.actions = actions;
        this.subjectPos = subjectPos;
    }

    public int getSubjectPosFromEnd(){
        return subjectPos;
    }

    public boolean isAction(String input){
        for(String s : actions){
            if(s.equalsIgnoreCase(input)) return true;
        }
        return false;
    }

    public static Action getAction(String input){
        for(Action a: Action.values()){
            if(a.isAction(input)) return a;
        }
        return Action.UNKNOWN;
    }
}
