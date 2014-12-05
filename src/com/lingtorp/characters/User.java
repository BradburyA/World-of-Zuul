package com.lingtorp.characters;

/**
 * Created by AlexanderLingtorp on 04/12/14.
 */
public interface User {

    /**
     * Called by Characters talking to the User.
     */
    public void talkTo(Character character, String sentence); // TODO: Add "yells", "says", "whispers", et cetera ...

}
