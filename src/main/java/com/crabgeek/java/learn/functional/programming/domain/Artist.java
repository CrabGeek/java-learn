package com.crabgeek.java.learn.functional.programming.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Artist {
    String name;
    List<String> members;
    String origin;
    boolean isSolo;

    public boolean isFrom(String location) {
        return location.equals(origin);
    }
}
