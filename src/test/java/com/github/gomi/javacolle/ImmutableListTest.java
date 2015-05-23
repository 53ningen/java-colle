package com.github.gomi.javacolle;

import org.junit.Test;

import static com.github.gomi.javacolle.ImmutableList.length;
import static com.github.gomi.javacolle.ImmutableList.of;
import static com.github.gomi.javacolle.ImmutableList.prepend;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ImmutableListTest {

    @Test
    public void testLength() {
        assertThat(length(of()), is(0));
        assertThat(length(of(1)), is(1));
        assertThat(length(of(1, 2)), is(2));
        assertThat(length(of(1, 2, 3)), is(3));
        assertThat(length(of(1, 2, 3, 4)), is(4));
        assertThat(length(of(1, 2, 3, 4, 5)), is(5));
    }

    @Test
    public void testPrepend() {
        assertThat(prepend(1d, of()), is(of(1d)));
        assertThat(prepend(1d, of(2d)), is(of(1d, 2d)));
        assertThat(prepend(1d, of(2d, 3d)), is(of(1d, 2d, 3d)));
    }

}
