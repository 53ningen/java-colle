package com.github.gomi.javacolle;

import com.github.gomi.javacolle.data.tuple.Tuple0;

public class Stack<T> {

    private final ImmutableList<T> stack;

    private Stack(final ImmutableList<T> stack) {
        this.stack = stack;
    }

    public static <A> Stack<A> empty() {
        return new Stack<>(ImmutableList.of());
    }

    static <A> Stack<A> of(final ImmutableList<A> s) {
        return new Stack<>(s);
    }

    @SafeVarargs
    static <A> Stack<A> of(final A... values) {
        return of(ImmutableList.of(values));
    }

    public static <A> State<Stack<A>, A> pop() {
        return State.of(s -> Tuple.of(s.stack.head(), Stack.of(s.stack.tail())));
    }

    public static <A> State<Stack<A>, A> peek() {
        return State.of(s -> Tuple.of(s.stack.head(), s));
    }

    public static <A> State<Stack<A>, Tuple0> push(final A a) {
        return State.of(s -> Tuple.of(Tuple0.unit, Stack.of(ImmutableList.prepend(a, s.stack))));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stack)) return false;

        Stack stack1 = (Stack) o;

        if (!stack.equals(stack1.stack)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return stack.hashCode();
    }

    @Override
    public String toString() {
        return "Stack{" +
                "stack=" + stack +
                '}';
    }

}
