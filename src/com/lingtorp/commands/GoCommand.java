package com.lingtorp.commands;

import com.lingtorp.Game;

import java.util.HashMap;

/**
 * Created by AlexanderLingtorp on 02/12/14.
 */
public class GoCommand extends Command {

    private Game.Direction commandArgument;
    private HashMap<String, Game.Direction> validArguments;
    private String rawCommandArgument;

    /**
     * Setup the Command
     */
    public GoCommand()
    {
        validArguments = new HashMap<String, Game.Direction>();
        for (Game.Direction commandArgument : Game.Direction.values()) {
            validArguments.put(commandArgument.toString(), commandArgument);
        }
    }

    @Override
    public void executeCommand()
    {
        Game.getGameInstance().goRoom(commandArgument);
    }

    @Override
    public void passArgument(String commandArgument)
    {
        this.commandArgument = validArguments.getOrDefault(commandArgument, Game.Direction.UNKNOWN);
        rawCommandArgument = commandArgument;
    }

    @Override
    public String getRawCommandString()
    {
        return CommandWord.GO.toString() + rawCommandArgument;
    }
}
