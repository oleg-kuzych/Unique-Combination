package com.ok.combination;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class UniqueCombinationParametrizedTest {

    private UniqueCombination<Integer> combinator;
    private List<Integer> list;
    private int k;
    private double expectedNumberOfCombinations;
    private List<List<Integer>> expectedCombinations;

    public UniqueCombinationParametrizedTest(List<Integer> list, int k, int expectedNumberOfCombinations, List<List<Integer>> expectedCombinations) {
        this.list = list;
        this.k = k;
        this.expectedNumberOfCombinations = expectedNumberOfCombinations;
        this.expectedCombinations = expectedCombinations;
    }

    @Parameters(name = "{index}: N({0}) & K({1})={2}")
    public static Collection<Object[]> data() {
        return asList(new Object[][]{
                {asList(1, 2, 3, 4), 2, 6, asList(
                        asList(1, 2), asList(1, 3), asList(1, 4), asList(2, 3), asList(2, 4), asList(3, 4)
                )},
                {asList(1, 2, 3, 4), 3, 4, asList(
                        asList(1, 2, 3), asList(1, 2, 4), asList(1, 3, 4), asList(2, 3, 4)
                )},
                {asList(1, 2, 3, 4), 4, 1, singletonList(asList(1, 2, 3, 4))},
                {asList(1, 2, 3, 4), 1, 4, asList(
                        singletonList(1), singletonList(2), singletonList(3), singletonList(4)
                )},
                {asList(1, 2, 3, 4), 5, 0, emptyList()}
        });
    }

    @Before
    public void before() {
        combinator = new UniqueCombination<>(list);
    }

    @Test
    public void checkNumberOfCombinations() {
        boolean expectException = list.size() < k; // is negative case
        try {
            assertEquals(expectedNumberOfCombinations, combinator.getNumberOfCombinations(k), 0.000001d);

            assertFalse("No exception is generated for negative case", expectException);
        } catch (IllegalArgumentException e) {
            assertTrue("Not expected exception", expectException);
        }
    }

    @Test
    public void checkCombinations() {
        boolean expectException = list.size() < k; // is negative case

        try {
            List<List<Integer>> result = combinator.getCombinations(k);
            assertFalse("No exception is generated for negative case", expectException);

            assertEquals("Size is different", expectedCombinations.size(), result.size());

            for (List<Integer> expectCombination : expectedCombinations) {
                assertThat("No combination found", result, hasItem(expectCombination));
            }
        } catch (IllegalArgumentException e) {
            assertTrue("Not expected exception", expectException);
        }
    }
}
