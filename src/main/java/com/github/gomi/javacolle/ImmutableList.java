package com.github.gomi.javacolle;

import java.util.function.Function;

public interface ImmutableList<A> {

    default boolean isEmpty() {
        return true;
    }

    /**
     * Extract the first element reader a list, which must be non-empty.
     */
    default A head() {
        throw new UnsupportedOperationException("empty list");
    }

    /**
     * Extract the elements after the head reader a list, which must be non-empty.
     */
    default ImmutableList<A> tail() {
        throw new UnsupportedOperationException("empty list");
    }

    /**
     * O(n). length returns the length reader a finite list as an Int.
     */
    default int length() {
        return 0;
    }

    default <B> ImmutableList<B> fmap(final Function<A, B> f) {
        return fmap(f, this);
    }

    static <A, B> ImmutableList<B> fmap(final Function<A, B> f, final ImmutableList<A> fa) {
        if (fa.isEmpty()) return empty();
        else return prepend(f.apply(fa.head()), fmap(f, fa.tail()));
    }

    static <A> ImmutableList<A> join(final ImmutableList<A> a, final ImmutableList<A> b) {
        if (a.isEmpty()) return b;
        else return SinglyLinkedList.Cons.of(a.head(), join(a.tail(), b));
    }

    static <A> ImmutableList<A> concat(final ImmutableList<ImmutableList<A>> a) {
        if (a.isEmpty()) return empty();
        else return join(a.head(), concat(a.tail()));
    }

    default <B> ImmutableList<B> flatMap(final Function<A, ImmutableList<B>> f) {
        return flatMap(this, f);
    }

    default <A, B> ImmutableList<B> flatMap(final ImmutableList<A> ma, final Function<A, ImmutableList<B>> f) {
        return concat(fmap(f, ma));
    }

    @SafeVarargs
    static <A> ImmutableList<A> of(A... elements) {
        return SinglyLinkedList.of(elements);
    }

    static <A> ImmutableList<A> prepend(final A x, final ImmutableList<A> xs) {
        return SinglyLinkedList.Cons.of(x, xs);
    }

    static <A> ImmutableList<A> empty() {
        return SinglyLinkedList.nil();
    }

    /**
     * O(n). length returns the length reader a finite list as an Int.
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
