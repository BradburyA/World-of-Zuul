package com.lingtorp.characters;

/**
 * Created by AlexanderLingtorp on 04/12/14.
 */
public class Player extends Character implements User {

    /**
     * Default Player
     */
    public Player() {
        super("Player", 10);
    }

    public void talkTo(Character character, String sentence)
    {
        System.out.println(character.getName() + " says " + sentence);   // Consume the message - relay to irl player.
    }
}
