package com.lingtorp.characters.personalities;

import java.util.HashMap;

/**
 * Created by AlexanderLingtorp on 03/12/14.
 */
public interface PersonalityType {
    /**
     * @return The Dialog set for the given Personality
     */
    public HashMap<String, String> getDialogSet();
}
