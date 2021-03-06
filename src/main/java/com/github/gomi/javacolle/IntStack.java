package com.github.gomi.javacolle;

import com.github.gomi.javacolle.data.tuple.Tuple0;

import javax.annotation.Nonnull;
import java.util.function.Function;

import static com.github.gomi.javacolle.ImmutableList.prepend;
import static com.github.gomi.javacolle.State.state;
import static com.github.gomi.javacolle.Tuple.tuple;

public class IntStack {

    private final ImmutableList<Integer> stack;

    public Tuple<Integer, IntStack> pop() {
        return pop(this);
    }

    public Tuple<Tuple0, IntStack> push(final Integer a) {
        return push(a, this);
    }

    public Tuple<Integer, IntStack> peek() {
        return peek(this);
    }

    private IntStack(@Nonnull final ImmutableList<Integer> stack) {
        this.stack = stack;
    }

    public static IntStack of(@Nonnull final ImmutableList<Integer> stack) {
        return new IntStack(stack);
    }

    public static IntStack of(@Nonnull final Integer... values) {
        return IntStack.of(ImmutableList.of(values));
    }

    public static Function<IntStack, Tuple<Integer, IntStack>> pop = s -> tuple(s.stack.head(), IntStack.of(s.stack.tail()));

    public static State<IntStack, Integer> sPop = state(s -> tuple(s.stack.head(), IntStack.of(s.stack.tail())));

    public static Function<IntStack, Tuple<Integer, IntStack>> peek = s -> tuple(s.stack.head(), s);

    public static State<IntStack, Integer> sPeek = state(s -> tuple(s.stack.head(), s));

    public static Function<Integer, Function<IntStack, Tuple<Tuple0, IntStack>>> push = a -> s -> tuple(Tuple0.unit, IntStack.of(prepend(a, s.stack)));

    public static Function<Integer, State<IntStack, Tuple0>> sPush = a -> state(s -> tuple(Tuple0.unit, IntStack.of(prepend(a, s.stack))));

    public static Tuple<Integer, IntStack> pop(@Nonnull final IntStack intStack) {
        return tuple(intStack.stack.head(), of(intStack.stack.tail()));
    }

    public static Tuple<Tuple0, IntStack> push(@Nonnull final Integer a, final IntStack intStack) {
        return tuple(Tuple0.unit, of(prepend(a, intStack.stack)));
    }

    public static Tuple<Integer, IntStack> peek(@Nonnull final IntStack intStack) {
        return tuple(intStack.stack.head(), intStack);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof IntStack)) return false;
        final IntStack intStack = (IntStack) o;
        if (!stack.equals(intStack.stack)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return stack.hashCode();
    }

    @Override
    public String toString() {
        return "IntStack{" +
                "stack=" + stack +
                '}';
    }

}
