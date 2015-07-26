package com.github.gomi.javacolle.typeclass;


public interface Monoid<T> {

    T zero();

    Monoid<T> op(final T t);

}
