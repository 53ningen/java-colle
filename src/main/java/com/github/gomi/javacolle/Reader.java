package com.github.gomi.javacolle;

import java.util.function.Function;

public interface Reader<R, T> {

    Function<R, T> runReader();

    static <R, T> Reader<R, T> of(final Function<R, T> runReader) {
        return new Reader<R, T>() {
            @Override
            public Function<R, T> runReader() {
                return runReader;
            }
        };
    }

    default <A> Reader<R, A> flatMap(final Reader<R, A> f) {
        return of(r -> f.runReader().apply(r));
    }

}
