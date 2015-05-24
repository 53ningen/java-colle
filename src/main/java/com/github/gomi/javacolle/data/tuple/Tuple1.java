package com.github.gomi.javacolle.data.tuple;

import javax.annotation.Nonnull;

public class Tuple1<A> {

    private final A a;

    private Tuple1(@Nonnull final A a) {
        this.a = a;
    }

    public static <A> Tuple1<A> of(@Nonnull final A a) {
        return new Tuple1<>(a);
    }

    public static <A> A get(final Tuple1<A> tuple1) {
        return tuple1.a;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple1)) return false;
        final Tuple1 tuple1 = (Tuple1) o;
        if (!a.equals(tuple1.a)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return a.hashCode();
    }

    @Override
    public String toString() {
        return "(" + a + ")";
    }

}
