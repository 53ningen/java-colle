package com.github.gomi.javacolle;

import java.util.function.Function;

public interface Maybe<T> {

    default T get() {
        throw new UnsupportedOperationException();
    }

    static <A> boolean isJust(final Maybe<A> maybeA) {
        return maybeA instanceof MaybeImpl.Just && maybeA.get() != null;
    }

    static <A> boolean isNothing(final Maybe<A> maybeA) {
        return !isJust(maybeA);
    }

    static <A> Maybe<A> just(final A a) {
        return MaybeImpl.just(a);
    }

    static <A> Maybe<A> nothing() {
        return MaybeImpl.nothing();
    }

    static <A, B> Maybe<B> fmap(final Function<A, B> f, final Maybe<A> a) {
        if (isNothing(a)) return nothing();
        try {
            return just(f.apply(a.get()));
        } catch (final Throwable t) {
            return nothing();
        }
    }

    static <A> Maybe<A> unit(final A a) {
        return just(a);
    }

    static <A, B> Maybe<B> flatMap(final Function<A, Maybe<B>> f, final Maybe<A> fa) {
        if (isNothing(fa)) return nothing();
        try {
            return f.apply(fa.get());
        } catch (final Throwable t) {
            return nothing();
        }
    }

}
