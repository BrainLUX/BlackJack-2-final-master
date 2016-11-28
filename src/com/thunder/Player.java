package com.thunder;

import javax.print.DocFlavor;
import java.util.Random;

/**
 * Created by student2 on 14.11.16.
 */
public abstract class Player {
    Hand hand = new Hand();
    Intellect intellect;
    String name;
    String[] names={"JOHN","MORIARTY","LESTRAID","SHERLOCK"};

    int money;
    PlayerState state = PlayerState.IN_GAME;

    public void take(Card current) {
        hand.add(current);
    }

    public Player(Intellect intellect) {
        this.intellect = intellect;
        Random rand = new Random();
        money = rand.nextInt(1000);
        name = names[rand.nextInt(names.length)];
    }


    public Command decision() {
        int score = hand.getScore();
        if(score>21)
            return Command.STAND;
        return intellect.decide(score);
    }


}
