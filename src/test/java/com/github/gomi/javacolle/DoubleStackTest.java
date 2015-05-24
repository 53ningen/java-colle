package com.github.gomi.javacolle;

import org.junit.Test;

import static com.github.gomi.javacolle.DoubleStack.pop;

public class DoubleStackTest {

    @Test
    public void testPush() {
    }

    @Test
    public void testPop() {
        pop.runState().apply(DoubleStack.of(0d, 1d)).fst();
    }

    @Test
    public void testPeek() {
        pop.runState().apply(DoubleStack.of(0d, 1d)).fst();
    }

}
