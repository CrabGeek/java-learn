package com.crabgeek.java.learn.functional.programming.chapter.five;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SortedStream {

    @Test
    public void generateSortedStream() {
        Set<Integer> numbers = new HashSet<>(Arrays.asList(4, 3, 2, 1));
        List<Integer> collect = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), collect);
    }
}
