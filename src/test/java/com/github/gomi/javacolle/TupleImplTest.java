package com.github.gomi.javacolle;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TupleImplTest {

    @Test
    public void testFst() throws Exception {
        assertThat(TupleImpl.of(0, 1).fst(), is(0));
        assertThat(TupleImpl.of(3.14, "string").fst(), is(3.14));
    }

    @Test
    public void testSnd() throws Exception {
        assertThat(TupleImpl.of(0, 1).snd(), is(1));
        assertThat(TupleImpl.of(3.14, "string").snd(), is("string"));
    }

}
