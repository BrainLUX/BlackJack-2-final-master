package com.thunder;

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
}
