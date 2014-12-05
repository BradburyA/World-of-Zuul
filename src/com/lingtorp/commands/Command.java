/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a CommandWord and a string
 * (for example, if the command was "take map", then the two parts
 * are TAKE and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second word is <null>.
 *
 * The Command Object should handle all setup within it's constructor.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */
package com.lingtorp.commands;

public abstract class Command
{
    /**
     * Executes the command.
     */
    public abstract void executeCommand();

    /**
     * Sets the Command argument
     *
     * Handle Argument setting within the Command
     */
    public abstract void passArgument(String commandArgument);

    /**
     * @return The String that was given by the user.
     */
    public abstract String getRawCommandString();
}

