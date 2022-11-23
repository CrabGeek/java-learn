package com.crabgeek.java.learn.functional.programming.chapter.three.practice;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

@Slf4j
public class Practice {

    @Test
    public void test_addUp() {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5});
        Integer result = addUp(stream);
        log.info("{}", result);
    }

    private Integer addUp(Stream<Integer> integerStream) {
        return integerStream.reduce(0, Integer::sum);
    }
}
