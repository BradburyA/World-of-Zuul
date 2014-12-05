package com.lingtorp.commands;

import com.lingtorp.Game;

/**
 * Created by AlexanderLingtorp on 03/12/14.
 */
public class UnknownCommand extends Command {

    private String commandArgument;

    @Override
    public void executeCommand()
    {
        Game.getGameInstance().unknownCommand(this);
    }

    @Override
    public void passArgument(String commandArgument)
    {
        this.commandArgument = commandArgument;
    }

    @Override
    public String getRawCommandString()
    {
        return commandArgument;
    }
}
