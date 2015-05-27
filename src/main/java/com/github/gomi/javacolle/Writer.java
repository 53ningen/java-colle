package com.github.gomi.javacolle;

import com.github.gomi.javacolle.typeclass.Monoid;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.Function;

/**
 * Writer Action
 */
public interface Writer<W, A> {

    Tuple<A, Monoid<W>> runWriter();

    static <W, A> Writer<W, A> writer(final Tuple<A, Monoid<W>> runWriter) {
        return new Writer<W, A>() {
            @Override
            public Tuple<A, Monoid<W>> runWriter() {
                return runWriter;
            }
        };
    }

    static <W, A, B> Writer<W, B> flatMap(final Writer<W, A> h, final Function<A, Writer<W, B>> f) {
        throw new NotImplementedException();
    }

}
