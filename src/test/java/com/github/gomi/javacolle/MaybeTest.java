package com.github.gomi.javacolle;

import org.junit.Test;

import static com.github.gomi.javacolle.Maybe.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaybeTest {

    @Test
    public void testGet() throws Exception {
        assertThat(just(10).get(), is(10));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetNothing() throws Exception {
        nothing().get();
    }

    @Test
    public void testIsJust() throws Exception {
        assertThat(isJust(just("string")), is(true));
        assertThat(isJust(nothing()), is(false));
    }

    @Test
    public void testIsNothing() throws Exception {
        assertThat(isNothing(just("string")), is(false));
        assertThat(isNothing(nothing()), is(true));
    }

    @Test
    public void testFmap() throws Exception {
        assertThat(fmap(a -> a + "123", just("abc")), is(just("abc123")));
        assertThat(fmap(a -> a + "123", nothing()), is(nothing()));
        assertThat(fmap(a -> new int[]{}[1], just("abc")), is(nothing()));
        assertThat(fmap(a -> new int[]{}[1], nothing()), is(nothing()));
    }

    @Test
    public void testFlatMap() throws Exception {
        assertThat(flatMap(a -> just(a + "123"), just("abc")), is(just("abc123")));
        assertThat(flatMap(a -> nothing(), just("abc")), is(nothing()));
        assertThat(flatMap(a -> just(a + "123"), nothing()), is(nothing()));
        assertThat(flatMap(a -> just(new int[]{}[1]), just("123")), is(nothing()));
        assertThat(flatMap(a -> just(new int[]{}[1]), nothing()), is(nothing()));
    }

    @Test
    public void testEquals() throws Exception {
        assertThat(just("abc"), is(just("abc")));
        assertThat(just(123), is(just(123)));
        assertThat(nothing(), is(nothing()));
    }
}