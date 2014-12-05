package com.lingtorp.characters;

import com.lingtorp.Game;
import com.lingtorp.Room;

/**
 * Created by AlexanderLingtorp on 03/12/14.
 */
public class Character { // TODO: Redesign this as abstract class later on

    private String name;
    private int maxHealth;
    private int currentHealth;
    private boolean alive = true;
    private Room currentRoom;

    private int updateTickRate = 5;

    public Character(String name, int maxHealth)
    {
        this.name = name;
        this.currentHealth = maxHealth;
        this.maxHealth = maxHealth;
    }

    public void takeDamage(int damage)
    {
        if (damage >= currentHealth) {
            alive = false;
        } else {
            currentHealth -= damage;
        }
    }

    public void heal(int health)
    {
        currentHealth += health;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    /**
     * Get the name of the Character
     */
    public String getName()
    {
        return name;
    }

    /**
     * Used by Characters to feel the drift of time.
     */
    public void tick() { }

    /**
     * Try to move this Character in a direction.
     * @param direction
     */
    public void move(Game.Direction direction)
    {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            nextRoom.enterRoomFromDirection(this, direction);
            if (currentRoom != null) {
                currentRoom.leave(this);
            }
            currentRoom = nextRoom;
        }
    }

    /**
     * Teleports the Character to the given room.
     * @param room
     */
    public void moveTo(Room room)
    {
        if (room.enter(this)) {
            currentRoom = room;
        }
    }

    /**
     * Kills the Character
     */
    public void kill()
    {
        // TODO: Call the Game
        alive = false;
    }

    /**
     * Called by the Room in which the Character is in whenever a Character enters the Room.
     * @param character
     */
    public void noticeCharacter(Character character)
    {

    }
}
