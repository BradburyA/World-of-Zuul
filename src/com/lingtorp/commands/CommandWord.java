/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */
package com.lingtorp.commands;

public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go", new GoCommand())
    , QUIT("quit", new QuitCommand())
    , HELP("help", new HelpCommand())
    , UNKNOWN(null, new UnknownCommand());
    
    // The command string.
    private String commandString;

    // The Command Object tied to the commandString
    private Command command;
    
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString, Command command)
    {
        this.commandString = commandString;
        this.command = command;
    }
    
    /**
     * @return The command word as a String.
     */
    @Override
    public String toString()
    {
        return commandString;
    }

    /**
     * @return The Command tied with the String
     */
    Command toCommand()
    {
        return command;
    }
}
