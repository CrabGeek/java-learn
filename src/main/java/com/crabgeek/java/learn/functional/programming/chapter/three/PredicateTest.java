package com.crabgeek.java.learn.functional.programming.chapter.three;

import com.crabgeek.java.learn.functional.programming.domain.Artist;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class PredicateTest {

    private List<Artist> artists;

    @Before
    public void init() {
        Artist artist = Artist.builder()
                .name("Haha")
                .members(Arrays.asList("A", "B", "C"))
                .origin("London")
                .build();

        Artist artist1 = Artist.builder()
                .name("LoLo")
                .members(Arrays.asList("A", "B", "C"))
                .origin("Beijing")
                .build();

        Artist artist2 = Artist.builder()
                .name("Domi")
                .members(Arrays.asList("A", "B", "C"))
                .origin("Shanghai")
                .build();

        artists = Arrays.asList(artist, artist1, artist2);
    }

    @Test
    public void test_predicate() {
        Predicate<Integer> atLeastFive = x -> x > 5;
        System.out.println(atLeastFive.test(6));
    }

    @Test
    public void test_BinaryOperator() {
        BinaryOperator<Long> addLongs = (x, y) -> x + y;

        System.out.println(addLongs.apply(100L, 200L));
    }

    @Test
    public void test_stream_lazy() {
        artists.stream()
                .filter(artist -> artist.isFrom("London"));
    }

    @Test
    public void test_stream_print() {
        long london = artists.stream()
                .filter(artist -> {
                    System.out.println(artist.getName());
                    return artist.isFrom("London");
                }).count();
        System.out.println(london);
    }
}
