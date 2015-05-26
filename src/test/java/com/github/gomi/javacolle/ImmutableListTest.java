package com.github.gomi.javacolle;

import org.junit.Test;

import static com.github.gomi.javacolle.ImmutableList.*;
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

    @Test
    public void testGet() {
        assertThat(get(of(1, 2, 3, 4, 5), 0), is(1));
        assertThat(get(of(1, 2, 3, 4, 5), 1), is(2));
        assertThat(get(of(1, 2, 3, 4, 5), 2), is(3));
        assertThat(get(of(1, 2, 3, 4, 5), 3), is(4));
        assertThat(get(of(1, 2, 3, 4, 5), 4), is(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBoundsException() {
        get(of(1, 2, 3, 4, 5), 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNegativeIndexNumber() {
        get(of(1, 2, 3, 4, 5), -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFromNull() {
        get(null, 0);
    }

    @Test
    public void testFmap() {
        assertThat(of(1, 2, 3).fmap(x -> 2 * x), is(of(2, 4, 6)));
        assertThat(of(1, 2, 3).fmap(x -> "No." + 2 * x), is(of("No.2", "No.4", "No.6")));
    }

    @Test
    public void testFlatMap() {
        assertThat(of(1, 2, 3).flatMap(x -> of(2 * x)), is(of(2, 4, 6)));
        assertThat(of(1, 2, 3).flatMap(x -> of(x, -x)), is(of(1, -1, 2, -2, 3, -3)));
        assertThat(
                of(1, 2)
                        .flatMap(x -> of(x, -x)
                                .flatMap(y -> of(x + y, x - y))),
                is(of(2, 0, 0, 2, 4, 0, 0, 4))
        );
    }

}
