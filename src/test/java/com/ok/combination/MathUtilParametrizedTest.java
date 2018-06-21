package com.ok.combination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.ok.combination.MathUtil.fact;
import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MathUtilParametrizedTest {

    private double n;
    private double expected;

    public MathUtilParametrizedTest(double n, double expected) {
        this.n = n;
        this.expected = expected;
    }

    @Parameters(name = "{index}: fact({0})={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 1}, {1, 1}, {2, 2}, {3, 6}, {4, 24}, {5, 120}, {6, 720}, {7, 5_040}, {8, 40_320}, {9, 362_880},
                {10, 3_628_800}, {11, 39_916_800}, {12, 479_001_600}, {13, 6_227_020_800d}, {14, 87_178_291_200d},
                {15, 1_307_674_368_000d}, {16, 20_922_789_888_000d}, {17, 355_687_428_096_000d}, {18, 6_402_373_705_728_000d},
                {19, 121_645_100_408_832_000d}, {20, 2_432_902_008_176_640_000d}
        });
    }

    @Test(timeout = 5_000)
    public void checkCalculateFactorial() {
        assertEquals(expected, fact(n), 0.0001d);
    }
}
