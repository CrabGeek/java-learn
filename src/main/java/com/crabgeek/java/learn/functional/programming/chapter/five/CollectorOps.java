package com.crabgeek.java.learn.functional.programming.chapter.five;

import com.crabgeek.java.learn.functional.programming.domain.Album;
import com.crabgeek.java.learn.functional.programming.domain.Artist;
import com.crabgeek.java.learn.functional.programming.domain.Track;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorOps {
    private List<Artist> artists;
    private List<Album> albums;

    @Before
    public void init() {
        Artist artist = Artist.builder()
                .name("Haha")
                .members(Arrays.asList("A", "B", "C"))
                .origin("London")
                .isSolo(true)
                .build();

        Artist artist1 = Artist.builder()
                .name("LoLo")
                .members(Arrays.asList("A", "B", "C"))
                .origin("Beijing")
                .isSolo(true)
                .build();

        Artist artist2 = Artist.builder()
                .name("Domi")
                .members(Arrays.asList("A", "B", "C"))
                .origin("Shanghai")
                .isSolo(false)
                .build();

        artists = Arrays.asList(artist, artist1, artist2);

        Track baKai = new Track("Bakai", 524);
        Track violetsForYourFurs = new Track("Violets for your Furs", 378);
        Track timeWas = new Track("Time Was", 451);

        List<Artist> artists = Lists.newArrayList(artist, artist1, artist2);
        List<Track> tracks = Lists.newArrayList(baKai, violetsForYourFurs, timeWas);

        Album album = Album.builder()
                .tracks(tracks)
                .musicians(artists)
                .build();

        Album album1 = Album.builder()
                .tracks(tracks)
                .musicians(artists)
                .build();

        Album album2 = Album.builder()
                .tracks(tracks)
                .musicians(artists)
                .build();

        this.albums = Lists.newArrayList(album, album1, album2);
    }

    @Test
    public void getBiggestGroup() {
        Stream<Artist> stream = artists.stream();
        Function<Artist, Integer> getCount = artist -> artist.getMembers().size();
        Optional<Artist> collect = stream.collect(Collectors.maxBy(Comparator.comparing(getCount)));
        System.out.println(collect.orElse(null));
    }

    @Test
    public void partitioningByOps() {
        Map<Boolean, List<Artist>> collect = artists.stream()
                .collect(Collectors.partitioningBy(Artist::isSolo));
        System.out.println(collect);
    }

    @Test
    public void groupByOps() {
        Map<String, List<Artist>> collect = artists.stream()
                .collect(Collectors.groupingBy(Artist::getOrigin));
        System.out.println(collect);
    }

    @Test
    public void stringJoinOps() {
        String collect = artists.stream()
                .map(Artist::getName)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(collect);
    }

    @Test
    public void combineOps() {
        Map<List<Artist>, Long> collect = albums.stream()
                .collect(Collectors.groupingBy(Album::getMusicians, Collectors.counting()));
        System.out.println(collect);
    }

    @Test
    public void combineOps2() {
        Map<List<Artist>, List<String>> collect = albums.stream()
                .collect(Collectors.groupingBy(Album::getMusicians,
                        Collectors.mapping(Album::getName, Collectors.toList())));
        System.out.println(collect);
    }

}
