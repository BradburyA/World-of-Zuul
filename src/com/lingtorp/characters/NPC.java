package com.lingtorp.characters;

import com.lingtorp.Game;
import com.lingtorp.characters.personalities.Personality;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by AlexanderLingtorp on 03/12/14.
 *
 * Completes Exercises 6.47 & 6.48
 */
public class NPC extends Character implements Dialog {

    private HashMap<String, String> replies;

    private long ticks = 0;
    private int tickInterval = 2;

    // TODO: Find a more elegant solution.
    private String initialGreeting = "Hello there darling! Haven't seen you here before.";

    public NPC(String name, int health, Personality personality)
    {
        super(name, health);
        this.replies = personality.getDialogSet();
    }

    @Override
    public String talkTo(String sentence)
    {
        StringBuilder sb = new StringBuilder();
        for (String token : sentence.split("")) {
            if (replies.containsKey(token)) {
                sb.append(replies.get(token));
            }
        }
        return sb.toString();
    }

    @Override
    public void noticeCharacter(Character character)
    {
        super.noticeCharacter(character);

        if (character instanceof Player) {
            Player player = (Player) character;
            player.talkTo(this, initialGreeting);
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        ticks++;
        if (ticks % tickInterval == 0) { // For every tickInterval move in random direction
            Random random = new Random();
            int randInt = random.nextInt(Game.Direction.values().length - 1);
            move(Game.Direction.values()[randInt]);
        }
    }
}
