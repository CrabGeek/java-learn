package com.crabgeek.java.learn.functional.programming.chapter.three;

import com.crabgeek.java.learn.functional.programming.domain.Track;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOps {

    @Test
    public void collect_ops() {
        List<String> collect = Stream.of("a", "b", "c")
                .collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList("a", "b", "c"), collect);
    }

    @Test
    public void map_ops() {
        List<String> collect = Stream.of("a", "b", "hello")
                .map(word -> word.toUpperCase())
                .collect(Collectors.toList());

        Assert.assertEquals(Arrays.asList("A", "B", "HELLO"), collect);
    }

    @Test
    public void filter_ops() {
        List<String> collect = Stream.of("a", "1abc", "abc1")
                .filter(value -> Character.isDigit(value.charAt(0)))
                .collect(Collectors.toList());

        Assert.assertEquals(Arrays.asList("1abc"), collect);
    }

    @Test
    public void flapMap_ops() {
        List<Integer> collect = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());

        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), collect);
    }

    @Test
    public void min_max_ops() {
        List<Track> tracks = Arrays.asList(new Track("Bakai", 524), new Track("Violets for your Furs", 378),
                new Track("Time Was", 451));

        Track shortestTrack = tracks.stream()
                .min(Comparator.comparing(track -> track.getLength()))
                .get();

        Assert.assertEquals(tracks.get(1), shortestTrack);
    }

    @Test
    public void reduce_ops() {
        int reduce = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);
        Assert.assertEquals(6, reduce);
    }

    @Test
    public void reduce_open_ops() {
        BinaryOperator<Integer> accumulator = (acc, element) -> acc + element;

        Integer apply = accumulator.apply(
                accumulator.apply(
                        accumulator.apply(0, 1),
                        2),
                3);
        System.out.println(apply);
    }
}
