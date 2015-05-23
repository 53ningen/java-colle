package com.github.gomi.javacolle;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SinglyLinkedListTest {

    @Test
    public void testCreateEmptyList() {
        assertThat(SinglyLinkedList.of(), is(SinglyLinkedList.Nil.get()));
    }

    @Test
    public void testCreateOneElementList() {
        final ImmutableList<String> l = SinglyLinkedList.of("single elem");
        assertThat(l.head(), is("single elem"));
        assertThat(l.tail(), is(SinglyLinkedList.Nil.get()));
    }

    @Test
    public void testCreateList() {
        final ImmutableList<Integer> l = SinglyLinkedList.of(1, 2, 3, 4, 5);
        assertThat(l.head(), is(1));
        assertThat(l.tail().head(), is(2));
        assertThat(l.tail().tail().head(), is(3));
        assertThat(l.tail().tail().tail().head(), is(4));
        assertThat(l.tail().tail().tail().tail().head(), is(5));
        assertThat(l.tail().tail().tail().tail().tail(), is(SinglyLinkedList.Nil.get()));
    }

    @Test
    public void testLength() {
        assertThat(SinglyLinkedList.of().length(), is(0));
        assertThat(SinglyLinkedList.of(1).length(), is(1));
        assertThat(SinglyLinkedList.of(1, 2).length(), is(2));
        assertThat(SinglyLinkedList.of(1, 2, 3).length(), is(3));
        assertThat(SinglyLinkedList.of(1, 2, 3, 4).length(), is(4));
        assertThat(SinglyLinkedList.of(1, 2, 3, 4, 5).length(), is(5));
    }

}
