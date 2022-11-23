package com.crabgeek.java.learn.functional.programming.chapter.three;


import com.crabgeek.java.learn.functional.programming.domain.Album;
import com.crabgeek.java.learn.functional.programming.domain.Artist;
import com.crabgeek.java.learn.functional.programming.domain.Track;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class RefactorOldCode {

    @Test
    public void test_findLongTracks() {
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

        List<Album> albums = Lists.newArrayList(album, album1, album2);

        Set<String> longTracks = findLongTracks(albums);
        log.info("{}", longTracks.toString());
    }

    private Set<String> findLongTracks(List<Album> albums) {
        return albums.stream()
                .flatMap(album -> album.getTracks().stream())
                .filter(track -> track.getLength() > 60)
                .map(Track::getName)
                .collect(Collectors.toSet());
    }
}
