package com.github.gomi.javacolle;

import java.util.function.Function;

public interface Reader<R, A> {

    Function<R, A> runReader();

    static <R, T> Reader<R, T> reader(final Function<R, T> runReader) {
        return new Reader<R, T>() {
            @Override
            public Function<R, T> runReader() {
                return runReader;
            }
        };
    }

    default <B> Reader<R, B> flatMap(final Function<A, Reader<R, B>> f) {
        return reader(r -> f.apply(runReader().apply(r)).runReader().apply(r));
    }

}
