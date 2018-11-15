package com.mochul.testadventure.actions;

import com.mochul.testadventure.player.Player;
import com.mochul.testadventure.ui.Output;

public interface CanDoAction {

    boolean canDoAction(Action action);
    boolean act(Player player, Command command, Output output);
    boolean hasAction(Action action);

    Action[] getActions();
    void addAction(Action action);
}
