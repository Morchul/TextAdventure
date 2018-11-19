package com.mochul.testadventure.actions;

import com.mochul.testadventure.place.Location;
import com.mochul.testadventure.place.PlaceConnection;
import com.mochul.testadventure.player.Player;
import com.mochul.testadventure.ui.Output;

public class ActionHandler {

    private ActionInterpreter interpreter;
    private ActionExecutor executor;
    private Output output;
    private Player player;

    public ActionHandler(Output output, Player player){
        this.output = output;
        this.player = player;
        interpreter = new ActionInterpreter();
        executor = new ActionExecutor(output, player);
    }

    public void act(Command command) {
        if(command.words == null || !command.validCommand){ return ; }
        if(command.action == Action.UNKNOWN){
            output.printInfoText("Unknown action: " + command.words[0]);
            return;
        }

        Location cp = player.getCurrentPosition();

        if(command.action == Action.GO) {
            Location con = interpreter.interpretGoAction(command, cp);
            executor.goAction(con != null, con, command);
        } else if(command.action == Action.LEAVE){
            Location con = interpreter.interpretLeaveAction(command, cp);
            executor.leafAction(); //TODO leave action
        }


        else if(cp.canDoAction(command.action)){
            CanDoAction actionObject = cp.getActionObject(command.subject);
            if(actionObject != null && actionObject.hasAction(command.action)){
                actionObject.act(player, command, output);
            }
        }
    }
}
