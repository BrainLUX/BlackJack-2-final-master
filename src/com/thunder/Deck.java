package com.thunder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by student2 on 14.11.16.
 */
public class Deck extends LinkedList<Card> {
    int[] deckmoney;
    List<Player> players = new LinkedList<>();
    public Deck(List<Player> players, Dealer dealer) {
        for(Suit s : Suit.values()) {
            for (Value v : Value.values()) {
                Card c = new Card(s, v);
                this.add(c);
            }
        }
        Collections.shuffle(this);
        deckmoney = new int[players.size()];
        for(Player player: players){
            if(player.state != PlayerState.SPECTATOR) {
                deckmoney[players.indexOf(player)] += player.money;
                player.money = 0;
                dealer.deal(player, this);
                dealer.deal(player, this);
                System.out.println(player.name + ": " + player.hand);
            }
        }
    }

    public void playGame(List<Player> players, Dealer dealer) {
        for(Player player: players)
        {
            if(player!=dealer && player.state != PlayerState.SPECTATOR) {
                while (true) {
                    Command command = player.decision();

                    System.out.println(player.name + ": " + player.hand.getScore() + ": " + player.hand);
                    System.out.println(command);

                    if (command == Command.STAND)
                        break;
                    if (command == Command.HIT)
                        dealer.deal(player, this);
                }
            }
        }
        getWinner(players, dealer);
    }

    public void getWinner(List<Player> players, Dealer dealer) {
        for(Player player: players){
            if(player!=dealer && player.state != PlayerState.SPECTATOR) {
                if (player.hand.getScore() > 21) {
                    player.state = PlayerState.LOSS;
                } else {
                    if (dealer.hand.getScore() > 21) {
                        player.state = PlayerState.WIN;
                        player.money += (int)(deckmoney[players.indexOf(player)]*3.5);
                    } else {
                        if (dealer.hand.getScore() > player.hand.getScore()) {
                            player.state = PlayerState.LOSS;
                        } else {
                            if (dealer.hand.getScore() == player.hand.getScore()) {
                                player.state = PlayerState.DRAW;
                                player.money += deckmoney[players.indexOf(player)];
                            } else {
                                player.state = PlayerState.WIN;
                                player.money += (int)(deckmoney[players.indexOf(player)]*3.5);
                            }
                        }
                    }
                }
                System.out.println(player.name + " " + player.state + " with " + player.hand + " and get: "+ player.money);
                if(player.money==0)
                {
                    player.state = PlayerState.SPECTATOR;
                }
            }
            this.addAll(player.hand);
            player.hand.removeAll(player.hand);
        }
        this.players = players;
    }

}
