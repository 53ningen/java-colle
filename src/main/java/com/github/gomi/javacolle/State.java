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

    static <S, A, B> Function<S, Tuple<B, S>> _flatMap(final Function<S, Tuple<A, S>> h, final Function<A, Function<S, Tuple<B, S>>> f) {
        return s -> {
            final Tuple<A, S> res = h.apply(s);
            final Function<S, Tuple<B, S>> sta = f.apply(res.fst());
            return sta.apply(res.snd());
        };
    }

    default <B> State<S, B> flatMap(final Function<A, State<S, B>> f) {
        return state(s -> {
            final Tuple<A, S> res = runState().apply(s);
            final State<S, B> sta = f.apply(res.fst());
            return sta.runState().apply(res.snd());
        });
    }

    default <B> Function<S, Tuple<B, S>> _flatMap(final Function<A, Function<S, Tuple<B, S>>> f) {
        return s -> {
            final Tuple<A, S> res = runState().apply(s);
            final Function<S, Tuple<B, S>> sta = f.apply(res.fst());
            return sta.apply(res.snd());
        };
    }

}
