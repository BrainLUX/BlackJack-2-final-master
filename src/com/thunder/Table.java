package com.thunder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by student on 02.12.2016.
 */
public class Table {
    private Dealer dealer;
    private LinkedList<Player> players;
    private int[] deckmoney;
    private int humans;

    public Table() {
        players = new LinkedList<>();
        players.add(new Computer(new LimitIntellect(14)));
        players.add(new Computer(new LimitIntellect(20)));
        players.add(new Human(new ConsoleIntellect()));
        humans = 1;
        dealer = new Dealer();
        players.add(dealer);

    }

    public void makeBets() {
        deckmoney = new int[players.size()];
        for (Player player : players) {
            if (player.money != 0) {
                deckmoney[players.indexOf(player)] += player.coindecision();
                player.money = 0;
            }
        }
        System.out.println();

    }

    public void dealCards() {
        for (Player player : players) {
            dealer.deal(player);
            dealer.deal(player);
            System.out.println(player.name + ": " + player.hand);
        }
        System.out.println();
    }

    public void playGame() {
        for (Player player : players) {
            if (player != dealer) {
                while (true) {

                    System.out.println(player.name + ": " + player.hand.getScore() + ": " + player.hand);
                    Command command = player.decision();
                    System.out.println(command);

                    if (command == Command.STAND)
                        break;
                    if (command == Command.HIT)
                        dealer.deal(player);
                    if (command == Command.DOUBLE)
                        deckmoney[players.indexOf(player)] *= 2;
                }
            }
        }
    }

    public void getWinner() {
        for (Player player : players) {
            if (player.hand.getScore() > 21) {
                player.state = PlayerState.LOSS;
            } else if (dealer.hand.getScore() > 21) {
                player.state = PlayerState.WIN;
            } else if (dealer.hand.getScore() > player.hand.getScore()) {
                player.state = PlayerState.LOSS;
            } else if (dealer.hand.getScore() == player.hand.getScore()) {
                player.state = PlayerState.DRAW;
            } else {
                player.state = PlayerState.WIN;
            }
        }
    }

    LinkedList<Player> bankrots = new LinkedList<>();

    public void payBets() {

        for (Player player : players) {
            double count = 0;
            if (player.state == PlayerState.WIN) {
                player.money += (int) (deckmoney[players.indexOf(player)] * 3.5);
                count = 3.5;
            } else {
                if (player.state == PlayerState.DRAW) {
                    player.money += deckmoney[players.indexOf(player)];
                    count = 1;
                }

                if (player.money == 0) {
                    bankrots.add(player);
                    if (player instanceof Human)
                        humans--;
                }
                System.out.println(player.name + " " + player.state + " with " + player.hand + " and get: " + (int) (deckmoney[players.indexOf(player)] * count));
                player.hand.clear();
            }

        }

    }

    public void clear() {
        for (Player player : bankrots) {
            this.players.remove(player);
        }
        if (humans == 0)
            System.exit(0);

        System.out.println();
        System.out.println("STARTING NEW GAME");
        System.out.println();
    }
}
