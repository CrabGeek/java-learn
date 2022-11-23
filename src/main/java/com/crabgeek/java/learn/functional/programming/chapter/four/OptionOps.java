package com.crabgeek.java.learn.functional.programming.chapter.four;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class OptionOps {

    @Test
    public void createOption() {
        Optional<String> a = Optional.of("a");
        Assert.assertEquals("a", a.get());
    }

    @Test
    public void optionEmpty() {
        Optional<Object> empty = Optional.empty();
        Optional<Object> o = Optional.ofNullable(null);

        Assert.assertFalse(empty.isPresent());
        Assert.assertFalse(o.isPresent());
    }

    public void orElseAndorElseGet() {
        Optional<Object> empty = Optional.empty();
        Assert.assertEquals("b", empty.orElse("b"));
        Assert.assertEquals("c", empty.orElseGet(() -> "c"));
    }
}
