package com.github.gomi.javacolle;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReaderTest {

    class Environment {

        final String settingValue;

        Environment(final String settingValue) {
            this.settingValue = settingValue;
        }

    }

    Reader<Environment, String> getSettingValue() {
        return Reader.of(r -> r.settingValue);
    }

    @Test
    public void testReader() {
        final Environment env = new Environment("012345");
        assertThat(getSettingValue().runReader().apply(env), is("012345"));
    }

}
