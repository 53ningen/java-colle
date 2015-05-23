package com.github.gomi.javacolle;

public interface ImmutableList<A> {

    default boolean isEmpty() {
        return true;
    }

    /**
     * Extract the first element of a list, which must be non-empty.
     */
    default A head() {
        throw new UnsupportedOperationException("empty list");
    }

    /**
     * Extract the elements after the head of a list, which must be non-empty.
     */
    default ImmutableList<A> tail() {
        throw new UnsupportedOperationException("empty list");
    }

    /**
     * O(n). length returns the length of a finite list as an Int.
     */
    default int length() {
        return 0;
    }

    @SafeVarargs
    static <A> ImmutableList<A> of(A... elements) {
        return SinglyLinkedList.of(elements);
    }

    static <A> ImmutableList<A> prepend(final A x, final ImmutableList<A> xs) {
        return SinglyLinkedList.Cons.of(x, xs);
    }

    /**
     * O(n). length returns the length of a finite list as an Int.
     */
    static <A> int length(final ImmutableList<A> l) {
        return l.length();
    }

    static <A> A get(final ImmutableList<A> l, final int n) {
        if (l == null || n < 0) throw new IllegalArgumentException();
        if (n > l.length() - 1) throw new IndexOutOfBoundsException();
        if (n == 0) return l.head();
        return get(l.tail(), n - 1);
    }

}
