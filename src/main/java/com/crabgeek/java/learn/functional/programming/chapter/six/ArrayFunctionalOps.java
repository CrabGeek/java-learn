package com.crabgeek.java.learn.functional.programming.chapter.six;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayFunctionalOps {

    @Test
    public void parallelInitialize() {
        double[] doubles = new double[10];
        Arrays.parallelSetAll(doubles, i -> i);
        Arrays.stream(doubles).forEach(System.out::println);
    }

    @Test
    public void simpleMovingAverage() {
        int windowSize = 3;
        double[] values = new double[]{0, 1, 2, 3, 4, 3.5};
        double[] sums = Arrays.copyOf(values, values.length);
        Arrays.parallelPrefix(sums, Double::sum);
        int start = 3 - 1;
        double[] doubles = IntStream.range(start, sums.length)
                .mapToDouble(i -> {
                    double prefix = i == start ? 0 : sums[i - windowSize];
                    return (sums[i] - prefix) / windowSize;
                }).toArray();
        Arrays.stream(doubles).forEach(System.out::println);
    }
}
