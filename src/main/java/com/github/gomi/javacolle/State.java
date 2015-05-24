package com.github.gomi.javacolle;

import java.util.function.Function;

public interface State<S, A> {

    Function<S, Tuple<A, S>> runState();

    static <S, A> State<S, A> of(final Function<S, Tuple<A, S>> runState) {
        return new State<S, A>() {
            @Override
            public Function<S, Tuple<A, S>> runState() {
                return runState;
            }
        };
    }

    default <B> State<S, B> flatMap(final State<S, B> f) {
        return State.of(t -> f.runState().apply(this.runState().apply(t).snd()));
    }

}
