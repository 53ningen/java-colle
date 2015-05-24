package com.github.gomi.javacolle;

import org.junit.Test;

import static com.github.gomi.javacolle.Stack.empty;
import static com.github.gomi.javacolle.Stack.push;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StackTest {

    @Test
    public void testManipulation() {
        final Stack<Integer> pushedOneTwoThree = push(1).flatMap(push(2)).flatMap(push(3)).runState().apply(empty()).snd();
        assertThat(pushedOneTwoThree, is(Stack.of(3, 2, 1)));
        final Stack<Integer> pushedOneTwoThreeAndPopped = Stack.<Integer>pop().runState().apply(pushedOneTwoThree).snd();
        assertThat(pushedOneTwoThreeAndPopped, is(Stack.of(2, 1)));
        final Stack<Integer> pushedOneTwoThreeAndPeeked = Stack.<Integer>peek().runState().apply(pushedOneTwoThree).snd();
        assertThat(pushedOneTwoThreeAndPeeked, is(Stack.of(3, 2, 1)));
    }

}
