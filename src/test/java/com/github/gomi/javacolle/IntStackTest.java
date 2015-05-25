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
        assertThat(push(12, stack), is(Tuple.tuple(Tuple0.unit, IntStack.of(12))));
        assertThat(push(100, push(12, stack).snd()), is(Tuple.tuple(Tuple0.unit, IntStack.of(100, 12))));
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
        assertThat(sPop.flatMap(a -> sPop).runState().apply(of(1, 2, 3)).snd(), is(of(3)));
        assertThat(sPop.flatMap(sPush::apply).runState().apply(of(1, 2, 3)).snd(), is(of(1, 2, 3)));
        assertThat(
                sPush.apply(1)
                        .flatMap(v1 -> sPush.apply(2)
                                .flatMap(v2 -> sPush.apply(3)))
                        .runState().apply(of()).snd(),
                is(of(3, 2, 1))
        );
    }

}
