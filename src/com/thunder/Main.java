package com.thunder;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Player> players = new LinkedList<>();
        players.add(new Computer(new LimitIntellect(14)));
        players.add(new Computer(new LimitIntellect(20)));
        players.add(new Human( new ConsoleIntellect()));
        Dealer dealer = new Dealer();
        players.add(dealer);
        while (true) {
            Deck deck = new Deck(players, dealer);
            deck.playGame(players, dealer);
        }

    }
}
