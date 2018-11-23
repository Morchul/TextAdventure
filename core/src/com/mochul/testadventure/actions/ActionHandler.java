package com.mochul.testadventure.actions;

import com.mochul.testadventure.place.Location;
import com.mochul.testadventure.place.PlacePosition;
import com.mochul.testadventure.place.Position;
import com.mochul.testadventure.player.Player;
import com.mochul.testadventure.ui.Output;

public class ActionHandler {

    private Player player;
    private Output output;

    private ActionExecutor actionExecutor;
    private ActionInterpreter actionInterpreter;

    public ActionHandler(Output output, Player player) {
        this.player = player;
        this.output = output;
        actionExecutor = new ActionExecutor(output, player);
        actionInterpreter = new ActionInterpreter(output, player);
    }

    public void handle(Command command){

        if(command.action == Action.UNKNOWN){
            output.printInfoText("Unknown action: " + command.action);
            return ;
        }

        if(command.action == Action.GO){
            Position nextLocation = actionInterpreter.goAction(command);
            if(nextLocation != null){
                if(!actionExecutor.executeGoAction(nextLocation, command)){
                    //Do something if execute go action return false
                }
            } else
                output.printInfoText("Can't go to " + command.subject);
        } else if(command.action == Action.LEAVE) {
            PlacePosition pp;
            if((pp = player.getCurrentPlace().canLeave(command.subject)) != null) {
                actionExecutor.executeLeaveAction(pp, command);
            }

        } else {
            Location cp = player.getCurrentLocation();
            if(cp.canDoAction(command.action)){
                CanDoAction canDoAction = cp.getActionObject(command.subject);
                if(canDoAction != null && canDoAction.hasAction(command.action)){
                    canDoAction.act(player, command, output);
                } else {
                    output.printInfoText("Can't do action: " + command.action);
                }
            } else {
                output.printInfoText("Can't doo action: " + command.action);
            }
        }

    }
}
