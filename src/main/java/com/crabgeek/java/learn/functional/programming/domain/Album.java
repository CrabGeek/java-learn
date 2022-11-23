package com.crabgeek.java.learn.functional.programming.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Album {
    private String name;
    private List<Track> tracks;
    private List<Artist> musicians;
}
