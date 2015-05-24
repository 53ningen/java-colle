package com.github.gomi.javacolle;


import com.github.gomi.javacolle.data.tuple.Tuple0;

import javax.annotation.Nonnull;
import java.util.function.Function;

public class DoubleStack {

    private final ImmutableList<Double> stack;

    private DoubleStack(@Nonnull final ImmutableList<Double> stack) {
        this.stack = stack;
    }

    public static DoubleStack of(@Nonnull final ImmutableList<Double> stack) {
        return new DoubleStack(stack);
    }

    public static DoubleStack of(@Nonnull final Double... values) {
        return of(ImmutableList.of(values));
    }

    public static State<DoubleStack, Double> pop = new State<DoubleStack, Double>() {
        @Override
        public Function<DoubleStack, Tuple<Double, DoubleStack>> runState() {
            return s -> Tuple.tuple(s.stack.head(), DoubleStack.of(s.stack.tail()));
        }
    };

    public static State<DoubleStack, Double> peek = new State<DoubleStack, Double>() {
        @Override
        public Function<DoubleStack, Tuple<Double, DoubleStack>> runState() {
            return s -> Tuple.tuple(s.stack.head(), s);
        }
    };

    public static Function<Double, State<DoubleStack, Tuple0>> push = a -> new State<DoubleStack, Tuple0>() {
        @Override
        public Function<DoubleStack, Tuple<Tuple0, DoubleStack>> runState() {
            return s -> Tuple.tuple(Tuple0.unit, s);
        }
    };

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DoubleStack)) return false;
        final DoubleStack that = (DoubleStack) o;
        if (!stack.equals(that.stack)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return stack.hashCode();
    }

    @Override
    public String toString() {
        return "DoubleStack{" +
                "stack=" + stack +
                '}';
    }

}
