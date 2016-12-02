package com.thunder;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Table table = new Table();
        while (true) {
            table.makeBets();
            table.dealCards();
            table.playGame();
            table.getWinner();
            table.payBets();
            table.clear();
        }
    }
}
