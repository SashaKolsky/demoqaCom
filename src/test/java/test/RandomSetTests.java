package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.RandomUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomSetTests {

    @Test
    void randomItemsFromListOfString() {
        List<String> values = Stream.of("Cooking", "Driving", "Swimming", "Yoga").collect(Collectors.toList());

        List<String> results = RandomUtils.getRandomElementsFromList(values);

        Assertions.assertNotNull(results.get(0));
        Assertions.assertTrue(results.size() <= values.size());
        //Assertions.assertNotEquals(results, values);
    }

    @Test
    void randomItemsFromListOfIntegers() {
        List<Integer> values = List.of(123, 5, 1020, 1234561);

        List<Integer> results = RandomUtils.getRandomElementsFromList(values);

        Assertions.assertNotNull(results.get(0));
        Assertions.assertTrue(results.size() <= values.size());
    }

    @Test
    void ThreeRandomItemsFromListOfString() {
        List<String> values = Stream.of("Cooking", "Driving", "Swimming", "Yoga").collect(Collectors.toList());
        int numberOfRandomElements = 3;

        List<String> results = RandomUtils.getRandomElementsFromList(values, numberOfRandomElements);

        Assertions.assertNotNull(results.get(0));
        Assertions.assertEquals(numberOfRandomElements, results.size());
    }
}
