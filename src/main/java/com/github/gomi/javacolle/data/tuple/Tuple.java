package com.github.gomi.javacolle.data.tuple;

import javax.annotation.Nonnull;

public class Tuple {

    public static Tuple0 unit() {
        return Tuple0.unit;
    }

    public static <A> Tuple1<A> of(@Nonnull final A a) {
        return Tuple1.of(a);
    }

}
