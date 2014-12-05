/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */
package com.lingtorp;

import com.lingtorp.characters.Character;
import com.lingtorp.room.Enterable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Room implements Enterable
{
    private String description;
    private HashMap<Game.Direction, Room> exits;        // stores exits of this room.
    private ArrayList<Character> characters;            // Characters in the room.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        characters = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(Game.Direction direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<Game.Direction> keys = exits.keySet();
        for(Game.Direction exit : keys) {
            returnString += " " + exit.toString();
        }
        return returnString;
    }


    // TODO: Redesign - Room does not need knownledge about adjecent Rooms ...
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(Game.Direction direction)
    {
        return exits.get(direction);
    }

    /** Enter-able */

    @Override
    public boolean enterRoomFromDirection(Character character, Game.Direction direction)
    {
        enter(character);
        return true;
    }

    @Override
    public boolean enter(Character character)
    {
        characters.add(character);
        characters.stream().forEach((a) -> a.noticeCharacter(character));
        return true;
    }

    @Override
    public boolean leave(Character character)
    {
        // TODO: Redo so that the room knows when a Character leaves ...
        characters.remove(character);
        return true;
    }
}

