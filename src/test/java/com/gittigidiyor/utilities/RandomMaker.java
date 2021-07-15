package com.gittigidiyor.utilities;

public class RandomMaker {

    public static String makeRandom(String name) {
        String stock = "abcdefghijklmnoprstuvyz1234567890";
        for (int i = 0; i < 4; i++) {
            int random = (int) (Math.random() * stock.length() - 1);
            name = name + stock.charAt(random);
        }

        return name;

    }
}
