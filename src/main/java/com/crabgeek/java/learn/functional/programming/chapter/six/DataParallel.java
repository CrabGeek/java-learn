package com.crabgeek.java.learn.functional.programming.chapter.six;

import com.crabgeek.java.learn.functional.programming.domain.Album;
import com.crabgeek.java.learn.functional.programming.domain.Artist;
import com.crabgeek.java.learn.functional.programming.domain.Track;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DataParallel {

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
    public void parallelGetSum() {
        int sum = albums.parallelStream()
                .flatMap(album -> album.getTracks().stream())
                .mapToInt(Track::getLength)
                .sum();
        System.out.println(sum);
    }
}
