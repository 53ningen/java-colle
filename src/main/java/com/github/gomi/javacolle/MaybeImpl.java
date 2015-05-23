package com.github.gomi.javacolle;

class MaybeImpl<T> implements Maybe<T> {

    private MaybeImpl() {
    }

    private final static Maybe<Object> nothing = new Maybe<Object>() {

        @Override
        public boolean equals(Object obj) {
            return this == obj;
        }

        @Override
        public String toString() {
            return "Nothing{}";
        }

    };

    static class Just<T> implements Maybe<T> {

        final T a;

        private Just(final T a) {
            this.a = a;
        }

        static <A> Maybe<A> of(final A a) {
            if (a == null) throw new IllegalArgumentException();
            return new Just<>(a);
        }

        @Override
        public T get() {
            return a;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof Just)) return false;
            final Just just = (Just) o;
            if (!a.equals(just.a)) return false;
            return true;
        }

        @Override
        public int hashCode() {
            return a.hashCode();
        }
    }

    @SuppressWarnings("unchecked")
    static <A> Maybe<A> nothing() {
        return (Maybe) nothing;
    }

    static <A> Maybe<A> just(final A a) {
        return Just.of(a);
    }

}
