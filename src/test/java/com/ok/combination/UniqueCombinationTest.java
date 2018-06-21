package com.ok.combination;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeThat;

@RunWith(JUnit4.class)
public class UniqueCombinationTest {

    private static final List<String> data = Arrays.asList("one", "two", "three", "four");

    private UniqueCombination<String> combinator;

    @Before
    public void before() {
        combinator = new UniqueCombination<>(data);
    }

    @Test
    public void checkGetCombinationsForAllKStartingFrom() {
        List<List<String>> result = combinator.getCombinationsForAllKStartingFrom(2);

        int numberOfResults = 0;
        for (int i = 2; i <= data.size(); i++) {
            numberOfResults += combinator.getNumberOfCombinations(i);

            List<List<String>> list = combinator.getCombinations(i);
            for (List<String> item : list) {
                assumeThat(result, hasItem(item));
            }
        }

        assertEquals(numberOfResults, result.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkGetCombinationsForAllKStartingFromNegative() {
        combinator.getCombinationsForAllKStartingFrom(data.size() + 1);
    }
}
