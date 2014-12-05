package com.lingtorp.characters.personalities;

import java.util.HashMap;

/**
 * Created by AlexanderLingtorp on 03/12/14.
 */
public class FriendlyPersonalityType implements PersonalityType {

    private HashMap<String, String> dialogSet;

    // Setup the approaches and replies.
    public FriendlyPersonalityType()
    {
        dialogSet = new HashMap<String, String>();
        dialogSet.put("hello", "Greetings mate!");
        dialogSet.put("bye", "Have a nice day laddy!");
        dialogSet.put("why are you here", "I am lost. Like you! Fun, huh?");
    }

    @Override
    public HashMap<String, String> getDialogSet() {
        return dialogSet;
    }
}
