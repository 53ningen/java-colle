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

    static <S, A, B> State<S, B> flatMap(final State<S, A> h, final Function<A, State<S, B>> f) {
        return state(s -> {
            final Tuple<A, S> res = h.runState().apply(s);
            final State<S, B> sta = f.apply(res.fst());
            return sta.runState().apply(res.snd());
        });
    }

    default <B> State<S, B> flatMap(final Function<A, State<S, B>> f) {
        return state(s -> {
            final Tuple<A, S> res = runState().apply(s);
            final State<S, B> sta = f.apply(res.fst());
            return sta.runState().apply(res.snd());
        });
    }

}
