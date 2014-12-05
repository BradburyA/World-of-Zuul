/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 *  Completes Exercises 6.41 & 6.46.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */
package com.lingtorp;

import com.lingtorp.characters.Character;
import com.lingtorp.characters.NPC;
import com.lingtorp.characters.Player;
import com.lingtorp.characters.personalities.Personality;
import com.lingtorp.commands.*;

import java.util.ArrayList;
import java.util.Stack;

public class Game
{
    public enum Direction {
        WEST("west"), EAST("east"), SOUTH("south"), NORTH("north"), UNKNOWN("Unknown direction.");

        private String infoString;

        Direction(String info)
        {
            infoString = info;
        }

        public String toString()
        {
            return infoString;
        }
    }

    // Game
    private static Game gameInstance = new Game();
    private Parser parser;
    private Stack<Command> commandStack;
    private Player player;

    // Game Logic
    private Room currentRoom;       // TODO: Refactor to Player class
    private boolean finished = false;
    private Room worldMap[][];
    private ArrayList<NPC> npcs = new ArrayList<NPC>();

    // Gameplay
    private int timeLimit = 12;

    /**
     * Create the game and initialise its internal map.
     */
    private Game()
    {
        generateWorld();
        parser = new Parser();
        player = createDefaultPlayer();
        npcs.addAll(createRandomNPCs());
        commandStack = new Stack<Command>();
        commandStack.setSize(5);
    }

    /**
     * Singleton instance getter
     */
    public static Game getGameInstance()
    {
        return gameInstance;
    }

    /**
     * Create the default Player
     * @return A Player object
     */
    private Player createDefaultPlayer()
    {
        return new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void generateWorld()
    {
        Room outside, theater, pub, lab, office;

        worldMap = new Room[3][3];

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // Using a hybrid world design for now ...
        worldMap[1][1] = outside;
        worldMap[0][1] = theater;
        worldMap[2][1] = pub;
        worldMap[1][0] = lab;
        worldMap[0][0] = office;

        // initialise room exits
        outside.setExit(Direction.EAST, theater);
        outside.setExit(Direction.SOUTH, lab);
        outside.setExit(Direction.WEST, pub);

        theater.setExit(Direction.WEST, outside);

        pub.setExit(Direction.EAST, outside);

        lab.setExit(Direction.NORTH, outside);
        lab.setExit(Direction.EAST, office);

        office.setExit(Direction.WEST, lab);

        currentRoom = outside;  // start game outside
    }

    /**
     * Creates random Characters in the world.
     * @return Random Characters.
     */
    private ArrayList<NPC> createRandomNPCs()
    {

        // TODO: 1. Generate random number of characters.
        //       2. Generate random characteristics for each one.
        //       3. Add them to a random place in the worldMap.

        ArrayList<NPC> npcs = new ArrayList<>();

        NPC npc = new NPC("Emil", 1, Personality.FRIENDLY);

        npc.moveTo(worldMap[1][0]);

        npcs.add(npc);

        return npcs;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        while (! finished) {
            Command command = parser.getCommand();
            processCommand(command);
            npcs.stream().forEach(Character::tick);             // Tick all the Characters
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("\n Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help. \n");
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private void processCommand(Command command)        //E6.46
    {
        // Game Managing Stuff

        // Time Management - E6.41
        if(!(command instanceof UnknownCommand)) {
            timeLimit--;
        }

        if (timeLimit == 0) {
            timeUp();
        }

        // Execution of the fetched Command
        command.executeCommand();
    }

    private void timeUp()
    {
        System.out.println("You are out of moves! Game Over.");
        quit();
    }

    /** Public Interface */

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    public void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university. \n");
        System.out.println("Your command words are: ");
        CommandFactory.getCommandFactory().showAllCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    public void goRoom(Direction direction)
    {
        if (direction == Direction.UNKNOWN) {
            System.out.println("Go where?");
            return;
        }

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no exit in the " + direction.toString() + "!");
        }
        else {
            nextRoom.enterRoomFromDirection(player, direction);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * Prints the Commands raw string value to the user.
     */
    public void unknownCommand(Command command)
    {
        System.out.println(command.getRawCommandString());
    }

    /** 
     * Quits the game.
     */
    public void quit()
    {
        finished = true;
    }
}
