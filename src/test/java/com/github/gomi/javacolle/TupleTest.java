package com.github.gomi.javacolle;

import org.junit.Test;

import java.util.function.Function;

import static com.github.gomi.javacolle.Tuple.curry;
import static com.github.gomi.javacolle.Tuple.swap;
import static com.github.gomi.javacolle.Tuple.uncurry;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TupleTest {

    @Test
    public void testCurry() throws Exception {
        final Function<Tuple<Double, String>, Double> function = Tuple::fst;
        assertThat(curry(function).apply(0d).apply("string"), is(0d));
    }

    @Test
    public void testUncurry() throws Exception {
        final Function<Double, Function<String, Double>> function = a -> b -> a;
        assertThat(uncurry(function).apply(Tuple.tuple(0d, "string")), is(0d));
    }

    @Test
    public void testSwap() throws Exception {
        assertThat(swap(Tuple.tuple(0d, "string")), is(Tuple.tuple("string", 0d)));
    }

}
