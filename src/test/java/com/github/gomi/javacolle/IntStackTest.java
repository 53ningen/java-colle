package com.github.gomi.javacolle;

import com.github.gomi.javacolle.data.tuple.Tuple0;
import org.junit.Test;

import static com.github.gomi.javacolle.IntStack.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IntStackTest {

    @Test
    public void testPop() throws Exception {
        final IntStack stack = push(100, push(12, IntStack.of()).snd()).snd();
        assertThat(pop(stack).fst(), is(100));
        assertThat(pop(stack).snd(), is(IntStack.of(12)));
    }

    @Test
    public void testPush() throws Exception {
        final IntStack stack = IntStack.of(ImmutableList.of());
        assertThat(push(12, stack), is(Tuple.of(Tuple0.unit, IntStack.of(12))));
        assertThat(push(100, push(12, stack).snd()), is(Tuple.of(Tuple0.unit, IntStack.of(100, 12))));
    }

    @Test
    public void testPeek() throws Exception {
        final IntStack stack = push(100, push(12, IntStack.of()).snd()).snd();
        assertThat(peek(stack).fst(), is(100));
        assertThat(peek(stack).snd(), is(IntStack.of(100, 12)));
    }

    @Test
    public void testEquals() throws Exception {
        assertThat(IntStack.of(), is(IntStack.of()));
        assertThat(IntStack.of(1), is(IntStack.of(1)));
        assertThat(IntStack.of(1, 2), is(IntStack.of(1, 2)));
    }

    @Test
    public void testFlatMap() {
        assertThat(flatMap(pop, push.apply(1)).apply(IntStack.of(0)).snd(), is(IntStack.of(1)));
        assertThat(flatMap(State.of(push.apply(1)), State.of(push.apply(2))).runState().apply(IntStack.of()).snd(), is(IntStack.of(2, 1)));
        assertThat(State.of(pop).flatMap(State.of(pop)).flatMap(State.of(pop)).runState().apply(IntStack.of(1, 2, 3, 4, 5)).snd(), is(IntStack.of(4, 5)));
    }

}
