package com.lingtorp.commands;

import com.lingtorp.Game;

/**
 * Created by AlexanderLingtorp on 04/12/14.
 */
public class QuitCommand extends Command {

    @Override
    public void executeCommand()
    {
        Game.getGameInstance().quit();
    }

    @Override
    public void passArgument(String commandArgument) { }

    @Override
    public String getRawCommandString() {
        return null;
    }
}
