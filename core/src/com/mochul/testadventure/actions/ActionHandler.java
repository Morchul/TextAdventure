package com.mochul.testadventure.actions;

import com.mochul.testadventure.place.Location;
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
            if(nextLocation != null)
                actionExecutor.executeGoAction(nextLocation, command);
            else
                output.printInfoText("Can't go to " + command.subject);
        } else {
            Location cp = player.getCurrentPosition();
            if(cp.canDoAction(command.action)){
                CanDoAction canDoAction = cp.getActionObject(command.subject);
                if(canDoAction.hasAction(command.action)){
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
