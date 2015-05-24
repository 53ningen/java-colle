package com.github.gomi.javacolle.typeclass;

import java.util.function.Function;

/**
 * The Functor class is used for types that can be mapped over. Instances of Functor should satisfy the following laws:
 * fmap id  ==  id
 * fmap (f . g)  ==  fmap f . fmap g
 * The instances of Functor for lists, Maybe and IO satisfy these laws.
 */
public interface Functor<T> {

    /**
     * Replace all locations in the input with the same value. The default definition is fmap . const, but this may be overridden with a more efficient version.
     */
    default <B> Functor<B> fmap(final Function<T, B> f, final Functor<T> fa) {
        return liftM(f).apply(fa);
    }

    /**
     * Promote a function to a monad.
     */
    default <B> Function<Functor<T>, Functor<B>> liftM(final Function<T, B> f) {
        return a -> fmap(f, a);
    }

}
