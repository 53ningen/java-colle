package com.github.gomi.javacolle;

import java.util.Arrays;

public class SinglyLinkedList<A> implements ImmutableList<A> {

    private SinglyLinkedList() {
    }

    @SafeVarargs
    static <A> ImmutableList<A> of(final A... elems) {
        if (elems == null || elems.length == 0) return nil();
        if (elems.length == 1) return Cons.of(elems[0], nil());
        return Cons.of(elems[0], of(Arrays.copyOfRange(elems, 1, elems.length)));
    }

    private final static ImmutableList<Object> Nil = new ImmutableList<Object>() {

        @Override
        public boolean equals(final Object o) {
            return this == o;
        }

        @Override
        public String toString() {
            return "Nil{}";
        }

    };

    @SuppressWarnings("unchecked")
    static <B> ImmutableList<B> nil() {
        return (ImmutableList) Nil;
    }

    static class Cons<A> implements ImmutableList<A> {

        final A head;
        final ImmutableList<A> tail;

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public A head() {
            return head;
        }

        @Override
        public ImmutableList<A> tail() {
            return tail;
        }

        @Override
        public int length() {
            return length(this, 1);
        }

        int length(final ImmutableList<A> l, final int n) {
            if (l.tail().isEmpty()) return n;
            return length(l.tail(), n + 1);
        }

        private Cons(final A head, ImmutableList<A> tail) {
            this.head = head;
            this.tail = tail;
        }

        static <A> ImmutableList<A> of(final A head, final ImmutableList<A> tail) {
            if (head == null && (tail == null || tail.isEmpty())) return nil();
            if (head == null) return tail;
            return new Cons<>(head, tail);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof Cons)) return false;
            final Cons cons = (Cons) o;
            if (!head.equals(cons.head)) return false;
            if (!tail.equals(cons.tail)) return false;
            return true;
        }

        @Override
        public int hashCode() {
            int result = head.hashCode();
            result = 31 * result + tail.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Cons{" +
                    "head=" + head +
                    ", tail=" + tail +
                    '}';
        }
    }

}
