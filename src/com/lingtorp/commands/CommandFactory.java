/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 */
package com.lingtorp.commands;

import java.util.HashMap;

public class CommandFactory
{
    private static CommandFactory commandFactory = new CommandFactory();
    private HashMap<String, Command> validCommands;

    /**
     * Singleton Constructor - initialise the command words.
     */
    private CommandFactory() {
        validCommands = new HashMap<String, Command>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command.toCommand());
            }
        }
    }

    public static CommandFactory getCommandFactory()
    {
        return commandFactory;
    }

    /**
     * Creates a new Command object and returns it.
     * @param commandWord The CommandWord that maps to the type of Command object.
     * @param commandArgument Argument to the Command object.
     * @return A new Command object.
     */
    public Command newCommand(String commandWord, String commandArgument)
    {
        Command command = validCommands.getOrDefault(commandWord, CommandWord.UNKNOWN.toCommand());

        // TODO: Handle a unknown command case better.
        if (command instanceof UnknownCommand) {
            command.passArgument(commandWord + " " + commandArgument);
        } else {
            command.passArgument(commandArgument);
        }

        return command;
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAllCommands()
    {
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
