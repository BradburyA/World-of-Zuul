package com.lingtorp.characters.personalities;

import java.util.HashMap;

/**
 * Created by AlexanderLingtorp on 03/12/14.
 */
public class AggressivePersonalityType implements PersonalityType {

    private HashMap<String, String> dialogSet;

    // Setup the approaches and replies.
    public AggressivePersonalityType()
    {
        dialogSet = new HashMap<String, String>();
        dialogSet.put("hello", "Hello, now go away.");
        dialogSet.put("bye", "Fuck off ...");
        dialogSet.put("why are you here", "Because I fucking like it here. Retard..");
    }

    @Override
    public HashMap<String, String> getDialogSet() {
        return dialogSet;
    }
}
