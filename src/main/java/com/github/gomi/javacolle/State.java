package com.github.gomi.javacolle;

import java.util.function.Function;

public interface State<S, A> {

    Function<S, Tuple<A, S>> runState();

    static <S, A> State<S, A> state(final Function<S, Tuple<A, S>> runState) {
        return new State<S, A>() {
            @Override
            public Function<S, Tuple<A, S>> runState() {
                return runState;
            }
        };
    }

    default <B> State<S, B> flatMap(final State<S, B> f) {
        return State.state(t -> f.runState().apply(this.runState().apply(t).snd()));
    }

    default <B> State<S, B> flatMap(final Function<S, Tuple<B, S>> f) {
        return flatMap(state(f));
    }

}
