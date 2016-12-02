package com.thunder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by student2 on 14.11.16.
 */
public class Deck extends LinkedList<Card> {
    public Deck() {
        for (Suit s : Suit.values()) {
            for (Value v : Value.values()) {
                Card c = new Card(s, v);
                this.add(c);
            }
        }
        Collections.shuffle(this);
    }
}
