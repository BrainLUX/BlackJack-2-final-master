package com.thunder;

import java.util.Random;

/**
 * Created by student on 18.11.2016.
 */
public class LimitIntellect extends Intellect{

    public LimitIntellect(int limit) {
        this.limit = limit;
    }

    private int limit;

    @Override
    public Command decide(int score) {
        if(score<limit)
        {
            return Command.HIT;
        }
        else {
            return Command.STAND;
        }
    }

    @Override
    public int decidemoney(int current) {
        Random random = new Random();
        return random.nextInt((current-1))+1;
    }
}
