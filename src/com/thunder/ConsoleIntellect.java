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
            System.out.println("HIT/STAND/DOUBLE? ");
            String c = in.nextLine();

            if("hit".startsWith(c.toLowerCase()))
                return Command.HIT;
            if("stand".startsWith(c.toLowerCase()))
                return Command.STAND;
            if("double".startsWith(c.toLowerCase()))
                return Command.DOUBLE;

            System.out.println("can't understand, please repeat");
        }
    }

    @Override
    public int decidemoney(int current) {
        while(true)
        {
            System.out.println("Bet: ");
            String c = in.nextLine();

                if (Integer.parseInt(c) > 0 && Integer.parseInt(c) <= current)
                    return Integer.parseInt(c);
            System.out.println("Enter please a value "+ "> 0"+ " and "+ "< "+ current);
        }
    }
}
