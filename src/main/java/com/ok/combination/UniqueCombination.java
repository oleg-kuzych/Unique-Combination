package com.ok.combination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.ok.combination.MathUtil.fact;

public class UniqueCombination<T> {

    private final List<T> data;

    public UniqueCombination(List<T> n) {
        data = n;
    }

    private static TripleConsumer forLoop(int limit, TripleConsumer consumer) {
        return (indexes, cIdx, startFrom) -> {
            for (int i = startFrom; i < limit; i++) {
                indexes[cIdx] = i;
                consumer.accept(indexes, cIdx + 1, i + 1);
            }
        };
    }

    public double getNumberOfCombinations(int k) {
        if (k < data.size()) {
            return fact(data.size()) / (fact(k) * fact(data.size() - k));
        } else if (k == data.size()) {
            return 1d;
        }
        throw new IllegalArgumentException("K is greater than N!");
    }

    public List<List<T>> getCombinationsForAllKStartingFrom(int n) {
        if (n <= data.size()) {
            return IntStream.iterate(n, i -> i + 1)
                    .limit(data.size())
                    .mapToObj(this::getCombinations)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException("K is greater than N!");
    }

    public List<List<T>> getCombinations(int k) {
        List<List<T>> combinations = new ArrayList<>();

        if (k < data.size()) {
            TripleConsumer inner = (indexes, cIdx, startFrom) -> {
                List<T> combination = new ArrayList<>();
                for (int idx : indexes) {
                    combination.add(data.get(idx));
                }
                combinations.add(combination);
            };
            for (int i = 0; i < k; i++) {
                inner = forLoop(data.size(), inner);
            }
            // start with initial values
            inner.accept(new int[k], 0, 0);

        } else if (k == data.size()) {
            combinations.add(data);
        } else if (k == 1) {
            data.stream()
                    .map(Collections::singletonList)
                    .forEach(combinations::add);
        }

        return combinations;
    }

    @FunctionalInterface
    private interface TripleConsumer {
        void accept(int[] idxs, int currentIdx, int startFrom);
    }
}
