package com.lingtorp.room;

import com.lingtorp.Game;
import com.lingtorp.characters.Character;

/**
 * Created by AlexanderLingtorp on 03/12/14.
 */
public interface Enterable {

    /**
     * Called when a Character tries to enter from a specific direction.
     * @param character
     * @param direction
     * @return True if successful.
     */
    public boolean enterRoomFromDirection(Character character, Game.Direction direction);

    /**
     * Called when a Character enters the Room.
     * @return True if successful and permitted.
     */
    public boolean enter(Character character);

    /**
     * Called when a Character leaves the Room.
     */
    public boolean leave(Character character);
}
