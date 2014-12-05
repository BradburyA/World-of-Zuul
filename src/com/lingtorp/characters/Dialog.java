package com.lingtorp.characters;

/**
 * Created by AlexanderLingtorp on 03/12/14.
 */
public interface Dialog {

    /**
     * Called by a Character when talked to by a Character.
     * @param sentence
     * @return
     */
    public String talkTo(String sentence);
}
