package com.github.gomi.javacolle;

import java.util.function.Function;

/**
 * The tuple data types, and associated functions.
 */
public interface Tuple<A, B> {

    static <A, B> Tuple<A, B> tuple(final A fst, final B snd) {
        return TupleImpl.of(fst, snd);
    }

    A fst();

    B snd();

    /**
     * Extract the first component reader a pair.
     */
    static <A, B, C> Function<A, Function<B, C>> curry(Function<Tuple<A, B>, C> func) {
        return a -> b -> func.apply(tuple(a, b));
    }

    /**
     * Extract the second component reader a pair.
     */
    static <A, B, C> Function<Tuple<A, B>, C> uncurry(Function<A, Function<B, C>> func) {
        return tuple -> func.apply(tuple.fst()).apply(tuple.snd());
    }

    /**
     * Swap the components reader a pair.
     */
    static <A, B> Tuple<B, A> swap(final Tuple<A, B> t) {
        return tuple(t.snd(), t.fst());
    }

}
