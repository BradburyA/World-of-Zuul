package com.lingtorp.commands;

import com.lingtorp.Game;

/**
 * Created by AlexanderLingtorp on 04/12/14.
 */
public class HelpCommand extends Command {

    @Override
    public void executeCommand()
    {
        Game.getGameInstance().printHelp();
    }

    @Override
    public void passArgument(String commandArgument) { }

    @Override
    public String getRawCommandString() {
        return null;
    }
}
