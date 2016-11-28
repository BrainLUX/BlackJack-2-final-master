package com.thunder;

import java.util.Scanner;

/**
 * Created by student on 21.11.2016.
 */
public class ConsoleIntellect extends Intellect {
    private Scanner in = new Scanner(System.in);

    @Override
    public Command decide(int score) {
        while(true)
        {
            System.out.println("HIT/STAND? ");
            String c = in.nextLine();

            if("hit".startsWith(c.toLowerCase()))
                return Command.HIT;
            if("stand".startsWith(c.toLowerCase()))
                return Command.STAND;

            System.out.println("can't understand, please repeat");
        }
    }
}
