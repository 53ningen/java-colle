package com.github.gomi.javacolle;

class TupleImpl<A, B> implements Tuple<A, B> {

    private final A fst;
    private final B snd;

    private TupleImpl(final A fst, final B snd) {
        this.fst = fst;
        this.snd = snd;
    }

    static <A, B> Tuple<A, B> of(final A fst, final B snd) {
        return new TupleImpl<>(fst, snd);
    }

    @Override
    public A fst() {
        return fst;
    }

    @Override
    public B snd() {
        return snd;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof TupleImpl)) return false;
        final TupleImpl tuple = (TupleImpl) o;
        if (fst != null ? !fst.equals(tuple.fst) : tuple.fst != null) return false;
        if (snd != null ? !snd.equals(tuple.snd) : tuple.snd != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = fst != null ? fst.hashCode() : 0;
        result = 31 * result + (snd != null ? snd.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TupleImpl{" +
                "fst=" + fst +
                ", snd=" + snd +
                '}';
    }

}
