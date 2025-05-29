package guru.qa.utils;

import java.util.Random;

public class RandomUtils {

    public static String getRandomString(int length){

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder result = new StringBuilder();
        Random random = new Random();
        while (result.length() < length){
            int index = (int) (random.nextFloat() * chars.length());
            result.append(chars.charAt(index));
        }

        return result.toString();
    }
}
