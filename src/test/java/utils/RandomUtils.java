package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtils {

    public static String getRandomString(int length) {
        final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < length) {
            int index = (int) (rnd.nextFloat() * CHARSET.length());
            result.append(CHARSET.charAt(index));
        }
        return result.toString();
    }

    public static String getRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.co", "blablah.guru"};
        return getRandomString(12) + "@" + domains[new Random().nextInt(domains.length)];
    }

    public static <T> List<T>getRandomElementsFromList(List<T> list)
    {
        int numberOfItems = new Random().nextInt(list.size()) + 1;

        return getRandomElementsFromList(list, numberOfItems);
    }

    public static <T> List<T>getRandomElementsFromList(List<T> list, int numberOfItems) {
        List<T> localList = new ArrayList<>();
        List<T> newList = new ArrayList<>();
        Random rand = new Random();

        localList.addAll(list);

        for (int i = 0; i < numberOfItems; i++) {
            int randomIndex = rand.nextInt(localList.size());
            newList.add(localList.get(randomIndex));
            localList.remove(randomIndex);
        }
        return newList;
    }

}
