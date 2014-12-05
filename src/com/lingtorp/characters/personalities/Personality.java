package com.lingtorp.characters.personalities;

import java.util.HashMap;

/**
 * Created by AlexanderLingtorp on 03/12/14.
 */
public enum Personality {

    AGGERSSIVE(new AggressivePersonalityType()), FRIENDLY(new FriendlyPersonalityType());

    private PersonalityType personalityType;

    Personality(PersonalityType personalityType)
    {
        this.personalityType = personalityType;
    }

    /**
     * @return The dialog set
     */
    public HashMap<String, String> getDialogSet()
    {
        return personalityType.getDialogSet();
    }
}
